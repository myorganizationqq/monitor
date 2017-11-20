package com.bluedon.monitor.project.entity.transferTable;

import com.bluedon.monitor.common.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 *@author  JiangFeng
 * on 2017/11/10.
 */
@Entity
@Table(name = "TRANSFER_TABLE")
@DynamicInsert
@DynamicUpdate
public class TransferTable extends BaseEntity{
    /*输入表名*/
    private String impTableName;
    /*输出表名*/
    private String expTableName;
    /*查询列*/
    private String tableColumn;
    /*查询需要的sql*/
    private String extraSql;
    /*状态 0 不可用 1 可用 */
    private int stat;
    /*查类型,用来区分不同表的不同处理*/
    private String tableType;
    public TransferTable() {
    }
    public TransferTable(String impTableName, String expTableName, String tableColumn, String extraSql, int stat, String tableType) {
        this.impTableName = impTableName;
        this.expTableName = expTableName;
        this.tableColumn = tableColumn;
        this.extraSql = extraSql;
        this.stat = stat;
        this.tableType = tableType;
    }

    /*查询列个数 存变量用*/
    private int columnNum;
    @Column(name="STAT")
    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
    @Id
    @Column(name="IMP_TABLE_NAME")
    public String getImpTableName() {
        return impTableName;
    }

    public void setImpTableName(String impTableName) {
        this.impTableName = impTableName;
    }
    @Column(name="EXP_TABLE_NAME")
    public String getExpTableName() {
        return expTableName;
    }

    public void setExpTableName(String expTableName) {
        this.expTableName = expTableName;
    }
    @Column(name="TABLE_COLUMN")
    public String getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }
    @Column(name="EXTRA_SQL")
    public String getExtraSql() {
        return extraSql;
    }

    public void setExtraSql(String extraSql) {
        this.extraSql = extraSql;
    }
    @Column(name="TABLE_TYPE")
    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

}
