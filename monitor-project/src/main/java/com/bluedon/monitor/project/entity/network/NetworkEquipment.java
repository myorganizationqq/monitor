package com.bluedon.monitor.project.entity.network;

import com.bluedon.monitor.common.entity.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author JiangFeng
 * @date 2017/12/13
 * @Description 网络设备实体表
 */
@Entity
@Table(name="network_equipment")
@DynamicInsert
@DynamicUpdate
public class NetworkEquipment extends BaseEntity{
    @Id
    private long id;
    /*作用域*/
    @Column(name = "scope")
    private String scope;
    //设备名称*/
    @Column(name = "equipment_name")
    private String equipmentName;
    /*设备型号*/
    @Column(name = "equipment_version")
    private String equipmentVersion;
    /*ip*/
    @Column(name = "ip")
    private String ip;
    /*子网掩码*/
    @Column(name = "subnet_mark")
    private String subnetMark;
    /*网关*/
    @Column(name = "gateway")
    private String gateway;
    /*主机名*/
    @Column(name = "host_name")
    private String hostName;
    /*备注说明*/
    @Column(name = "remark")
    private String remark;
    /*类型*/
    @Column(name = "type")
    private String type;
    /*系统or厂商*/
    @Column(name = "os_type")
    private int osType;
   /* @Column(name = "cpu")
    private String cpu;
    *//**内存*//*
    @Column(name = "memory")
    private int memory;*/
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "create_by")
    private int createBy;
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "update_by")
    private int updateBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentVersion() {
        return equipmentVersion;
    }

    public void setEquipmentVersion(String equipmentVersion) {
        this.equipmentVersion = equipmentVersion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSubnetMark() {
        return subnetMark;
    }

    public void setSubnetMark(String subnetMark) {
        this.subnetMark = subnetMark;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }
}
