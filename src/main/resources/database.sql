drop schema if exists `elitebaby`;
create database `elitebaby`;
use `elitebaby`;


drop table if exists `MEMBER`; 
create table `MEMBER` (
	`USER_ID` int not null auto_increment comment '使用者編號' primary key,
	`USER_EMAIL` varchar(50) not null comment '使用者信箱' unique,
	`USER_NAME` varchar(20)  not null comment '使用者姓名',
    `USER_PASSWORD` text not null comment '使用者密碼',
    `ADDRESS` varchar(50) not null comment '居住地址',
    `PHONE_NUMBER` varchar(10)  not null  comment '手機號碼'
) comment '會員資料表';

    
    INSERT INTO MEMBER
	(`USER_EMAIL`,`USER_NAME`,`USER_PASSWORD`,`ADDRESS`,`PHONE_NUMBER`)
VALUES
	('cho199001@yahoo.com.tw','秋成勳','cho123456','土城金城路三段208號','0933123123'),
	('fang790328@gmail.com','方大同','fang123456','林口文化三路二段239號','0937881356'),
	('lirock00001@gmail.com','李石端','li123456','桃園蘆竹奉化路69號','0939200359'),
	('pigting8787@yahoo.com.tw','朱廷廷','pig123456','台北復興南路二段168號','0928346510'),
	('garylin8888@gmail.com','林蓋瑞','lin123456','台北仁愛路四段268號','0937093487'),
	('liwei1888@gmail.com','林帆','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei2888@gmail.com','林奕帆','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei3888@gmail.com','吳奕帆','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei4888@gmail.com','蕭奕帆','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei5888@gmail.com','黃奕帆','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei6888@gmail.com','洪力維','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei7888@gmail.com','石力維','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei8888@gmail.com','黃力維','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei9888@gmail.com','林力維','lin123456','台北仁愛路四段268號','0937093487'),
    ('liwei0888@gmail.com','蕭力維','lin123456','台北仁愛路四段268號','0937093487');


drop table if exists `ADMINISTRATOR`;  
create table `ADMINISTRATOR` (
`ADMIN_ID` int not null auto_increment comment '管理員編號' primary key,
`ADMIN_NAME` varchar(20) not null comment '管理員名稱',
`ADMIN_ACCOUNT` varchar(50) not null comment '管理員帳號' unique,
`ADMIN_PASSWORD` text not null comment '管理員密碼',
`ADMIN_PERMISSION` varchar(10) not null comment '管理員權限'
)comment '後臺管理員';


insert into ADMINISTRATOR
	(`ADMIN_NAME`,`ADMIN_ACCOUNT`,`ADMIN_PASSWORD`,`ADMIN_PERMISSION`)
VALUES
	('張哲瑋','eliteadmin101','elite105105','系統管理員'),
    ('連千港','eliteadmin102','elite105105','房況系統管理員'),
    ('林禹祐','eliteadmin103','elite105105','購物系統管理員'),
    ('姜儼軒','eliteadmin104','elite105105','網頁設計師'),
    ('林昱安','eliteadmin105','elite105105','討論區管理員'),
    ('周玟廷','eliteadmin106','elite105105','資訊更新管理員');   
 
 
 
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

INSERT INTO `ROOM_TYPE` VALUES (1,'尊爵房',4000,'<p>后悅VIP房 / 15坪</p><p>一房一廳設計 / 雙玄關設計 / 送餐檯與送餐燈</p><p>QUEEN SIZE獨立筒舒適乳膠床墊 / 防螨抗菌寢具組</p><p>TOTO衛浴設備 / 五合一暖風機 / 乾濕分離淋浴間</p><p>大金全熱交換系統 / 獨立變頻冷暖空調</p><p>雙人哺乳型沙發 / COTEX哺乳枕 / 單人哺乳沙發腳椅組</p><p>奶瓶蒸氣烘乾消毒鍋 / 雙邊電動吸乳器(AVENT、貝瑞克、美樂)</p><p>全館UV過濾殺菌淨水設備</p><p>100%純棉哺乳衣 / 歐洲進口齊葉雅天然橄欖沐浴膠、洗髮精組 / 白因子潔手慕斯</p><p>LG大白智能空氣清淨機</p>','2023-03-15 08:41:28','2023-03-15 08:42:02','上架'),(2,'雅致房',3000,'<p>后愛麗緻房 / 10坪</p><p>雙玄關設計 / 送餐檯與送餐燈</p><p>QUEEN SIZE獨立筒舒適乳膠床墊 / 防螨抗菌寢具組</p><p>TOTO衛浴設備 / 五合一暖風機 / 乾濕分離淋浴間</p><p>大金全熱交換系統 / 獨立變頻冷暖空調</p><p>雙人哺乳型沙發 / COTEX哺乳枕</p><p>奶瓶蒸氣烘乾消毒鍋 / 雙邊電動吸乳器(AVENT、貝瑞克、美樂)</p><p>全館UV過濾殺菌淨水設備</p><p>100%純棉哺乳衣 / 歐洲進口齊葉雅天然橄欖沐浴膠、洗髮精組 / 白因子潔手慕斯</p>','2023-03-15 08:42:25',NULL,'下架');

drop table if exists `ROOM`;
create table `ROOM` (
                        `ROOM_ID` int not null auto_increment comment '房間編號' primary key,
                        `ROOM_TYPE_ID` int not null comment '房型編號',
                        `ROOM_NAME` varchar(20) not null comment '房間名稱' unique,
                        foreign key (`ROOM_TYPE_ID`) references `ROOM_TYPE` (`ROOM_TYPE_ID`)
) comment = '房間';

INSERT INTO `ROOM` VALUES (1,1,'101房'),(2,1,'102房'),(3,1,'103房'),(4,2,'201房'),(5,2,'202房');

drop table if exists `ROOM_PHOTO`;
create table `ROOM_PHOTO` (
                              `ROOM_PHOTO_ID` int not null auto_increment comment '房型照片編號' primary key,
                              `ROOM_TYPE_ID` int not null comment '房型編號',
                              `ROOM_PHOTO` longblob not null comment '房型照片',
                              foreign key (`ROOM_TYPE_ID`) references `ROOM_TYPE` (`ROOM_TYPE_ID`)
) comment = '房型照片';



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
	
    (1,'2023-01-21','2023-01-29','其他',32000,null,5,null,'完成單'),
    (2,'2023-01-21','2023-01-28','其他',28000,'幫我朋友下訂',3,null,'完成單'),
    (1,'2023-01-11','2023-01-17','其他',24000,'入住人是妹妹',1,null,'完成單'),
    (2,'2023-01-11','2023-01-18','其他',28000,'姐姐叫素琴',4,null,'完成單'),
    (1,'2023-02-11','2023-02-14','其他',24000,'入住人是堂妹',2,null,'完成單'),
    (2,'2023-02-10','2023-02-18','其他',32000,null,4,null,'完成單'),
    (1,'2023-02-21','2023-02-28','其他',28000,'媳婦對海鮮過敏',2,null,'客訂單'),
    (2,'2023-02-21','2023-02-28','其他',28000,'null',2,null,'完成單'),
    (2,'2023-03-01','2023-03-03',null,null,'空調保養',null,3,'關房單'),
    (1,'2023-03-13','2023-03-14',null,null,'消毒',null,2,'關房單'),
    (2,'2023-03-16','2023-03-17',null,null,'更換床墊',null,3,'關房單'),
    (3,'2023-03-28','2023-03-29',null,null,'消毒',null,1,'關房單'),
    (1,'2023-04-13','2023-04-18',null,null,'維修浴缸',null,2,'關房單'),
    (1,'2023-03-20','2023-04-09','老婆',80000,null,1,null,'客訂單'),
    (1,'2023-04-21','2023-04-28','老婆',28000,'不吃牛肉',2,null,'客訂單'),
    (2,'2023-04-21','2023-04-28','老婆',28000,'鍋邊素食',3,null,'客訂單'),
    (2,'2023-03-19','2023-04-08','本人',80000,'床單每天都要更換',4,null,'客訂單');



-- 消息種類
 DROP TABLE IF EXISTS NEWS_SORT;
create table NEWS_SORT (
SORT_ID int not null auto_increment
  comment '種類編號'primary key, 
 SORT_NAME varchar(20) not null unique,
    STATUS varchar(20) default 0
  comment '種類名稱'
) comment = '消息種類';

insert into `NEWS_SORT`(`SORT_NAME`)
values
 ('綜合'),
 ('一般'),
    ('優惠');


 -- 最新消息-- 
 DROP TABLE IF EXISTS LATEST_NEWS;
CREATE TABLE LATEST_NEWS (
  NEWS_ID int not null auto_increment
  comment '最新消息編號'PRIMARY KEY,
  SORT_ID int not null
  comment '種類編號',
  ADMIN_ID int DEFAULT NULL
  comment '管理員編號',
  NEWS_INTRO text not null
  comment '消息內容',
  SCHEDULED_TIME date not null
  comment '上架時間',
  POST_TITLE varchar(50) not null
  comment '標題名稱',
  NEWS_PHOTO longblob comment '消息照片',
  foreign key (`SORT_ID`) references NEWS_SORT (`SORT_ID`),
    foreign key (`ADMIN_ID`) references ADMINISTRATOR (`ADMIN_ID`)
  )comment = '最新消息';
  
insert into
`LATEST_NEWS`(`SORT_ID`,`ADMIN_ID`,`NEWS_INTRO`,`SCHEDULED_TIME`,`POST_TITLE`,`NEWS_PHOTO`)
values
(1,1,'櫃檯客服正常營業
 09:30 ~ 19:30,10F咖啡廳
 1/21(六) ~ 1/26(四)：公休
 1/27(五) 起正常營業
 美麗人生預祝您
 鴻 兔 大 展 一 整 年','2023-02-18','春節營業時間',null),
(1,2,'各位來到美麗人生的爸比媽咪、大朋友小朋友，
                        為了因應武漢肺炎的防疫與保護所有的媽咪與寶貝們，進入館前請配合以下幾點事項唷：
                        ☑開放實體參觀。
                        ☑入本機構須 實名制登記、體溫量測、酒精消毒及全程配戴口罩 。
                        ☑會客時間10:00~18:00。
                        訪客限制為一天兩次(每次限半小時)每次限三人(不限對象)，
                        一律必須施打經政府核准合格之疫苗三劑(需出示小黃卡或健保APP查閱，大寶不在此限)，
                        訪客時間僅限於嬰兒室外看寶寶(七歲以下不可至住房樓層)。
                        ☑ 宿者如非先生者，一律比照訪客標準，必須施打三劑經政府核准合格之疫苗。
                        ☑ 剃頭、新生兒攝影、泌乳師、美甲美睫、塑身衣及整椎廠商相關之業務暫停，僅提供洗頭服務。
                        ☑ 十樓咖啡廳僅提供外帶服務。
                        ❤ 請大家與美麗人生一起守護自己最愛的家人 ❤','2023-02-18','新型冠狀病毒防疫公告',null),
(2,3,'3/3是日本女兒節，3/8是婦女節，3/8在這裡有日式婦女節，西瓜和服和鳳梨和服，大飽眼福
                        還有隱藏版的明星目前入住在館內喲!
                        櫻花樹目前還在館內，賞櫻趁現在','2023-02-20','日式婦女節',null),
(2,1,'美麗人生 祈福迎春
	  懷著希望，迎接嶄新的一年
	  祈求新的一年福氣降臨在每位媽咪身上
	  媽咪今年的新希望是什麼呢?
      美麗人生讓媽咪福上加福
	  凡參與祈福迎春活動並完成指定任務，就有機會獲得新春福袋！
	  快將心裡的期許化作文字，並分享給我們吧~','2023-02-18','新環境公告',null),
(3,2,'在千萬人海中相遇、相戀,並攜手創造新生活是人生重要的抉擇之一
                        如同爸比媽咪在重要的產後時光選擇了美麗人生
                        今天我們特地準備了甜甜的小驚喜送給爸比媽咪
                        在回家與寶貝創造更多回憶之前
                        就先將寶寶交給嬰兒室！安心享受兩人時光','2023-02-01','2/14情人節',null),
(3,3,'元旦來臨，祝福聲聲響起
                        願快樂常伴左右，好運平安在前後
                        2023，也要全力以赴的讓自己開心
                        美麗人生伴媽咪們開啟新的一年
                        以手作的溫度帶給您溫暖，分享幸運禮袋','2022-12-31','元旦來臨，祝福聲聲響起',null);
    
    -- 消息留言-- 
    DROP TABLE IF EXISTS NEWS_MESSAGE;
   create table NEWS_MESSAGE (
NEWS_MESSAGE_ID int not null auto_increment
  comment '消息留言編號'PRIMARY KEY,
 USER_ID int not null  comment '使用者編號' ,
 MESSAGE_CONTENT text not null comment '留言內容',
 CONTENT_TIME timestamp default current_timestamp comment '留言時間',
    NEWS_ID int not null comment '最新消息編號',
    foreign key (`USER_ID`) references MEMBER (`USER_ID`),
foreign key (`NEWS_ID`) references LATEST_NEWS (`NEWS_ID`)
) comment = '消息留言';

    insert into `NEWS_MESSAGE`(`USER_ID`,`MESSAGE_CONTENT`,`NEWS_ID`)
values
 (1,'',1),
    (2,'',2),
    (3,'',3);
    
 




DROP TABLE IF EXISTS access;
create table access
(
    user_id   int auto_increment
        primary key,
    user_name varchar(20) null,
    password  varchar(50) not null
);


DROP TABLE IF EXISTS category;
create table category
(
    id       int auto_increment
        primary key,
    category varchar(50)  not null,
    img      varchar(200),
    level    int,
    constraint category
        unique (category),
    constraint checklevel
        check (`level` in (0, 1, 2))
);

insert into category (category, img, level)
VALUES ('住房體驗', 'fa-solid fa-baby', 0),
       ('月子膳食', 'fa-solid fa-plane-departure', 0),
       ('家庭旅遊', 'fa-solid fa-comments-dollar', 0),
       ('寵物關係', 'fa-solid fa-dog', 0),
       ('育嬰知識', 'fa-solid fa-briefcase-medical', 0),
       ('養身食譜', 'fa-solid fa-money-bill-trend-up', 1),
       ('嬰兒睡眠', 'fa-solid fa-house-building', 1),
       ('產後恢復','fa-solid fa-venus-mars',1),
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






drop table if exists `MEAL`;  
create table `MEAL` (
	`MEAL_ID` int not null auto_increment comment '餐點編號' primary key,
	`MEAL_NAME` varchar(20) not null comment '餐點名稱',
	`MEAL_PIC` Longblob comment '餐點照片',
    `MEAL_PRICE` int not null comment '餐點價格',
	`MEAL_INFO` varchar(100) comment '餐點介紹',
    `MEAL_STATUS` int not null default 0 comment '餐點狀態'
) comment = '月子膳食';

insert into `MEAL`(`MEAL_NAME`,`MEAL_PRICE`, `MEAL_INFO`, `MEAL_STATUS`)
values
	('頂級月子餐',699,'有著多年月子餐經驗的專業主廚，率領五星級廚藝團隊，堅持古法熬製湯品，使用新鮮食材與薄鹽少油，加上天然的烹調，為您打造出道道精彩的美味月子飲食。', 1),
    ('月芽養生月子餐',599,'月芽聘請專業中醫師、營養師、護理師，以現代食品營養學角度討論研發配方，餐飲源自於最高規格、品質與新鮮食材，透過食療的方式調理孕、產婦體質、滋補養身、增強體力，讓下一代更健康與茁壯。', 1),
    ('尊爵月子餐',499,'健康鮮滋味，讓您營養好心情。雙主菜設計，補充優質蛋白質與多重營養素溫和漸進式調養，滿足現代人坐月子膳食需求。', 1),
    ('芽果膳食月子餐',1099,'秉持著『食藥同源』的觀念，月子期間的藥膳大多使用溫補的中藥材與食材搭配，烹調成美味又補身的佳餚，專為產後幫助恢復身體而設計。', 1),
	('靚媽咪月子餐',399,'結合了傳統的產後藥膳食補與現代營養學的概念，以大骨精燉的湯底來烹調出最適合產後媽媽食用的餐點。', 1),
	('藥膳月子餐',449,'食補、藥補、休息。藥膳月子餐是由獨家配方搭配頂級草本漢材之養生法精製而成，能讓妳快速達到恢復健康、回復身材的效果。', 1),
	('精緻月子餐',349,'針對產後所需提供營養、豐盛及多變化的菜色，把握產後黃金時期，由資深助產士及中、西醫師團隊來為您階段性調理。', 1);

drop table if exists `MEAL_ORDER`;   
create table `MEAL_ORDER` (
	`MEAL_ORDER_ID` int not null auto_increment comment '訂單編號' primary key,
	`USER_ID` int not null comment '會員編號',
	`ORDER_PAYMENT` int not null comment '付款方式',
    `ORDER_STATUS` varchar(20) not null default 0 comment '訂單狀態',
    `ADDRESS` varchar(100) not null comment '地址',
    `ORDER_DATE` timestamp  not null default current_timestamp comment '訂單建立時間',
    `AUTH_CODE` varchar(50) not null UNIQUE comment '隨機亂碼',
    foreign key (`USER_ID`) references `MEMBER` (`USER_ID`)
) comment = '膳食訂單';

insert into `MEAL_ORDER`(`USER_ID`,`ORDER_PAYMENT`, `ADDRESS`,`AUTH_CODE`)
values
	(1,1,'104台北市中山區南京東路三段219號5樓','12344321'),
    (2,1,'235新北市中和區中安街85號','aabbaabb'),
    (3,1,'236新北市土城區清水路78號','okokokok'),
	(1,1,'104台北市中山區南京東路三段219號5樓','hihihihi');
    
    
drop table if exists `MEAL_ORDER_DETAIL`;      
create table `MEAL_ORDER_DETAIL` (
	`MEAL_ORDER_DETAIL_ID` int not null auto_increment comment '訂單明細編號' primary key,
	`MEAL_ID` int not null comment '餐點編號',
    `ORDER_COUNT` int not null comment '數量',
	`AUTH_CODE` varchar(50) not null comment '隨機亂碼',
    foreign key (`AUTH_CODE`) references `MEAL_ORDER` (`AUTH_CODE`),
    foreign key (`MEAL_ID`) references `MEAL` (`MEAL_ID`)
) comment = '膳食訂單明細';

insert into `MEAL_ORDER_DETAIL`(`MEAL_ID`,`ORDER_COUNT`,`AUTH_CODE`)
values
	(1,1,'12344321'),
    (2,2,'aabbaabb'),
	(3,4,'aabbaabb'),
    (4,2,'okokokok'),
	(5,4,'okokokok'),
	(6,1,'okokokok'),
	(1,1,'hihihihi'),
	(3,2,'hihihihi'),
	(5,3,'hihihihi'),
	(7,4,'hihihihi');
    
    





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
values(1,'姜儼軒','0993918331',"早上9點-12點",'2023-04-08','sam232142@yahoo.com.tw','2','2023-04-08','JAVA好難'),
	  (1,'姜儼軒','0993918345',"早上9點-12點",'2023-04-01','sam232142@yahoo.com.tw','2','2023-04-08','JAVA好難'),
	  (2,'吳敦義','0993871232',"下午1點-6點",'2023-06-14','Jerry232142@google.com','1','2023-10-21','我是誰我在哪裡'),
      (2,'吳敦義','0993871221',"下午1點-6點",'2023-06-18','Jerry232142@google.com','1','2023-10-21','我是誰我在哪裡'),
      (3,'蔡英文','0959392932',"晚上7點-9點",'2023-03-29','David232142@yahoo.com.tw','3','2023-07-08','我終於要下台了,不用被罵了,YA!'),
      (3,'蔡英文','0959392934',"晚上7點-9點",'2023-03-29','David232142@yahoo.com.tw','3','2023-07-08','我終於要下台了,不用被罵了,YA!'),
      (4,'馬英九','0994930121',"下午1點-6點",'2023-04-13','BigWu8080@yahoo.com.tw','5','2023-04-18','我要參加三項鐵人4'),
	  (4,'馬英九','0994930127',"下午1點-6點",'2023-04-12','BigWu8080@yahoo.com.tw','5','2023-04-18','我要參加三項鐵人4'),
      (5,'柯文哲','0998489245',"早上9點-12點",'2023-05-25','AntomWu55688@yahoo.com.tw','1','2024-012-08','我要選總統請投我一票'),
      (5,'柯文哲','0998489209',"早上9點-12點",'2023-05-28','AntomWu55688@yahoo.com.tw','1','2024-012-08','我要選總統請投我一票'),
      (6,'賴清德','0998489212',"早上9點-12點",'2023-05-20','AntomWu55688@yahoo.com.tw','1','2024-012-08','我要選總統請投我一票');



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
	`DETERMINE` varchar(50) not null comment '判斷前後台發出',
    foreign key (`USER_ID`) references `MEMBER` (`USER_ID`),
    foreign key (`CATEGORY_ID`) references `REPORT_CATEGORY` (`CATEGORY_ID`),
    foreign key (`ADMIN_ID`) references `ADMINISTRATOR` (`ADMIN_ID`)
) comment '用戶/員工回報回覆信件';



insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(1,'3',"食物不新鮮",'  列寧講過一段耐人尋思的話，吹牛撒謊是道義上的滅亡，它勢必引向政治上的滅亡。這句話反映了問題的急切性。生活中，若回報食物出現了，我們就不得不考慮它出現了的事實。儘管如此，我們仍然需要對回報食物保持懷疑的態度。培根說過，幸運並非沒有許多的恐懼與煩惱，厄運也並非沒有許多的安慰與希望。這不禁令我重新仔細的思考。對我個人而言，回報食物不僅僅是一個重大的事件，還可能會改變我的人生。回過神才發現，思考回報食物的存在意義，已讓我廢寢忘食。回報食物改變了我的命運。','d31dqs','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(2,'1',"床好難睡",'  德謨克利特曾說過一句意義深遠的話，一個人必須要么做個好人，要么仿效好人。這影響了我的價值觀。一般來講，我們都必須務必慎重的考慮考慮。我們要從本質思考，從根本解決問題。看看別人，再想想自己，會發現問題的核心其實就在你身旁。既然，其實，若思緒夠清晰，那麼訂房不滿意也就不那麼複雜了。我們一般認為，抓住了問題的關鍵，其他一切則會迎刃而解。在人類的歷史中，我們總是盡了一切努力想搞懂訂房不滿意。問題的關鍵究竟為何？王夫之曾經認為，力行而後知之真。這句話決定了一切。','d3ihvs','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(3,'2',"參訪接待好沒禮貌",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','ibr912','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(4,'4',"有人亂發表文張",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','idnm12','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(5,'5',"你們消息也太慢了吧",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','oqics2','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(6,'6',"我密碼忘記",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','pmwq13','會員');

insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(1,'3',"食物不新鮮",'  列寧講過一段耐人尋思的話，吹牛撒謊是道義上的滅亡，它勢必引向政治上的滅亡。這句話反映了問題的急切性。生活中，若回報食物出現了，我們就不得不考慮它出現了的事實。儘管如此，我們仍然需要對回報食物保持懷疑的態度。培根說過，幸運並非沒有許多的恐懼與煩惱，厄運也並非沒有許多的安慰與希望。這不禁令我重新仔細的思考。對我個人而言，回報食物不僅僅是一個重大的事件，還可能會改變我的人生。回過神才發現，思考回報食物的存在意義，已讓我廢寢忘食。回報食物改變了我的命運。','d0mbqs','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(2,'1',"床好難睡",'  德謨克利特曾說過一句意義深遠的話，一個人必須要么做個好人，要么仿效好人。這影響了我的價值觀。一般來講，我們都必須務必慎重的考慮考慮。我們要從本質思考，從根本解決問題。看看別人，再想想自己，會發現問題的核心其實就在你身旁。既然，其實，若思緒夠清晰，那麼訂房不滿意也就不那麼複雜了。我們一般認為，抓住了問題的關鍵，其他一切則會迎刃而解。在人類的歷史中，我們總是盡了一切努力想搞懂訂房不滿意。問題的關鍵究竟為何？王夫之曾經認為，力行而後知之真。這句話決定了一切。','lnqes','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(3,'2',"參訪接待好沒禮貌",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','cre912','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(4,'4',"有人亂發表文張",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','ybw18','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(5,'5',"你們消息也太慢了吧",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','kmqe2','會員');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(6,'6',"我密碼忘記",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','pmqke1','會員');

insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`ADMIN_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(1,'3',2,"食物不新鮮",' 列寧講過一段耐人尋思的話，吹牛撒謊是道義上的滅亡，它勢必引向政治上的滅亡。這句話反映了問題的急切性。生活中，若回報食物出現了，我們就不得不考慮它出現了的事實。儘管如此，我們仍然需要對回報食物保持懷疑的態度。培根說過，幸運並非沒有許多的恐懼與煩惱，厄運也並非沒有許多的安慰與希望。這不禁令我重新仔細的思考。對我個人而言，回報食物不僅僅是一個重大的事件，還可能會改變我的人生。回過神才發現，思考回報食物的存在意義，已讓我廢寢忘食。回報食物改變了我的命運。','sddq1dqs','後台');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`ADMIN_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(2,'1',2,"床好難睡",'  德謨克利特曾說過一句意義深遠的話，一個人必須要么做個好人，要么仿效好人。這影響了我的價值觀。一般來講，我們都必須務必慎重的考慮考慮。我們要從本質思考，從根本解決問題。看看別人，再想想自己，會發現問題的核心其實就在你身旁。既然，其實，若思緒夠清晰，那麼訂房不滿意也就不那麼複雜了。我們一般認為，抓住了問題的關鍵，其他一切則會迎刃而解。在人類的歷史中，我們總是盡了一切努力想搞懂訂房不滿意。問題的關鍵究竟為何？王夫之曾經認為，力行而後知之真。這句話決定了一切。','d3ihvsdwdq','後台');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`ADMIN_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(3,'2',2,"參訪接待好沒禮貌",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','oqwiqbr912','後台');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`ADMIN_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(4,'4',1,"有人亂發表文張",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','dwqsidnm12','後台');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`ADMIN_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(5,'5',4,"你們消息也太慢了吧",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','qqeowqics2','後台');
insert into `REPORT_MAIL`(`USER_ID`,`CATEGORY_ID`,`ADMIN_ID`,`REPORT_TITLE`,`REPORT_CONTENT`,`AUTH_CODE`,`DETERMINE`)
values(6,'6',3,"我密碼忘記",' 波爾克說過一句富有哲理的話，讀書而不思考，等於吃飯而不消化。希望大家能發現話中之話。問題的關鍵究竟為何？參訪不開心的存在，令我無法停止對他的思考。我們不妨可以這樣來想: 列寧深信，在純粹的光明中就像在純黑暗中一。這句話幾乎解讀出了問題的根本。博斯威爾講過一段深奧的話，能對你開懷直言的人，便是你的摯友。這段話令我陷入了沈思。總而言之，若無法徹底理解參訪不開心，恐怕會是人類的一大遺憾。','eqbn3pmwq13','後台');



drop table if exists `REPORT_MAIL_IMG`;
create table `REPORT_MAIL_IMG` (
	`RIMG_ID` int not null auto_increment comment '回報圖片編號' primary key,
	`AUTH_CODE` varchar(50) not null comment '隨機亂碼',
    `REPORT_IMG` longblob  comment '回報信件圖片',
    foreign key (`AUTH_CODE`) references `REPORT_MAIL` (`AUTH_CODE`)
) comment '回報信件的圖片';




drop table if exists `ANSWER_MAIL_IMG`;
create table `ANSWER_MAIL_IMG` (
	`AIMG_ID` int not null auto_increment comment '回覆圖片編號' primary key,
	`AUTH_CODE` varchar(50) not null comment '隨機亂碼',
    `ANSWER_IMG` longblob  comment '回覆信件圖片',
    foreign key (`AUTH_CODE`) references `REPORT_MAIL` (`AUTH_CODE`)
) comment '回覆信件的圖片';