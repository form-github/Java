# Sam_system（Student Achievement Management System）

## 1、数据

### 1.1、数据库

- 用户表（user）
  1. account
  2. password
  3. identification

- 学生表（student）
  1. id
  2. name
  3. sex
  4. classname
  5. teachername
- 教师表（teacher）
  1. id
  2. name
  3. sex
  4. subject
  5. classname
- 成绩表（grade）
  1. id
  2. name
  3. classname
  4. Chinesegrade
  5. mathgrade
  6. englishgrade

### 1.2、数据表建立过程

- 将用户设置为主表，其他表为从表，将主键的account设置为其他表的外键，约束名分别为：account，account_1，account_2

- 补充：**还得添加级联操作**

  - ON DELETE CASCADE ：级联删除约束
  - ON UPDATE CASCADE：级联更新约束
  - 以下数据表均以添加级联删除约束

- 用户表

  ```mysql
  create table user(
  	id int not null auto_increment,
      password varchar(20) not null,
      identification varchar(20) not null,
  	primary key(id)
  )engine = innodb default charset = utf8;
  ```

- 学生表

  ```mysql
  create table student(
      id int not null auto_increment,
      name varchar(20) not null,
      sex varchar(5) not null,
      classname varchar(20) not null,
  	primary key(id),
      constraint account foreign key(id) references user(id) ON DELETE CASCADE
  )engine = innodb default charset = utf8;
  ```
  
- 教师表

  ```MySQL
  create table teacher(
      id int not null auto_increment,
      name varchar(20) not null,
      sex varchar(5) not null,
      subject varchar(20) not null,
      classname varchar(20) not null,
  	primary key(id),
      constraint account_2 foreign key(id) references user(id) ON DELETE CASCADE
  )engine = innodb default charset = utf8;
  ```

- 成绩表

  ```mysql
  create table grade(
      id int not null auto_increment,
      classname varchar(20) not null,
      Chinsesgrade float,
      mathgrade float,
      englishgrade float,
  	primary key(id),
      constraint account_3 foreign key(id) references user(id) ON DELETE CASCADE
  )engine = innodb default charset = utf8;
  ```


### 1.3、数据操作的一些命令：

- ​	学生查询班主任的信息

  ```mysql
  /*当你想查询指定的数据就用第一条，要是比较懒查全部就用下面那条*/
  SELECT t.id, t.name, t.sex, t.subject, t.classname FROM teacher t INNER JOIN student s ON t.classname = s.classname;
  
  SELECT t.* FROM teacher t INNER JOIN student s ON t.classname = s.classname;
  ```


- 教师查询本班学生成绩

  ```mysql
  select g.id, g.name, g.classname, g.Chinsesgrade, g.mathgrade, g.englishgrade from grade g inner join teacher t on g.classname = t.classname and t.id = 21041001; 
  
  select g.* from grade g inner join teacher t on g.classname = t.classname and t.id = 21041001; 
  ```

- 建完表后修改外键操作

  ```mysql
  /*alter table 需加外键的表 add constraint 外键名 foreign key(需加外键表的字段名) references 关联表名(关联字段名);*/
  
  alter table student add constraint account foreign key(id) references user(id) on delete cascade;
  alter table teacher add constraint account1 foreign key(id) references user(id) on delete cascade;
  alter table grade add constraint account2 foreign key(id) references user(id) on delete cascade;
  
  /*因为我在之前已经添加过外键，所以要先把之前的外键设置都删除然后在添加。不知道有没有直接更新约束的代码，郁闷*/
  show create table student;
  /*查看一下表结构，确定约束名为account*/
  alter table student drop foreign key account;
  /*然后再次增加就ok了*/
  ```

  

## 2、总结

### 	2.1、总用时：

​			从2021.04.05日到2021.04.11完成

### 	2.2、不足：

- ​		这个项目有好多不足，好多构想都都没有去实现，比如更改密码，一些重复性的代码删减，还有输入重复（就是数据库里面已经有的还重复输入，麻烦）等等。

- 引用

  ```mysql
  /*
  DAO层：
  DAO层叫数据访问层，全称为data access object，属于一种比较底层，比较基础的操作，具体到对于某个表的增删改查，也就是说某个DAO一定是和数据库的某一张表一一对应的，其中封装了增删改查基本操作，建议DAO只做原子操作，增删改查。
  
  Service层：
  Service层叫服务层，被称为服务，粗略的理解就是对一个或多个DAO进行的再次封装，封装成一个服务，所以这里也就不会是一个原子操作了，需要事物控制。
  
  Controler层：
  Controler负责请求转发，接受页面过来的参数，传给Service处理，接到返回值，再传给页面。
  ————————————————
  版权声明：本文为CSDN博主「诚o」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
  原文链接：https://blog.csdn.net/qq_22771739/article/details/82344336 
  */
  ```

  然后就是没分好dao层，service层，controller层。应该把菜单界面放到controller层内的，在这里我直接合并了service层和controller层，这也是没有做好的事情。

- 如果有哪个大佬可以带带我就好了。呜呜呜