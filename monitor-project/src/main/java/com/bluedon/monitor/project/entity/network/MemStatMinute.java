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
@Table(name = "mem_stat_minute")
@DynamicUpdate
@DynamicInsert
public class MemStatMinute {
    @Id
    private long id;
    @Column(name = "server_info_id")
    private long serverInfoId;
    @Column(name = "totalreal")
    private double totalreal;
    @Column(name = "availreal")
    private double availreal;
    @Column(name = "userage_rate")
    private double userageRate;
    @Column(name = "buffer")
    private double buffer;
    @Column(name = "cache")
    private double cache;
    @Column(name = "totalswap")
    private double totalswap;
    @Column(name = "availswap")
    private double availswap;
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

    public double getTotalreal() {
        return totalreal;
    }

    public void setTotalreal(double totalreal) {
        this.totalreal = totalreal;
    }

    public double getAvailreal() {
        return availreal;
    }

    public void setAvailreal(double availreal) {
        this.availreal = availreal;
    }

    public double getUserageRate() {
        return userageRate;
    }

    public void setUserageRate(double userageRate) {
        this.userageRate = userageRate;
    }

    public double getBuffer() {
        return buffer;
    }

    public void setBuffer(double buffer) {
        this.buffer = buffer;
    }

    public double getCache() {
        return cache;
    }

    public void setCache(double cache) {
        this.cache = cache;
    }

    public double getTotalswap() {
        return totalswap;
    }

    public void setTotalswap(double totalswap) {
        this.totalswap = totalswap;
    }

    public double getAvailswap() {
        return availswap;
    }

    public void setAvailswap(double availswap) {
        this.availswap = availswap;
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
