create database lemming;
use lemming;
create table exam_info
(
    id      int            not null
        primary key,
    title   text           not null,
    options text           not null comment '选项之间用英文分号";"隔开',
    answer  int            not null comment '正确答案在options中的索引位置，从0开始',
    score   int default 10 not null,
    active  int default 1  not null,
    constraint exam_id_uindex
        unique (id)
);

create table user_group
(
    id            int                  not null
        primary key,
    name          varchar(20)          not null,
    allow_post    tinyint(1) default 1 not null comment '允许发帖',
    allow_comment tinyint(1) default 1 not null comment '允许评论',
    allow_admin   tinyint(1) default 0 not null comment '允许删除其他人的帖子',
    allow_audit   tinyint(1) default 0 not null comment '允许参与审核工作',
    constraint user_group_id_uindex
        unique (id),
    constraint user_group_name_uindex
        unique (name)
)
    comment '用户组信息表';

create table user
(
    id                int auto_increment
        primary key,
    user_name         varchar(20)   not null,
    group_id          int default 0 null,
    phone             char(11)      null,
    sex               char(2)       null,
    registration_time datetime      not null comment '注册时间',
    email             varchar(20)   null,
    user_password     varchar(20)   not null,
    user_condition    int default 1 null,
    img_filename      varchar(100)  null,
    constraint user_email_uindex
        unique (email),
    constraint user_id_uindex
        unique (id),
    constraint user_user_name_uindex
        unique (user_name),
    constraint FK_user
        foreign key (group_id) references user_group (id)
)
    comment '用户数据表';

create table post_info
(
    id             int auto_increment
        primary key,
    title          varchar(50)   not null comment '标题',
    brief          varchar(100)  null comment '简介',
    image_filename varchar(100)  null comment '封面路径',
    post_filename  varchar(100)  not null comment '文章内容路径',
    read_num       int default 0 not null comment '阅读量',
    like_num       int default 0 not null comment '点赞数',
    user_id        int           not null comment '发布人',
    post_time      datetime      null comment '发布时间',
    constraint post_info_id_uindex
        unique (id),
    constraint post_info_post_uindex
        unique (post_filename),
    constraint FK_post_info
        foreign key (user_id) references user (id)
);

create table discuss_info
(
    id       int auto_increment
        primary key,
    post_id  int                                not null comment '被评论的文章ID',
    discuss  varchar(200)                       not null comment '评论内容',
    like_num int      default 0                 not null comment '点赞数',
    user_id  int                                not null comment '发出评论的用户ID',
    reg_time datetime default CURRENT_TIMESTAMP not null comment '发出此条评论的时间',
    constraint FK_discuss_info
        foreign key (post_id) references post_info (id),
    constraint FK_discuss_info_user
        foreign key (user_id) references user (id)
)
    comment '评论信息';

# 插入基本数据
insert into user_group values (0,'游客',0,1,0,0);
insert into user_group values (1,'博主',1,1,0,0);
insert into user_group values (999,'管理员',1,1,1,1);