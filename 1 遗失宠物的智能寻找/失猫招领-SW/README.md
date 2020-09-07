<img src="https://fzhcats.s3-ap-northeast-1.amazonaws.com/documents/cat.png" width="100">

----------

# 🐱失猫招领-[主页](http://lostandfound-env.eba-ftezekhq.ap-northeast-1.elasticbeanstalk.com/)
基于宠物猫分类智能识别的遗失宠物猫信息发布共享平台  

家养宠物猫意外走失心急如焚？发现疑似迷路的宠物猫却不知如何找到它的主人？不要着急，将猫的照片发布到[**失猫招领**](http://lostandfound-env.eba-ftezekhq.ap-northeast-1.elasticbeanstalk.com/)，我们将利用深度学习模型智能识别猫的品种、颜色等外貌特征，并综合考虑您的所在地区，猫的年龄，发布时间等因素，向您推荐最相似的宠物猫信息，帮您尽快找到您丢失的宠物猫或走失宠物猫的主人。


----------


### 功能特性
1. 邮箱注册登录
2. 上传猫的图片并填写基本信息，发布丢失猫或发现猫的启事
3. 根据上传的猫照片识别出猫的分类，并综合所得信息推荐所有相似的猫的启事
4. 用户也可以自己根据发布位置，猫的分类，启事分类（Lost还是Found）以及发布时间段等条件手动筛选平台上的所有启事
5. 用户可在个人主页查看并编辑自己已发布的所有启事，若已找到丢失的猫或猫的主人或放弃寻找，可将自己发布的启事状态设置为“不活跃”或删除启事

----------
### 使用的AWS服务
1. AWS EC2：作为存放web服务的容器
2. AWS Elastic Beanstalk：创建tomcat容器和Java环境，并一键部署web应用
3. AWS RDS：创建并使用MySQL数据库
4. AWS SegeMaker：深度学习模型训练和保存
5. AWS S3：对象存储，存储训练数据集和模型，Elastic Beanstalk需要使用的配置文件和部署的war包，以及作为图床存储用户上传的图片
6. AWS IAM：资源访问权限管理


----------
### 架构设计


<img src="https://fzhcats.s3-ap-northeast-1.amazonaws.com/documents/architecture.png" >


### 1. 数据集获取和模型训练


**数据集**  


我们利用必应图片搜集了中国最常见的宠物猫图片，分为13类：  
美国短毛猫，黑猫，英国短毛猫，奶牛猫（黑白猫），折耳猫，加菲猫，橘猫，布偶猫，暹罗猫，斯芬克斯猫，狸花猫，三花猫（三色猫），白猫  
其中按颜色区分的分类（如白猫，黑猫，三花，橘猫等）主要是针对毛色繁多的非纯种猫和中华田园猫，而纯种猫的特征相对明显且毛色单一，因此不做颜色区分。  
经过人工筛选后，每种猫有200张图片，因此最终数据集大小是2600张图片。


**训练模型**


在AWS SegeMaker上创建Jupyter实例用于训练模型，代码见[/segemaker_jupyter](https://github.com/zerofang/aws-hackathon-2020/blob/master/1%20%E9%81%97%E5%A4%B1%E5%AE%A0%E7%89%A9%E7%9A%84%E6%99%BA%E8%83%BD%E5%AF%BB%E6%89%BE/%E5%A4%B1%E7%8C%AB%E6%8B%9B%E9%A2%86-SW/segemaker_jupyter/train.ipynb)。  
由于我们能得到的数据量很小，也没有大量计算资源，直接利用现有数据集和计算资源直接训练模型的效果不佳，所以我们选择使用预训练的VGG16进行迁移学习，Keras使得这一实现非常容易，最终我们的模型结构如下：

<img src="https://fzhcats.s3-ap-northeast-1.amazonaws.com/documents/model_summary.png" >


经过训练和参数调优后，最终我们的模型的预测准确率达到80.27%，考虑到我们的数据集很小且筛选比较粗糙，这是个不错的结果。

<img src="https://fzhcats.s3-ap-northeast-1.amazonaws.com/documents/learning_curve.png" >


### 2. 用于预测猫的分类的Web API
我们将训练好的模型保存为saved model形式，并存放在AWS S3上。由于图片在输入到我们的模型之前需要进行裁剪，像素转换，归一化等预处理操作，而时间紧迫，我们此前没有使用Java做图像预处理的经验，所以我们这次没有选择使用AWS Lambda等工具部署模型。  


我们用flask做了一个微型Web API，见[predict_webapi](https://github.com/zerofang/aws-hackathon-2020/blob/master/1%20%E9%81%97%E5%A4%B1%E5%AE%A0%E7%89%A9%E7%9A%84%E6%99%BA%E8%83%BD%E5%AF%BB%E6%89%BE/%E5%A4%B1%E7%8C%AB%E6%8B%9B%E9%A2%86-SW/predict_webapi/predict.py)。我们创建了一个EC2实例，安装了Python3.7 + Tensorflow2.30运行环境，用于运行这个Web API服务。这一解决方案很粗糙，而且免费的EC2实例仅包含一个单核CPU计算资源，所以该API响应速度较慢，达到了数百毫秒，这是未来的优化点之一，未来我们将尝试使用AWS Lambda重新部署模型。


----------
### 编译部署

----------
### 贡献者
