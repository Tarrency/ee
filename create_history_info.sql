use CUQADatabase;
create table history_dialogue_info
(
    id         bigint auto_increment primary key,
    session_id varchar(50) not null,
    user_id varchar(50) not null ,
    agent_id bigint not null ,
    message varchar(500) not null comment '消息内容',
    sender smallint not null comment '消息发送方[0:user;1:agent]',
    date  datetime default CURRENT_TIMESTAMP not null comment '更新时间' ,
    if_deliverd boolean default true comment '是否发送成功',
    other varchar(500) comment '补充字段',
    check (sender in ( 0, 1))
) charset = utf8;