package com.form.service;

import com.form.dao.OperationDao;
import com.form.dao.OperationDaoImpl;
import com.form.entity.Grade;
import com.form.entity.Student;
import com.form.entity.Teacher;
import com.form.entity.User;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Form      J
 */
public class InputOperationImpl implements InputOperation{
    Scanner sc = new Scanner(System.in);
    @Override
    public User getUserInput() {
        User user = new User();
        System.out.println("请输入账号：");
        user.setAccount(sc.nextInt());
        System.out.println("请输入密码：");
        user.setPassWord(sc.next());
        System.out.println("请输入身份：");
        user.setIdentification(sc.next());
        return user;
    }

    @Override
    public Student getStudentInput() {
        Student student = new Student();
        System.out.println("请输入学号：");
        student.setId(sc.nextInt());
        System.out.println("请输入姓名：");
        student.setName(sc.next());
        System.out.println("请输入性别：");
        student.setSex(sc.next());
        System.out.println("请输入班级：");
        student.setClassName(sc.next());
        return student;
    }

    @Override
    public Teacher getTeacherInput() {
        Teacher teacher = new Teacher();
        System.out.println("请输入工号：");
        teacher.setId(sc.nextInt());
        System.out.println("请输入姓名：");
        teacher.setName(sc.next());
        System.out.println("请输入性别：");
        teacher.setSex(sc.next());
        System.out.println("请输入科目：");
        teacher.setSubject(sc.next());
        System.out.println("请输入管理的班级名字：");
        teacher.setClassName(sc.next());
        return teacher;
    }

    @Override
    public Grade getGradeInput() {
        Grade grade = new Grade();
        System.out.println("学号为：");
        grade.setId(sc.nextInt());
        System.out.println("班级名字为：");
        grade.setClassName(sc.next());
        System.out.println("请输入语文成绩：");
        grade.setChineseGrade(sc.nextFloat());
        System.out.println("请输入数学成绩：");
        grade.setMathGrade(sc.nextFloat());
        System.out.println("请输入英语成绩：");
        grade.setEnglishGrade(sc.nextFloat());
        return grade;
    }

    @Override
    public Integer getDeleteUser() {
        Integer id;
        String str;
        System.out.println("请输入您要删除的id号：");
        id = sc.nextInt();
        System.out.println("请慎重考虑是否删除！！！");
        System.out.println("确认删除请输入（yes），取消请输入（no）,请输入：");
        str = sc.next();
        if("yes".equalsIgnoreCase(str)){
            System.out.println("删除成功！！！");
            return id;
        }else{
            return -1;
        }
    }

    @Override
    public String getModifyPassWordInput(Integer id) {
        String password;
        System.out.println("请输入新密码：");
        password = sc.next();
        return password;
    }

    @Override
    public Student getModifyStudentInput(Integer id) {
        Student student = new Student();
        OperationDao operationDao = new OperationDaoImpl();
        ArrayList<String> str;
        str = operationDao.findAll("personal",1, id);
        System.out.println("你的学号是：\n" + Integer.valueOf(str.get(0)));
        student.setId(Integer.valueOf(str.get(0)));
        System.out.println("请输入你更改的姓名：");
        student.setName(sc.next());
        System.out.println("请输入你更改的性别：");
        student.setSex(sc.next());
        System.out.println("你的班级是：\n" + str.get(3));
        student.setClassName(str.get(3));
        return student;
    }

    @Override
    public Teacher getModifyTeacherInput(Integer id) {
        Teacher teacher = new Teacher();
        OperationDao operationDao = new OperationDaoImpl();
        ArrayList<String> str;
        str = operationDao.findAll("personal",2, id);
        System.out.println("你的工号是：\n" + Integer.valueOf(str.get(0)));
        teacher.setId(Integer.valueOf(str.get(0)));
        System.out.println("请输入你更改的姓名：");
        teacher.setName(sc.next());
        System.out.println("请输入你更改的性别：");
        teacher.setSex(sc.next());
        System.out.println("请输入你更改的科目：");
        teacher.setSubject(sc.next());
        System.out.println("你的管理的班级是：\n" + str.get(4));
        teacher.setClassName(str.get(4));
        return teacher;
    }

    @Override
    public Grade getModifyGradeInput(Integer id) {
        Grade grade = new Grade();
        OperationDao operationDao = new OperationDaoImpl();
        ArrayList<String> str;
        Integer idNum;
        System.out.println("请输入需要修改成绩的学号：");
        idNum = sc.nextInt();
        str = operationDao.findAll("personal", 1, idNum);
        System.out.println("你想修改成绩的学生的学号为：");
        System.out.println(str.get(0));
        grade.setId(Integer.valueOf(str.get(0)));
        System.out.println("你想修改成绩的学生的姓名为：");
        System.out.println(str.get(1));
        System.out.println("修改新的语文成绩为：");
        grade.setChineseGrade(sc.nextFloat());
        System.out.println("修改新的数学成绩为：");
        grade.setMathGrade(sc.nextFloat());
        System.out.println("修改新的英语成绩为：");
        grade.setEnglishGrade(sc.nextFloat());
        return grade;
    }

    @Override
    public ArrayList<String> inputLogin() {
        OperationDao operationDao = new OperationDaoImpl();
        String identification = null;
        ArrayList<String> information = new ArrayList<>();
        User user = new User();
        System.out.println("\t欢迎进入 Sam_system！！！");
        System.out.println("请输入您的账号密码：");
        while(true) {
            user.setAccount(sc.nextInt());
            information.add(0, String.valueOf(user.getAccount()));
            System.out.println("请输入您的密码：");
            user.setPassWord(sc.next());
            identification = operationDao.findByLogin(user.getAccount(), user.getPassWord());
            information.add(1, identification);
            if ("-1".equals(identification)) {
                System.out.println("请重新输入您的账号：");
            }
            else {
                break;
            }
        }
        return information;
    }

    @Override
    public int inputMenuAdministrator() {
        int num;
        System.out.println("欢迎您，Administrator！");
        System.out.println("请选择需要操作的代号：");
        System.out.print("1、查看用户信息\t2、查看学生信息\t3、查看教师信息\t4、删除用户\t5、添加用户\t6、添加学生\t7、添加教师\t8、返回登录界面\t0、退出系统\n");
        System.out.println("请输入：");
        num = sc.nextInt();
        return num;
    }

    @Override
    public int inputMenuStudent() {
        int num;
        System.out.println("欢迎您，Student！");
        System.out.println("请选择需要操作的代号：");
        System.out.print("1、查看个人信息\t2、查看成绩\t3、查看班主任信息\t4、修改个人信息\t0、退出系统\n");
        System.out.println("请输入：");
        num = sc.nextInt();
        return num;
    }

    @Override
    public int inputMenuTeacher() {
        int num;
        System.out.println("欢迎您，Teacher！");
        System.out.println("请选择需要操作的代号：");
        System.out.print("1、查看个人信息\t2、查看本班学生信息\t3、添加学生成绩\t4、修改学生成绩\t5、查看本班学生成绩\t6、修改个人信息\t0、退出系统\n");
        System.out.println("请输入：");
        num = sc.nextInt();
        return num;
    }
}
