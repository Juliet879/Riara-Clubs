package com.saya.coco;

public class UserHelper {

    String first;
    String last;
    String email;
    String studentID;
    String num;
    String school;
    String yr;
    String sex;
    int age;
    String password;
    String confirmPassword;

    UserHelper() {}

    public UserHelper(String first, String last, String email,
                      String studentID, String num, String school,
                      String yr, String sex, int age,
                      String password, String confirmPassword) {
        this.first = first;
        this.last = last;
        this.email = email;
        this.studentID = studentID;
        this.num = num;
        this.school = school;
        this.yr = yr;
        this.sex = sex;
        this.age = age;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentID() { return studentID; }

    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getYr() {
        return yr;
    }

    public void setYr(String yr) {
        this.yr = yr;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
}
