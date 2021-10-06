package cn.tarsknock.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Blog implements Serializable {
    private Integer id;
    private String title;
    private String statu;
    private String description;
    private String content;
    private Date releaseDate;
    private Date updateDate;
    private Integer clickHit;
    private Integer replyHit;
    private BlogType blogType;
    private String keyWord;


    //方便操作
    private String releaseDateStr;
    private String updateDateStr;

}
