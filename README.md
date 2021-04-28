## 需求分析

基于java+jsp+servlet+mysql技术, 实现一个在线教学答疑系统, 学生可以通过该网站提出问题留言，教师登录后可以进行答疑, 系统管理员可以对答疑系统进行管理

## 实现功能

> * 实现学生的账号登录注册、数据导入
> * 教师与管理员的登录与退出
> * 管理员完成学院管理,教师管理, 课程管理, 并能修改管理员密码
> * 教师能够对所有提问进行答疑, 并给自己的课程添加学生权限
> * 学生能够在某个课程中进行提问, 并能查看已有的提问状态

##  技术栈

本项目是基于JSP Servlet技术进行实现, 采用了Servlet3 API, 采用注解方式避免了大量的xml定义, 通过servlet设计了一个小型的mvc框架, 减少了项目中的重复代码

## 项目部署

项目直接导入IDEA即可，作为一个工程直接导入。对于数据源配置，需在`tomcat/conf/context.xml`里面的`<Context></Context>`添加如下配置:

```      <Resource 
        <Resource 
        name="jdbc/mysql"
        auth="Container" 
        type="javax.sql.DataSource"
        maxActive="100" 
        maxIdle="30" 
        maxWait="10000"
        username="root" 
        password="123456"
        driverClassName="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/p21116?useUnicode=true&amp;characterEncoding=utf-8"/>	   
```

项目需配置tomcat配置

## 访问路径：
`http://localhost:8080/on-line-question/`

## 项目截图

![01](C:\Users\15120\Downloads\21116-JavaWeb在线答疑系统-2\javaweb在线答疑系统.201909260824\项目截图\01.jpg)

![02](C:\Users\15120\Downloads\21116-JavaWeb在线答疑系统-2\javaweb在线答疑系统.201909260824\项目截图\02.jpg)

![03](C:\Users\15120\Downloads\21116-JavaWeb在线答疑系统-2\javaweb在线答疑系统.201909260824\项目截图\03.jpg)

![04](C:\Users\15120\Downloads\21116-JavaWeb在线答疑系统-2\javaweb在线答疑系统.201909260824\项目截图\04.jpg)

![05](C:\Users\15120\Downloads\21116-JavaWeb在线答疑系统-2\javaweb在线答疑系统.201909260824\项目截图\05.jpg)

![06](C:\Users\15120\Downloads\21116-JavaWeb在线答疑系统-2\javaweb在线答疑系统.201909260824\项目截图\06.jpg)

![07](C:\Users\15120\Downloads\21116-JavaWeb在线答疑系统-2\javaweb在线答疑系统.201909260824\项目截图\07.jpg)





## 系统账号

本项目为在线答疑系统，支持学生、教师、管理员三个账户登陆

> * 管理员账号 admin 123
> * 教师账号 dufu 123
> * 测试账号 qinkai 123

