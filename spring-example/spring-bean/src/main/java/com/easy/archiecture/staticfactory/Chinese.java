package com.easy.archiecture.staticfactory;

public class Chinese implements Person {

    public String getMsg() {
        return msg;
    }

    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void say() {
        System.out.println(msg + "China is my homeland");
    }


}
