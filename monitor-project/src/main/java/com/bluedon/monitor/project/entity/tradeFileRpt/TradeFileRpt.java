package com.bluedon.monitor.project.entity.tradeFileRpt;

import com.bluedon.monitor.common.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author JiangFeng
 * @date 2017/11/14
 */
@Table(name="trade_file_rpt")
@Entity
@DynamicInsert
@DynamicUpdate
public class TradeFileRpt extends BaseEntity {
    private long id;
    /*清算流水号*/
    private String balanceWaterNo;
    /*文件类型*/
    private String fileType;
    /*文件数量*/
    private int fileCount;
    /*处理数量*/
    private int handleCount;
    /*不合法数据*/
    private int wrongfulCount;
    /*重复数据*/
    private int duplicateCount;
    /*无法预处理的数据*/
    private int noPretreatmentCount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "balance_water_no")
    public String getBalanceWaterNo() {
        return balanceWaterNo;
    }

    public void setBalanceWaterNo(String balanceWaterNo) {
        this.balanceWaterNo = balanceWaterNo;
    }
    @Column(name = "file_type")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    @Column(name = "file_count")
    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }
    @Column(name = "handle_count")
    public int getHandleCount() {
        return handleCount;
    }

    public void setHandleCount(int handleCount) {
        this.handleCount = handleCount;
    }
    @Column(name = "wrongful_count")
    public int getWrongfulCount() {
        return wrongfulCount;
    }

    public void setWrongfulCount(int wrongfulCount) {
        this.wrongfulCount = wrongfulCount;
    }
    @Column(name = "duplicate_count")
    public int getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(int duplicateCount) {
        this.duplicateCount = duplicateCount;
    }
    @Column(name = "no_pretreatment_count")
    public int getNoPretreatmentCount() {
        return noPretreatmentCount;
    }

    public void setNoPretreatmentCount(int noPretreatmentCount) {
        this.noPretreatmentCount = noPretreatmentCount;
    }
}
