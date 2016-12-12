package com.soy.test.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by Soy on 2016/12/10.
 */
public class Bean {

    private Integer status;
    private String ret_msg;
    private List<Aaaaaaa> list;
    private Map<Integer,String> trantype;
    private Map<Integer,String> transtatus;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public List<Aaaaaaa> getList() {
        return list;
    }

    public void setList(List<Aaaaaaa> list) {
        this.list = list;
    }

    public Map<Integer, String> getTrantype() {
        return trantype;
    }

    public void setTrantype(Map<Integer, String> trantype) {
        this.trantype = trantype;
    }

    public Map<Integer, String> getTranstatus() {
        return transtatus;
    }

    public void setTranstatus(Map<Integer, String> transtatus) {
        this.transtatus = transtatus;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "status=" + status +
                ", ret_msg='" + ret_msg + '\'' +
                ", list=" + list +
                ", trantype=" + trantype +
                ", transtatus=" + transtatus +
                '}';
    }
}
