DROP TABLE IF EXISTS category;
create table category
(
    id       int auto_increment
        primary key,
    category varchar(50)  not null,
    img      varchar(200) not null,
    level    int          not null,
    constraint category
        unique (category),
    constraint checklevel
        check (`level` in (0, 1, 2))
);

insert into category (category, img, level)
VALUES ('育嬰', 'fa-solid fa-baby', 0),
       ('出遊', 'fa-solid fa-plane-departure', 0),
       ('花費', 'fa-solid fa-comments-dollar', 0),
       ('寵物', 'fa-solid fa-dog', 0),
       ('健康', 'fa-solid fa-briefcase-medical', 0),
       ('財經', 'fa-solid fa-money-bill-trend-up', 1),
       ('房地產', 'fa-solid fa-house-building', 1),
       ('好康福利','fa-solid fa-venus-mars',1),
       ('會員專區', 'fa-solid fa-money-check-dollar', 2);


DROP TABLE IF EXISTS post;
create table post
(
    post_id  int auto_increment
        primary key,
    user_id  int                                 not null,
    category varchar(50)                         not null,
    topic    varchar(50)                         not null,
    content  text                                not null,
    timing   timestamp default CURRENT_TIMESTAMP null,
    constraint post_category_category_fk
        foreign key (category) references elitebaby.category (category),
    constraint post_member_id_fk
        foreign key (user_id) references elitebaby.member (USER_ID)
);

create index post_category_index
    on elitebaby.post (category);

-- insert into post (user_id, category, topic, content)
-- VALUES (1, '健康', '多吃蔬菜有益健康', ' '),
--        (2, '出遊', '蜜月旅行攻略', ' '),
--        (3, '寵物', '學步期小孩和寵物！安全共處必知要點', ' '),
--        (4, '健康', '健康好吃的兒童食品', ' '),
--        (5, '出遊', '免費消耗小孩電力哪裡去？', ' '),
--        (3, '育嬰', '如何協助孩子長得又高又健康', ' '),
--        (1, '花費', '請問大家的育嬰開銷?', ' '),
--        (4, '財經', '育嬰補貼申請懶人包', ' '),
--        (5, '健康', '小孩逃避青菜怎麼辦?', ' '),
--        (1, '出遊', '今年夏季旅遊推薦', ' '),
--        (2, '寵物', '談談狗狗與新生兒的相處之道', ' '),
--        (5, '健康', '嬰兒食品大開箱', ' '),
--        (3, '出遊', '帶小孩旅行很辛苦嗎?', ' '),
--        (1, '育嬰', '孩子的學習不能等?', ' '),
--        (5, '花費', '哪牌的尿布CP值最高?', ' '),
--        (4, '財經', '小孩長得很醜', ' '),
--        (1, '房地產', '高房價造成生育率創新低?', ' '),
--        (2, '房地產', '央行預測下半年減緩升息', ' '),
--        (2, '會員專區', 'elitebaby會員獨享', ' ');


DROP TABLE IF EXISTS post_like;
create table post_like
(
    like_id int auto_increment
        primary key,
    post_id int not null,
    user_id int not null,
    constraint post_like_pk
        unique (post_id, user_id),
    constraint post_like_member_USER_ID_fk
        foreign key (user_id) references member (USER_ID),
    constraint postlike_post_post_id_fk
        foreign key (post_id) references post (post_id)
);

DROP TABLE IF EXISTS msg;
create table msg
(
    msg_id  int auto_increment
        primary key,
    user_id int                                 not null,
    post_id int                                 not null,
    content text                                not null,
    timing  timestamp default CURRENT_TIMESTAMP not null,
    constraint msg_member_USER_ID_fk
        foreign key (user_id) references elitebaby.member (USER_ID),
    constraint msg_post_post_id_fk
        foreign key (post_id) references elitebaby.post (post_id)
);



DROP TABLE IF EXISTS msg_like;
create table msg_like
(
    like_id int auto_increment
        primary key,
    msg_id int not null,
    user_id int not null,
    constraint msg_like_pk
        unique (msg_id, user_id),
    constraint msg_like_member_USER_ID_fk
        foreign key (user_id) references member (USER_ID),
    constraint msglike_msg_msg_id_fk
        foreign key (msg_id) references msg (msg_id)
);



delete
from msg
where 1 = 1;
alter table msg
    auto_increment = 0;
delete
from post_like
where 1 = 1;
alter table post_like
    auto_increment = 0;
delete
from post
where 1 = 1;
alter table post
    auto_increment = 0;

DROP TABLE IF EXISTS collection_category;
create table collection_category
(
    id          int auto_increment
        primary key,
    user_id     int not null,
    category_id int not null,
    constraint collection_category_uk
        unique (user_id, category_id),
    constraint collection_category_category_id_fk
        foreign key (category_id) references elitebaby.category (id),
    constraint collection_category_member_USER_ID_fk
        foreign key (user_id) references elitebaby.member (USER_ID)
);