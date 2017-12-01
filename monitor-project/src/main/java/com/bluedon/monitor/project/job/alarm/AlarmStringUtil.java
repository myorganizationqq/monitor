package com.bluedon.monitor.project.job.alarm;

import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.stSysFlowCurrentDt.StSysFlowCurrentDt;

public class AlarmStringUtil {
	
	//毫秒转为分钟的除数
    static int m = 1000 * 60;
	
    /**
     * 清算子系统_生成寄存器数据统计报表
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void ccjcqsjtjbb(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_ccjcqsjtjbb_wx() != 0 && t.getRemark().equals("生成寄存器数据统计报表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_ccjcqsjtjbb_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_ccjcqsjtjbb_gz() != 0 && t.getRemark().equals("生成寄存器数据统计报表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_ccjcqsjtjbb_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_金融IC卡黑名单
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void jrickhmd(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_jrickhmd_wx() != 0 && t.getRemark().equals("金融IC卡黑名单")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_jrickhmd_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_jrickhmd_gz() != 0 && t.getRemark().equals("金融IC卡黑名单")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_jrickhmd_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_初始化羊城通导出
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void cshyctdc(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_cshyctdc_wx() != 0 && t.getRemark().equals("初始化羊城通导出")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_cshyctdc_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_cshyctdc_gz() != 0 && t.getRemark().equals("初始化羊城通导出")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_cshyctdc_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_表清理
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void bql(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_bql_wx() != 0 && t.getRemark().equals("表清理")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_bql_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_bql_gz() != 0 && t.getRemark().equals("表清理")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_bql_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_初始化待入库FTP文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void cshdrkftpwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_cshdrkftpwj_wx() != 0 && t.getRemark().equals("初始化待入库FTP文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_cshdrkftpwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_cshdrkftpwj_gz() != 0 && t.getRemark().equals("初始化待入库FTP文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_cshdrkftpwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出羊城通其他线路CPU卡文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcyctqtxlcpukwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcyctqtxlcpukwj_wx() != 0 && t.getRemark().equals("导出羊城通其他线路CPU卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctqtxlcpukwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcyctqtxlcpukwj_gz() != 0 && t.getRemark().equals("导出羊城通其他线路CPU卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctqtxlcpukwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_审计文件下发
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void sjwjxf(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_sjwjxf_wx() != 0 && t.getRemark().equals("审计文件下发")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_sjwjxf_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_sjwjxf_gz() != 0 && t.getRemark().equals("审计文件下发")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_sjwjxf_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出羊城通广佛普通卡文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcyctgfptkwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcyctgfptkwj_wx() != 0 && t.getRemark().equals("导出羊城通广佛普通卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctgfptkwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcyctgfptkwj_gz() != 0 && t.getRemark().equals("导出羊城通广佛普通卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctgfptkwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_重跑文件入库
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void cpwjrk(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_cpwjrk_wx() != 0 && t.getRemark().equals("重跑文件入库")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_cpwjrk_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_cpwjrk_gz() != 0 && t.getRemark().equals("重跑文件入库")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_cpwjrk_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_下发黑名单
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void xfhmd(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_xfhmd_wx() != 0 && t.getRemark().equals("下发黑名单")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_xfhmd_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_xfhmd_gz() != 0 && t.getRemark().equals("下发黑名单")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_xfhmd_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_计算线网、有轨金融IC卡票价
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void jsxwygjrickpj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_jsxwygjrickpj_wx() != 0 && t.getRemark().equals("计算线网、有轨金融IC卡票价")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_jsxwygjrickpj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_jsxwygjrickpj_gz() != 0 && t.getRemark().equals("计算线网、有轨金融IC卡票价")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_jsxwygjrickpj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_备份本地文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void bfbdwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_bfbdwj_wx() != 0 && t.getRemark().equals("备份本地文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_bfbdwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_bfbdwj_gz() != 0 && t.getRemark().equals("备份本地文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_bfbdwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_报表统计
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void bbtj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_bbtj_wx() != 0 && t.getRemark().equals("报表统计")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_bbtj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_bbtj_gz() != 0 && t.getRemark().equals("报表统计")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_bbtj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出羊城通广佛CPU卡文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcyctgfcpukwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcyctgfcpukwj_wx() != 0 && t.getRemark().equals("导出羊城通广佛CPU卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctgfcpukwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcyctgfcpukwj_gz() != 0 && t.getRemark().equals("导出羊城通广佛CPU卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctgfcpukwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出羊城通有轨普通卡文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcyctygptkwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcyctygptkwj_wx() != 0 && t.getRemark().equals("导出羊城通有轨普通卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctygptkwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcyctygptkwj_gz() != 0 && t.getRemark().equals("导出羊城通有轨普通卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctygptkwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出羊城通有轨CPU卡文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcyctygcpukwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcyctygcpukwj_wx() != 0 && t.getRemark().equals("导出羊城通有轨CPU卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctygcpukwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcyctygcpukwj_gz() != 0 && t.getRemark().equals("导出羊城通有轨CPU卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctygcpukwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出有轨金融IC卡交易文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcygjrickjywj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcygjrickjywj_wx() != 0 && t.getRemark().equals("导出有轨金融IC卡交易文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcygjrickjywj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcygjrickjywj_gz() != 0 && t.getRemark().equals("导出有轨金融IC卡交易文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcygjrickjywj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出金融IC卡退款文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcjricktkwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcjricktkwj_wx() != 0 && t.getRemark().equals("导出金融IC卡退款文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcjricktkwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcjricktkwj_gz() != 0 && t.getRemark().equals("导出金融IC卡退款文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcjricktkwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出金融IC卡交易文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcjrickjywj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcjrickjywj_wx() != 0 && t.getRemark().equals("导出金融IC卡交易文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcjrickjywj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcjrickjywj_gz() != 0 && t.getRemark().equals("导出金融IC卡交易文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcjrickjywj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导出羊城通其他线路普通卡文件
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void dcyctqtxlptkwj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_dcyctqtxlptkwj_wx() != 0 && t.getRemark().equals("导出羊城通其他线路普通卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctqtxlptkwj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_dcyctqtxlptkwj_gz() != 0 && t.getRemark().equals("导出羊城通其他线路普通卡文件")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_dcyctqtxlptkwj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导进出闸错误表非重复数据
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void djczcwbfcfsj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_djczcwbfcfsj_wx() != 0 && t.getRemark().equals("导进出闸错误表非重复数据")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_djczcwbfcfsj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_djczcwbfcfsj_gz() != 0 && t.getRemark().equals("导进出闸错误表非重复数据")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_djczcwbfcfsj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_审计、异常统计
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void sjyctj(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_sjyctj_wx() != 0 && t.getRemark().equals("审计、异常统计")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_sjyctj_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_sjyctj_gz() != 0 && t.getRemark().equals("审计、异常统计")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_sjyctj_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_收益统计模块
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void sytjmk(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_sytjmk_wx() != 0 && t.getRemark().equals("收益统计模块")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_sytjmk_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_sytjmk_gz() != 0 && t.getRemark().equals("收益统计模块")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_sytjmk_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_结算模块
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void jsmk(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_jsmk_wx() != 0 && t.getRemark().equals("结算模块")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_jsmk_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_jsmk_gz() != 0 && t.getRemark().equals("结算模块")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_jsmk_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_客流、乘距分析
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void klcjfx(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_klcjfx_wx() != 0 && t.getRemark().equals("客流、乘距分析")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_klcjfx_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_klcjfx_gz() != 0 && t.getRemark().equals("客流、乘距分析")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_klcjfx_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_生成运营商收益报表
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void ccyyssybb(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_ccyyssybb_wx() != 0 && t.getRemark().equals("生成运营商收益报表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_ccyyssybb_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_ccyyssybb_gz() != 0 && t.getRemark().equals("生成运营商收益报表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_ccyyssybb_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_分表日志记录
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void fbrzjl(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_fbrzjl_wx() != 0 && t.getRemark().equals("分表日志记录")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_fbrzjl_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_fbrzjl_gz() != 0 && t.getRemark().equals("分表日志记录")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_fbrzjl_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_金融IC卡对账报表
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void jrickdzbb(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_jrickdzbb_wx() != 0 && t.getRemark().equals("金融IC卡对账报表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_jrickdzbb_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_jrickdzbb_gz() != 0 && t.getRemark().equals("金融IC卡对账报表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_jrickdzbb_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_羊城通对账报表
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void yctdzbb(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_yctdzbb_wx() != 0 && t.getRemark().equals("羊城通对账报表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_yctdzbb_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_yctdzbb_gz() != 0 && t.getRemark().equals("羊城通对账报表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_yctdzbb_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
	/**
     * 清算子系统_导入历史表
     * @param alarm
     * @param t
     * @param sbf
     */
	public static void drllb(Alarm alarm, StSysFlowCurrentDt t, StringBuffer sbf) {
        if (alarm.getJyzxt_drllb_wx() != 0 && t.getRemark().equals("导入历史表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_drllb_wx() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                }
            }
        } else if (alarm.getJyzxt_drllb_gz() != 0 && t.getRemark().equals("导入历史表")) {
            if (t.getEndTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
            } else if (t.getBeginTime() == null) {
            	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
            } else {
                long time = t.getEndTime().getTime() / m - t.getBeginTime().getTime() / m;
                if (alarm.getJyzxt_drllb_gz() < time) {
                	sbf.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                }
            }
        }
	}
	
}
