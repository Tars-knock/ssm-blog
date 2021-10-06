package cn.tarsknock.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private int id;
    private String userIp;
    private Blog blog;
    private String content;
    private Date date;
    private Integer state;
    private User user;

    private String dateStr;
}
