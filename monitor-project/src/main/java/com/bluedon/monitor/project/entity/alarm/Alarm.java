package com.bluedon.monitor.project.entity.alarm;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * 告警配置
 * @author zhouming
 * @date 2017/11/20
 */
@Table(name="alarm")
@Entity
@DynamicInsert
@DynamicUpdate
public class Alarm {

    private long id;

    /**
     * 告警配置名称
     */
    private String alarmName;

    /**
     * 告警配置类型
     */
    private String alarmType;

    private Date createDate;

    private Date updateDate;

    private String createDateStr;

    private String updateDateStr;

    //--------交易文件和数据
    /**
     * 交易文件和数据_不合法数据个数_故障（小于）
     */
    private int jywjhsj_bhfsjgs_gz;

    /**
     * 交易文件和数据_不合法数据个数_危险（小于）
     */
    private int jywjhsj_bhfsjgs_wx;

    /**
     * 交易文件和数据_重复数据个数_故障（大于）
     */
    private int jywjhsj_cfsjgs_gz;

    /**
     * 交易文件和数据_重复数据个数_危险（大于）
     */
    private int jywjhsj_cfsjgs_wx;

    /**
     * 交易文件和数据_无法预处理文件个数_故障（小于）
     */
    private int jywjhsj_wfyclwjgs_gz;

    /**
     * 交易文件和数据_无法预处理文件个数_危险（小于）
     */
    private int jywjhsj_wfyclwjgs_wx;
    //--------交易文件和数据

    //--------通信业务系统
    /**
     * 通信业务系统_接受最大流量_故障（小于）
     */
    private int txywxt_jszdll_gz;

    /**
     * 通信业务系统_接受最大流量_危险（小于）
     */
    private int txywxt_jszdll_wx;

    /**
     * 通信业务系统_接受最大流量_故障（大于）
     */
    private int txywxt_jszxll_gz;

    /**
     * 通信业务系统_接受最大流量_危险（大于）
     */
    private int txywxt_jszxll_wx;


    /**
     * 通信业务系统_成功个数_故障（小于）
     */
    private int txywxt_cggs_gz;

    /**
     * 通信业务系统_成功个数_危险（小于）
     */
    private int txywxt_cggs_wx;

    /**
     * 通信业务系统_失败个数_故障（小于）
     */
    private int txywxt_sbgs_gz;

    /**
     * 通信业务系统_失败个数_危险（小于）
     */
    private int txywxt_sbgs_wx;

    /**
     * 通信业务系统_ftp成功数量_故障（小于）
     */
    private int txywxt_ftpcgsl_gz;

    /**
     * 通信业务系统_ftp成功数量_危险（小于）
     */
    private int txywxt_ftpcgsl_wx;

    /**
     * 通信业务系统_ftp失败数量_故障（大于）
     */
    private int txywxt_ftpsbsl_gz;

    /**
     * 通信业务系统_ftp失败数量_危险（大于）
     */
    private int txywxt_ftpsbsl_wx;
    //--------通信业务系统


    //--------清算子系统
    /**
     * 清算子系统_金融IC卡黑名单_故障（小于）
     */
    private int jyzxt_jrickhmd_gz;

    /**
     * 清算子系统_金融IC卡黑名单_危险（小于）
     */
    private int jyzxt_jrickhmd_wx;

    /**
     * 清算子系统_初始化羊城通导出_故障（大于）
     */
    private int jyzxt_cshyctdc_gz;

    /**
     * 清算子系统_初始化羊城通导出_危险（大于）
     */
    private int jyzxt_cshyctdc_wx;

    /**
     * 清算子系统_表清理_故障（小于）
     */
    private int jyzxt_bql_gz;

    /**
     * 清算子系统_表清理_危险（小于）
     */
    private int jyzxt_bql_wx;

    //--------清算子系统

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Column(name = "alarm_name")
    public String getAlarmName() {
        return alarmName;
    }

    @Column(name = "alarm_type")
    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

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
}
