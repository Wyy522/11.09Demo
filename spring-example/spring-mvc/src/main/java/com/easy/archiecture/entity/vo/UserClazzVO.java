package com.easy.archiecture.entity.vo;

import com.easy.archiecture.entity.User;

import java.io.Serializable;

public class UserClazzVO implements Serializable {
    private static final long serialVersionUID = -8711352530008547786L;

    private User user;
    private int clazzId;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }
}
