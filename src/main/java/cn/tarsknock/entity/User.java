package cn.tarsknock.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(exclude = "password")
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String profile;
    private String imageUrl;
    private String email;

    public static void main(String[] args) {
        User u = new User();
        u.setPassword("010101");
        System.out.println(u);
    }
}

