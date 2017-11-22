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

    public int getJywjhsj_bhfsjgs_gz() {
        return jywjhsj_bhfsjgs_gz;
    }

    public void setJywjhsj_bhfsjgs_gz(int jywjhsj_bhfsjgs_gz) {
        this.jywjhsj_bhfsjgs_gz = jywjhsj_bhfsjgs_gz;
    }

    public int getJywjhsj_bhfsjgs_wx() {
        return jywjhsj_bhfsjgs_wx;
    }

    public void setJywjhsj_bhfsjgs_wx(int jywjhsj_bhfsjgs_wx) {
        this.jywjhsj_bhfsjgs_wx = jywjhsj_bhfsjgs_wx;
    }

    public int getJywjhsj_cfsjgs_gz() {
        return jywjhsj_cfsjgs_gz;
    }

    public void setJywjhsj_cfsjgs_gz(int jywjhsj_cfsjgs_gz) {
        this.jywjhsj_cfsjgs_gz = jywjhsj_cfsjgs_gz;
    }

    public int getJywjhsj_cfsjgs_wx() {
        return jywjhsj_cfsjgs_wx;
    }

    public void setJywjhsj_cfsjgs_wx(int jywjhsj_cfsjgs_wx) {
        this.jywjhsj_cfsjgs_wx = jywjhsj_cfsjgs_wx;
    }

    public int getJywjhsj_wfyclwjgs_gz() {
        return jywjhsj_wfyclwjgs_gz;
    }

    public void setJywjhsj_wfyclwjgs_gz(int jywjhsj_wfyclwjgs_gz) {
        this.jywjhsj_wfyclwjgs_gz = jywjhsj_wfyclwjgs_gz;
    }

    public int getJywjhsj_wfyclwjgs_wx() {
        return jywjhsj_wfyclwjgs_wx;
    }

    public void setJywjhsj_wfyclwjgs_wx(int jywjhsj_wfyclwjgs_wx) {
        this.jywjhsj_wfyclwjgs_wx = jywjhsj_wfyclwjgs_wx;
    }

    public int getTxywxt_jszdll_gz() {
        return txywxt_jszdll_gz;
    }

    public void setTxywxt_jszdll_gz(int txywxt_jszdll_gz) {
        this.txywxt_jszdll_gz = txywxt_jszdll_gz;
    }

    public int getTxywxt_jszdll_wx() {
        return txywxt_jszdll_wx;
    }

    public void setTxywxt_jszdll_wx(int txywxt_jszdll_wx) {
        this.txywxt_jszdll_wx = txywxt_jszdll_wx;
    }

    public int getTxywxt_jszxll_gz() {
        return txywxt_jszxll_gz;
    }

    public void setTxywxt_jszxll_gz(int txywxt_jszxll_gz) {
        this.txywxt_jszxll_gz = txywxt_jszxll_gz;
    }

    public int getTxywxt_jszxll_wx() {
        return txywxt_jszxll_wx;
    }

    public void setTxywxt_jszxll_wx(int txywxt_jszxll_wx) {
        this.txywxt_jszxll_wx = txywxt_jszxll_wx;
    }

    public int getTxywxt_cggs_gz() {
        return txywxt_cggs_gz;
    }

    public void setTxywxt_cggs_gz(int txywxt_cggs_gz) {
        this.txywxt_cggs_gz = txywxt_cggs_gz;
    }

    public int getTxywxt_cggs_wx() {
        return txywxt_cggs_wx;
    }

    public void setTxywxt_cggs_wx(int txywxt_cggs_wx) {
        this.txywxt_cggs_wx = txywxt_cggs_wx;
    }

    public int getTxywxt_sbgs_gz() {
        return txywxt_sbgs_gz;
    }

    public void setTxywxt_sbgs_gz(int txywxt_sbgs_gz) {
        this.txywxt_sbgs_gz = txywxt_sbgs_gz;
    }

    public int getTxywxt_sbgs_wx() {
        return txywxt_sbgs_wx;
    }

    public void setTxywxt_sbgs_wx(int txywxt_sbgs_wx) {
        this.txywxt_sbgs_wx = txywxt_sbgs_wx;
    }

    public int getTxywxt_ftpcgsl_gz() {
        return txywxt_ftpcgsl_gz;
    }

    public void setTxywxt_ftpcgsl_gz(int txywxt_ftpcgsl_gz) {
        this.txywxt_ftpcgsl_gz = txywxt_ftpcgsl_gz;
    }

    public int getTxywxt_ftpcgsl_wx() {
        return txywxt_ftpcgsl_wx;
    }

    public void setTxywxt_ftpcgsl_wx(int txywxt_ftpcgsl_wx) {
        this.txywxt_ftpcgsl_wx = txywxt_ftpcgsl_wx;
    }

    public int getTxywxt_ftpsbsl_gz() {
        return txywxt_ftpsbsl_gz;
    }

    public void setTxywxt_ftpsbsl_gz(int txywxt_ftpsbsl_gz) {
        this.txywxt_ftpsbsl_gz = txywxt_ftpsbsl_gz;
    }

    public int getTxywxt_ftpsbsl_wx() {
        return txywxt_ftpsbsl_wx;
    }

    public void setTxywxt_ftpsbsl_wx(int txywxt_ftpsbsl_wx) {
        this.txywxt_ftpsbsl_wx = txywxt_ftpsbsl_wx;
    }

    public int getJyzxt_jrickhmd_gz() {
        return jyzxt_jrickhmd_gz;
    }

    public void setJyzxt_jrickhmd_gz(int jyzxt_jrickhmd_gz) {
        this.jyzxt_jrickhmd_gz = jyzxt_jrickhmd_gz;
    }

    public int getJyzxt_jrickhmd_wx() {
        return jyzxt_jrickhmd_wx;
    }

    public void setJyzxt_jrickhmd_wx(int jyzxt_jrickhmd_wx) {
        this.jyzxt_jrickhmd_wx = jyzxt_jrickhmd_wx;
    }

    public int getJyzxt_cshyctdc_gz() {
        return jyzxt_cshyctdc_gz;
    }

    public void setJyzxt_cshyctdc_gz(int jyzxt_cshyctdc_gz) {
        this.jyzxt_cshyctdc_gz = jyzxt_cshyctdc_gz;
    }

    public int getJyzxt_cshyctdc_wx() {
        return jyzxt_cshyctdc_wx;
    }

    public void setJyzxt_cshyctdc_wx(int jyzxt_cshyctdc_wx) {
        this.jyzxt_cshyctdc_wx = jyzxt_cshyctdc_wx;
    }

    public int getJyzxt_bql_gz() {
        return jyzxt_bql_gz;
    }

    public void setJyzxt_bql_gz(int jyzxt_bql_gz) {
        this.jyzxt_bql_gz = jyzxt_bql_gz;
    }

    public int getJyzxt_bql_wx() {
        return jyzxt_bql_wx;
    }

    public void setJyzxt_bql_wx(int jyzxt_bql_wx) {
        this.jyzxt_bql_wx = jyzxt_bql_wx;
    }
}
