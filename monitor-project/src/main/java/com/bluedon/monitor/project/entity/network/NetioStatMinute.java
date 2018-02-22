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
 * @date 2018/2/2
 * @Description
 */
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="netio_stat_minute")
public class NetioStatMinute {
    @Id
    private long id;
    @Column(name="server_info_id")
    private long serverInfoId;
    @Column(name = "netioname")
    private String netioname;
    @Column(name = "ifmtu")
    private long ifmtu;
    @Column(name = "ifspeed")
    private double ifspeed;
    @Column(name = "ifinoctets")
    private double ifinoctets;
    @Column(name = "ifoutoctets")
    private double ifoutoctets;
    @Column(name = "ifinucastpkts")
    private long ifinucastpkts;
    @Column(name = "ifoutucastpkts")
    private long ifoutucastpkts;
    @Column(name = "ifinerrors")
    private long ifinerrors;
    @Column(name = "ifouterrors")
    private long ifouterrors;
    @Column(name = "ifInDiscards")
    private long ifInDiscards;
    @Column(name = "ifOutDiscards")
    private long ifOutDiscards;
    @Column(name = "stat_begin_tm")
    private Date statBeginTm;
    @Column(name="stat_end_tm")
    private Date statEndTm;
    @Column(name="create_date")
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

    public String getNetioname() {
        return netioname;
    }

    public void setNetioname(String netioname) {
        this.netioname = netioname;
    }

    public long getIfmtu() {
        return ifmtu;
    }

    public void setIfmtu(long ifmtu) {
        this.ifmtu = ifmtu;
    }

    public double getIfspeed() {
        return ifspeed;
    }

    public void setIfspeed(double ifspeed) {
        this.ifspeed = ifspeed;
    }

    public double getIfinoctets() {
        return ifinoctets;
    }

    public void setIfinoctets(double ifinoctets) {
        this.ifinoctets = ifinoctets;
    }

    public double getIfoutoctets() {
        return ifoutoctets;
    }

    public void setIfoutoctets(double ifoutoctets) {
        this.ifoutoctets = ifoutoctets;
    }

    public long getIfinucastpkts() {
        return ifinucastpkts;
    }

    public void setIfinucastpkts(long ifinucastpkts) {
        this.ifinucastpkts = ifinucastpkts;
    }

    public long getIfoutucastpkts() {
        return ifoutucastpkts;
    }

    public void setIfoutucastpkts(long ifoutucastpkts) {
        this.ifoutucastpkts = ifoutucastpkts;
    }

    public long getIfinerrors() {
        return ifinerrors;
    }

    public void setIfinerrors(long ifinerrors) {
        this.ifinerrors = ifinerrors;
    }

    public long getIfouterrors() {
        return ifouterrors;
    }

    public void setIfouterrors(long ifouterrors) {
        this.ifouterrors = ifouterrors;
    }

    public long getIfInDiscards() {
        return ifInDiscards;
    }

    public void setIfInDiscards(long ifInDiscards) {
        this.ifInDiscards = ifInDiscards;
    }

    public long getIfOutDiscards() {
        return ifOutDiscards;
    }

    public void setIfOutDiscards(long ifOutDiscards) {
        this.ifOutDiscards = ifOutDiscards;
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
