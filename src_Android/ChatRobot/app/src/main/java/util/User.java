package util;

import java.io.Serializable;

/**
 * Created by 11917 on 2017/10/29.
 */

public class User implements Serializable{

    private int id=-1;   //用户编号
    private String name=""; //姓名
    private String pass="";  //密码
    private String phone="";   //手机号
    private String time="";   //注册时间
    private String address="";  //开户行
    private String money="";   //余额

    public User(int id, String name, String pass, String phone, String time, String address, String money) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.phone = phone;
        this.time = time;
        this.address = address;
        this.money = money;
    }
    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}
