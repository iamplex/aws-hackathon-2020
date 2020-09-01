# import matplotlib.pyplot as plt
# import matplotlib.image as mpimg
# import numpy as np
# cat0 = mpimg.imread('data/cat_12_train/cQ3AJSYuRM9V0T2WwLfqy4Etbs1NeBFx.jpg')
# cat1 = mpimg.imread('data/cat_12_train/spNU7J8uk6BXiAyQErHegYMzjOaFR2qV.jpg')
# cat2 = mpimg.imread('data/cat_12_train/jbIdxGyNpoql3XQZrfREMiAzh7B46WOa.jpg')
# cat3 = mpimg.imread('data/cat_12_train/cCeBo4EJ9H1hbXsIS5G6Kxdzg27nwqfy.jpg')
# cat4 = mpimg.imread('data/cat_12_train/yxNcRSz4TI7FpwCVJBuea6MmGitZYUkK.jpg')
# cat5 = mpimg.imread('data/cat_12_train/NZw3P0Wfz4JDsSECG8y7HXihl2Oon6rA.jpg')
# cat6 = mpimg.imread('data/cat_12_train/K5wdv0zEnx3cti4OagyPphCVJUIXYuSZ.jpg')
# cat7 = mpimg.imread('data/cat_12_train/BOmo5yiKzMGV8qvleRIdLQC4bZcPxwWD.jpg')
# cat8 = mpimg.imread('data/cat_12_train/COJUByb07wYXqcTMovWFnAgpNZk1SxrI.jpg')
# cat9 = mpimg.imread('data/cat_12_train/dHJn0vb8XoSTM4DPG965fQ1swczARBel.jpg')
# cat10 = mpimg.imread('data/cat_12_train/sv3RcZgEInHWtBoVKr9Q46PMUmA8Jy2h.jpg')
# cat11 = mpimg.imread('data/cat_12_train/mrgAsyPJdDvwp1EYnUG3Hj92ehMTKNxt.jpg')
#
# # plt.imshow(cat0)
# # plt.imshow(cat1)
# # plt.imshow(cat2)
# # plt.imshow(cat3)
# plt.imshow(cat11)
# plt.show()

# import os
# path = "D:\ChromeDownloads\white"
# filelist = os.listdir(path) #该文件夹下所有的文件（包括文件夹）
# count=0
# for file in filelist:
#     print(file)
# for file in filelist:   #遍历所有文件
#     Olddir=os.path.join(path, file)   #原来的文件路径
#     if os.path.isdir(Olddir):   #如果是文件夹则跳过
#         continue
#     filename=os.path.splitext(file)[0]   #文件名
#     filetype=os.path.splitext(file)[1]   #文件扩展名
#     Newdir=os.path.join(path, "White"+str(count).zfill(6)+filetype)  #用字符串函数zfill 以0补全所需位数
#     os.rename(Olddir, Newdir)#重命名
#     count+=1
# import os
# import pandas as pd
# path = "data/cat_13_dataset"
# prefix = "cat_13_dataset/"
#
# labels = pd.DataFrame(columns = ['path', 'label'])
# filelist = os.listdir(path) #该文件夹下所有的文件（包括文件夹）
# count=0
# for file in filelist:
#     print(file)
#     if file.startswith("AS"):
#         a = {'path': prefix+file, 'label': 0}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('Black'):
#         a = {'path': prefix+file, 'label': 1}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('BS'):
#         a = {'path': prefix+file, 'label': 2}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('BW'):
#         a = {'path': prefix+file, 'label': 3}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('Fold'):
#         a = {'path': prefix+file, 'label': 4}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('GF'):
#         a = {'path': prefix+file, 'label': 5}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('Orange'):
#         a = {'path': prefix+file, 'label': 6}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('Ragdoll'):
#         a = {'path': prefix+file, 'label': 7}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('Siamese'):
#         a = {'path': prefix+file, 'label': 8}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('Sphinx'):
#         a = {'path': prefix+file, 'label': 9}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('Tabby'):
#         a = {'path': prefix+file, 'label': 10}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('TC'):
#         a = {'path': prefix+file, 'label': 11}
#         labels = labels.append(a, ignore_index=True)
#     elif file.startswith('White'):
#         a = {'path': prefix+file, 'label': 12}
#         labels = labels.append(a, ignore_index=True)
#     else:
#         print("unknown prefix", file)
#         exit(1)
#
# labels.to_csv('data/cat_13_labels.txt', index=False, sep=' ')
import tensorflow as tf
import numpy as np

model = tf.keras.models.load_model('model')
# IMG_SHAPE = (None, 256, 256, 3)
# model.build(IMG_SHAPE)
# 读取图片
img_raw = tf.io.read_file('data/cat_13_dataset/AS000000.jpg')
# 解码图片
img_tensor = tf.image.decode_jpeg(img_raw, channels=3)
# 统一图片大小
img_tensor = tf.image.resize_with_pad(img_tensor, 256, 256)
# 转换数据类型
img_tensor = tf.cast(img_tensor, tf.float32)
# 归一化
img = img_tensor / 255
X = np.array([np.asarray(img)])
print(X.shape)
print(model.predict_classes(X))
