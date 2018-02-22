package com.bluedon.monitor.project.entity.network;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * @author JiangFeng
 * @date 2018/1/29
 * @Description  snmp信息表
 */
@Table(name="snmp_info")
@Entity
@DynamicInsert
@DynamicUpdate
public class SnmpInfo implements Serializable{
    public SnmpInfo(){
    }
    public SnmpInfo(long id, long serverInfoId, String serverIp, int port, String version, String user, String passwd, int state) {
        this.id = id;
        this.serverInfoId = serverInfoId;
        this.serverIp = serverIp;
        this.port = port;
        this.version = version;
        this.user = user;
        this.passwd = passwd;
        this.state = state;
    }

    @Id
    private long id;
    /*服务器ID，关联网络设备表的ID*/
    @Column(name = "server_info_id")
    private long serverInfoId;
    /*服务器ip*/
    @Column(name = "server_ip")
    private String serverIp;
    /*snmp开放端口*/
    @Column(name = "port")
    private int port;
    /*SNMP版本*/
    @Column(name = "version")
    private String version;
    /*SNMP认证用户*/
    @Column(name = "user")
    private String user;
    /*3版本：SNMP认证密码，1和2c版本: 团体名*/
    @Column(name = "passwd")
    private String passwd;
    /*认证协议*/
    @Column(name = "auth_version")
    private int authVersion;
    /*snmp状态 1启用 0停用*/
    @Column(name = "state")
    private int state;

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

    public long getServerInfoId() {
        return serverInfoId;
    }

    public void setServerInfoId(long serverInfoId) {
        this.serverInfoId = serverInfoId;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getAuthVersion() {
        return authVersion;
    }

    public void setAuthVersion(int authVersion) {
        this.authVersion = authVersion;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
