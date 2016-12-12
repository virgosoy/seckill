package com.soy.test.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Soy on 2016/12/10.
 */
public class Aaaaaaa {
    private String type;
    private String pay_type;
    private String name;
    private BigDecimal money;
    private Date addtime;
    private String issue;
    private String id;
    private Integer money_type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMoney_type() {
        return money_type;
    }

    public void setMoney_type(Integer money_type) {
        this.money_type = money_type;
    }

    @Override
    public String toString() {
        return "Aaaaaaa{" +
                "type='" + type + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", addtime=" + addtime +
                ", issue='" + issue + '\'' +
                ", id='" + id + '\'' +
                ", money_type=" + money_type +
                '}';
    }
}
