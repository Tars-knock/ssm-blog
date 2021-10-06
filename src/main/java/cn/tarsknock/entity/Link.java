package cn.tarsknock.entity;

import lombok.Data;

@Data
public class Link {
    private Integer id;
    private String name;
    private String profile;
    private String imageUrl;
    private String url;
    private Integer orderNo;
}
