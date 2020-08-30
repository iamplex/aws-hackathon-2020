package com.cats.lostandfound.entity;

public class Filter {
    private int location;
    private int cat_class;
    private int type;
    private int status;
    private long startTime;
    private long endTime;
    private int offset;
    private int page_size;

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
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
