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
### 架构设计
<img src="https://fzhcats.s3-ap-northeast-1.amazonaws.com/documents/architecture.png" >
####1. 模型训练


----------
### 使用的AWS服务
1. AWS EC2：作为存放web服务的容器
2. AWS Elastic Beanstalk：创建tomcat容器和Java环境，并一键部署web应用
3. AWS RDS：创建并使用MySQL数据库
4. AWS SegeMaker：深度学习模型训练和保存
5. AWS S3：对象存储，存储训练数据集和模型，以及作为图床存储用户上传的图片
6. AWS IAM：资源访问权限管理


----------
### 贡献者
