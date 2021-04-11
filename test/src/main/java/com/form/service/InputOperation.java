package com.form.service;

import com.form.entity.Grade;
import com.form.entity.Student;
import com.form.entity.Teacher;
import com.form.entity.User;

import java.util.ArrayList;

/**
 * @author Form      J
 */
public interface InputOperation {
    public User getUserInput();
    public Student getStudentInput();
    public Teacher getTeacherInput();
    public Grade getGradeInput();
    public Integer getDeleteUser();
    public String getModifyPassWordInput(Integer id);
    public Student getModifyStudentInput(Integer id);
    public Teacher getModifyTeacherInput(Integer id);
    public Grade getModifyGradeInput(Integer id);
    public ArrayList<String> inputLogin();
    public int inputMenuAdministrator();
    public int inputMenuStudent();
    public int inputMenuTeacher();

}
