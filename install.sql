CREATE DATABASE IF NOT EXISTS scoremanager;
USE scoremanager;

/*管理员、教师表
number：工号
username：用户名
password：密码
power：权限：0为未审核，1，教师，2，教师
email：电子邮箱
*/
DROP TABLE IF EXISTS admin;	
create table admin(
	id int(5) PRIMARY KEY AUTO_INCREMENT,
	number varchar(8) NOT NULL,
	name varchar(64) NOT NULL UNIQUE,
	password varchar(64) NOT NULL,
	power tinyint(1) NOT NULL DEFAULT '0',
	email varchar(64) NOT NULL DEFAULT ''
);

/*班级表
name：班级名
department：所属系部
teacher：教师工号
*/
DROP TABLE IF EXISTS classes;	
create table classes(
	id int(5) PRIMARY KEY AUTO_INCREMENT,
	name varchar(64) NOT NULL UNIQUE,
	/*department varchar(32) NOT NULL DEFAULT '',*/
	teacher varchar(8) NOT NULL DEFAULT ''
);
/*学生表
number:学号
classes：班级编号
idcard：身份证号
phone：手机号
address：地址
*/
DROP TABLE IF EXISTS student;
create table student(
	id int(6) PRIMARY KEY AUTO_INCREMENT,
	number varchar(16) NOT NUll UNIQUE,
	password varchar(64) NOT NULL DEFAULT '123456',
	name varchar(64) NOT NULL,
	sex varchar(1) NOT NULL,
	classes int(5) NOT NULL,
	idcard varchar(18),
	phone varchar(20),
	address varchar(128)
);
/*成绩表
number：学号
course：课程
score：成绩
*/
DROP TABLE IF EXISTS score;
create table score(
	id int(10) PRIMARY KEY AUTO_INCREMENT,
	number varchar(16) NOT NULL,
	course int(5) NOT NULL,
	score float(3,1)
);

/*课程表
subject:科目名
credit:学分
term:学期 格式 
teacher:教师工号
学时！
*/
DROP TABLE IF EXISTS course;
create table course(
	id int(5) PRIMARY KEY AUTO_INCREMENT,
	subject varchar(64) NOT NULL,
	credit int(2),
	term varchar(32) NOT NULL,
	teacher varchar(8)
);
/*日志表
person:操作人
opreate：操作行为   [添加|删除|修改][注册|登录]
target：操作对象    [班级|教师|课程|学生|成绩|][系统]
oldvalue：原值
newvalue：新值
effect:是否生效
time：操作时间
*/
DROP TABLE IF EXISTS log;
create table log(
	id int(10) PRIMARY KEY AUTO_INCREMENT,
	person varchar(8) NOT NULL,
	opreate varchar(32) NOT NULL,
	target varchar(32) NOT NULL,
	oldvalue varchar(128) NOT NULL,
	newvalue varchar(128) NOT NULL,
	effect tinyint(1) NOT NULL,
	time datetime NOT NULL
);
/*课程-班级 关系表
*/
DROP TABLE IF EXISTS course_classes;
create table course_classes(
	id int(5) PRIMARY KEY AUTO_INCREMENT,
	course int(5) NOT NULL,
	classes int(5) NOT NULL
);