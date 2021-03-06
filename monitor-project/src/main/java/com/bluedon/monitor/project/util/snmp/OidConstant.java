package com.bluedon.monitor.project.util.snmp;

/**
 * @author JiangFeng
 * @date 2018/1/23
 * @Description OID常量类
 */
public class OidConstant {
    public enum Window{

    }

    public enum H3C{
        /*用户cpu使用率*/
        ssCpuUser(".1.3.6.1.4.1.25506.2.6.1.1.1.1.6"),
        /*内存使用率*/
        memUser(".1.3.6.1.4.1.25506.2.6.1.1.1.1.8");

        private String value;
        private H3C(String value){
            this.value=value;
        }
        public String getValue(){
            return this.value;
        }
    }

    public enum Linux{
        //------------------------get------------------------------------------------
        /*用户cpu使用率*/
        ssCpuUser(".1.3.6.1.4.1.2021.11.9.0"),
        /*系统cpu使用率*/
        ssCpuSystem(".1.3.6.1.4.1.2021.11.10.0"),
        /*空闲cpu*/
        ssCpuIdle(".1.3.6.1.4.1.2021.11.11.0"),
        /*cpu负载,包括1分钟,5分钟,15分钟三条*/
        laload(".1.3.6.1.4.1.2021.10.1.3"),

        //--------------------------get----------------------------------------------------------
        /*原始用户CPU使用时间*/
        ssCpuRawUser(".1.3.6.1.4.1.2021.11.50.0"),
        /*原始系统CPU使用时间*/
        ssCpuRawSystem(".1.3.6.1.4.1.2021.11.52.0"),
        /*原始nice占用时间*/
        ssCpuRawNice(".1.3.6.1.4.1.2021.11.51.0"),
        ssCpuRawInterrupt(".1.3.6.1.4.1.2021.11.56.0"),
        ssCpuRawSoftIRQ(".1.3.6.1.4.1.2021.11.61.0"),
        ssCpuRawWait(".1.3.6.1.4.1.2021.11.54.0"),
        ssCpuRawKernel(""),

        //--------------------------walk-----------------------------------------------------
        /*系统运行进程列表*/
        hrSWRunName(".1.3.6.1.2.1.25.4.2.1.2"),
        /*系统运行进程列表*/
        hrSWRunPerfCPU(".1.3.6.1.2.1.25.5.1.1.1"),
        /*系统运行进程列表*/
        hrSWRunPerfMem(".1.3.6.1.2.1.25.5.1.1.2"),

        //--------------------------get------------------------------------------------------
        /*总虚拟内存*/
        memTotalSwap(".1.3.6.1.4.1.2021.4.3.0"),
        /*可使用虚拟内存*/
        memAvailSwap(".1.3.6.1.4.1.2021.4.4.0"),
        /*总物理内存*/
        memTotalReal(".1.3.6.1.4.1.2021.4.5.0"),
        /*可使用内存*/
        memAvailReal(".1.3.6.1.4.1.2021.4.6.0"),
        /*内存buffer */
        memBuffer(".1.3.6.1.4.1.2021.4.14.0"),
        /*内存cached*/
        memCached(".1.3.6.1.4.1.2021.4.15.0"),

        //----------------------get---------------------------------------------------------
        /*接口数目*/
        ifNumber(".1.3.6.1.2.1.2.1.0"),
        //----------------------walk-------------------------------------------------------
        /*接口名称*/
        ifDescr(".1.3.6.1.2.1.2.2.1.2"),
        /*接口类型*/
        ifType(".1.3.6.1.2.1.2.2.1.3"),
        /*网络传输最大数据*/
        IfMTU(".1.3.6.1.2.1.2.2.1.4"),
        /*接口当前带宽*/
        IfSpeed(".1.3.6.1.2.1.2.2.1.5"),
        /*
         以上指令需要执行两次，第一次获得的总流入字节数 firInOcts 单位字节，第二次获得总流入字节数 secInOcts 单位字节。
         输出：每秒流入网卡数据
         ifIncctets  =（secInOcts – firInOcts）除以 1024 乘以 8 除以 两次时间差（单位秒）
         计算后结果转换成kbps输出
         */
        ifInOctets(".1.3.6.1.2.1.2.2.1.10"),
        /*输出(发送)网卡数据*/
        ifOutOctets(".1.3.6.1.2.1.2.2.1.16"),
        /*流入网卡包数*/
        ifInUcastPkts(".1.3.6.1.2.1.2.2.1.11"),
        /*流出网卡包数*/
        ifOutUcastPkts(".1.3.6.1.2.1.2.2.1.17"),
        /*流入错误包数*/
        ifInErrors(".1.3.6.1.2.1.2.2.1.14"),
        /*流出错误包数*/
        ifOutErrors(".1.3.6.1.2.1.2.2.1.20"),
        /*流入丢包数*/
        ifInDiscards(".1.3.6.1.2.1.2.2.1.13"),
        /*流出丢包数*/
        ifOutDiscards(".1.3.6.1.2.1.2.2.1.19"),

        //--------------------------------------walk--------------------------------------------------------------
        /*输出磁盘大小*/
        hrStorageSize(".1.3.6.1.2.1.25.2.3.1.5"),
        /*已使用磁盘大小*/
        hrStorageUsed(".1.3.6.1.2.1.25.2.3.1.6");

        private String value;
        private Linux(String value){
            this.value=value;
        }
        public String getValue(){
            return this.value;
        }
    }
}
