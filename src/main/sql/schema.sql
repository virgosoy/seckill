-- 数据库初始化脚本
-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
USE seckill;
-- 创建秒杀库存表
CREATE TABLE seckill(
    seckill_id int not null auto_increment comment '商品库存id',
    name varchar(120) not null comment '商品名称',
    number int not null comment '库存数量',
    create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
    start_time timestamp not null comment '秒杀开始时间',
    end_time timestamp not null comment '秒杀结束时间',
    PRIMARY KEY (seckill_id),
    KEY idx_start_time(start_time),
    KEY idx_end_time(end_time),
    key ide_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 comment='秒杀库存表';

-- 初始化数据
insert into seckill(name,number,start_time,end_time) values
    ('2000元秒杀iPhone7',100,'2016-12-11 12:00:00','2016-12-12 11:48:48'),
    ('1000元秒杀iPad4',200,'2015-11-11 11:51:45','2015-12-10 11:51:59'),
    ('500元秒杀小米5',300,'2016-12-11 14:51:45','2016-12-18 11:51:59'),
    ('200元秒杀小米note',500,'2016-12-14 11:51:45','2016-12-18 11:51:59');

-- 秒杀成功明细表
CREATE TABLE success_killed(
    seckill_id int not null comment '库存商品id',
    user_phone varchar(20) not null comment '用户手机号',
    status tinyint not null comment '状态标识 -1-无效，0-成功，1-已经付款',
    create_time timestamp not null default CURRENT_TIMESTAMP comment '创建时间/秒杀时间',
    PRIMARY KEY (seckill_id,user_phone), -- 联合主键
    key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='秒杀成功明细表';

