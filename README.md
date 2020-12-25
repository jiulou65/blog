# Blog
an easy personal blog

![Image text](https://github.com/jiulou65/blog/blob/main/src/main/resources/static/images/logo.png)


### 项目技术架构采用：Springboot + SpringDataJpa + Mysql + Thymeleaf

上传图片采用的是在线图片，待改进
搜索目前采用的是like模糊查询，待改进

登录采用的是jwt生成token来保证登录状态，在拦截器里面进行校验
评论是数据库的评论表中增加一个父评论id，自连接进行查找，二级评论

待完善...
