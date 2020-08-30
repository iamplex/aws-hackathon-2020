import os

import tensorflow as tf
import matplotlib.pyplot as plt
from sklearn.utils import shuffle
from keras.backend.tensorflow_backend import set_session
import pandas as pd

folder = "data/"
IMG_SIZE = 256

data = []
label = []

os.environ["CUDA_VISIBLE_DEVICES"] = "0"
gpus = tf.config.experimental.list_physical_devices(device_type='GPU')
tf.config.experimental.set_virtual_device_configuration(
    gpus[0],
    [tf.config.experimental.VirtualDeviceConfiguration(memory_limit=3072)]
)

# 图片预处理函数
def load_preprosess_image(image_path, image_label):
    # 读取图片
    img_raw = tf.io.read_file(image_path)
    # 解码图片
    img_tensor = tf.image.decode_jpeg(img_raw, channels=3)
    # 统一图片大小
    img_tensor = tf.image.resize_with_pad(img_tensor, IMG_SIZE, IMG_SIZE)
    # 转换数据类型
    img_tensor = tf.cast(img_tensor, tf.float32)
    # 归一化
    img = img_tensor / 255

    return img, image_label


df = pd.read_csv(folder + 'cat_13_labels.txt', sep='\s+')
df = shuffle(df)
df['path'] = folder + df['path']
print(df.head())
path_ds = tf.data.Dataset.from_tensor_slices((df['path'], df['label']))
dataset = path_ds.map(load_preprosess_image)
print(dataset)

#划分训练集和测试集
#测试个数取整
image_count = len(df)
test_count = int(image_count*0.2)
#训练个数
train_count = image_count - test_count
#训练数据集

print("train_count", train_count)
print("test_count", test_count)

train_dataset = dataset.skip(test_count)
#测试数据集
test_dataset = dataset.take(test_count)
#电脑性能好的话batch值可以大点
batch_size = 64
#训练数据集重复,乱序,规定batch值
train_dataset = train_dataset.repeat().shuffle(buffer_size=train_count).batch(batch_size)
#测试数据集不用过多处理
test_dataset = test_dataset.batch(batch_size)


#建立模型
#引用预训练网络(多次测试,发现自己写的模型准确率不是很高,用现成的不仅不用自己写那么多神经层,而且也不用自己一次次优化,真香)
IMG_SHAPE = (IMG_SIZE, IMG_SIZE, 3)
covn_base = tf.keras.applications.VGG16(input_shape=IMG_SHAPE, weights='imagenet',include_top=False)
model = tf.keras.Sequential()
model.add(covn_base)
#全局平均值化 降维
model.add(tf.keras.layers.GlobalAveragePooling2D())
model.add(tf.keras.layers.Dense(512, activation='relu'))
model.add(tf.keras.layers.Dropout(0.3))
model.add(tf.keras.layers.Dense(13, activation='softmax'))
model.summary()
#训练模型
#预训练网络权重不被训练
covn_base.trainable = False
#没自定义训练,均默认
#配置优化器,损失函数,显示准确率
opt = tf.keras.optimizers.Adam(lr=0.001, beta_1=0.9, beta_2=0.999, epsilon=1e-08, decay=0.001, amsgrad=False)
model.compile(optimizer=opt, loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True), metrics=['acc'])
#步长
steps_per_epoch = train_count//batch_size
validation_steps = test_count//batch_size

checkpointer = tf.keras.callbacks.ModelCheckpoint('model', monitor='val_acc', verbose=0,
 								save_best_only=True,
 								save_weights_only=False, mode='auto',
 								period=1)

#记录数据
history = model.fit(train_dataset, epochs=150, steps_per_epoch=steps_per_epoch,validation_data=test_dataset,validation_steps=validation_steps, callbacks=[checkpointer])


#调用参数,绘制图型
plt.subplot(211)
plt.plot(history.epoch,history.history['acc'])
plt.plot(history.epoch,history.history['val_acc'])
plt.subplot(212)
plt.plot(history.epoch,history.history.get('loss'))
plt.plot(history.epoch,history.history.get('val_loss'))
plt.show()