package com.form.entity;

/**
 * @author Form      J
 */
public class Grade {
    private Integer id;
    private String className;
    private float ChineseGrade;
    private float mathGrade;
    private float englishGrade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getChineseGrade() {
        return ChineseGrade;
    }

    public void setChineseGrade(float chineseGrade) {
        ChineseGrade = chineseGrade;
    }

    public float getMathGrade() {
        return mathGrade;
    }

    public void setMathGrade(float mathGrade) {
        this.mathGrade = mathGrade;
    }

    public float getEnglishGrade() {
        return englishGrade;
    }

    public void setEnglishGrade(float englishGrade) {
        this.englishGrade = englishGrade;
    }
}
