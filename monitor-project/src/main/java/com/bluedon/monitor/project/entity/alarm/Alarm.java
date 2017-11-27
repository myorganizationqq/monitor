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

    /**
     * 交易文件和数据告警
     */
    public static String ALARM_TYPE_JYWJHSJ = "JYWJHSJ";

    /**
     * 交易子系统告警
     */
    public static String ALARM_TYPE_JYZXT = "JYZXT";

    /**
     * 通信业务系统告警
     */
    public static String ALARM_TYPE_TXYWXT = "TXYWXT";


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

    /**
     * 告警状态
     */
    private int alarmStatus;

    /**
     * 短信告警
     */
    private String alarmMessage;

    /**
     * 邮件告警
     */
    private String alarmEmail;

    /**
     * 告警对象
     */
    private String alarmUser;

    @Column(name = "alarm_cronTrigger")
    public String getAlarmCronTrigger() {
        return AlarmCronTrigger;
    }

    public void setAlarmCronTrigger(String alarmCronTrigger) {
        AlarmCronTrigger = alarmCronTrigger;
    }


    /**
     * 定时规则
     */
    private String AlarmCronTrigger;

    @Column(name = "alarm_cycle")
    public int getAlarmCycle() {
        return alarmCycle;
    }

    public void setAlarmCycle(int alarmCycle) {
        this.alarmCycle = alarmCycle;
    }

    /**
     * 告警周期
     */
    private int alarmCycle;

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
     * 通信业务系统_接受最小流量_故障（大于）
     */
    private int txywxt_jszxll_gz;

    /**
     * 通信业务系统_接受最小流量_危险（大于）
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

    /**
     * 清算子系统_初始化待入库FTP文件_故障（小于）
     */
    private int jyzxt_cshdrkftpwj_gz;

    /**
     * 清算子系统_初始化待入库FTP文件_危险（小于）
     */
    private int jyzxt_cshdrkftpwj_wx;

    /**
     * 清算子系统_导出羊城通其他线路CPU卡文件_故障（小于）
     */
    private int jyzxt_dcyctqtxlcpukwj_gz;

    /**
     * 清算子系统_导出羊城通其他线路CPU卡文件_危险（小于）
     */
    private int jyzxt_dcyctqtxlcpukwj_wx;

    /**
     * 清算子系统_审计文件下发_故障（小于）
     */
    private int jyzxt_sjwjxf_gz;

    /**
     * 清算子系统_审计文件下发_危险（小于）
     */
    private int jyzxt_sjwjxf_wx;

    /**
     * 清算子系统_导出羊城通广佛普通卡文件_故障（小于）
     */
    private int jyzxt_dcyctgfptkwj_gz;

    /**
     * 清算子系统_导出羊城通广佛普通卡文件_危险（小于）
     */
    private int jyzxt_dcyctgfptkwj_wx;

    /**
     * 清算子系统_重跑文件入库_故障（小于）
     */
    private int jyzxt_cpwjrk_gz;

    /**
     * 清算子系统_重跑文件入库_危险（小于）
     */
    private int jyzxt_cpwjrk_wx;

    /**
     * 清算子系统_下发黑名单_故障（小于）
     */
    private int jyzxt_xfhmd_gz;

    /**
     * 清算子系统_下发黑名单_危险（小于）
     */
    private int jyzxt_xfhmd_wx;


    /**
     * 清算子系统_计算线网、有轨金融IC卡票价_故障（小于）
     */
    private int jyzxt_jsxwygjrickpj_gz;

    /**
     * 清算子系统_计算线网、有轨金融IC卡票价_危险（小于）
     */
    private int jyzxt_jsxwygjrickpj_wx;

    /**
     * 清算子系统_备份本地文件_故障（小于）
     */
    private int jyzxt_bfbdwj_gz;

    /**
     * 清算子系统_备份本地文件_危险（小于）
     */
    private int jyzxt_bfbdwj_wx;

    /**
     * 清算子系统_报表统计_故障（小于）
     */
    private int jyzxt_bbtj_gz;

    /**
     * 清算子系统_报表统计_危险（小于）
     */
    private int jyzxt_bbtj_wx;

    /**
     * 清算子系统_导出羊城通广佛CPU卡文件_故障（小于）
     */
    private int jyzxt_dcyctgfcpukwj_gz;

    /**
     * 清算子系统_导出羊城通广佛CPU卡文件_危险（小于）
     */
    private int jyzxt_dcyctgfcpukwj_wx;

    /**
     * 清算子系统_导出羊城通有轨普通卡文件_故障（小于）
     */
    private int jyzxt_dcyctygptkwj_gz;

    /**
     * 清算子系统_导出羊城通有轨普通卡文件_危险（小于）
     */
    private int jyzxt_dcyctygptkwj_wx;

    /**
     * 清算子系统_导出羊城通有轨CPU卡文件_故障（小于）
     */
    private int jyzxt_dcyctygcpukwj_gz;

    /**
     * 清算子系统_导出羊城通有轨CPU卡文件_危险（小于）
     */
    private int jyzxt_dcyctygcpukwj_wx;

    /**
     * 清算子系统_导出有轨金融IC卡交易文件_故障（小于）
     */
    private int jyzxt_dcygjrickjywj_gz;

    /**
     * 清算子系统_导出有轨金融IC卡交易文件_危险（小于）
     */
    private int jyzxt_dcygjrickjywj_wx;


    /**
     * 清算子系统_导出金融IC卡退款文件_故障（小于）
     */
    private int jyzxt_dcjricktkwj_gz;

    /**
     * 清算子系统_导出金融IC卡退款文件_危险（小于）
     */
    private int jyzxt_dcjricktkwj_wx;


    /**
     * 清算子系统_导出金融IC卡交易文件_故障（小于）
     */
    private int jyzxt_dcjrickjywj_gz;

    /**
     * 清算子系统_导出金融IC卡交易文件_危险（小于）
     */
    private int jyzxt_dcjrickjywj_wx;

    /**
     * 清算子系统_导出羊城通其他线路普通卡文件_故障（小于）
     */
    private int jyzxt_dcyctqtxlptkwj_gz;

    /**
     * 清算子系统_导出羊城通其他线路普通卡文件_危险（小于）
     */
    private int jyzxt_dcyctqtxlptkwj_wx;

    /**
     * 清算子系统_导进出闸错误表非重复数据_故障（小于）
     */
    private int jyzxt_djczcwbfcfsj_gz;

    /**
     * 清算子系统_导进出闸错误表非重复数据_危险（小于）
     */
    private int jyzxt_djczcwbfcfsj_wx;

    /**
     * 清算子系统_审计、异常统计_故障（小于）
     */
    private int jyzxt_sjyctj_gz;

    /**
     * 清算子系统_审计、异常统计_危险（小于）
     */
    private int jyzxt_sjyctj_wx;


    /**
     * 清算子系统_收益统计模块_故障（小于）
     */
    private int jyzxt_sytjmk_gz;

    /**
     * 清算子系统_收益统计模块_危险（小于）
     */
    private int jyzxt_sytjmk_wx;

    /**
     * 清算子系统_结算模块_故障（小于）
     */
    private int jyzxt_jsmk_gz;

    /**
     * 清算子系统_结算模块_危险（小于）
     */
    private int jyzxt_jsmk_wx;

    /**
     * 清算子系统_客流、乘距分析_故障（小于）
     */
    private int jyzxt_klcjfx_gz;

    /**
     * 清算子系统_客流、乘距分析_危险（小于）
     */
    private int jyzxt_klcjfx_wx;

    /**
     * 清算子系统_生成寄存器数据统计报表_故障（小于）
     */
    private int jyzxt_ccjcqsjtjbb_gz;

    /**
     * 清算子系统_生成寄存器数据统计报表_危险（小于）
     */
    private int jyzxt_ccjcqsjtjbb_wx;

    /**
     * 清算子系统_生成运营商收益报表_故障（小于）
     */
    private int jyzxt_ccyyssybb_gz;

    /**
     * 清算子系统_生成运营商收益报表_危险（小于）
     */
    private int jyzxt_ccyyssybb_wx;

    /**
     * 清算子系统_分表日志记录_故障（小于）
     */
    private int jyzxt_fbrzjl_gz;

    /**
     * 清算子系统_分表日志记录_危险（小于）
     */
    private int jyzxt_fbrzjl_wx;

    /**
     * 清算子系统_金融IC卡对账报表_故障（小于）
     */
    private int jyzxt_jrickdzbb_gz;

    /**
     * 清算子系统_金融IC卡对账报表_危险（小于）
     */
    private int jyzxt_jrickdzbb_wx;


    /**
     * 清算子系统_羊城通对账报表_故障（小于）
     */
    private int jyzxt_yctdzbb_gz;

    /**
     * 清算子系统_羊城通对账报表_危险（小于）
     */
    private int jyzxt_yctdzbb_wx;



    /**
     * 清算子系统_导入历史表_故障（小于）
     */
    private int jyzxt_drllb_gz;

    /**
     * 清算子系统_导入历史表_危险（小于）
     */
    private int jyzxt_drllb_wx;






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

    public void setJywjhsj_bhfsjgs_gz(int jywjhsj_bhfsjgs_gz) {
        this.jywjhsj_bhfsjgs_gz = jywjhsj_bhfsjgs_gz;
    }

    public void setJywjhsj_bhfsjgs_wx(int jywjhsj_bhfsjgs_wx) {
        this.jywjhsj_bhfsjgs_wx = jywjhsj_bhfsjgs_wx;
    }

    public void setJywjhsj_cfsjgs_gz(int jywjhsj_cfsjgs_gz) {
        this.jywjhsj_cfsjgs_gz = jywjhsj_cfsjgs_gz;
    }

    public void setJywjhsj_cfsjgs_wx(int jywjhsj_cfsjgs_wx) {
        this.jywjhsj_cfsjgs_wx = jywjhsj_cfsjgs_wx;
    }

    public void setJywjhsj_wfyclwjgs_gz(int jywjhsj_wfyclwjgs_gz) {
        this.jywjhsj_wfyclwjgs_gz = jywjhsj_wfyclwjgs_gz;
    }

    public void setJywjhsj_wfyclwjgs_wx(int jywjhsj_wfyclwjgs_wx) {
        this.jywjhsj_wfyclwjgs_wx = jywjhsj_wfyclwjgs_wx;
    }

    public void setTxywxt_jszdll_gz(int txywxt_jszdll_gz) {
        this.txywxt_jszdll_gz = txywxt_jszdll_gz;
    }

    public void setTxywxt_jszdll_wx(int txywxt_jszdll_wx) {
        this.txywxt_jszdll_wx = txywxt_jszdll_wx;
    }

    public void setTxywxt_jszxll_gz(int txywxt_jszxll_gz) {
        this.txywxt_jszxll_gz = txywxt_jszxll_gz;
    }

    public void setTxywxt_jszxll_wx(int txywxt_jszxll_wx) {
        this.txywxt_jszxll_wx = txywxt_jszxll_wx;
    }

    public void setTxywxt_cggs_gz(int txywxt_cggs_gz) {
        this.txywxt_cggs_gz = txywxt_cggs_gz;
    }

    public void setTxywxt_cggs_wx(int txywxt_cggs_wx) {
        this.txywxt_cggs_wx = txywxt_cggs_wx;
    }

    public void setTxywxt_sbgs_gz(int txywxt_sbgs_gz) {
        this.txywxt_sbgs_gz = txywxt_sbgs_gz;
    }

    public void setTxywxt_sbgs_wx(int txywxt_sbgs_wx) {
        this.txywxt_sbgs_wx = txywxt_sbgs_wx;
    }

    public void setTxywxt_ftpcgsl_gz(int txywxt_ftpcgsl_gz) {
        this.txywxt_ftpcgsl_gz = txywxt_ftpcgsl_gz;
    }

    public void setTxywxt_ftpcgsl_wx(int txywxt_ftpcgsl_wx) {
        this.txywxt_ftpcgsl_wx = txywxt_ftpcgsl_wx;
    }

    public void setTxywxt_ftpsbsl_gz(int txywxt_ftpsbsl_gz) {
        this.txywxt_ftpsbsl_gz = txywxt_ftpsbsl_gz;
    }

    public void setTxywxt_ftpsbsl_wx(int txywxt_ftpsbsl_wx) {
        this.txywxt_ftpsbsl_wx = txywxt_ftpsbsl_wx;
    }

    public void setJyzxt_jrickhmd_gz(int jyzxt_jrickhmd_gz) {
        this.jyzxt_jrickhmd_gz = jyzxt_jrickhmd_gz;
    }

    public void setJyzxt_jrickhmd_wx(int jyzxt_jrickhmd_wx) {
        this.jyzxt_jrickhmd_wx = jyzxt_jrickhmd_wx;
    }

    public void setJyzxt_cshyctdc_gz(int jyzxt_cshyctdc_gz) {
        this.jyzxt_cshyctdc_gz = jyzxt_cshyctdc_gz;
    }

    public void setJyzxt_cshyctdc_wx(int jyzxt_cshyctdc_wx) {
        this.jyzxt_cshyctdc_wx = jyzxt_cshyctdc_wx;
    }

    public void setJyzxt_bql_gz(int jyzxt_bql_gz) {
        this.jyzxt_bql_gz = jyzxt_bql_gz;
    }

    public void setJyzxt_bql_wx(int jyzxt_bql_wx) {
        this.jyzxt_bql_wx = jyzxt_bql_wx;
    }

    @Column(name = "jywjhsj_bhfsjgs_gz")
    public int getJywjhsj_bhfsjgs_gz() {
        return jywjhsj_bhfsjgs_gz;
    }

    @Column(name = "jywjhsj_bhfsjgs_wx")
    public int getJywjhsj_bhfsjgs_wx() {
        return jywjhsj_bhfsjgs_wx;
    }

    @Column(name = "jywjhsj_cfsjgs_gz")
    public int getJywjhsj_cfsjgs_gz() {
        return jywjhsj_cfsjgs_gz;
    }

    @Column(name = "jywjhsj_cfsjgs_wx")
    public int getJywjhsj_cfsjgs_wx() {
        return jywjhsj_cfsjgs_wx;
    }

    @Column(name = "jywjhsj_wfyclwjgs_gz")
    public int getJywjhsj_wfyclwjgs_gz() {
        return jywjhsj_wfyclwjgs_gz;
    }

    @Column(name = "jywjhsj_wfyclwjgs_wx")
    public int getJywjhsj_wfyclwjgs_wx() {
        return jywjhsj_wfyclwjgs_wx;
    }
    @Column(name = "txywxt_jszdll_gz")
    public int getTxywxt_jszdll_gz() {
        return txywxt_jszdll_gz;
    }
    @Column(name = "txywxt_jszdll_wx")
    public int getTxywxt_jszdll_wx() {
        return txywxt_jszdll_wx;
    }
    @Column(name = "txywxt_jszxll_gz")
    public int getTxywxt_jszxll_gz() {
        return txywxt_jszxll_gz;
    }
    @Column(name = "txywxt_jszxll_wx")
    public int getTxywxt_jszxll_wx() {
        return txywxt_jszxll_wx;
    }
    @Column(name = "txywxt_cggs_gz")
    public int getTxywxt_cggs_gz() {
        return txywxt_cggs_gz;
    }
    @Column(name = "txywxt_cggs_wx")
    public int getTxywxt_cggs_wx() {
        return txywxt_cggs_wx;
    }
    @Column(name = "txywxt_sbgs_gz")
    public int getTxywxt_sbgs_gz() {
        return txywxt_sbgs_gz;
    }
    @Column(name = "txywxt_sbgs_wx")
    public int getTxywxt_sbgs_wx() {
        return txywxt_sbgs_wx;
    }
    @Column(name = "txywxt_ftpcgsl_gz")
    public int getTxywxt_ftpcgsl_gz() {
        return txywxt_ftpcgsl_gz;
    }
    @Column(name = "txywxt_ftpcgsl_wx")
    public int getTxywxt_ftpcgsl_wx() {
        return txywxt_ftpcgsl_wx;
    }
    @Column(name = "txywxt_ftpsbsl_gz")
    public int getTxywxt_ftpsbsl_gz() {
        return txywxt_ftpsbsl_gz;
    }
    @Column(name = "txywxt_ftpsbsl_wx")
    public int getTxywxt_ftpsbsl_wx() {
        return txywxt_ftpsbsl_wx;
    }
    @Column(name = "jyzxt_jrickhmd_gz")
    public int getJyzxt_jrickhmd_gz() {
        return jyzxt_jrickhmd_gz;
    }
    @Column(name = "jyzxt_jrickhmd_wx")
    public int getJyzxt_jrickhmd_wx() {
        return jyzxt_jrickhmd_wx;
    }
    @Column(name = "jyzxt_cshyctdc_gz")
    public int getJyzxt_cshyctdc_gz() {
        return jyzxt_cshyctdc_gz;
    }
    @Column(name = "jyzxt_cshyctdc_wx")
    public int getJyzxt_cshyctdc_wx() {
        return jyzxt_cshyctdc_wx;
    }
    @Column(name = "jyzxt_bql_gz")
    public int getJyzxt_bql_gz() {
        return jyzxt_bql_gz;
    }
    @Column(name = "jyzxt_bql_wx")
    public int getJyzxt_bql_wx() {
        return jyzxt_bql_wx;
    }

    public void setAlarmStatus(int alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
    }

    @Column(name = "alarm_status")
    public int getAlarmStatus() {
        return alarmStatus;
    }

    @Column(name = "alarm_message")
    public String getAlarmMessage() {
        return alarmMessage;
    }

    @Column(name = "alarm_email")
    public String getAlarmEmail() {
        return alarmEmail;
    }

    @Column(name = "alarm_user")
    public String getAlarmUser() {
        return alarmUser;
    }

    public void setAlarmUser(String alarmUser) {
        this.alarmUser = alarmUser;
    }


    public void setJyzxt_cshdrkftpwj_gz(int jyzxt_cshdrkftpwj_gz) {
        this.jyzxt_cshdrkftpwj_gz = jyzxt_cshdrkftpwj_gz;
    }

    public void setJyzxt_cshdrkftpwj_wx(int jyzxt_cshdrkftpwj_wx) {
        this.jyzxt_cshdrkftpwj_wx = jyzxt_cshdrkftpwj_wx;
    }

    public void setJyzxt_dcyctqtxlcpukwj_gz(int jyzxt_dcyctqtxlcpukwj_gz) {
        this.jyzxt_dcyctqtxlcpukwj_gz = jyzxt_dcyctqtxlcpukwj_gz;
    }

    public void setJyzxt_dcyctqtxlcpukwj_wx(int jyzxt_dcyctqtxlcpukwj_wx) {
        this.jyzxt_dcyctqtxlcpukwj_wx = jyzxt_dcyctqtxlcpukwj_wx;
    }

    public void setJyzxt_sjwjxf_gz(int jyzxt_sjwjxf_gz) {
        this.jyzxt_sjwjxf_gz = jyzxt_sjwjxf_gz;
    }

    public void setJyzxt_sjwjxf_wx(int jyzxt_sjwjxf_wx) {
        this.jyzxt_sjwjxf_wx = jyzxt_sjwjxf_wx;
    }

    public void setJyzxt_dcyctgfptkwj_gz(int jyzxt_dcyctgfptkwj_gz) {
        this.jyzxt_dcyctgfptkwj_gz = jyzxt_dcyctgfptkwj_gz;
    }

    public void setJyzxt_dcyctgfptkwj_wx(int jyzxt_dcyctgfptkwj_wx) {
        this.jyzxt_dcyctgfptkwj_wx = jyzxt_dcyctgfptkwj_wx;
    }

    public void setJyzxt_cpwjrk_gz(int jyzxt_cpwjrk_gz) {
        this.jyzxt_cpwjrk_gz = jyzxt_cpwjrk_gz;
    }

    public void setJyzxt_cpwjrk_wx(int jyzxt_cpwjrk_wx) {
        this.jyzxt_cpwjrk_wx = jyzxt_cpwjrk_wx;
    }

    public void setJyzxt_xfhmd_gz(int jyzxt_xfhmd_gz) {
        this.jyzxt_xfhmd_gz = jyzxt_xfhmd_gz;
    }

    public void setJyzxt_xfhmd_wx(int jyzxt_xfhmd_wx) {
        this.jyzxt_xfhmd_wx = jyzxt_xfhmd_wx;
    }

    public void setJyzxt_jsxwygjrickpj_gz(int jyzxt_jsxwygjrickpj_gz) {
        this.jyzxt_jsxwygjrickpj_gz = jyzxt_jsxwygjrickpj_gz;
    }

    public void setJyzxt_jsxwygjrickpj_wx(int jyzxt_jsxwygjrickpj_wx) {
        this.jyzxt_jsxwygjrickpj_wx = jyzxt_jsxwygjrickpj_wx;
    }

    public void setJyzxt_bfbdwj_gz(int jyzxt_bfbdwj_gz) {
        this.jyzxt_bfbdwj_gz = jyzxt_bfbdwj_gz;
    }

    public void setJyzxt_bfbdwj_wx(int jyzxt_bfbdwj_wx) {
        this.jyzxt_bfbdwj_wx = jyzxt_bfbdwj_wx;
    }

    public void setJyzxt_bbtj_gz(int jyzxt_bbtj_gz) {
        this.jyzxt_bbtj_gz = jyzxt_bbtj_gz;
    }

    public void setJyzxt_bbtj_wx(int jyzxt_bbtj_wx) {
        this.jyzxt_bbtj_wx = jyzxt_bbtj_wx;
    }

    public void setJyzxt_dcyctgfcpukwj_gz(int jyzxt_dcyctgfcpukwj_gz) {
        this.jyzxt_dcyctgfcpukwj_gz = jyzxt_dcyctgfcpukwj_gz;
    }

    public void setJyzxt_dcyctgfcpukwj_wx(int jyzxt_dcyctgfcpukwj_wx) {
        this.jyzxt_dcyctgfcpukwj_wx = jyzxt_dcyctgfcpukwj_wx;
    }

    public void setJyzxt_dcyctygptkwj_gz(int jyzxt_dcyctygptkwj_gz) {
        this.jyzxt_dcyctygptkwj_gz = jyzxt_dcyctygptkwj_gz;
    }

    public void setJyzxt_dcyctygptkwj_wx(int jyzxt_dcyctygptkwj_wx) {
        this.jyzxt_dcyctygptkwj_wx = jyzxt_dcyctygptkwj_wx;
    }

    public void setJyzxt_dcyctygcpukwj_gz(int jyzxt_dcyctygcpukwj_gz) {
        this.jyzxt_dcyctygcpukwj_gz = jyzxt_dcyctygcpukwj_gz;
    }

    public void setJyzxt_dcyctygcpukwj_wx(int jyzxt_dcyctygcpukwj_wx) {
        this.jyzxt_dcyctygcpukwj_wx = jyzxt_dcyctygcpukwj_wx;
    }

    public void setJyzxt_dcygjrickjywj_gz(int jyzxt_dcygjrickjywj_gz) {
        this.jyzxt_dcygjrickjywj_gz = jyzxt_dcygjrickjywj_gz;
    }

    public void setJyzxt_dcygjrickjywj_wx(int jyzxt_dcygjrickjywj_wx) {
        this.jyzxt_dcygjrickjywj_wx = jyzxt_dcygjrickjywj_wx;
    }

    public void setJyzxt_dcjricktkwj_gz(int jyzxt_dcjricktkwj_gz) {
        this.jyzxt_dcjricktkwj_gz = jyzxt_dcjricktkwj_gz;
    }

    public void setJyzxt_dcjricktkwj_wx(int jyzxt_dcjricktkwj_wx) {
        this.jyzxt_dcjricktkwj_wx = jyzxt_dcjricktkwj_wx;
    }

    public void setJyzxt_dcjrickjywj_gz(int jyzxt_dcjrickjywj_gz) {
        this.jyzxt_dcjrickjywj_gz = jyzxt_dcjrickjywj_gz;
    }

    public void setJyzxt_dcjrickjywj_wx(int jyzxt_dcjrickjywj_wx) {
        this.jyzxt_dcjrickjywj_wx = jyzxt_dcjrickjywj_wx;
    }

    public void setJyzxt_dcyctqtxlptkwj_gz(int jyzxt_dcyctqtxlptkwj_gz) {
        this.jyzxt_dcyctqtxlptkwj_gz = jyzxt_dcyctqtxlptkwj_gz;
    }

    public void setJyzxt_dcyctqtxlptkwj_wx(int jyzxt_dcyctqtxlptkwj_wx) {
        this.jyzxt_dcyctqtxlptkwj_wx = jyzxt_dcyctqtxlptkwj_wx;
    }

    public void setJyzxt_djczcwbfcfsj_gz(int jyzxt_djczcwbfcfsj_gz) {
        this.jyzxt_djczcwbfcfsj_gz = jyzxt_djczcwbfcfsj_gz;
    }

    public void setJyzxt_djczcwbfcfsj_wx(int jyzxt_djczcwbfcfsj_wx) {
        this.jyzxt_djczcwbfcfsj_wx = jyzxt_djczcwbfcfsj_wx;
    }

    public void setJyzxt_sjyctj_gz(int jyzxt_sjyctj_gz) {
        this.jyzxt_sjyctj_gz = jyzxt_sjyctj_gz;
    }

    public void setJyzxt_sjyctj_wx(int jyzxt_sjyctj_wx) {
        this.jyzxt_sjyctj_wx = jyzxt_sjyctj_wx;
    }

    public void setJyzxt_sytjmk_gz(int jyzxt_sytjmk_gz) {
        this.jyzxt_sytjmk_gz = jyzxt_sytjmk_gz;
    }

    public void setJyzxt_sytjmk_wx(int jyzxt_sytjmk_wx) {
        this.jyzxt_sytjmk_wx = jyzxt_sytjmk_wx;
    }

    public void setJyzxt_jsmk_gz(int jyzxt_jsmk_gz) {
        this.jyzxt_jsmk_gz = jyzxt_jsmk_gz;
    }

    public void setJyzxt_jsmk_wx(int jyzxt_jsmk_wx) {
        this.jyzxt_jsmk_wx = jyzxt_jsmk_wx;
    }

    public void setJyzxt_klcjfx_gz(int jyzxt_klcjfx_gz) {
        this.jyzxt_klcjfx_gz = jyzxt_klcjfx_gz;
    }

    public void setJyzxt_klcjfx_wx(int jyzxt_klcjfx_wx) {
        this.jyzxt_klcjfx_wx = jyzxt_klcjfx_wx;
    }

    public void setJyzxt_ccjcqsjtjbb_gz(int jyzxt_ccjcqsjtjbb_gz) {
        this.jyzxt_ccjcqsjtjbb_gz = jyzxt_ccjcqsjtjbb_gz;
    }

    public void setJyzxt_ccjcqsjtjbb_wx(int jyzxt_ccjcqsjtjbb_wx) {
        this.jyzxt_ccjcqsjtjbb_wx = jyzxt_ccjcqsjtjbb_wx;
    }

    public void setJyzxt_ccyyssybb_gz(int jyzxt_ccyyssybb_gz) {
        this.jyzxt_ccyyssybb_gz = jyzxt_ccyyssybb_gz;
    }

    public void setJyzxt_ccyyssybb_wx(int jyzxt_ccyyssybb_wx) {
        this.jyzxt_ccyyssybb_wx = jyzxt_ccyyssybb_wx;
    }

    public void setJyzxt_fbrzjl_gz(int jyzxt_fbrzjl_gz) {
        this.jyzxt_fbrzjl_gz = jyzxt_fbrzjl_gz;
    }

    public void setJyzxt_fbrzjl_wx(int jyzxt_fbrzjl_wx) {
        this.jyzxt_fbrzjl_wx = jyzxt_fbrzjl_wx;
    }

    public void setJyzxt_jrickdzbb_gz(int jyzxt_jrickdzbb_gz) {
        this.jyzxt_jrickdzbb_gz = jyzxt_jrickdzbb_gz;
    }

    public void setJyzxt_jrickdzbb_wx(int jyzxt_jrickdzbb_wx) {
        this.jyzxt_jrickdzbb_wx = jyzxt_jrickdzbb_wx;
    }

    public void setJyzxt_yctdzbb_gz(int jyzxt_yctdzbb_gz) {
        this.jyzxt_yctdzbb_gz = jyzxt_yctdzbb_gz;
    }

    public void setJyzxt_yctdzbb_wx(int jyzxt_yctdzbb_wx) {
        this.jyzxt_yctdzbb_wx = jyzxt_yctdzbb_wx;
    }

    public void setJyzxt_drllb_gz(int jyzxt_drllb_gz) {
        this.jyzxt_drllb_gz = jyzxt_drllb_gz;
    }

    public void setJyzxt_drllb_wx(int jyzxt_drllb_wx) {
        this.jyzxt_drllb_wx = jyzxt_drllb_wx;
    }







    @Column(name = "jyzxt_cshdrkftpwj_gz")
    public int getJyzxt_cshdrkftpwj_gz() {
        return jyzxt_cshdrkftpwj_gz;
    }
    @Column(name = "jyzxt_cshdrkftpwj_wx")
    public int getJyzxt_cshdrkftpwj_wx() {
        return jyzxt_cshdrkftpwj_wx;
    }
    @Column(name = "jyzxt_dcyctqtxlcpukwj_gz")
    public int getJyzxt_dcyctqtxlcpukwj_gz() {
        return jyzxt_dcyctqtxlcpukwj_gz;
    }
    @Column(name = "jyzxt_dcyctqtxlcpukwj_wx")
    public int getJyzxt_dcyctqtxlcpukwj_wx() {
        return jyzxt_dcyctqtxlcpukwj_wx;
    }
    @Column(name = "jyzxt_sjwjxf_gz")
    public int getJyzxt_sjwjxf_gz() {
        return jyzxt_sjwjxf_gz;
    }
    @Column(name = "jyzxt_sjwjxf_wx")
    public int getJyzxt_sjwjxf_wx() {
        return jyzxt_sjwjxf_wx;
    }
    @Column(name = "jyzxt_dcyctgfptkwj_gz")
    public int getJyzxt_dcyctgfptkwj_gz() {
        return jyzxt_dcyctgfptkwj_gz;
    }
    @Column(name = "jyzxt_dcyctgfptkwj_wx")
    public int getJyzxt_dcyctgfptkwj_wx() {
        return jyzxt_dcyctgfptkwj_wx;
    }
    @Column(name = "jyzxt_cpwjrk_gz")
    public int getJyzxt_cpwjrk_gz() {
        return jyzxt_cpwjrk_gz;
    }
    @Column(name = "jyzxt_cpwjrk_wx")
    public int getJyzxt_cpwjrk_wx() {
        return jyzxt_cpwjrk_wx;
    }
    @Column(name = "jyzxt_xfhmd_gz")
    public int getJyzxt_xfhmd_gz() {
        return jyzxt_xfhmd_gz;
    }
    @Column(name = "jyzxt_xfhmd_wx")
    public int getJyzxt_xfhmd_wx() {
        return jyzxt_xfhmd_wx;
    }
    @Column(name = "jyzxt_jsxwygjrickpj_gz")
    public int getJyzxt_jsxwygjrickpj_gz() {
        return jyzxt_jsxwygjrickpj_gz;
    }
    @Column(name = "jyzxt_jsxwygjrickpj_wx")
    public int getJyzxt_jsxwygjrickpj_wx() {
        return jyzxt_jsxwygjrickpj_wx;
    }
    @Column(name = "jyzxt_bfbdwj_gz")
    public int getJyzxt_bfbdwj_gz() {
        return jyzxt_bfbdwj_gz;
    }
    @Column(name = "jyzxt_bfbdwj_wx")
    public int getJyzxt_bfbdwj_wx() {
        return jyzxt_bfbdwj_wx;
    }
    @Column(name = "jyzxt_bbtj_gz")
    public int getJyzxt_bbtj_gz() {
        return jyzxt_bbtj_gz;
    }
    @Column(name = "jyzxt_bbtj_wx")
    public int getJyzxt_bbtj_wx() {
        return jyzxt_bbtj_wx;
    }
    @Column(name = "jyzxt_dcyctgfcpukwj_gz")
    public int getJyzxt_dcyctgfcpukwj_gz() {
        return jyzxt_dcyctgfcpukwj_gz;
    }
    @Column(name = "jyzxt_dcyctgfcpukwj_wx")
    public int getJyzxt_dcyctgfcpukwj_wx() {
        return jyzxt_dcyctgfcpukwj_wx;
    }
    @Column(name = "jyzxt_dcyctygptkwj_gz")
    public int getJyzxt_dcyctygptkwj_gz() {
        return jyzxt_dcyctygptkwj_gz;
    }
    @Column(name = "jyzxt_dcyctygptkwj_wx")
    public int getJyzxt_dcyctygptkwj_wx() {
        return jyzxt_dcyctygptkwj_wx;
    }
    @Column(name = "jyzxt_dcyctygcpukwj_gz")
    public int getJyzxt_dcyctygcpukwj_gz() {
        return jyzxt_dcyctygcpukwj_gz;
    }
    @Column(name = "jyzxt_dcyctygcpukwj_wx")
    public int getJyzxt_dcyctygcpukwj_wx() {
        return jyzxt_dcyctygcpukwj_wx;
    }
    @Column(name = "jyzxt_dcygjrickjywj_gz")
    public int getJyzxt_dcygjrickjywj_gz() {
        return jyzxt_dcygjrickjywj_gz;
    }
    @Column(name = "jyzxt_dcygjrickjywj_wx")
    public int getJyzxt_dcygjrickjywj_wx() {
        return jyzxt_dcygjrickjywj_wx;
    }
    @Column(name = "jyzxt_dcjricktkwj_gz")
    public int getJyzxt_dcjricktkwj_gz() {
        return jyzxt_dcjricktkwj_gz;
    }
    @Column(name = "jyzxt_dcjricktkwj_wx")
    public int getJyzxt_dcjricktkwj_wx() {
        return jyzxt_dcjricktkwj_wx;
    }
    @Column(name = "jyzxt_dcjrickjywj_gz")
    public int getJyzxt_dcjrickjywj_gz() {
        return jyzxt_dcjrickjywj_gz;
    }
    @Column(name = "jyzxt_dcjrickjywj_wx")
    public int getJyzxt_dcjrickjywj_wx() {
        return jyzxt_dcjrickjywj_wx;
    }
    @Column(name = "jyzxt_dcyctqtxlptkwj_gz")
    public int getJyzxt_dcyctqtxlptkwj_gz() {
        return jyzxt_dcyctqtxlptkwj_gz;
    }
    @Column(name = "jyzxt_dcyctqtxlptkwj_wx")
    public int getJyzxt_dcyctqtxlptkwj_wx() {
        return jyzxt_dcyctqtxlptkwj_wx;
    }
    @Column(name = "jyzxt_djczcwbfcfsj_gz")
    public int getJyzxt_djczcwbfcfsj_gz() {
        return jyzxt_djczcwbfcfsj_gz;
    }
    @Column(name = "jyzxt_djczcwbfcfsj_wx")
    public int getJyzxt_djczcwbfcfsj_wx() {
        return jyzxt_djczcwbfcfsj_wx;
    }
    @Column(name = "jyzxt_sjyctj_gz")
    public int getJyzxt_sjyctj_gz() {
        return jyzxt_sjyctj_gz;
    }
    @Column(name = "jyzxt_sjyctj_wx")
    public int getJyzxt_sjyctj_wx() {
        return jyzxt_sjyctj_wx;
    }
    @Column(name = "jyzxt_sytjmk_gz")
    public int getJyzxt_sytjmk_gz() {
        return jyzxt_sytjmk_gz;
    }
    @Column(name = "jyzxt_sytjmk_wx")
    public int getJyzxt_sytjmk_wx() {
        return jyzxt_sytjmk_wx;
    }
    @Column(name = "jyzxt_jsmk_gz")
    public int getJyzxt_jsmk_gz() {
        return jyzxt_jsmk_gz;
    }
    @Column(name = "jyzxt_jsmk_wx")
    public int getJyzxt_jsmk_wx() {
        return jyzxt_jsmk_wx;
    }
    @Column(name = "jyzxt_klcjfx_gz")
    public int getJyzxt_klcjfx_gz() {
        return jyzxt_klcjfx_gz;
    }
    @Column(name = "jyzxt_klcjfx_wx")
    public int getJyzxt_klcjfx_wx() {
        return jyzxt_klcjfx_wx;
    }
    @Column(name = "jyzxt_ccjcqsjtjbb_gz")
    public int getJyzxt_ccjcqsjtjbb_gz() {
        return jyzxt_ccjcqsjtjbb_gz;
    }
    @Column(name = "jyzxt_ccjcqsjtjbb_wx")
    public int getJyzxt_ccjcqsjtjbb_wx() {
        return jyzxt_ccjcqsjtjbb_wx;
    }
    @Column(name = "jyzxt_ccyyssybb_gz")
    public int getJyzxt_ccyyssybb_gz() {
        return jyzxt_ccyyssybb_gz;
    }
    @Column(name = "jyzxt_ccyyssybb_wx")
    public int getJyzxt_ccyyssybb_wx() {
        return jyzxt_ccyyssybb_wx;
    }
    @Column(name = "jyzxt_fbrzjl_gz")
    public int getJyzxt_fbrzjl_gz() {
        return jyzxt_fbrzjl_gz;
    }
    @Column(name = "jyzxt_fbrzjl_wx")
    public int getJyzxt_fbrzjl_wx() {
        return jyzxt_fbrzjl_wx;
    }
    @Column(name = "jyzxt_jrickdzbb_gz")
    public int getJyzxt_jrickdzbb_gz() {
        return jyzxt_jrickdzbb_gz;
    }
    @Column(name = "jyzxt_jrickdzbb_wx")
    public int getJyzxt_jrickdzbb_wx() {
        return jyzxt_jrickdzbb_wx;
    }
    @Column(name = "jyzxt_yctdzbb_gz")
    public int getJyzxt_yctdzbb_gz() {
        return jyzxt_yctdzbb_gz;
    }
    @Column(name = "jyzxt_yctdzbb_wx")
    public int getJyzxt_yctdzbb_wx() {
        return jyzxt_yctdzbb_wx;
    }
    @Column(name = "jyzxt_drllb_gz")
    public int getJyzxt_drllb_gz() {
        return jyzxt_drllb_gz;
    }
    @Column(name = "jyzxt_drllb_wx")
    public int getJyzxt_drllb_wx() {
        return jyzxt_drllb_wx;
    }
}
