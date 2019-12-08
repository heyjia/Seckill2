package com.heyjia.seckill.domain;

public class FormVo {
    private String mobile;
    private String password;
    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FormVo(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public FormVo() {
    }

    @Override
    public String toString() {
        return "FormVo{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
