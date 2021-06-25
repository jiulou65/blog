# Blog
an easy personal blog | 一个简单的个人博客

![Image text](https://github.com/jiulou65/blog/blob/main/src/main/resources/static/images/logo.png)


### 项目技术架构采用：SpringBoot + SpringDataJpa + Mysql + Thymeleaf

上传图片采用的是在线图片，待改进

搜索目前采用的是like模糊查询，待改进


登录采用的是jwt生成token来保证登录状态，在拦截器里面进行校验

评论是数据库的评论表中增加一个父评论id，自连接进行查找，二级评论


### 项目主要功能

#### 前台
网站首页、博客详情、博客搜索、博客分类、博客分标签、归档（历史）、本人介绍

#### 后台
登录校验、博客管理、分类管理、标签管理

博客地址：http://106.14.43.104:8081

欢迎大家反馈问题，待完善...


#### 待补充

评论、删除


well
