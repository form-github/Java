package com.form.dao;

import java.util.ArrayList;

/**
 * @author Form      J
 */
public interface OperationDao {

    public int addOperation(int num);
    public void deleteOperation();
    public void modifyOperation(Integer id, int num);
    public ArrayList<String> findAll(String flag, int num, Integer id);
    public String findByLogin(Integer id, String password);
    public void getClose();
}
