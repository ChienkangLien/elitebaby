drop schema if exists `elitebaby`;
create database `elitebaby`;
use `elitebaby`;


drop table if exists `MEMBER`;
create table `MEMBER` (
                          `USER_ID` int not null auto_increment comment '使用者編號' primary key,
                          `USER_EMAIL` varchar(50) not null comment '使用者信箱' unique,
                          `USER_NAME` varchar(20)  not null comment '使用者姓名',
                          `USER_PASSWORD` varchar(20) not null comment '使用者密碼',
                          `ADDRESS` varchar(50) not null comment '居住地址',
                          `PHONE_NUMBER` varchar(10)  not null  comment '手機號碼',
                          `EM_CONTACT` varchar(20) not null comment '緊急聯絡人',
                          `BIRTHDAY` date not null comment '出生日期',
                          `NICKNAME` varchar(10) not null comment '暱稱',
                          `PROFILE PHOTO` longblob comment '頭像'
) comment '會員資料表';


INSERT INTO MEMBER
(`USER_ID`,`USER_EMAIL`,`USER_NAME`,`USER_PASSWORD`,`ADDRESS`,`PHONE_NUMBER`,`EM_CONTACT`,`BIRTHDAY`,`NICKNAME`)
VALUES
    (1,'cho199001@yahoo.com.tw','秋成勳','cho123456','土城金城路三段208號','0933123123','邱爸爸0933999888','1990-01-27','阿勳'),
    (2,'fang790328@gmail.com','方大同','fang123456','林口文化三路二段239號','0937881356','方爸爸0933999888','1992-04-01','國字臉'),
    (3,'lirock00001@gmail.com','李石端','li123456','桃園蘆竹奉化路69號','0939200359','李爸爸0933999888','1986-12-26','馮迪索'),
    (4,'pigting8787@yahoo.com.tw','朱廷廷','pig123456','台北復興南路二段168號','0928346510','朱太太0933999888','1999-05-20','公子哥'),
    (5,'garylin8888@gmail.com','林蓋瑞','lin123456','台北仁愛路四段268號','0937093487','林老師0933999888','2003-07-16','爪哇哥');

drop table if exists `ADMINISTRATOR`;
create table `ADMINISTRATOR` (
                                 `ADMIN_ID` int not null auto_increment comment '管理員編號' primary key,
                                 `ADMIN_NAME` varchar(20) not null comment '管理員名稱',
                                 `ADMIN_ACCOUNT` varchar(20) not null comment '管理員帳號' unique,
                                 `ADMIN_PASSWORD` varchar(50) not null comment '管理員密碼',
                                 `ADMIN_PERMISSION` varchar(10) not null comment '管理員權限'
)comment '後臺管理員';


insert into ADMINISTRATOR
(`ADMIN_NAME`,`ADMIN_ACCOUNT`,`ADMIN_PASSWORD`,`ADMIN_PERMISSION`)
VALUES
    ('張哲瑋','eliteadmin101','elite105105','系統管理員'),
    ('連千港','eliteadmin102','elite105105','房況系統管理員'),
    ('林禹祐','eliteadmin103','elite105105','購物系統管理員'),
    ('姜儼軒','eliteadmin104','elite105105','網頁設計師'),
    ('林昱安','eliteadmin105','elite105105','討論區管理員');


drop table if exists `ROOM_TYPE`;
create table `ROOM_TYPE` (
                             `ROOM_TYPE_ID` int not null auto_increment comment '房型編號' primary key,
                             `ROOM_TYPE_NAME` varchar(20) not null unique comment '房型名稱',
                             `ROOM_PRICE` int not null comment '房間單價',
                             `ROOM_DESCRIPTION` text not null comment '房型描述',
                             `ROOM_CREATE_TIME` timestamp  not null default current_timestamp comment '房型建立時間戳記',
                             `ROOM_CHANGE_TIME` timestamp on update current_timestamp comment '房型修改時間戳記',
                             `ROOM_STATUS` varchar(20) not null comment '房型狀態'
) comment = '房型';

insert into `ROOM_TYPE`(`ROOM_TYPE_NAME`,`ROOM_PRICE`,`ROOM_DESCRIPTION`,`ROOM_STATUS`)
values
    ('尊爵房',5000,'<p>后樂大VIP房 / 親子房 / 18坪</p>','上架'),
    ('雅致房',4000,'<p>一房一廳設計 / 雙玄關設計 / 送餐檯與送餐燈</p>','下架'),
    ('經典房',3000,'<p>雙玄關設計 / 送餐檯與送餐燈</p>','上架');

drop table if exists `ROOM`;
create table `ROOM` (
                        `ROOM_ID` int not null auto_increment comment '房間編號' primary key,
                        `ROOM_TYPE_ID` int not null comment '房型編號',
                        `ROOM_NAME` varchar(20) not null comment '房間名稱' unique,
                        foreign key (`ROOM_TYPE_ID`) references `ROOM_TYPE` (`ROOM_TYPE_ID`)
) comment = '房間';

insert into `ROOM`(`ROOM_TYPE_ID`,`ROOM_NAME`)
values
    ('1','101房'),
    ('1','102房'),
    ('1','103房'),
    ('1','104房'),
    ('1','105房'),
    ('2','201房'),
    ('2','202房'),
    ('2','203房'),
    ('2','204房'),
    ('3','301房'),
    ('3','302房'),
    ('3','303房'),
    ('3','304房'),
    ('3','305房'),
    ('3','306房');

drop table if exists `ROOM_PHOTO`;
create table `ROOM_PHOTO` (
                              `ROOM_PHOTO_ID` int not null auto_increment comment '房型照片編號' primary key,
                              `ROOM_TYPE_ID` int not null comment '房型編號',
                              `ROOM_PHOTO` longblob comment '房型照片',
                              foreign key (`ROOM_TYPE_ID`) references `ROOM_TYPE` (`ROOM_TYPE_ID`)
) comment = '房型照片';

insert into `ROOM_PHOTO`(`ROOM_TYPE_ID`,`ROOM_PHOTO`)
values
    (1,null),
    (2,null),
    (3,null);


drop table if exists `ROOM_ORDER`;
create table `ROOM_ORDER` (
                              `ROOM_ORDER_ID` int not null auto_increment comment '訂單編號' primary key,
                              `ROOM_ID` int not null comment '房間編號',
                              `ORDER_START_DATE` date not null comment '入住日期',
                              `ORDER_END_DATE` date not null comment '退房日期',
                              `ORDER_CREATE_TIME` timestamp not null default current_timestamp comment '訂單建立時間戳記',
                              `ORDER_CHANGE_TIME` timestamp on update current_timestamp comment '訂單修改時間戳記',
                              `ORDER_RESIDENT` varchar(20) comment '入住人',
                              `ORDER_PRICE` int comment '訂單總額',
                              `ORDER_REMARK` text comment '訂單備註',
                              `USER_ID` int comment '使用者編號',
                              `ADMIN_ID` int comment '後臺管理員編號',
                              `ORDER_STATUS` varchar(20) not null comment '訂單狀態',
                              foreign key (`ROOM_ID`) references `ROOM` (`ROOM_ID`),
                              foreign key (`USER_ID`) references `MEMBER` (`USER_ID`),
                              foreign key (`ADMIN_ID`) references `ADMINISTRATOR` (`ADMIN_ID`)
) comment = '房型訂單';

insert into `ROOM_ORDER`(`ROOM_ID`,`ORDER_START_DATE`,`ORDER_END_DATE`,`ORDER_RESIDENT`,
                         `ORDER_PRICE`,`ORDER_REMARK`,`USER_ID`,`ADMIN_ID`,`ORDER_STATUS`)
values
    (1,'2023-02-13','2023-02-18',null,null,'維修馬桶',null,2,'關房單'),
    (12,'2023-02-16','2023-02-28',null,null,'更換床墊',null,3,'關房單'),
    (5,'2023-02-23','2023-03-08',null,null,'維修地板',null,1,'關房單'),
    (11,'2023-03-13','2023-03-14',null,null,'消毒',null,2,'關房單'),
    (10,'2023-02-28','2023-03-03',null,null,'空調保養',null,3,'關房單'),
    (2,'2023-02-13','2023-02-18','本人',25000,null,1,null,'客訂單'),
    (3,'2023-02-21','2023-02-28','老婆',35000,'不吃牛肉',1,null,'客訂單'),
    (13,'2023-02-19','2023-03-02','本人',33000,'床單每天都要更換',1,null,'客訂單'),
    (1,'2023-02-20','2023-02-28','老婆',40000,null,1,null,'客訂單'),
    (12,'2023-03-01','2023-03-18','其他',51000,'入住人是妹妹',1,null,'客訂單');


-- 消息種類
create table `NEWS_SORT` (
                             `SORT_ID` int not null auto_increment
                                 comment '種類編號'primary key,
                             `SORT_NAME` varchar(20) not null unique,
                             `STATUS` varchar(20) default 0
                                 comment '種類名稱'
) comment = '消息種類';

insert into `NEWS_SORT`(`SORT_NAME`)
values
    ('一般'),
    ('優惠'),
    ('其他');

-- 最新消息--

drop table if exists `LATEST_NEWS`;
CREATE TABLE `LATEST_NEWS` (
                               `NEWS_ID` int not null auto_increment
                                   comment '最新消息編號'PRIMARY KEY,
                               `SORT_ID` int not null
                                   comment '種類編號',
                               `ADMIN_ID` int DEFAULT NULL
                                   comment '管理員編號',
                               `NEWS_INTRO` text not null
                                   comment '消息內容',
                               `PUBLISHED_TIME` date default null
                                   comment '排程時間',
                               `ON_NEWS` date not null
                                   comment '上架時間',
                               `OFF_NEWS` date default null
                                   comment '下架時間',
                               `POST_TITLE` varchar(50) not null
                                   comment '標題名稱',
                               `STATUS` varchar(20) default 0,
                               foreign key (`SORT_ID`) references `NEWS_SORT` (`SORT_ID`),
                               foreign key (`ADMIN_ID`) references `ADMINISTRATOR` (`ADMIN_ID`)
)comment = '最新消息';



insert into
    `LATEST_NEWS`(`SORT_ID`,`ADMIN_ID`,`NEWS_INTRO`,`ON_NEWS`,`OFF_NEWS`,`POST_TITLE`,`PUBLISHED_TIME`)
values
    (1,1,'婦女節','2023-02-18','2023-12-31','歡慶婦女節','2022-04-22 10:34:23'),
    (2,2,'優惠','2023-02-11','2023-12-31','慶祝元旦','2022-04-22 10:34:23'),
    (3,3,'其他','2023-08-1','2023-12-31','88節快樂','2022-04-22 10:34:23');

-- 消息照片--
create table `NEWS_PHOTO` (
                              `PHOTO_ID` int not null auto_increment
                                  comment '照片編號'PRIMARY KEY,
                              `NEWS_ID` int not null  comment '消息編號',
                              `NEWS_PHOTO` longblob comment '消息照片',
                              `STATUS` varchar(20) default 0,
                              foreign key (`NEWS_ID`) references `LATEST_NEWS` (`NEWS_ID`)
) comment = '消息照片';

insert into `NEWS_PHOTO`(`NEWS_ID`,`NEWS_PHOTO`)
values
    (1,null),
    (2,null),
    (3,null);



-- 消息留言--
create table `NEWS_MESSAGE` (
                                `NEW_MESSAGE_ID` int not null auto_increment
                                    comment '消息留言編號'PRIMARY KEY,
                                `USER_ID` int not null  comment '使用者編號' ,
                                `MESSAGE_CONTENT` text not null comment '留言內容',
                                `CONTENT_TIME` timestamp default current_timestamp comment '留言時間',
                                `NEWS_ID` int not null comment '最新消息編號',
                                `LIKE_ID` int default 0 not null comment '按讚總數',
                                `STATUS` varchar(20) default 0,
                                foreign key (`USER_ID`) references `MEMBER` (`USER_ID`),
                                foreign key (`NEWS_ID`) references `LATEST_NEWS` (`NEWS_ID`)
) comment = '消息留言';

insert into `NEWS_MESSAGE`(`USER_ID`,`MESSAGE_CONTENT`,`NEWS_ID`)
values
    (1,'頭好痛',1),
    (2,'乾!頭好痛',2),
    (3,'乾!!頭好痛!!!!!',3);

--  --  留言按讚--
create table `MESSAGE_LIKE` (
                                `MESSAGE_LIKE_ID` int not null auto_increment
                                    comment '留言按讚編號'PRIMARY KEY,
                                `USER_ID` int not null  comment '使用者編號' ,
                                `STATUS` varchar(20) default 0,
                                foreign key (`USER_ID`) references `MEMBER` (`USER_ID`)
) comment = '留言按讚';


insert into `MESSAGE_LIKE`(`USER_ID`)
values
    (1),
    (2),
    (3);



drop table if exists `MEAL`;
create table `MEAL` (
                        `MEAL_ID` int not null auto_increment comment '餐點編號' primary key,
                        `MEAL_NAME` varchar(20) not null comment '餐點名稱',
                        `MEAL_PIC` Longblob comment '餐點照片',
                        `MEAL_QUANTITY` int not null comment '餐點數量',
                        `MEAL_PRICE` int not null comment '餐點價格',
                        `RESERVE_PRICE` int not null comment '預約試吃價格',
                        `MEAL_STATUS` int not null default 0 comment '餐點狀態'
) comment = '月子膳食';

insert into `MEAL`(`MEAL_NAME`,`MEAL_QUANTITY`,`MEAL_PRICE`,`RESERVE_PRICE`)
values
    ('頂級月子餐',123,699,299),
    ('月芽養生月子餐',45,599,249),
    ('尊爵月子餐',0,499,200),
    ('芽果膳食月子餐月子餐',11,1099,399);

drop table if exists `RESERVER_SAMPLE`;
create table `RESERVER_SAMPLE` (
                                   `RESERVER_ID` int not null auto_increment comment '試吃訂單編號' primary key,
                                   `USER_ID` int not null comment '會員編號',
                                   `RESERVER_DATE` varchar(50) not null comment '預約時間',
                                   `CREATE_TIME` timestamp  not null default current_timestamp comment '訂單建立時間',
                                   `RESERVER_STATUS` int not null default 1 comment '預約狀態',
                                   foreign key (`USER_ID`) references `MEMBER` (`USER_ID`)
) comment = '預約試吃訂單';

insert into `RESERVER_SAMPLE`(`USER_ID`,`RESERVER_DATE`)
values
    (1,'2023-02-05 14:00:00'),
    (2,'2023-02-06 14:00:00'),
    (3,'2023-02-06 14:00:00');


drop table if exists `RESERVER_SAMPLE_DETAIL`;
create table `RESERVER_SAMPLE_DETAIL` (
                                          `RESERVER_DATAIL_ID` int not null auto_increment comment '試吃訂單明細編號' primary key,
                                          `RESERVER_ID` int not null comment '試吃訂單編號',
                                          `MEAL_ID` int not null comment '餐點編號',
                                          `HEADCOUNT` int not null comment '人數',
                                          foreign key (`RESERVER_ID`) references `RESERVER_SAMPLE` (`RESERVER_ID`),
                                          foreign key (`MEAL_ID`) references `MEAL` (`MEAL_ID`)
) comment = '預約試吃訂單明細';

insert into `RESERVER_SAMPLE_DETAIL`(`RESERVER_ID`,`MEAL_ID`,`HEADCOUNT`)
values
    (1,1,2),
    (2,2,1),
    (3,3,2);


drop table if exists `MEAL_ORDER`;
create table `MEAL_ORDER` (
                              `MEAL_ORDER_ID` int not null auto_increment comment '訂單編號' primary key,
                              `USER_ID` int not null comment '會員編號',
                              `ORDER_PAYMENT` int not null comment '付款方式',
                              `ORDER_STATUS` varchar(20) not null default 0 comment '訂單狀態',
                              `ORDER_DATE` timestamp  not null default current_timestamp comment '訂單建立時間',
                              `ORDER_NOTES` text comment '備註 ',
                              foreign key (`USER_ID`) references `MEMBER` (`USER_ID`)
) comment = '膳食訂單';

insert into `MEAL_ORDER`(`USER_ID`,`ORDER_PAYMENT`,`ORDER_NOTES`)
values
    (1,1,'無'),
    (2,2,null),
    (3,3,null);


drop table if exists `MEAL_ORDER_DETAIL`;
create table `MEAL_ORDER_DETAIL` (
                                     `MEAL_ORDER_DETAIL_ID` int not null auto_increment comment '訂單明細編號' primary key,
                                     `MEAL_ORDER_ID` int not null comment '訂單編號',
                                     `MEAL_ID` int not null comment '餐點編號',
                                     `ORDER_COUNT` int not null comment '數量',

                                     foreign key (`MEAL_ORDER_ID`) references `MEAL_ORDER` (`MEAL_ORDER_ID`),
                                     foreign key (`MEAL_ID`) references `MEAL` (`MEAL_ID`)
) comment = '膳食訂單明細';

insert into `MEAL_ORDER_DETAIL`(`MEAL_ORDER_ID`,`MEAL_ID`,`ORDER_COUNT`)
values
    (1,1,2),
    (2,2,4),
    (3,3,6);







drop table if exists `ROOM_VISIT`;
create table `ROOM_VISIT` (
                              `VISIT_ID` int not null auto_increment comment '預約編號' primary key,
                              `USER_ID` int not null comment '使用者編號',
                              `USER_NAME` varchar(20)  not null comment '姓名',
                              `PHONE_NUMBER` varchar(20) not null comment '手機號碼',
                              `CONTECT_TIME` varchar(50) not null comment '聯絡時間',
                              `DUE_DATE` date not null  comment '預產期',
                              `EMAIL` varchar(50)  not null  comment '信箱',
                              `KIDS` varchar(20) not null comment '胎次',
                              `VISIT_TIME` date not null comment '參訪時間',
                              `REMARK` text  comment '備註內容',
                              `VISIT_STATUS` int not null default 0 comment '參觀狀態',
                              `CONTACT_STATUS` int not null default 0 comment '聯絡狀態',
                              `CREATE_TIME` timestamp not null default current_timestamp comment '建立時間',
                              foreign key (`USER_ID`) references `MEMBER` (`USER_ID`)
) comment '預約參觀';


insert into `ROOM_VISIT`(`USER_ID`,`USER_NAME`,`PHONE_NUMBER`,`CONTECT_TIME`,`DUE_DATE`,`EMAIL`,`KIDS`,`VISIT_TIME`,`REMARK`)
values(1,'姜儼軒','09939183',"下午1點-6點",'2023-04-08','sam232142@y','2','2023-04-08','我是備註1'),
      (2,'吳敦義','09938712',"下午1點-6點",'2023-06-18','sam232142@g','1','2023-10-21','我是備註2'),
      (3,'蔡英文','095939293',"下午1點-6點",'2023-03-29','sam232142@y','3','2023-07-08','我是備註3'),
      (4,'馬英九','09949301',"下午1點-6點",'2023-04-12','sam232142@d','5','2023-04-18','我是備註4'),
      (5,'柯文哲','09984892',"下午1點-6點",'2023-05-28','sam232142@q','1','2023-012-08','我是備註5');



drop table if exists `REPORT_CATEGORY`;
create table `REPORT_CATEGORY` (
                                   `CATEGORY_ID` int not null auto_increment comment '類別編號' primary key,
                                   `REPORT_CATEGORY` varchar(50) not null comment '類別名稱'
) comment '回報類別';

insert into `REPORT_CATEGORY`(`REPORT_CATEGORY`)
values  ("訂房"),
        ("參訪"),
        ("訂餐"),
        ("文章"),
        ("消息"),
        ("會員"),
        ("試吃"),
        ("其他");



drop table if exists `REPORT_MAIL`;
create table `REPORT_MAIL` (
                               `MAIL_ID` int not null auto_increment comment '信件編號' primary key,
                               `USER_ID` int not null comment '使用者編號',
                               `CATEGORY_ID` int  not null comment '類別編號',
                               `ADMIN_ID` int  comment '後台管理員編號',
                               `REPORT_TITLE` varchar(50) not null comment '回報標題',
                               `REPORT_CONTENT` text not null comment '回報內容',
                               `REPORT_CREATE_TIME` timestamp  not null default current_timestamp comment '回報發送時間',
                               `ANSWER_CONTENT` text  comment '回覆內容',
                               `ANSWER_CREATE_TIME` timestamp  comment '回覆發送時間',
                               `ANSWER_TITLE` varchar(50)  comment '回覆標題',
                               `AUTH_CODE` varchar(50) not null UNIQUE comment '隨機亂碼',
                               foreign key (`USER_ID`) references `MEMBER` (`USER_ID`),
                               foreign key (`CATEGORY_ID`) references `REPORT_CATEGORY` (`CATEGORY_ID`),
                               foreign key (`ADMIN_ID`) references `ADMINISTRATOR` (`ADMIN_ID`)
) comment '用戶/員工回報回覆信件';

-- 測試有回報沒回覆
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`ADMIN_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`)
values(1,'3','1',"你好我是使用者回報",'djsajdjadkjkjwadjakjdksajdka',"eka29ek");
-- 測試管理員回覆信件
update REPORT_MAIL
set `ANSWER_CONTENT` = 'dsadsadsadsadsadsadsadsa'
where MAIL_ID = 1;

update REPORT_MAIL
set `ANSWER_CREATE_TIME` = current_time()
where MAIL_ID = 1;

update REPORT_MAIL
set `ANSWER_TITLE` = '你好我是管理員回覆'
where MAIL_ID = 1;


drop table if exists `REPORT_MAIL_IMG`;
create table `REPORT_MAIL_IMG` (
                                   `RIMG_ID` int not null auto_increment comment '回報圖片編號' primary key,
                                   `AUTH_CODE` varchar(50) not null comment '隨機亂碼',
                                   `REPORT_IMG` longblob  comment '回報信件圖片',
                                   foreign key (`AUTH_CODE`) references `REPORT_MAIL` (`AUTH_CODE`)
) comment '回報信件的圖片';

insert into `REPORT_MAIL_IMG`(`AUTH_CODE`,`REPORT_IMG`)
values
    ("eka29ek",null),
    ("eka29ek",null),
    ("eka29ek",null);


drop table if exists `ANSWER_MAIL_IMG`;
create table `ANSWER_MAIL_IMG` (
                                   `AIMG_ID` int not null auto_increment comment '回覆圖片編號' primary key,
                                   `AUTH_CODE` varchar(50) not null comment '隨機亂碼',
                                   `ANSWER_IMG` longblob  comment '回覆信件圖片',
                                   foreign key (`AUTH_CODE`) references `REPORT_MAIL` (`AUTH_CODE`)
) comment '回覆信件的圖片';

insert into `ANSWER_MAIL_IMG`(`AUTH_CODE`,`ANSWER_IMG`)
values
    ("eka29ek",null),
    ("eka29ek",null),
    ("eka29ek",null);


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

DROP TABLE IF EXISTS post_imgs;
create table post_imgs
(
    id      int auto_increment
        primary key,
    post_id int      not null,
    img     longblob not null,
    constraint post_imgs_post_post_id_fk
        foreign key (post_id) references elitebaby.post (post_id)
);

DROP TABLE IF EXISTS msg_imgs;
create table elitebaby.msg_imgs
(
    id      int auto_increment
        primary key,
    msg_id int      not null,
    img     longblob not null,
    constraint msg_imgs_msg_msg_id_fk
        foreign key (msg_id) references elitebaby.msg (msg_id)
);