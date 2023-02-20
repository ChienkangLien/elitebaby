delete from post_imgs where 1=1;
alter table post_imgs
    auto_increment = 0;

delete from post_like where 1=1;
alter table post_like
    auto_increment = 0;

delete from msg_imgs where 1=1;
alter table msg_imgs
    auto_increment = 0;

delete from msg_like where 1=1;
alter table msg_like
    auto_increment = 0;

delete from msg where 1=1;
alter table msg
    auto_increment = 0;

delete from post where 1=1;
alter table post
    auto_increment = 0;
