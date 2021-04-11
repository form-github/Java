package com.form.service;

import com.form.dao.OperationDao;
import com.form.dao.OperationDaoImpl;

import java.util.ArrayList;

/**
 * @author Form      J
 */
public class LoginOperation {

    public void login() {
        Integer id;
        ArrayList<String> str;
        OperationDao operationDao = new OperationDaoImpl();
        InputOperation inputOperation = new InputOperationImpl();
        MenuOperation menuOperation = new MenuOperationImpl();
        str = inputOperation.inputLogin();
        id = Integer.valueOf(str.get(0));
        do {
            if ("administrator".equals(str.get(1))) {
                menuOperation.menuAdministrator(id);
            } else if ("student".equals(str.get(1))) {
                menuOperation.menuStudent(id);
            } else if ("teacher".equals(str.get(1))) {
                menuOperation.menuTeacher(id);
            } else {
                System.out.println("咦，你是什么鬼？？？？？");
                operationDao.getClose();
                System.exit(0);
            }
        } while (true);
    }
}
