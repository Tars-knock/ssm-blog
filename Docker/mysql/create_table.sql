create DATABASE db_blog;
use db_blog;
create table t_user
(
    user_id   int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    user_name varchar(50) COMMENT '用户名',
    password  varchar(100),
    profile   text comment '个人简介',
    image_url varchar(100) comment '头像地址',
    email     varchar(100) comment '邮箱'
) COMMENT ='用户表';

create table t_blogtype
(
    blogtype_id int(11) NOT NULL AUTO_INCREMENT primary key,
    type_name   varchar(100) comment '类型名称',
    order_no    int(11) comment '排序'
) COMMENT ='博文类型表';


create table t_blog
(
    article_id   int(11) not null auto_increment primary key,
    title        varchar(200),
    statu       varchar(50) comment '发布状态',
    description  text comment '摘要',
    content      text,
    release_date datetime comment '发表时间',
    update_date  datetime comment '最后修改时间',
    click_hit    int(11) comment '点击数',
    reply_hit    int(11),
    type_id      int(11),
    key_word     varchar(200) comment '关键字'
);
ALTER TABLE `db_blog`.`t_blog` ADD FOREIGN KEY (`type_id`) REFERENCES `db_blog`.`t_blogtype`(`blogtype_id`);
create table t_comment
(
    comment_id int(11) not null auto_increment primary key,
    user_ip    varchar(50) comment '用户ip',
    article_id int(11) comment '评论所属文章',
    content    varchar(1000),
    date       datetime comment '评论时间',
    state      int(11) comment '评论状态：0，未审核 1，审核通过 2. 审核不通过 ',
    user_id    int(11) comment '用户id'
);
ALTER TABLE `db_blog`.`t_comment` ADD FOREIGN KEY (`article_id`) REFERENCES `db_blog`.`t_blog`(`article_id`);
ALTER TABLE `db_blog`.`t_comment` ADD FOREIGN KEY (`user_id`) REFERENCES `db_blog`.`t_user`(`user_id`);

create table t_link
(
    link_id   int(11) not null auto_increment primary key,
    link_name varchar(100),
    profile   varchar(500),
    image_url varchar(500),
    url       varchar(500),
    order_no  int(11)
);
