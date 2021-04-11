package com.form.service;

import java.util.ArrayList;

/**
 * @author Form      J
 */
public class OutputOperationImpl implements OutputOperation{

    @Override
    public void outputInformation(ArrayList<String> information, int num) {
        int j = 1;
        switch (num) {
            case 0:
                System.out.print("账号\t\t\t\t密码\t\t\t身份\n");
                break;
            case 1:
                System.out.print("学号\t\t\t\t姓名\t\t性别\t\t班级\n");
                break;
            case 2:
                System.out.print("工号\t\t\t\t姓名\t\t性别\t\t任教科目\t管理班级\n");
                break;
            case 3:
                System.out.print("学号\t\t\t\t班级\t\t语文成绩\t\t数学成绩\t\t英语成绩\n");
                break;
            default:
                break;
        }
        //当不同查询时，需要不同的输出
       if(num == 0 && information.size() > 3){
           for (String i : information) {
               System.out.print(i + "\t\t");
               if(j % 3 ==0){
                   System.out.print("\n");
               }
               j++;
           }
       }else if(num == 1 && information.size() > 4){
           for (String i : information) {
               System.out.print(i + "\t\t");
               if(j % 4 ==0){
                   System.out.print("\n");
               }
               j++;
           }
       }else if (num == 2 && information.size() > 5){
           for (String i : information) {
               System.out.print(i + "\t\t");
               if(j % 5 ==0){
                   System.out.print("\n");
               }
               j++;
           }
       }else if(num ==3 && information.size() > 5){
           for (String i : information) {
               System.out.print(i + "\t\t");
               if(j % 5 ==0){
                   System.out.print("\n");
               }
               j++;
           }
       }else{
           for (String i : information) {
               System.out.print(i + "\t\t");
           }
           System.out.print("\n");
       }
    }

}
