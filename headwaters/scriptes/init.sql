DROP TABLE IF EXISTS `hw_mark`;

create table hw_mark
(
	id int auto_increment comment '记录主键',
	busi_key varchar(255) default '' not null comment '业务标识/数据库表名',
	inside_id int default 1 not null comment '隐式的id，用户定义的最大值。f(id,inside_id) = max_id',
	max_id bigint default 1 not null comment '当前号段最大值，达到该值则续号',
	step int default 1 not null comment '步长，每次续号的长度',
	`desc` varchar(255) null comment '备注',
	update_time timestamp default CURRENT_TIMESTAMP null comment '更新时间',
	constraint hw_mark_pk
		primary key (id)
);

create unique index hw_mark_busi_key_uindex
	on hw_mark (busi_key);

