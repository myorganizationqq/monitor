
create table TB_ACTIVITY_EXTRA
(
  id             NUMBER(20) not null,
  title          NVARCHAR2(50) not null,
  content        NVARCHAR2(500),
  click_count    NUMBER(9) not null,
  zan_count      NUMBER(9) not null,
  activity_time  DATE,
  activity_type  NUMBER(20) not null,
  activity_class NUMBER(20),
  upload_time    DATE not null,
  upload_author  NUMBER(20) not null,
  upload_type    NUMBER(1),
  remark         NVARCHAR2(200)
)
;
comment on table TB_ACTIVITY_EXTRA
  is '课外活动信息表';
comment on column TB_ACTIVITY_EXTRA.id
  is '主键';
comment on column TB_ACTIVITY_EXTRA.title
  is '活动标题';
comment on column TB_ACTIVITY_EXTRA.content
  is '活动内容';
comment on column TB_ACTIVITY_EXTRA.click_count
  is '点击次数';
comment on column TB_ACTIVITY_EXTRA.zan_count
  is '赞数';
comment on column TB_ACTIVITY_EXTRA.activity_time
  is '活动时间';
comment on column TB_ACTIVITY_EXTRA.activity_type
  is '活动类型';
comment on column TB_ACTIVITY_EXTRA.activity_class
  is '活动班级';
comment on column TB_ACTIVITY_EXTRA.upload_time
  is '上传时间';
comment on column TB_ACTIVITY_EXTRA.upload_author
  is '上传人';
comment on column TB_ACTIVITY_EXTRA.upload_type
  is '上传类型';
comment on column TB_ACTIVITY_EXTRA.remark
  is '备注';
alter table TB_ACTIVITY_EXTRA
  add constraint PK_ACTIVITY_EXTRA_ID primary key (ID);


create table TB_ACTIVITY_NAMELIST
(
  id           NUMBER(20) not null,
  activity_id  NUMBER(20) not null,
  student_id   NUMBER(20) not null,
  student_name NVARCHAR2(20),
  remark       NVARCHAR2(200) not null
)
;
comment on table TB_ACTIVITY_NAMELIST
  is '课外活动活跃名单表';
comment on column TB_ACTIVITY_NAMELIST.id
  is '主键';
comment on column TB_ACTIVITY_NAMELIST.activity_id
  is '活动ID';
comment on column TB_ACTIVITY_NAMELIST.student_id
  is '用户ID';
comment on column TB_ACTIVITY_NAMELIST.student_name
  is '学生姓名';
comment on column TB_ACTIVITY_NAMELIST.remark
  is '备注';
alter table TB_ACTIVITY_NAMELIST
  add constraint PK_ACTIVITY_NAMELIST_ID primary key (ID);

create table TB_ACTIVITY_REPLY
(
  id              NUMBER(20) not null,
  activity_id     NUMBER(20) not null,
  reply_user      NUMBER(20) not null,
  reply_content   NVARCHAR2(400) not null,
  reply_time      DATE not null,
  reply_parent_id NUMBER(20) not null
)
;
comment on table TB_ACTIVITY_REPLY
  is '课外活动回复表';
comment on column TB_ACTIVITY_REPLY.id
  is '主键';
comment on column TB_ACTIVITY_REPLY.activity_id
  is '活动ID';
comment on column TB_ACTIVITY_REPLY.reply_user
  is '回复用户';
comment on column TB_ACTIVITY_REPLY.reply_content
  is '回复内容';
comment on column TB_ACTIVITY_REPLY.reply_time
  is '回复时间';
comment on column TB_ACTIVITY_REPLY.reply_parent_id
  is '被回复信息ID';
alter table TB_ACTIVITY_REPLY
  add constraint PK_ACTIVITY_REPLY_ID primary key (ID);

create table TB_ANNEX
(
  id           NUMBER(20) not null,
  table_name   NVARCHAR2(50) not null,
  data_id      NVARCHAR2(50) not null,
  picture_path NVARCHAR2(200) not null,
  upload_user  NUMBER(20) not null,
  upload_time  DATE not null,
  remark       NVARCHAR2(200)
)
;
comment on table TB_ANNEX
  is '附件表';
comment on column TB_ANNEX.id
  is '主键';
comment on column TB_ANNEX.table_name
  is '表名';
comment on column TB_ANNEX.data_id
  is '关联数据ID';
comment on column TB_ANNEX.picture_path
  is '图片路径';
comment on column TB_ANNEX.upload_user
  is '上传人';
comment on column TB_ANNEX.upload_time
  is '上传时间';
comment on column TB_ANNEX.remark
  is '备注';
alter table TB_ANNEX
  add constraint PK_ANNEX_ID primary key (ID);

create table TB_ATTENDANCE_INFO
(
  id             NUMBER(20) not null,
  student_id     NUMBER(20) not null,
  student_name   NVARCHAR2(20) not null,
  grade          NVARCHAR2(2),
  class_id       NUMBER(20),
  read_card_time DATE not null,
  inorout        NVARCHAR2(2) not null,
  create_time    DATE,
  is_push        NUMBER(2),
  push_time      DATE,
  remark         NVARCHAR2(200)
)
partition by range (CREATE_TIME)
(
  partition P201505 values less than (TO_DATE(' 2015-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201506 values less than (TO_DATE(' 2015-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201507 values less than (TO_DATE(' 2015-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201508 values less than (TO_DATE(' 2015-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201509 values less than (TO_DATE(' 2015-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201510 values less than (TO_DATE(' 2015-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201511 values less than (TO_DATE(' 2015-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201512 values less than (TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201601 values less than (TO_DATE(' 2016-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201602 values less than (TO_DATE(' 2016-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201603 values less than (TO_DATE(' 2016-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201604 values less than (TO_DATE(' 2016-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201605 values less than (TO_DATE(' 2016-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201606 values less than (TO_DATE(' 2016-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201607 values less than (TO_DATE(' 2016-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201608 values less than (TO_DATE(' 2016-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201609 values less than (TO_DATE(' 2016-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201610 values less than (TO_DATE(' 2016-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201611 values less than (TO_DATE(' 2016-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201612 values less than (TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201701 values less than (TO_DATE(' 2017-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201702 values less than (TO_DATE(' 2017-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201703 values less than (TO_DATE(' 2017-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201704 values less than (TO_DATE(' 2017-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201705 values less than (TO_DATE(' 2017-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201706 values less than (TO_DATE(' 2017-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201707 values less than (TO_DATE(' 2017-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201708 values less than (TO_DATE(' 2017-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201709 values less than (TO_DATE(' 2017-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201710 values less than (TO_DATE(' 2017-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201711 values less than (TO_DATE(' 2017-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201712 values less than (TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201801 values less than (TO_DATE(' 2018-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201802 values less than (TO_DATE(' 2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201803 values less than (TO_DATE(' 2018-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201804 values less than (TO_DATE(' 2018-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201805 values less than (TO_DATE(' 2018-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201806 values less than (TO_DATE(' 2018-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201807 values less than (TO_DATE(' 2018-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201808 values less than (TO_DATE(' 2018-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201809 values less than (TO_DATE(' 2018-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201810 values less than (TO_DATE(' 2018-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201811 values less than (TO_DATE(' 2018-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201812 values less than (TO_DATE(' 2019-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201901 values less than (TO_DATE(' 2019-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201902 values less than (TO_DATE(' 2019-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201903 values less than (TO_DATE(' 2019-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201904 values less than (TO_DATE(' 2019-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201905 values less than (TO_DATE(' 2019-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201906 values less than (TO_DATE(' 2019-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201907 values less than (TO_DATE(' 2019-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201908 values less than (TO_DATE(' 2019-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201909 values less than (TO_DATE(' 2019-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201910 values less than (TO_DATE(' 2019-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201911 values less than (TO_DATE(' 2019-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201912 values less than (TO_DATE(' 2020-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202001 values less than (TO_DATE(' 2020-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202002 values less than (TO_DATE(' 2020-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202003 values less than (TO_DATE(' 2020-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202004 values less than (TO_DATE(' 2020-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202005 values less than (TO_DATE(' 2020-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202006 values less than (TO_DATE(' 2020-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202007 values less than (TO_DATE(' 2020-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202008 values less than (TO_DATE(' 2020-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202009 values less than (TO_DATE(' 2020-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202010 values less than (TO_DATE(' 2020-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202011 values less than (TO_DATE(' 2020-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202012 values less than (TO_DATE(' 2021-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS
);
comment on table TB_ATTENDANCE_INFO
  is '考勤信息表';
comment on column TB_ATTENDANCE_INFO.id
  is '主键';
comment on column TB_ATTENDANCE_INFO.student_id
  is '用户ID';
comment on column TB_ATTENDANCE_INFO.student_name
  is '学生姓名';
comment on column TB_ATTENDANCE_INFO.grade
  is '年级ID';
comment on column TB_ATTENDANCE_INFO.class_id
  is '班级ID';
comment on column TB_ATTENDANCE_INFO.read_card_time
  is '读卡时间';
comment on column TB_ATTENDANCE_INFO.inorout
  is '进出校标识';
comment on column TB_ATTENDANCE_INFO.create_time
  is '创建时间';
comment on column TB_ATTENDANCE_INFO.is_push
  is '是否已经推送';
comment on column TB_ATTENDANCE_INFO.push_time
  is '推送时间';
comment on column TB_ATTENDANCE_INFO.remark
  is '备注';
create index IDX_ATTENDANCE_USERID on TB_ATTENDANCE_INFO (STUDENT_ID);
alter table TB_ATTENDANCE_INFO
  add constraint PK_ATTENDANCE_ID primary key (ID);

create table TB_CLASS_HEADMASTER_HISTORY
(
  id            NUMBER(20) not null,
  class_id      NUMBER(20) not null,
  teacher_id    NUMBER(20) not null,
  begin_date    DATE not null,
  end_date      DATE,
  create_people NUMBER(20) not null,
  crate_time    DATE not null
)
;
comment on table TB_CLASS_HEADMASTER_HISTORY
  is '班级班主任历史表';
comment on column TB_CLASS_HEADMASTER_HISTORY.id
  is '主键';
comment on column TB_CLASS_HEADMASTER_HISTORY.class_id
  is '班级ID';
comment on column TB_CLASS_HEADMASTER_HISTORY.teacher_id
  is '教师ID';
comment on column TB_CLASS_HEADMASTER_HISTORY.begin_date
  is '开始时间';
comment on column TB_CLASS_HEADMASTER_HISTORY.end_date
  is '结束时间';
comment on column TB_CLASS_HEADMASTER_HISTORY.create_people
  is '创建人';
comment on column TB_CLASS_HEADMASTER_HISTORY.crate_time
  is '创建时间';
create index IDX_HEADMASTER_HISTORY_CLASSID on TB_CLASS_HEADMASTER_HISTORY (CLASS_ID);
create index IDX_HEADMASTER_HISTORY_TEACHER on TB_CLASS_HEADMASTER_HISTORY (TEACHER_ID);
alter table TB_CLASS_HEADMASTER_HISTORY
  add constraint PK_CLASS_HEADMASTER_HISTORY_ID primary key (ID);

create table TB_CLASS_INFO
(
  id            NUMBER(20) not null,
  class_code    NVARCHAR2(20) not null,
  class_name    NVARCHAR2(50) not null,
  entrance_year NVARCHAR2(4) not null,
  entrance_seid NVARCHAR2(1) not null,
  headmaster_id NUMBER(20) not null,
  org_id        NUMBER(20) not null,
  district_id   NUMBER(20),
  stage         NVARCHAR2(2),
  create_people NUMBER(20) not null,
  create_time   DATE not null,
  update_people NUMBER(20),
  update_time   DATE,
  remark        NVARCHAR2(200)
)
;
comment on table TB_CLASS_INFO
  is '班级信息表';
comment on column TB_CLASS_INFO.id
  is '主键';
comment on column TB_CLASS_INFO.class_code
  is '班级编码';
comment on column TB_CLASS_INFO.class_name
  is '班级名称';
comment on column TB_CLASS_INFO.entrance_year
  is '入学年份';
comment on column TB_CLASS_INFO.entrance_seid
  is '入学学期';
comment on column TB_CLASS_INFO.headmaster_id
  is '班主任';
comment on column TB_CLASS_INFO.org_id
  is '组织机构ID';
comment on column TB_CLASS_INFO.district_id
  is '校区ID';
comment on column TB_CLASS_INFO.stage
  is '阶段';
comment on column TB_CLASS_INFO.create_people
  is '创建人';
comment on column TB_CLASS_INFO.create_time
  is '创建时间';
comment on column TB_CLASS_INFO.update_people
  is '修改人';
comment on column TB_CLASS_INFO.update_time
  is '修改时间';
comment on column TB_CLASS_INFO.remark
  is '备注';
create index IDX_CLASS_INFO_ORGID on TB_CLASS_INFO (ORG_ID);
alter table TB_CLASS_INFO
  add constraint PK_CLASS_INFO_ID primary key (ID);

create table TB_CLASS_TIMETABLE
(
  id            NUMBER(20) not null,
  class_id      NUMBER(20) not null,
  year          NVARCHAR2(4) not null,
  se_id         NVARCHAR2(1) not null,
  is_valid      NUMBER(1),
  org_id        NUMBER(20) not null,
  create_people NUMBER(20) not null,
  create_time   DATE not null,
  update_people NUMBER(20),
  update_time   DATE
)
;
comment on table TB_CLASS_TIMETABLE
  is '班级课程表表';
comment on column TB_CLASS_TIMETABLE.id
  is '主键';
comment on column TB_CLASS_TIMETABLE.class_id
  is '班级ID';
comment on column TB_CLASS_TIMETABLE.year
  is '年份';
comment on column TB_CLASS_TIMETABLE.se_id
  is '学期';
comment on column TB_CLASS_TIMETABLE.is_valid
  is '是否有效';
comment on column TB_CLASS_TIMETABLE.org_id
  is '组织机构ID';
comment on column TB_CLASS_TIMETABLE.create_people
  is '创建人';
comment on column TB_CLASS_TIMETABLE.create_time
  is '创建时间';
comment on column TB_CLASS_TIMETABLE.update_people
  is '修改人';
comment on column TB_CLASS_TIMETABLE.update_time
  is '修改时间';
alter table TB_CLASS_TIMETABLE
  add constraint PK_CLASS_TIMETABLE_ID primary key (ID);

create table TB_CLASS_TIMETABLE_DETAIL
(
  id           NUMBER(20) not null,
  timetable_id NUMBER(20),
  day_id       NVARCHAR2(2),
  hour_id      NVARCHAR2(2),
  course_id    NUMBER(20),
  teacher_id   NUMBER(20),
  remark       NVARCHAR2(200)
)
;
comment on table TB_CLASS_TIMETABLE_DETAIL
  is '课程表明细表';
comment on column TB_CLASS_TIMETABLE_DETAIL.id
  is '主键';
comment on column TB_CLASS_TIMETABLE_DETAIL.timetable_id
  is '班级课程表ID';
comment on column TB_CLASS_TIMETABLE_DETAIL.day_id
  is '星期几';
comment on column TB_CLASS_TIMETABLE_DETAIL.hour_id
  is '第几节课';
comment on column TB_CLASS_TIMETABLE_DETAIL.course_id
  is '科目ID';
comment on column TB_CLASS_TIMETABLE_DETAIL.teacher_id
  is '教师ID';
comment on column TB_CLASS_TIMETABLE_DETAIL.remark
  is '备注';
create index IDX_CLASS_TIMETABLE_ID on TB_CLASS_TIMETABLE_DETAIL (TIMETABLE_ID);
alter table TB_CLASS_TIMETABLE_DETAIL
  add constraint PK_CLASS_TIMETABLE_DETAIL_ID primary key (ID);

create table TB_COMMON_DEPART
(
  id          NUMBER(20) not null,
  depart_code NVARCHAR2(20),
  depart_name NVARCHAR2(50) not null,
  description NVARCHAR2(200),
  parent_id   NUMBER(20),
  org_id      NUMBER(20) not null,
  phone       NVARCHAR2(20),
  create_time DATE not null,
  update_time DATE
)
;
comment on table TB_COMMON_DEPART
  is '部门表';
comment on column TB_COMMON_DEPART.id
  is '主键';
comment on column TB_COMMON_DEPART.depart_code
  is '部门编号';
comment on column TB_COMMON_DEPART.depart_name
  is '部门名称';
comment on column TB_COMMON_DEPART.description
  is '描述';
comment on column TB_COMMON_DEPART.parent_id
  is '上级部门ID';
comment on column TB_COMMON_DEPART.org_id
  is '组织机构ID';
comment on column TB_COMMON_DEPART.phone
  is '联系电话';
comment on column TB_COMMON_DEPART.create_time
  is '创建时间';
comment on column TB_COMMON_DEPART.update_time
  is '修改时间';
create index IDX_COMMON_DEPART_ORGID on TB_COMMON_DEPART (ORG_ID);
alter table TB_COMMON_DEPART
  add constraint PK_COMMON_DEPART_ID primary key (ID);

create table TB_COMMON_FUNCTION
(
  id                 NUMBER(20) not null,
  function_name      NVARCHAR2(15) not null,
  function_url       NVARCHAR2(100),
  function_level     NUMBER(2) not null,
  function_order     NUMBER(3) not null,
  parent_function_id NUMBER(20)
)
;
comment on table TB_COMMON_FUNCTION
  is '菜单表';
comment on column TB_COMMON_FUNCTION.id
  is '主键';
comment on column TB_COMMON_FUNCTION.function_name
  is '菜单名称';
comment on column TB_COMMON_FUNCTION.function_url
  is '菜单URL';
comment on column TB_COMMON_FUNCTION.function_level
  is '菜单级别';
comment on column TB_COMMON_FUNCTION.function_order
  is '菜单顺序';
comment on column TB_COMMON_FUNCTION.parent_function_id
  is '父菜单ID';
alter table TB_COMMON_FUNCTION
  add constraint PK_COMMON_FUNCTION_ID primary key (ID);

create table TB_COMMON_OPERATION
(
  id           NUMBER(20) not null,
  operate_code NVARCHAR2(10) not null,
  operate_name NVARCHAR2(15) not null,
  function_id  NUMBER(20) not null
)
;
comment on table TB_COMMON_OPERATION
  is '操作按钮表';
comment on column TB_COMMON_OPERATION.id
  is '主键';
comment on column TB_COMMON_OPERATION.operate_code
  is '操作代码';
comment on column TB_COMMON_OPERATION.operate_name
  is '操作名称';
comment on column TB_COMMON_OPERATION.function_id
  is '菜单ID';
create index IDX_COMMON_OPERATION_FUNCID on TB_COMMON_OPERATION (FUNCTION_ID);
alter table TB_COMMON_OPERATION
  add constraint PK_COMMON_OPERATION_ID primary key (ID);

create table TB_COMMON_ORG
(
  id                   NUMBER(20) not null,
  org_code             NVARCHAR2(10),
  org_name             NVARCHAR2(20),
  full_name            NVARCHAR2(50),
  parent_id            NUMBER(20),
  description          NVARCHAR2(200),
  disabled             NUMBER(1),
  school_address       NVARCHAR2(200),
  school_contact_phone NVARCHAR2(20),
  schoolmaster_name    NVARCHAR2(20),
  open_property        NVARCHAR2(2),
  school_type          NVARCHAR2(2),
  remark               NVARCHAR2(200)
)
;
comment on table TB_COMMON_ORG
  is '组织机构表';
comment on column TB_COMMON_ORG.id
  is '主键';
comment on column TB_COMMON_ORG.org_code
  is '机构代码';
comment on column TB_COMMON_ORG.org_name
  is '简称';
comment on column TB_COMMON_ORG.full_name
  is '全名';
comment on column TB_COMMON_ORG.parent_id
  is '父组织机构ID';
comment on column TB_COMMON_ORG.description
  is '描述';
comment on column TB_COMMON_ORG.disabled
  is '是否启用';
comment on column TB_COMMON_ORG.school_address
  is '机构地址';
comment on column TB_COMMON_ORG.school_contact_phone
  is '联系电话';
comment on column TB_COMMON_ORG.schoolmaster_name
  is '校长';
comment on column TB_COMMON_ORG.open_property
  is '办学性质(字典表)';
comment on column TB_COMMON_ORG.school_type
  is '学校类别(字典表)';
comment on column TB_COMMON_ORG.remark
  is '备注';
alter table TB_COMMON_ORG
  add constraint PK_COMMON_ORG_ID primary key (ID);
alter table TB_COMMON_ORG
  add constraint UK_COMMON_ORG_ORGCODE unique (ORG_CODE);

create table TB_COMMON_ROLE
(
  id          NUMBER(20) not null,
  rolecode    NVARCHAR2(20),
  rolename    NVARCHAR2(50),
  description NVARCHAR2(200),
  create_time DATE,
  update_time DATE,
  role_type   NUMBER(1)
)
;
comment on table TB_COMMON_ROLE
  is '角色表';
comment on column TB_COMMON_ROLE.id
  is '主键';
comment on column TB_COMMON_ROLE.rolecode
  is '角色编码';
comment on column TB_COMMON_ROLE.rolename
  is '角色名称';
comment on column TB_COMMON_ROLE.description
  is '描述';
comment on column TB_COMMON_ROLE.create_time
  is '创建时间';
comment on column TB_COMMON_ROLE.update_time
  is '修改时间';
alter table TB_COMMON_ROLE
  add constraint PK_COMMON_ROLE_ID primary key (ID);

create table TB_COMMON_ROLE_FUNCTION
(
  id           NUMBER(20) not null,
  role_id      NUMBER(20) not null,
  function_id  NUMBER(20) not null,
  operation_id NVARCHAR2(500)
)
;
comment on table TB_COMMON_ROLE_FUNCTION
  is '角色菜单关系表';
comment on column TB_COMMON_ROLE_FUNCTION.id
  is '主键';
comment on column TB_COMMON_ROLE_FUNCTION.role_id
  is '角色ID';
comment on column TB_COMMON_ROLE_FUNCTION.function_id
  is '菜单ID';
comment on column TB_COMMON_ROLE_FUNCTION.operation_id
  is '操作编码';
alter table TB_COMMON_ROLE_FUNCTION
  add constraint PK_COMMON_ROLE_FUNCTION_ID primary key (ID);

create table TB_COMMON_USER
(
  id         NUMBER(20) not null,
  login_name NVARCHAR2(50),
  password   NVARCHAR2(50),
  real_name  NVARCHAR2(50),
  user_type  NUMBER(1),
  depart_id  NUMBER(20),
  org_id     NUMBER(20),
  is_valid   NUMBER(1)
)
;
comment on table TB_COMMON_USER
  is '用户表';
comment on column TB_COMMON_USER.id
  is '主键';
comment on column TB_COMMON_USER.login_name
  is '用户名';
comment on column TB_COMMON_USER.password
  is '密码';
comment on column TB_COMMON_USER.real_name
  is '姓名';
comment on column TB_COMMON_USER.user_type
  is '用户类别';
comment on column TB_COMMON_USER.depart_id
  is '部门ID';
comment on column TB_COMMON_USER.org_id
  is '组织机构ID';
comment on column TB_COMMON_USER.is_valid
  is '是否有效';
create index IDX_COMMON_USER_ORGID on TB_COMMON_USER (ORG_ID);
alter table TB_COMMON_USER
  add constraint PK_COMMON_USER_ID primary key (ID);

create table TB_COMMON_USER_BASEINFO
(
  id              NUMBER(20) not null,
  user_id         NUMBER(20) not null,
  sex             NVARCHAR2(1),
  birthday        DATE,
  card_type       NVARCHAR2(2),
  card_num        NVARCHAR2(50),
  country         NVARCHAR2(4),
  nationality     NVARCHAR2(3),
  native_place    NVARCHAR2(100),
  photo           NVARCHAR2(200),
  mobile_phone    NVARCHAR2(50),
  email           NVARCHAR2(50),
  contact_address NVARCHAR2(200),
  home_address    NVARCHAR2(200),
  post_code       NVARCHAR2(20),
  create_people   NUMBER(20) not null,
  create_time     DATE not null,
  update_people   NUMBER(20),
  update_time     DATE,
  zjht_account    NVARCHAR2(30)
)
;
comment on table TB_COMMON_USER_BASEINFO
  is '用户基本信息表';
comment on column TB_COMMON_USER_BASEINFO.id
  is '主键';
comment on column TB_COMMON_USER_BASEINFO.user_id
  is '用户ID';
comment on column TB_COMMON_USER_BASEINFO.sex
  is '性别(字典表)';
comment on column TB_COMMON_USER_BASEINFO.birthday
  is '出生日期';
comment on column TB_COMMON_USER_BASEINFO.card_type
  is '证件类型';
comment on column TB_COMMON_USER_BASEINFO.card_num
  is '证件号码';
comment on column TB_COMMON_USER_BASEINFO.country
  is '国别';
comment on column TB_COMMON_USER_BASEINFO.nationality
  is '民族';
comment on column TB_COMMON_USER_BASEINFO.native_place
  is '籍贯';
comment on column TB_COMMON_USER_BASEINFO.photo
  is '头像图片路径';
comment on column TB_COMMON_USER_BASEINFO.mobile_phone
  is '手机号码';
comment on column TB_COMMON_USER_BASEINFO.email
  is '电子邮箱';
comment on column TB_COMMON_USER_BASEINFO.contact_address
  is '联系地址';
comment on column TB_COMMON_USER_BASEINFO.home_address
  is '家庭地址';
comment on column TB_COMMON_USER_BASEINFO.post_code
  is '邮政编码';
comment on column TB_COMMON_USER_BASEINFO.create_people
  is '创建人';
comment on column TB_COMMON_USER_BASEINFO.create_time
  is '创建时间';
comment on column TB_COMMON_USER_BASEINFO.update_people
  is '修改人';
comment on column TB_COMMON_USER_BASEINFO.update_time
  is '修改时间';
comment on column TB_COMMON_USER_BASEINFO.zjht_account
  is '中经汇通账号';
alter table TB_COMMON_USER_BASEINFO
  add constraint PK_COMMON_USER_BASEINFO_ID primary key (ID);
alter table TB_COMMON_USER_BASEINFO
  add constraint UK_COMMON_USER_BASEINFO_USERID unique (USER_ID);

create table TB_COMMON_USER_ORG
(
  id        NUMBER(20) not null,
  user_id   NUMBER(20) not null,
  org_id    NUMBER(20) not null,
  depart_id NUMBER(20) not null
)
;
comment on table TB_COMMON_USER_ORG
  is '用户组织机构关系表';
comment on column TB_COMMON_USER_ORG.id
  is 'id';
comment on column TB_COMMON_USER_ORG.user_id
  is '用户id';
comment on column TB_COMMON_USER_ORG.org_id
  is '机构id';
comment on column TB_COMMON_USER_ORG.depart_id
  is '部门id';
alter table TB_COMMON_USER_ORG
  add constraint PK_COMMON_USER_ORG_ID primary key (ID);

prompt Creating TB_COMMON_USER_ROLE...
create table TB_COMMON_USER_ROLE
(
  id      NUMBER(20) not null,
  user_id NUMBER(20) not null,
  role_id NUMBER(20) not null
)
;
comment on table TB_COMMON_USER_ROLE
  is '用户角色表';
comment on column TB_COMMON_USER_ROLE.id
  is '主键';
comment on column TB_COMMON_USER_ROLE.user_id
  is '用户ID';
comment on column TB_COMMON_USER_ROLE.role_id
  is '角色ID';
create index IDX_COMMON_USER_ROLE_USERID on TB_COMMON_USER_ROLE (USER_ID);
alter table TB_COMMON_USER_ROLE
  add constraint PK_COMMON_USER_ROLE_ID primary key (ID);

create table TB_COURSE_PLANNING
(
  id            NUMBER(20) not null,
  open_year     NVARCHAR2(4) not null,
  open_seid     NVARCHAR2(1) not null,
  grade         NVARCHAR2(2) not null,
  course_code   NVARCHAR2(4) not null,
  course_hours  NUMBER(4),
  is_exam       NUMBER(1) not null,
  org_id        NUMBER(20) not null,
  create_people NUMBER(20) not null,
  create_time   DATE not null,
  update_people NUMBER(20),
  update_time   DATE,
  remark        NVARCHAR2(200)
)
partition by range (OPEN_YEAR)
subpartition by list (OPEN_SEID)
(
  partition P_2015 values less than ('2016')
    tablespace USERS
  (
    subpartition P_2015_S_1 values ('1') tablespace USERS,
    subpartition P_2015_S_2 values ('2') tablespace USERS,
    subpartition P_2015_S_3 values ('3') tablespace USERS,
    subpartition P_2015_S_9 values ('9') tablespace USERS
  ),
  partition P_2016 values less than ('2017')
    tablespace USERS
  (
    subpartition P_2016_S_1 values ('1') tablespace USERS,
    subpartition P_2016_S_2 values ('2') tablespace USERS,
    subpartition P_2016_S_3 values ('3') tablespace USERS,
    subpartition P_2016_S_9 values ('9') tablespace USERS
  ),
  partition P_2017 values less than ('2018')
    tablespace USERS
  (
    subpartition P_2017_S_1 values ('1') tablespace USERS,
    subpartition P_2017_S_2 values ('2') tablespace USERS,
    subpartition P_2017_S_3 values ('3') tablespace USERS,
    subpartition P_2017_S_9 values ('9') tablespace USERS
  ),
  partition P_2018 values less than ('2019')
    tablespace USERS
  (
    subpartition P_2018_S_1 values ('1') tablespace USERS,
    subpartition P_2018_S_2 values ('2') tablespace USERS,
    subpartition P_2018_S_3 values ('3') tablespace USERS,
    subpartition P_2018_S_9 values ('9') tablespace USERS
  ),
  partition P_2019 values less than ('2020')
    tablespace USERS
  (
    subpartition P_2019_S_1 values ('1') tablespace USERS,
    subpartition P_2019_S_2 values ('2') tablespace USERS,
    subpartition P_2019_S_3 values ('3') tablespace USERS,
    subpartition P_2019_S_9 values ('9') tablespace USERS
  ),
  partition P_2020 values less than ('2021')
    tablespace USERS
  (
    subpartition P_2020_S_1 values ('1') tablespace USERS,
    subpartition P_2020_S_2 values ('2') tablespace USERS,
    subpartition P_2020_S_3 values ('3') tablespace USERS,
    subpartition P_2020_S_9 values ('9') tablespace USERS
  )
);
comment on table TB_COURSE_PLANNING
  is '教学计划表';
comment on column TB_COURSE_PLANNING.id
  is '主键';
comment on column TB_COURSE_PLANNING.open_year
  is '开设年份';
comment on column TB_COURSE_PLANNING.open_seid
  is '开设学期';
comment on column TB_COURSE_PLANNING.grade
  is '年级';
comment on column TB_COURSE_PLANNING.course_code
  is '科目代码';
comment on column TB_COURSE_PLANNING.course_hours
  is '课时';
comment on column TB_COURSE_PLANNING.is_exam
  is '是否必考';
comment on column TB_COURSE_PLANNING.org_id
  is '组织机构ID';
comment on column TB_COURSE_PLANNING.create_people
  is '创建人';
comment on column TB_COURSE_PLANNING.create_time
  is '创建时间';
comment on column TB_COURSE_PLANNING.update_people
  is '修改人';
comment on column TB_COURSE_PLANNING.update_time
  is '修改时间';
comment on column TB_COURSE_PLANNING.remark
  is '备注';
create index IDX_COURSE_PLANNING_ORGID on TB_COURSE_PLANNING (ORG_ID);
alter table TB_COURSE_PLANNING
  add constraint PK_COURSE_PLANNING_ID primary key (ID);

create table TB_DISTRICT_INFO
(
  id                     NUMBER(20) not null,
  district_id            NVARCHAR2(2) not null,
  district_name          NVARCHAR2(100) not null,
  district_address       NVARCHAR2(200),
  district_contact_phone NVARCHAR2(20),
  org_id                 NUMBER(20) not null,
  remark                 NVARCHAR2(200)
)
;
comment on table TB_DISTRICT_INFO
  is '校区信息表';
comment on column TB_DISTRICT_INFO.id
  is '主键';
comment on column TB_DISTRICT_INFO.district_id
  is '校区编码';
comment on column TB_DISTRICT_INFO.district_name
  is '校区名称';
comment on column TB_DISTRICT_INFO.district_address
  is '校区地址';
comment on column TB_DISTRICT_INFO.district_contact_phone
  is '联系电话';
comment on column TB_DISTRICT_INFO.org_id
  is '组织机构ID';
comment on column TB_DISTRICT_INFO.remark
  is '备注';
alter table TB_DISTRICT_INFO
  add constraint PK_DISTRICT_ID primary key (ID);

create table TB_HOMEWORK_BROWSE
(
  id           NUMBER(20) not null,
  homework_id  NUMBER(20) not null,
  student_id   NUMBER(20) not null,
  student_name NVARCHAR2(20) not null,
  browse_time  DATE not null
)
;
comment on table TB_HOMEWORK_BROWSE
  is '作业浏览表';
comment on column TB_HOMEWORK_BROWSE.id
  is '主键';
comment on column TB_HOMEWORK_BROWSE.homework_id
  is '作业ID';
comment on column TB_HOMEWORK_BROWSE.student_id
  is '用户(学生)ID';
comment on column TB_HOMEWORK_BROWSE.student_name
  is '学生姓名';
comment on column TB_HOMEWORK_BROWSE.browse_time
  is '浏览时间';
alter table TB_HOMEWORK_BROWSE
  add constraint PK_HOMEWORK_BROWSE_ID primary key (ID);

create table TB_HOMEWORK_DETAIL
(
  id               NUMBER(20) not null,
  homework_id      NUMBER(20) not null,
  homework_content NVARCHAR2(500) not null
)
;
comment on table TB_HOMEWORK_DETAIL
  is '作业明细表';
comment on column TB_HOMEWORK_DETAIL.id
  is '主键';
comment on column TB_HOMEWORK_DETAIL.homework_id
  is '作业ID';
comment on column TB_HOMEWORK_DETAIL.homework_content
  is '作业内容';
alter table TB_HOMEWORK_DETAIL
  add constraint PK_HOMEWORK_DETAIL_ID primary key (ID);

prompt Creating TB_HOMEWORK_INFO...
create table TB_HOMEWORK_INFO
(
  id              NUMBER(20) not null,
  class_id        NUMBER(20) not null,
  course_code     NVARCHAR2(4) not null,
  release_teacher NVARCHAR2(35) not null,
  release_time    DATE not null,
  istiku          NVARCHAR2(35) not null
)
;
comment on table TB_HOMEWORK_INFO
  is '作业表';
comment on column TB_HOMEWORK_INFO.id
  is '主键';
comment on column TB_HOMEWORK_INFO.class_id
  is '班级';
comment on column TB_HOMEWORK_INFO.course_code
  is '科目';
comment on column TB_HOMEWORK_INFO.release_teacher
  is '布置教师';
comment on column TB_HOMEWORK_INFO.release_time
  is '布置时间';
comment on column TB_HOMEWORK_INFO.istiku
  is '是否题库题目';
alter table TB_HOMEWORK_INFO
  add constraint PK_HOMEWORK_INFO_ID primary key (ID);

create table TB_LOGIN_LOG
(
  id                NUMBER(20) not null,
  operate_loginname NVARCHAR2(50) not null,
  login_time        DATE not null,
  logout_time       DATE,
  ip                NVARCHAR2(30)
)
partition by range (LOGIN_TIME)
(
  partition P201505 values less than (TO_DATE(' 2015-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201506 values less than (TO_DATE(' 2015-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201507 values less than (TO_DATE(' 2015-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201508 values less than (TO_DATE(' 2015-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201509 values less than (TO_DATE(' 2015-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201510 values less than (TO_DATE(' 2015-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201511 values less than (TO_DATE(' 2015-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201512 values less than (TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201601 values less than (TO_DATE(' 2016-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201602 values less than (TO_DATE(' 2016-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201603 values less than (TO_DATE(' 2016-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201604 values less than (TO_DATE(' 2016-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201605 values less than (TO_DATE(' 2016-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201606 values less than (TO_DATE(' 2016-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201607 values less than (TO_DATE(' 2016-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201608 values less than (TO_DATE(' 2016-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201609 values less than (TO_DATE(' 2016-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201610 values less than (TO_DATE(' 2016-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201611 values less than (TO_DATE(' 2016-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201612 values less than (TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201701 values less than (TO_DATE(' 2017-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201702 values less than (TO_DATE(' 2017-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201703 values less than (TO_DATE(' 2017-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201704 values less than (TO_DATE(' 2017-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201705 values less than (TO_DATE(' 2017-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201706 values less than (TO_DATE(' 2017-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201707 values less than (TO_DATE(' 2017-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201708 values less than (TO_DATE(' 2017-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201709 values less than (TO_DATE(' 2017-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201710 values less than (TO_DATE(' 2017-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201711 values less than (TO_DATE(' 2017-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201712 values less than (TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201801 values less than (TO_DATE(' 2018-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201802 values less than (TO_DATE(' 2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201803 values less than (TO_DATE(' 2018-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201804 values less than (TO_DATE(' 2018-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201805 values less than (TO_DATE(' 2018-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201806 values less than (TO_DATE(' 2018-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201807 values less than (TO_DATE(' 2018-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201808 values less than (TO_DATE(' 2018-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201809 values less than (TO_DATE(' 2018-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201810 values less than (TO_DATE(' 2018-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201811 values less than (TO_DATE(' 2018-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201812 values less than (TO_DATE(' 2019-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201901 values less than (TO_DATE(' 2019-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201902 values less than (TO_DATE(' 2019-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201903 values less than (TO_DATE(' 2019-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201904 values less than (TO_DATE(' 2019-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201905 values less than (TO_DATE(' 2019-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201906 values less than (TO_DATE(' 2019-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201907 values less than (TO_DATE(' 2019-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201908 values less than (TO_DATE(' 2019-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201909 values less than (TO_DATE(' 2019-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201910 values less than (TO_DATE(' 2019-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201911 values less than (TO_DATE(' 2019-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201912 values less than (TO_DATE(' 2020-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202001 values less than (TO_DATE(' 2020-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202002 values less than (TO_DATE(' 2020-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202003 values less than (TO_DATE(' 2020-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202004 values less than (TO_DATE(' 2020-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202005 values less than (TO_DATE(' 2020-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202006 values less than (TO_DATE(' 2020-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202007 values less than (TO_DATE(' 2020-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202008 values less than (TO_DATE(' 2020-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202009 values less than (TO_DATE(' 2020-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202010 values less than (TO_DATE(' 2020-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202011 values less than (TO_DATE(' 2020-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202012 values less than (TO_DATE(' 2021-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS
);
comment on table TB_LOGIN_LOG
  is '登录日志表';
comment on column TB_LOGIN_LOG.id
  is '主键';
comment on column TB_LOGIN_LOG.operate_loginname
  is '用户登录名';
comment on column TB_LOGIN_LOG.login_time
  is '登录时间';
comment on column TB_LOGIN_LOG.logout_time
  is '退出时间';
comment on column TB_LOGIN_LOG.ip
  is 'IP地址';
alter table TB_LOGIN_LOG
  add constraint PK_LOGIN_LOG_ID primary key (ID);


create table TB_MORAL_EVALUATION
(
  id                 NUMBER(20) not null,
  student_id         NUMBER(20) not null,
  year               NVARCHAR2(4) not null,
  term               NVARCHAR2(1) not null,
  weekth             NVARCHAR2(2) not null,
  grade              NVARCHAR2(2),
  class_id           NUMBER(20),
  evalua_level       NVARCHAR2(2),
  tch_evalua_content NVARCHAR2(500),
  pt_evalua_content  NVARCHAR2(500),
  status             NVARCHAR2(2) not null
)
;
comment on table TB_MORAL_EVALUATION
  is '德育评价表';
comment on column TB_MORAL_EVALUATION.id
  is '主键';
comment on column TB_MORAL_EVALUATION.student_id
  is '用户(学生)id';
comment on column TB_MORAL_EVALUATION.year
  is '年份';
comment on column TB_MORAL_EVALUATION.term
  is '学期';
comment on column TB_MORAL_EVALUATION.weekth
  is '周数';
comment on column TB_MORAL_EVALUATION.grade
  is '年级';
comment on column TB_MORAL_EVALUATION.class_id
  is '班级';
comment on column TB_MORAL_EVALUATION.evalua_level
  is '评价等级';
comment on column TB_MORAL_EVALUATION.tch_evalua_content
  is '老师评价内容';
comment on column TB_MORAL_EVALUATION.pt_evalua_content
  is '家长评价内容';
comment on column TB_MORAL_EVALUATION.status
  is '评价状态';
alter table TB_MORAL_EVALUATION
  add constraint PK_MORAL_EVALUATION_ID primary key (ID);

create table TB_OPERATION_LOG
(
  id                NUMBER(20) not null,
  operate_loginname NVARCHAR2(50) not null,
  operate_username  NVARCHAR2(50) not null,
  operation         NVARCHAR2(100) not null,
  tb_name           NVARCHAR2(50) not null,
  tb_id             NVARCHAR2(30),
  create_time       DATE not null,
  ip                NVARCHAR2(50)
)
partition by range (CREATE_TIME)
(
  partition P201505 values less than (TO_DATE(' 2015-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201506 values less than (TO_DATE(' 2015-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201507 values less than (TO_DATE(' 2015-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201508 values less than (TO_DATE(' 2015-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201509 values less than (TO_DATE(' 2015-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201510 values less than (TO_DATE(' 2015-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201511 values less than (TO_DATE(' 2015-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201512 values less than (TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201601 values less than (TO_DATE(' 2016-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201602 values less than (TO_DATE(' 2016-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201603 values less than (TO_DATE(' 2016-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201604 values less than (TO_DATE(' 2016-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201605 values less than (TO_DATE(' 2016-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201606 values less than (TO_DATE(' 2016-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201607 values less than (TO_DATE(' 2016-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201608 values less than (TO_DATE(' 2016-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201609 values less than (TO_DATE(' 2016-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201610 values less than (TO_DATE(' 2016-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201611 values less than (TO_DATE(' 2016-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201612 values less than (TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201701 values less than (TO_DATE(' 2017-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201702 values less than (TO_DATE(' 2017-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201703 values less than (TO_DATE(' 2017-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201704 values less than (TO_DATE(' 2017-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201705 values less than (TO_DATE(' 2017-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201706 values less than (TO_DATE(' 2017-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201707 values less than (TO_DATE(' 2017-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201708 values less than (TO_DATE(' 2017-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201709 values less than (TO_DATE(' 2017-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201710 values less than (TO_DATE(' 2017-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201711 values less than (TO_DATE(' 2017-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201712 values less than (TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201801 values less than (TO_DATE(' 2018-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201802 values less than (TO_DATE(' 2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201803 values less than (TO_DATE(' 2018-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201804 values less than (TO_DATE(' 2018-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201805 values less than (TO_DATE(' 2018-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201806 values less than (TO_DATE(' 2018-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201807 values less than (TO_DATE(' 2018-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201808 values less than (TO_DATE(' 2018-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201809 values less than (TO_DATE(' 2018-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201810 values less than (TO_DATE(' 2018-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201811 values less than (TO_DATE(' 2018-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201812 values less than (TO_DATE(' 2019-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201901 values less than (TO_DATE(' 2019-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201902 values less than (TO_DATE(' 2019-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201903 values less than (TO_DATE(' 2019-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201904 values less than (TO_DATE(' 2019-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201905 values less than (TO_DATE(' 2019-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201906 values less than (TO_DATE(' 2019-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201907 values less than (TO_DATE(' 2019-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201908 values less than (TO_DATE(' 2019-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201909 values less than (TO_DATE(' 2019-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201910 values less than (TO_DATE(' 2019-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201911 values less than (TO_DATE(' 2019-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201912 values less than (TO_DATE(' 2020-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202001 values less than (TO_DATE(' 2020-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202002 values less than (TO_DATE(' 2020-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202003 values less than (TO_DATE(' 2020-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202004 values less than (TO_DATE(' 2020-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202005 values less than (TO_DATE(' 2020-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202006 values less than (TO_DATE(' 2020-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202007 values less than (TO_DATE(' 2020-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202008 values less than (TO_DATE(' 2020-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202009 values less than (TO_DATE(' 2020-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202010 values less than (TO_DATE(' 2020-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202011 values less than (TO_DATE(' 2020-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202012 values less than (TO_DATE(' 2021-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS
);
comment on table TB_OPERATION_LOG
  is '操作日志表';
comment on column TB_OPERATION_LOG.id
  is '主键';
comment on column TB_OPERATION_LOG.operate_loginname
  is '用户登录名';
comment on column TB_OPERATION_LOG.operate_username
  is '用户名';
comment on column TB_OPERATION_LOG.operation
  is '操作描述';
comment on column TB_OPERATION_LOG.tb_name
  is '操作表名';
comment on column TB_OPERATION_LOG.tb_id
  is '操作ID';
comment on column TB_OPERATION_LOG.create_time
  is '创建时间';
comment on column TB_OPERATION_LOG.ip
  is 'IP地址';
alter table TB_OPERATION_LOG
  add constraint PK_OPERATION_LOG_ID primary key (ID);

create table TB_PARENT_COMMITTEE
(
  id            NUMBER(20) not null,
  parent_id     NUMBER(20) not null,
  class_id      NUMBER(20) not null,
  postion       NVARCHAR2(100) not null,
  create_people NUMBER(20) not null,
  create_time   DATE not null,
  update_people NUMBER(20),
  update_time   DATE,
  remark        NVARCHAR2(200)
)
;
comment on table TB_PARENT_COMMITTEE
  is '家庭委员成员表';
comment on column TB_PARENT_COMMITTEE.id
  is '主键';
comment on column TB_PARENT_COMMITTEE.parent_id
  is '家长ID';
comment on column TB_PARENT_COMMITTEE.class_id
  is '班级ID';
comment on column TB_PARENT_COMMITTEE.postion
  is '职务';
comment on column TB_PARENT_COMMITTEE.create_people
  is '创建人';
comment on column TB_PARENT_COMMITTEE.create_time
  is '创建时间';
comment on column TB_PARENT_COMMITTEE.update_people
  is '修改人';
comment on column TB_PARENT_COMMITTEE.update_time
  is '修改时间';
comment on column TB_PARENT_COMMITTEE.remark
  is '备注';
create index IDX_PARENT_COMMITTEE_CLASSID on TB_PARENT_COMMITTEE (CLASS_ID);
alter table TB_PARENT_COMMITTEE
  add constraint PK_PARENT_COMMITTEE_ID primary key (ID);

create table TB_QUESTION_ANSWER
(
  id               NUMBER(20) not null,
  question_id      NUMBER(20) not null,
  answer_content   NVARCHAR2(500) not null,
  answer_user      NUMBER(20) not null,
  answer_user_name NVARCHAR2(50) not null,
  answer_time      DATE not null,
  answer_id        NUMBER(20) not null
)
;
comment on table TB_QUESTION_ANSWER
  is '互动答疑回答表';
comment on column TB_QUESTION_ANSWER.id
  is '主键';
comment on column TB_QUESTION_ANSWER.question_id
  is '互动答疑ID';
comment on column TB_QUESTION_ANSWER.answer_content
  is '回答内容';
comment on column TB_QUESTION_ANSWER.answer_user
  is '回答人ID';
comment on column TB_QUESTION_ANSWER.answer_user_name
  is '回答人姓名';
comment on column TB_QUESTION_ANSWER.answer_time
  is '回答时间';
comment on column TB_QUESTION_ANSWER.answer_id
  is '被回复信息ID';
alter table TB_QUESTION_ANSWER
  add constraint PK_QUESTION_ANSWER_ID primary key (ID);

create table TB_QUESTION_INFO
(
  id                  NUMBER(20) not null,
  question_title      NVARCHAR2(100) not null,
  question_content    NVARCHAR2(1000) not null,
  question_releaser   NUMBER(20) not null,
  q_releaser_name     NVARCHAR2(50) not null,
  course_code         NVARCHAR2(4) not null,
  release_time        DATE not null,
  be_question_teacher NUMBER(20) not null,
  isshare             NUMBER(2) not null
)
;
comment on table TB_QUESTION_INFO
  is '互动答疑表';
comment on column TB_QUESTION_INFO.id
  is '主键';
comment on column TB_QUESTION_INFO.question_title
  is '提问标题';
comment on column TB_QUESTION_INFO.question_content
  is '提问内容';
comment on column TB_QUESTION_INFO.question_releaser
  is '提问人ID';
comment on column TB_QUESTION_INFO.q_releaser_name
  is '提问人姓名';
comment on column TB_QUESTION_INFO.course_code
  is '科目';
comment on column TB_QUESTION_INFO.release_time
  is '提问时间';
comment on column TB_QUESTION_INFO.be_question_teacher
  is '被提问老师';
comment on column TB_QUESTION_INFO.isshare
  is '是否分享';
alter table TB_QUESTION_INFO
  add constraint PK_QUESTION_INFO_ID primary key (ID);


create table TB_SCHOOL_OPEN_DATE_SET
(
  id            NUMBER(20) not null,
  year          NVARCHAR2(4) not null,
  se_id         NVARCHAR2(1) not null,
  open_date     DATE not null,
  org_id        NUMBER(20) not null,
  create_people NUMBER(20) not null,
  create_time   DATE not null,
  update_people NUMBER(20),
  update_time   DATE
)
;
comment on table TB_SCHOOL_OPEN_DATE_SET
  is '学校开设日期设置表';
comment on column TB_SCHOOL_OPEN_DATE_SET.id
  is '主键';
comment on column TB_SCHOOL_OPEN_DATE_SET.year
  is '年份';
comment on column TB_SCHOOL_OPEN_DATE_SET.se_id
  is '学期';
comment on column TB_SCHOOL_OPEN_DATE_SET.open_date
  is '开学日期';
comment on column TB_SCHOOL_OPEN_DATE_SET.org_id
  is '组织机构ID';
comment on column TB_SCHOOL_OPEN_DATE_SET.create_people
  is '创建人';
comment on column TB_SCHOOL_OPEN_DATE_SET.create_time
  is '创建时间';
comment on column TB_SCHOOL_OPEN_DATE_SET.update_people
  is '修改人';
comment on column TB_SCHOOL_OPEN_DATE_SET.update_time
  is '修改时间';
alter table TB_SCHOOL_OPEN_DATE_SET
  add constraint PK_SCHOOL_OPEN_DATE_SET_ID primary key (ID);


create table TB_SCORE_EXAM
(
  id            NUMBER(20) not null,
  exam_year     NVARCHAR2(4) not null,
  exam_seid     NVARCHAR2(1) not null,
  exam_name     NVARCHAR2(50) not null,
  exam_time     DATE not null,
  org_id        NUMBER(20) not null,
  create_people NUMBER(20) not null,
  create_time   DATE not null,
  update_people NUMBER(20),
  update_time   DATE,
  remark        NVARCHAR2(200)
)
;
comment on table TB_SCORE_EXAM
  is '考试信息表';
comment on column TB_SCORE_EXAM.id
  is '主键';
comment on column TB_SCORE_EXAM.exam_year
  is '年份';
comment on column TB_SCORE_EXAM.exam_seid
  is '学期';
comment on column TB_SCORE_EXAM.exam_name
  is '考试名称';
comment on column TB_SCORE_EXAM.exam_time
  is '考试时间';
comment on column TB_SCORE_EXAM.org_id
  is '组织机构ID';
comment on column TB_SCORE_EXAM.create_people
  is '创建人';
comment on column TB_SCORE_EXAM.create_time
  is '创建时间';
comment on column TB_SCORE_EXAM.update_people
  is '修改人';
comment on column TB_SCORE_EXAM.update_time
  is '修改时间';
comment on column TB_SCORE_EXAM.remark
  is '备注';
create index IDX_SCORE_EXAM_ORGID on TB_SCORE_EXAM (ORG_ID);
alter table TB_SCORE_EXAM
  add constraint PK_SCORE_EXAM_ID primary key (ID);

create table TB_SCORE_EXAM_DETAIL
(
  id          NUMBER(20) not null,
  exam_id     NUMBER(20) not null,
  class_id    NUMBER(20) not null,
  course_code NVARCHAR2(4) not null,
  import_user NUMBER(20) not null,
  import_time DATE not null
)
;
comment on table TB_SCORE_EXAM_DETAIL
  is '考试详情表';
comment on column TB_SCORE_EXAM_DETAIL.id
  is '主键';
comment on column TB_SCORE_EXAM_DETAIL.exam_id
  is '考试ID';
comment on column TB_SCORE_EXAM_DETAIL.class_id
  is '班级ID';
comment on column TB_SCORE_EXAM_DETAIL.course_code
  is '课程代码';
comment on column TB_SCORE_EXAM_DETAIL.import_user
  is '导入人';
comment on column TB_SCORE_EXAM_DETAIL.import_time
  is '导入时间';
create index IDX_SCORE_EXAM_EXAMID on TB_SCORE_EXAM_DETAIL (EXAM_ID);
alter table TB_SCORE_EXAM_DETAIL
  add constraint PK_SCORE_EXAM_DETAI_ID primary key (ID);

create table TB_SCORE_SCORE
(
  id             NUMBER(20) not null,
  stu_id         NUMBER(20) not null,
  stu_num        NVARCHAR2(50),
  stu_name       NVARCHAR2(50),
  stu_class_id   NUMBER(20),
  exam_detail_id NUMBER(20) not null,
  score          NUMBER(3,2) not null,
  update_user    NUMBER(20),
  update_time    DATE
)
;
comment on table TB_SCORE_SCORE
  is '成绩表';
comment on column TB_SCORE_SCORE.id
  is '主键';
comment on column TB_SCORE_SCORE.stu_id
  is '用户ID';
comment on column TB_SCORE_SCORE.stu_num
  is '学籍号';
comment on column TB_SCORE_SCORE.stu_name
  is '学生姓名';
comment on column TB_SCORE_SCORE.stu_class_id
  is '班级ID';
comment on column TB_SCORE_SCORE.exam_detail_id
  is '考试详情ID';
comment on column TB_SCORE_SCORE.score
  is '分数';
comment on column TB_SCORE_SCORE.update_user
  is '修改人';
comment on column TB_SCORE_SCORE.update_time
  is '修改时间';
create index IDX_SCORE_SCORE_DETAILID on TB_SCORE_SCORE (EXAM_DETAIL_ID);
create index IDX_SCORE_SCORE_USERID on TB_SCORE_SCORE (STU_ID);
alter table TB_SCORE_SCORE
  add constraint PK_SCORE_SCORE_ID primary key (ID);

create table TB_STUDENT_CHANGE_HISTORY
(
  id            NUMBER(20) not null,
  change_stuid  NUMBER(20) not null,
  change_field  NVARCHAR2(50) not null,
  old_value     NVARCHAR2(200) not null,
  new_value     NVARCHAR2(200) not null,
  change_userid NUMBER(20) not null,
  change_time   DATE not null
)
partition by range (CHANGE_TIME)
(
  partition P201505 values less than (TO_DATE(' 2015-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201506 values less than (TO_DATE(' 2015-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201507 values less than (TO_DATE(' 2015-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201508 values less than (TO_DATE(' 2015-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201509 values less than (TO_DATE(' 2015-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201510 values less than (TO_DATE(' 2015-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201511 values less than (TO_DATE(' 2015-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201512 values less than (TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201601 values less than (TO_DATE(' 2016-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201602 values less than (TO_DATE(' 2016-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201603 values less than (TO_DATE(' 2016-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201604 values less than (TO_DATE(' 2016-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201605 values less than (TO_DATE(' 2016-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201606 values less than (TO_DATE(' 2016-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201607 values less than (TO_DATE(' 2016-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201608 values less than (TO_DATE(' 2016-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201609 values less than (TO_DATE(' 2016-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201610 values less than (TO_DATE(' 2016-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201611 values less than (TO_DATE(' 2016-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201612 values less than (TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201701 values less than (TO_DATE(' 2017-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201702 values less than (TO_DATE(' 2017-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201703 values less than (TO_DATE(' 2017-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201704 values less than (TO_DATE(' 2017-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201705 values less than (TO_DATE(' 2017-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201706 values less than (TO_DATE(' 2017-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201707 values less than (TO_DATE(' 2017-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201708 values less than (TO_DATE(' 2017-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201709 values less than (TO_DATE(' 2017-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201710 values less than (TO_DATE(' 2017-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201711 values less than (TO_DATE(' 2017-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201712 values less than (TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201801 values less than (TO_DATE(' 2018-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201802 values less than (TO_DATE(' 2018-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201803 values less than (TO_DATE(' 2018-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201804 values less than (TO_DATE(' 2018-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201805 values less than (TO_DATE(' 2018-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201806 values less than (TO_DATE(' 2018-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201807 values less than (TO_DATE(' 2018-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201808 values less than (TO_DATE(' 2018-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201809 values less than (TO_DATE(' 2018-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201810 values less than (TO_DATE(' 2018-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201811 values less than (TO_DATE(' 2018-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201812 values less than (TO_DATE(' 2019-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201901 values less than (TO_DATE(' 2019-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201902 values less than (TO_DATE(' 2019-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201903 values less than (TO_DATE(' 2019-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201904 values less than (TO_DATE(' 2019-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201905 values less than (TO_DATE(' 2019-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201906 values less than (TO_DATE(' 2019-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201907 values less than (TO_DATE(' 2019-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201908 values less than (TO_DATE(' 2019-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201909 values less than (TO_DATE(' 2019-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201910 values less than (TO_DATE(' 2019-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201911 values less than (TO_DATE(' 2019-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P201912 values less than (TO_DATE(' 2020-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202001 values less than (TO_DATE(' 2020-02-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202002 values less than (TO_DATE(' 2020-03-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202003 values less than (TO_DATE(' 2020-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202004 values less than (TO_DATE(' 2020-05-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202005 values less than (TO_DATE(' 2020-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202006 values less than (TO_DATE(' 2020-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202007 values less than (TO_DATE(' 2020-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202008 values less than (TO_DATE(' 2020-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202009 values less than (TO_DATE(' 2020-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202010 values less than (TO_DATE(' 2020-11-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202011 values less than (TO_DATE(' 2020-12-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS,
  partition P202012 values less than (TO_DATE(' 2021-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace USERS
);
comment on table TB_STUDENT_CHANGE_HISTORY
  is '学生信息变更历史表';
comment on column TB_STUDENT_CHANGE_HISTORY.id
  is '主键';
comment on column TB_STUDENT_CHANGE_HISTORY.change_stuid
  is '更新学生ID';
comment on column TB_STUDENT_CHANGE_HISTORY.change_field
  is '更新字段名称';
comment on column TB_STUDENT_CHANGE_HISTORY.old_value
  is '更新前字段值';
comment on column TB_STUDENT_CHANGE_HISTORY.new_value
  is '更新后字段值';
comment on column TB_STUDENT_CHANGE_HISTORY.change_userid
  is '修改人';
comment on column TB_STUDENT_CHANGE_HISTORY.change_time
  is '修改时间';
create index IDX_STUDENT_CHANGE_HISTORY_STU on TB_STUDENT_CHANGE_HISTORY (CHANGE_STUID);
alter table TB_STUDENT_CHANGE_HISTORY
  add constraint PK_STUDENT_CHANGE_HISTORY_ID primary key (ID);

create table TB_STUDENT_INFO
(
  id                   NUMBER(20) not null,
  stu_name             NVARCHAR2(50),
  maintenance_num      NVARCHAR2(19),
  maintenance_status   NVARCHAR2(2) not null,
  territoriality_type  NVARCHAR2(2),
  territoriality_regio NVARCHAR2(6),
  territoriality_addr  NVARCHAR2(100),
  photo                NVARCHAR2(200),
  entrance_year        NVARCHAR2(4) not null,
  entrance_seid        NVARCHAR2(1) not null,
  class_id             NUMBER(20) not null,
  data_source          NUMBER(1) not null,
  is_valid             NUMBER(1) not null,
  user_id              NUMBER(20) not null
)
;
comment on table TB_STUDENT_INFO
  is '学生信息表';
comment on column TB_STUDENT_INFO.id
  is '主键';
comment on column TB_STUDENT_INFO.stu_name
  is '姓名';
comment on column TB_STUDENT_INFO.maintenance_num
  is '学籍号';
comment on column TB_STUDENT_INFO.maintenance_status
  is '学籍状态';
comment on column TB_STUDENT_INFO.territoriality_type
  is '户口性质';
comment on column TB_STUDENT_INFO.territoriality_regio
  is '户口属地';
comment on column TB_STUDENT_INFO.territoriality_addr
  is '户口地址';
comment on column TB_STUDENT_INFO.photo
  is '相片路径';
comment on column TB_STUDENT_INFO.entrance_year
  is '入学年份';
comment on column TB_STUDENT_INFO.entrance_seid
  is '入学学期';
comment on column TB_STUDENT_INFO.class_id
  is '班级ID';
comment on column TB_STUDENT_INFO.data_source
  is '数据来源';
comment on column TB_STUDENT_INFO.is_valid
  is '是否有效';
comment on column TB_STUDENT_INFO.user_id
  is '用户ID';
create index IDX_STUDENT_INFO_CLASSID on TB_STUDENT_INFO (CLASS_ID);
alter table TB_STUDENT_INFO
  add constraint PK_STUDENT_INFO_ID primary key (ID);
alter table TB_STUDENT_INFO
  add constraint UK_MAINTENANCE_NUM unique (MAINTENANCE_NUM);
alter table TB_STUDENT_INFO
  add constraint UK_STUDENT_INFO_USERID unique (USER_ID);

create table TB_STU_PARENT
(
  id           NUMBER(20) not null,
  stu_id       NUMBER(20) not null,
  parent_id    NUMBER(20) not null,
  relationship NVARCHAR2(100) not null
)
;
comment on table TB_STU_PARENT
  is '学生-家长关系表';
comment on column TB_STU_PARENT.id
  is '主键';
comment on column TB_STU_PARENT.stu_id
  is '学生ID';
comment on column TB_STU_PARENT.parent_id
  is '家长ID';
comment on column TB_STU_PARENT.relationship
  is '亲属关系';
create index IDX_STU_PARENT_PARENTID on TB_STU_PARENT (PARENT_ID);
create index IDX_STU_PARENT_STUID on TB_STU_PARENT (STU_ID);
alter table TB_STU_PARENT
  add constraint PK_STU_PARENT_ID primary key (ID);

create table TB_TEACHER_CLASS_COURSE
(
  id            NUMBER(20) not null,
  planning_id   NUMBER(20) not null,
  class_id      NUMBER(20) not null,
  teacher_id    NUMBER(20) not null,
  create_people NUMBER(20) not null,
  create_time   DATE not null,
  update_people NUMBER(20),
  update_time   DATE,
  remark        NVARCHAR2(200)
)
;
comment on table TB_TEACHER_CLASS_COURSE
  is '教学任务表';
comment on column TB_TEACHER_CLASS_COURSE.id
  is '主键';
comment on column TB_TEACHER_CLASS_COURSE.planning_id
  is '教学计划ID';
comment on column TB_TEACHER_CLASS_COURSE.class_id
  is '班级ID';
comment on column TB_TEACHER_CLASS_COURSE.teacher_id
  is '教师ID';
comment on column TB_TEACHER_CLASS_COURSE.create_people
  is '创建人';
comment on column TB_TEACHER_CLASS_COURSE.create_time
  is '创建时间';
comment on column TB_TEACHER_CLASS_COURSE.update_people
  is '修改人';
comment on column TB_TEACHER_CLASS_COURSE.update_time
  is '修改时间';
comment on column TB_TEACHER_CLASS_COURSE.remark
  is '备注';
create index IDX_TEACHER_CLASS_COURSE_PLAN on TB_TEACHER_CLASS_COURSE (PLANNING_ID);
alter table TB_TEACHER_CLASS_COURSE
  add constraint PK_TEACHER_CLASS_COURSE_ID primary key (ID);

create table TB_TEACHER_INFO
(
  id                 NUMBER(20) not null,
  teacher_num        NVARCHAR2(50),
  highest_education  NVARCHAR2(2),
  highest_offering   NVARCHAR2(3),
  politics_status    NVARCHAR2(2),
  professional_title NVARCHAR2(3),
  post_type          NUMBER(1),
  formation          NUMBER(1),
  mountguard_cf      NUMBER(1) not null,
  mountguard_cf_no   NVARCHAR2(50),
  teach_cf           NUMBER(1),
  teach_cf_no        NVARCHAR2(50),
  data_source        NUMBER(1) not null,
  is_valid           NUMBER(1) not null,
  user_id            NUMBER(20) not null,
  update_people      NUMBER(20),
  update_time        DATE
)
;
comment on table TB_TEACHER_INFO
  is '教师信息表';
comment on column TB_TEACHER_INFO.id
  is '主键';
comment on column TB_TEACHER_INFO.teacher_num
  is '教师工号';
comment on column TB_TEACHER_INFO.highest_education
  is '最高学历';
comment on column TB_TEACHER_INFO.highest_offering
  is '最高学位';
comment on column TB_TEACHER_INFO.politics_status
  is '政治面貌';
comment on column TB_TEACHER_INFO.professional_title
  is '职称';
comment on column TB_TEACHER_INFO.post_type
  is '教师岗位类型';
comment on column TB_TEACHER_INFO.formation
  is '编制状态';
comment on column TB_TEACHER_INFO.mountguard_cf
  is '是否教师上岗证';
comment on column TB_TEACHER_INFO.mountguard_cf_no
  is '教师上岗证编号';
comment on column TB_TEACHER_INFO.teach_cf
  is '是否教师资格证';
comment on column TB_TEACHER_INFO.teach_cf_no
  is '教师资格证编号';
comment on column TB_TEACHER_INFO.data_source
  is '数据来源';
comment on column TB_TEACHER_INFO.is_valid
  is '是否有效';
comment on column TB_TEACHER_INFO.user_id
  is '用户ID';
comment on column TB_TEACHER_INFO.update_people
  is '修改人';
comment on column TB_TEACHER_INFO.update_time
  is '修改时间';
alter table TB_TEACHER_INFO
  add constraint PK_TEACHER_INFO_ID primary key (ID);
alter table TB_TEACHER_INFO
  add constraint UK_TEACHER_INFO_USERID unique (USER_ID);

