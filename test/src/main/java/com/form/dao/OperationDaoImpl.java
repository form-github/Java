package com.form.dao;

import com.form.ConnectionJavaDb;
import com.form.entity.Grade;
import com.form.entity.Student;
import com.form.entity.Teacher;
import com.form.entity.User;
import com.form.service.InputOperation;
import com.form.service.InputOperationImpl;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Form      J
 */
public class OperationDaoImpl implements OperationDao{
    Connection conn = ConnectionJavaDb.getConnection();
    InputOperation inputOperation = new InputOperationImpl();
    String sql = null, sql1 = null, sql2 = null, sql3 = null, sql4 = null;
    PreparedStatement ptmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    int i;
    @Override
    public int addOperation(int num) {
        switch (num){
            // 添加用户
            case 0:
                sql = "insert into user values(?, ?, ?)";
                try {
                    ptmt = conn.prepareStatement(sql);
                    User user = inputOperation.getUserInput();
                    ptmt.setInt(1, user.getAccount());
                    ptmt.setString(2, user.getPassWord());
                    ptmt.setString(3, user.getIdentification());
                    ptmt.execute();
                } catch (SQLException e) {
                    System.out.println("添加失败！！！请检查是否输入错误！！！");
                    return -1;
                }
                break;
            // 添加学生信息
            case 1:
                sql = "insert into student value (?, ?, ?, ?)";
                try {
                    ptmt = conn.prepareStatement(sql);
                    Student student = inputOperation.getStudentInput();
                    ptmt.setInt(1, student.getId());
                    ptmt.setString(2, student.getName());
                    ptmt.setString(3, student.getSex());
                    ptmt.setString(4, student.getClassName());
                    ptmt.execute();
                } catch (SQLException e) {
                    System.out.println("添加失败！！！请检查是否输入错误！！！");
                    return -1;
                }
                break;
            // 添加教师的信息
            case 2:
                sql = "insert into teacher value (?, ?, ?, ?, ?)";
                try {
                    ptmt = conn.prepareStatement(sql);
                    Teacher teacher = inputOperation.getTeacherInput();
                    ptmt.setInt(1, teacher.getId());
                    ptmt.setString(2, teacher.getName());
                    ptmt.setString(3, teacher.getSex());
                    ptmt.setString(4, teacher.getSubject());
                    ptmt.setString(5, teacher.getClassName());
                    ptmt.execute();
                } catch (SQLException e) {
                    System.out.println("添加失败！！！请检查是否输入错误！！！");
                    return -1;
                }
                break;
            // 添加成绩信息
            case 3:
                sql = "insert into grade value (?, ?, ?, ?, ?)";
                try {
                    ptmt = conn.prepareStatement(sql);
                    Grade grade = inputOperation.getGradeInput();
                    ptmt.setInt(1, grade.getId());
                    ptmt.setString(2, grade.getClassName());
                    ptmt.setFloat(3, grade.getChineseGrade());
                    ptmt.setFloat(4, grade.getMathGrade());
                    ptmt.setFloat(5, grade.getEnglishGrade());
                    ptmt.execute();
                } catch (SQLException e) {
                    System.out.println("添加失败！！！请检查是否输入错误！！！");
                    return -1;
                }
                break;
            default:
                break;
        }
        return 1;
    }

    /**
     * 数据库弄一个连级删除。删除主表即可。
     * 如果没有级联删除，只能从从表开始删除，要删完从表才能删主表。
     * 删除只有管理员才有的权力。
     * */
    @Override
    public void deleteOperation() {
        Integer id = inputOperation.getDeleteUser();
        sql = "delete from user where id = " + id;
        if(id != -1){
            try {
                ptmt = conn.prepareStatement(sql);
                ptmt.execute();
            } catch (SQLException e) {
                System.out.println("删除失败，请确定库中包含此人！！！");
            }
        }else{
            System.out.println("返回主界面！！！");
        }
    }

    @Override
    public void modifyOperation(Integer id, int num) {
        switch(num){
            //修改密码
            case 0:
                sql = "update user set password = ? where id = " + id;
                try {
                    String password = inputOperation.getModifyPassWordInput(id);
                    ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, password);
                    //无执行语句
                } catch (SQLException e) {
                    System.out.println("修改失败！！！请检查是否输入错误！！！");
                }
                break;
            //修改学生信息
            case 1:
                Student student = inputOperation.getModifyStudentInput(id);
                sql = "update student set name = ?, sex = ?, classname = ? where id = " + student.getId();
                try {
                    ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, student.getName());
                    ptmt.setString(2, student.getSex());
                    ptmt.setString(3, student.getClassName());
                    ptmt.execute();
                    System.out.println("修改成功！");
                } catch (SQLException e) {
                    System.out.println("修改失败！！！请检查是否输入错误！！！");
                }
                break;
            //修改教师信息
            case 2:
                Teacher teacher = inputOperation.getModifyTeacherInput(id);
                sql = "update teacher set name = ?, sex = ?, subject = ?, classname = ? where id = " + teacher.getId();
                try {
                    ptmt = conn.prepareStatement(sql);
                    ptmt.setString(1, teacher.getName());
                    ptmt.setString(2, teacher.getSex());
                    ptmt.setString(3, teacher.getSubject());
                    ptmt.setString(4, teacher.getClassName());
                    ptmt.execute();
                    System.out.println("修改成功！");
                } catch (SQLException e) {
                    System.out.println("修改失败！！！请检查是否输入错误！！！");
                }
                break;
            //修改学生成绩
            case 3:
                Grade grade = inputOperation.getModifyGradeInput(id);
                sql = "update grade set Chinsesgrade = ?, mathgrade = ?, englishgrade = ? where id = " + grade.getId();
                System.out.println(sql);
                try {
                    ptmt = conn.prepareStatement(sql);
                    ptmt.setFloat(1, grade.getChineseGrade());
                    ptmt.setFloat(2, grade.getMathGrade());
                    ptmt.setFloat(3, grade.getEnglishGrade());
                    ptmt.execute();
                    System.out.println("修改成功！");
                } catch (SQLException e) {
                    System.out.println("修改失败！！！请检查是否输入错误！！！");
                }
                break;

            default:
                break;
        }
    }

    @Override
    /**
     *  当flag=ture时，id是无效的，意味着查询此表全部信息;
     *  当flag=false时，id有效，意味着查询单条信息，条件是 where id = id.
     * */
    public ArrayList<String> findAll(String flag, int num, Integer id) {
        String[] str = {"user", "student", "teacher", "grade"};
        ArrayList<String> information = new ArrayList<>();
        sql = "select * from " + str[num];
        sql1 = "select * from " + str[num] + " where id = " + id;
        // num要等于2，学生查询教师信息操作。
        sql2 = "select c.id, c.name, c.sex, c.subject, c.classname from " +
                "teacher c inner join student s on c.classname = s.classname and s.id = " + id;
        // num要等于1，教师查询学生信息操作。
        sql3 = "select s.id, s.name, s.sex, s.classname from "+
                "student s inner join teacher t on s.classname = t.classname and t.id = " + id;
        // num要等于3， 教师查询学生成绩操作
        sql4 = "select g.id, g.classname, g.Chinsesgrade, g.mathgrade, g.englishgrade from " +
                " grade g inner join teacher t on g.classname = t.classname and t.id = " + id;
        try {
            stmt = conn.createStatement();
            //flag为true代表查找全部信息
            if("all".equals(flag)){
                rs = stmt.executeQuery(sql);
            }else if ("personal".equals(flag)){
                rs = stmt.executeQuery(sql1);
            }else if("teacher".equals(flag)){
                rs = stmt.executeQuery(sql2);
            }else if("student".equals(flag)){
                rs = stmt.executeQuery(sql3);
            }else {
                rs = stmt.executeQuery(sql4);
            }
            switch(num) {
                // 查询用户表，需要管理员权限
                case 0:
                    while (rs.next()) {
                        information.add(rs.getString("id"));
                        information.add(rs.getString("password"));
                        information.add(rs.getString("identification"));
                    }
                    break;
                // 查询学生信息表
                case 1:
                    while(rs.next()){
                        information.add(rs.getString("id"));
                        information.add(rs.getString("name"));
                        information.add(rs.getString("sex"));
                        information.add(rs.getString("classname"));
                    }
                    break;
                // 查询教师信息表， 暂时设定教师及以上权限
                case 2:
                    while(rs.next()) {
                        information.add(rs.getString("id"));
                        information.add(rs.getString("name"));
                        information.add(rs.getString("sex"));
                        information.add(rs.getString("subject"));
                        information.add(rs.getString("classname"));
                    }
                    break;
                // 查询学生成绩表
                case 3:
                    while(rs.next()) {
                        information.add(rs.getString("id"));
                        information.add(rs.getString("classname"));
                        information.add(rs.getString("Chinsesgrade"));
                        information.add(rs.getString("mathgrade"));
                        information.add(rs.getString("englishgrade"));
                    }
                    break;

                default:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }

    @Override
    public String findByLogin(Integer id, String password) {
        String identification = null;
        sql = "select identification from user where id = " + id + " and password = \"" + password +"\"";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                identification = rs.getString("identification");
            }
            if(identification == null){
                System.out.println("账号或者密码错误！！！");
                return "-1";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return identification;
    }

    @Override
    public void getClose() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
