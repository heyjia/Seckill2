package com.heyjia.seckill.domain;

import java.util.Date;

public class SeckillUser {
    @Override
    public String toString() {
        return "SeckillUser{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", salt='" + salt + '\'' +
                ", head='" + head + '\'' +
                ", registerDate=" + registerDate +
                ", lastLoginDate=" + lastLoginDate +
                ", loginCount=" + loginCount +
                '}';
    }

    private Long id;
    private String nickname;
    private String userpassword;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public String getSalt() {
        return salt;
    }

    public String getHead() {
        return head;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public SeckillUser() {
    }

    public SeckillUser(Long id, String nickname, String userpassword, String salt, String head, Date registerDate, Date lastLoginDate, Integer loginCount) {
        this.id = id;
        this.nickname = nickname;
        this.userpassword = userpassword;
        this.salt = salt;
        this.head = head;
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.loginCount = loginCount;
    }
}
