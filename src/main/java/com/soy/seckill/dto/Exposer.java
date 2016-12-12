package com.soy.seckill.dto;

import java.util.Date;

/**
 * 暴露秒杀地址
 * Created by Soy on 2016/12/12.
 */
public class Exposer {

    //是否开启秒杀
    private boolean exposed;
    //加密措施
    private String md5;
    //id
    private int seckillId;
    //系统当前时间
    private Date now;
    //秒杀开始时间
    private Date start;
    //秒杀结束时间
    private Date end;

    /**
     * 秒杀开始时的构造函数
     * @param exposed
     * @param seckillId
     * @param md5
     */
    public Exposer(boolean exposed, int seckillId, String md5) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    /**
     * 秒杀未开始/结束时的构造函数
     * @param exposed
     * @param now
     * @param start
     * @param end
     */
    public Exposer(boolean exposed, Date now, Date start, Date end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    /**
     * 秒杀商品不存在时的构造函数
     * @param exposed
     * @param seckillId
     */
    public Exposer(boolean exposed, int seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
