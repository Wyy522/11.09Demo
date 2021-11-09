package com.easy.archiecture.entity;

import java.io.Serializable;

public class UserClazzRef implements Serializable {
    private static final long serialVersionUID = -6318923986402357451L;

    private int userId;
    private int clazzId;

    public UserClazzRef() {
    }

    public UserClazzRef(int userId, int clazzId) {
        this.userId = userId;
        this.clazzId = clazzId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }
}
