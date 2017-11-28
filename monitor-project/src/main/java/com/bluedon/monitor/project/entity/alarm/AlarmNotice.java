package com.bluedon.monitor.project.entity.alarm;

import com.bluedon.monitor.common.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 *告警通知表
 *@author  zhouming
 * on 2017/11/27.
 */
@Entity
@Table(name = "alarm_notice")
@DynamicInsert
@DynamicUpdate
public class AlarmNotice extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    /**
     * 告警级别
     */
    private String noticeLevel;

    /**
     * 告警状态
     */
    private String noticeStatus;

    /**
     * 设备名称
     */
    private String noticeName;

    /**
     * 监测指标
     */
    private String noticeType;

    /**
     * 告警原因
     */
    private String noticeReason;

    /**
     * 开始时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    @Transient
    private String createDateStr;

    @Transient
    private String updateDateStr;

    @Transient
    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    @Transient
    public String getUpdateDateStr() {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr) {
        this.updateDateStr = updateDateStr;
    }

    /**
     * 持续时间
     */
    @Transient
    private String noticeDuration;

    public void setNoticeLevel(String noticeLevel) {
        this.noticeLevel = noticeLevel;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public void setNoticeReason(String noticeReason) {
        this.noticeReason = noticeReason;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setNoticeDuration(String noticeDuration) {
        this.noticeDuration = noticeDuration;
    }

    @Column(name = "notice_level")
    public String getNoticeLevel() {
        return noticeLevel;
    }
    @Column(name = "notice_status")
    public String getNoticeStatus() {
        return noticeStatus;
    }
    @Column(name = "notice_name")
    public String getNoticeName() {
        return noticeName;
    }
    @Column(name = "notice_type")
    public String getNoticeType() {
        return noticeType;
    }
    @Column(name = "notice_reason")
    public String getNoticeReason() {
        return noticeReason;
    }
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }
    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }
    @Transient
    public String getNoticeDuration() {
        return noticeDuration;
    }
}
