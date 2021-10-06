package cn.tarsknock.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class BlogType implements Serializable {
    private Integer id;
    private String typeName;
    private Integer orderNo;
    /**
     * 该类型下博客的数量
     */
    private Integer blogCount;
}
