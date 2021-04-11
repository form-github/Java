package com.form.service;

import com.form.dao.OperationDao;
import com.form.dao.OperationDaoImpl;


/**
 * @author Form      J
 */
public class MenuOperationImpl implements MenuOperation{
    OperationDao operationDao = new OperationDaoImpl();
    OutputOperation outputOperation = new OutputOperationImpl();
    InputOperation inputOperation = new InputOperationImpl();
    LoginOperation loginOperation = new LoginOperation();

    @Override
    public void menuAdministrator(Integer id) {
        int num = inputOperation.inputMenuAdministrator();
        switch (num){
            //退出系统
            case 0:
                operationDao.getClose();
                System.exit(0);
                break;
            //查看用户信息
            case 1:
                outputOperation.outputInformation(operationDao.findAll("all", 0, id), 0);
                break;
            //查看学生信息
            case 2:
                outputOperation.outputInformation(operationDao.findAll("all", 1, id), 1);
                break;
            //查看教师信息
            case 3:
                outputOperation.outputInformation(operationDao.findAll("all", 2, id), 2);
                break;
            //删除用户，慎重，因为是级联删除
            case 4:
                operationDao.deleteOperation();
                System.out.println("添加成功");
                break;
            //添加用户
            case 5:
                if(operationDao.addOperation(0) == 1){
                    System.out.println("添加成功！");
                }
                break;
            //添加学生
            case 6:
                if(operationDao.addOperation(1) == 1){
                    System.out.println("添加成功！");
                }
                break;
            //添加教师
            case 7:
                if(operationDao.addOperation(2) == 1){
                    System.out.println("添加成功！");
                }
                break;
            //返回主界面
            case 8:
                loginOperation.login();
                break;
            default:
               break;
        }
    }

    @Override
    public void menuStudent(Integer id) {
        int num = inputOperation.inputMenuStudent();
        switch (num){
            case 0:
                //退出系统
                operationDao.getClose();
                System.exit(0);
                break;
            //查看学生个人信息
            case 1:
                outputOperation.outputInformation(operationDao.findAll("personal", 1, id), 1);
                break;
            //查看学生个人成绩
            case 2:
                outputOperation.outputInformation(operationDao.findAll("personal", 3, id), 3);
                break;
            //查看班主任信息
            case 3:
                outputOperation.outputInformation(operationDao.findAll("teacher", 2, id), 2);
                break;
            //修改个人信息
            case 4:
                operationDao.modifyOperation(id, 1);
                break;
            default:
                break;
        }
    }

    @Override
    public void menuTeacher(Integer id) {
        int num = inputOperation.inputMenuTeacher();
        switch (num){
            case 0:
                //退出系统
                operationDao.getClose();
                System.exit(0);
                break;
            //查看教师个人信息
            case 1:
                outputOperation.outputInformation(operationDao.findAll("personal", 2, id), 2);
                break;
            case 2:
                //指定本班学生信息
                outputOperation.outputInformation(operationDao.findAll("student", 1, id), 1);
                break;
            //添加学生成绩
            case 3:
                if(operationDao.addOperation(3) == 1){
                    System.out.println("添加成功！");
                }
                break;
            //修改学生成绩
            case 4:
                operationDao.modifyOperation(id,3);
                break;
            //查看教师所教的学生成绩
            case 5:
                //指定班级名字搜索
                outputOperation.outputInformation(operationDao.findAll("grade", 3, id), 3);
                break;
            //修改个人信息
            case 6:
                operationDao.modifyOperation(id, 2);
                break;
            default:
                break;
        }
    }
}
