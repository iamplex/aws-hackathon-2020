package com.cats.lostandfound.entity;

/**
 *
 * location:字符串，6位地区码
 * cat_class:数字，0到12，
 * type:lost-0,found-1
 * status:仍在寻找猫或寻找主人-0,已经找到-1
 * startTime:长整型的毫秒数，起始时间，用于按时间段查询，例如最近一个月
 * endTime:长整型毫秒数，终止时间
 * offset: = (pageNumber页码 - 1)*page_size
 * page_size:每页显示的post个数
 */
public class Filter {
    private String location;
    private int cat_class;
    private int type;
    private int status;
    private long startTime;
    private long endTime;
    private int offset;
    private int page_size;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCat_class() {
        return cat_class;
    }

    public void setCat_class(int cat_class) {
        this.cat_class = cat_class;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "location=" + location +
                ", cat_class=" + cat_class +
                ", type=" + type +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", offset=" + offset +
                ", page_size=" + page_size +
                '}';
    }
}
