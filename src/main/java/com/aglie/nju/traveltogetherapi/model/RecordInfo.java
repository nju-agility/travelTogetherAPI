package com.aglie.nju.traveltogetherapi.model;

public class RecordInfo {
    private String account;
    private Integer aid;
    private String comment;
    private Integer score;
    private Integer num_of_pic;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getNum_of_pic() {
        return num_of_pic;
    }

    public void setNum_of_pic(Integer num_of_pic) {
        this.num_of_pic = num_of_pic;
    }
}
