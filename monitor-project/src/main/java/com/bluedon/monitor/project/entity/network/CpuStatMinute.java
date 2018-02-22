package com.bluedon.monitor.project.entity.network;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author JiangFeng
 * @date 2018/2/7
 * @Description
 */
@Entity
@Table(name = "cpu_stat_minute")
@DynamicInsert
@DynamicUpdate
public class CpuStatMinute {
    @Id
    private long id;
    @Column(name = "server_info_id")
    private long serverInfoId;
    @Column(name = "user")
    private double user;
    @Column(name = "system")
    private double system;
    @Column(name = "io")
    private double io;
    @Column(name = "idle")
    private double idle;
    @Column(name = "stat_begin_tm")
    private Date statBeginTm;
    @Column(name = "stat_end_tm")
    private Date statEndTm;
    @Column(name = "create_date")
    private Date createDate;

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

    public double getUser() {
        return user;
    }

    public void setUser(double user) {
        this.user = user;
    }

    public double getSystem() {
        return system;
    }

    public void setSystem(double system) {
        this.system = system;
    }

    public double getIo() {
        return io;
    }

    public void setIo(double io) {
        this.io = io;
    }

    public double getIdle() {
        return idle;
    }

    public void setIdle(double idle) {
        this.idle = idle;
    }

    public Date getStatBeginTm() {
        return statBeginTm;
    }

    public void setStatBeginTm(Date statBeginTm) {
        this.statBeginTm = statBeginTm;
    }

    public Date getStatEndTm() {
        return statEndTm;
    }

    public void setStatEndTm(Date statEndTm) {
        this.statEndTm = statEndTm;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
