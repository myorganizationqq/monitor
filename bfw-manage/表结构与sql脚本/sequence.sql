--活动信息
create sequence SEQ_ACTIVITY_EXTRA minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--活动活跃名单
create sequence SEQ_ACTIVITY_NAMELIST minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--活动回复
create sequence SEQ_ACTIVITY_REPLY minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--附件表
create sequence SEQ_ANNEX minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--考勤表
create sequence SEQ_ATTENDANCE_INFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--班主任历史
create sequence SEQ_CLASS_HEADMASTER_HISTORY minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--班级信息
create sequence SEQ_CLASS_INFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--班级课程表
create sequence SEQ_CLASS_TIMETABLE minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--班级课程表明细
create sequence SEQ_CLASS_TIMETABLE_DETAIL minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--部门
create sequence SEQ_COMMON_DEPART minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--菜单
create sequence SEQ_COMMON_FUNCTION minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--操作
create sequence SEQ_COMMON_OPERATION minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--单位
create sequence SEQ_COMMON_ORG minvalue 1 maxvalue 99999999999999999999 start with 1 increment by 1 cache 20 ;
--角色
create sequence SEQ_COMMON_ROLE minvalue 1 maxvalue 99999999999999999999 start with 1 increment by 1 cache 20 ;
--角色菜单关系
create sequence SEQ_COMMON_ROLE_FUNCTION minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--用户
create sequence SEQ_COMMON_USER minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--用户基本信息
create sequence SEQ_COMMON_USER_BASEINFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--用户角色关系
create sequence SEQ_COMMON_USER_ROLE minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--教学计划
create sequence SEQ_COURSE_PLANNING minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--校区
create sequence SEQ_DISTRICT_INFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--作业浏览
create sequence SEQ_HOMEWORK_BROWSE minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--作业详情
create sequence SEQ_HOMEWORK_DETAIL minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--作业信息
create sequence SEQ_HOMEWORK_INFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--登录日志
create sequence SEQ_LOGIN_LOG minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--德育评价
create sequence SEQ_MORAL_EVALUATION minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--操作日志
create sequence SEQ_OPERATION_LOG minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--家长委员会
create sequence SEQ_PARENT_COMMITTEE minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--问题回答
create sequence SEQ_QUESTION_ANSWER minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--问题
create sequence SEQ_QUESTION_INFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--学校信息
create sequence SEQ_SCHOOL_INFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--学校开学日期设置
create sequence SEQ_SCHOOL_OPEN_DATE_SET minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--考试
create sequence SEQ_SCORE_EXAM minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--考试明细
create sequence SEQ_SCORE_EXAM_DETAIL minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--成绩
create sequence SEQ_SCORE_SCORE minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--学生信息变更历史
create sequence SEQ_STUDENT_CHANGE_HISTORY minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--学生信息
create sequence SEQ_STUDENT_INFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--学生家长关系
create sequence SEQ_STU_PARENT minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--教师班级课程关系
create sequence SEQ_TEACHER_CLASS_COURSE minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;
--教师信息
create sequence SEQ_TEACHER_INFO minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;

--add 
--用户组织关系
create sequence SEQ_COMMON_USER_ORG minvalue 1 maxvalue 9999999999999999999999999999 start with 1 increment by 1 cache 10 ;