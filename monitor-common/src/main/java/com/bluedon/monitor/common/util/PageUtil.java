package com.bluedon.monitor.common.util;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author  : jason
 * @date    : 2015年3月9日
 * @module  : 公共模块 
 * @comment : 分页数据接收对象
 */
public class PageUtil {
	// 日志输出对象
	private static final Logger log = Logger.getLogger(PageUtil.class);

	// 分页操作常量
	public static final String FIRST_PAGE = "FIRST_PAGE";// 第一页
	public static final String LAST_PAGE = "LAST_PAGE";// 最后一页
	public static final String PREV_PAGE = "PREV_PAGE";// 上一页
	public static final String NEXT_PAGE = "NEXT_PAGE";// 下一页
	public static final String GOTO_PAGE = "GOTO_PAGE";// 定位到页
	public static final String DEFAULT_PAGE = "DEFAULT_PAGE";// 点击查询按钮的操作
	public static final String RESET_PAGESIZE = "RESET_PAGESIZE";// 重置页面记录容量
	public static final String AUTO_PAGE = "AUTO_PAGE";// [配合easyUI
														// DataGrid]该类型的分页处理时直接设置页码及页面容量，然后获取数据

	// 页面请求操作
	private String pageAction;
	// 当前页码
	private int page;
	// 总页码
	private int totalPageNumber;
	// 总记录数
	private int totalRecordNumber;
	// 当前页面记录容量
	private int rows;
	// 定位至多少页
	private int goToPageNumber;
	// 对应分页查询结果的结果集(用于输出填充)
	private List<?> resultList;
	// 其他相关参数,该属性结合不同分页查询所需参数不同，进行不同填充
	private Map<String, Object> map;
	// 输出结果集开始位置
	private int startRecord;
	// 输出结果集结束位置
	private int endRecord;
	// 控制是否展示pageSize下拉列表,如果为空则展示,不为空则不展示
	private String showPageSize;
	
	public PageUtil(){
		this.pageAction = AUTO_PAGE;//默认给出easyui的分页操作模式
	}
	
	/**
	 * 根据已有的数据进行分页计算，然后填充其他属性<br/>
	 * 
	 * @return 返回true表示分页成功,参数均符合要求<br/>
	 *         返回false表示分页过程中参数不符合规范,分页失败</br>
	 */
	public boolean fetchPaging() {
		/**
		 * 在此之前已经填充了该实体的部分属性:总记录数量,页面记录容量 需要通过该方法计算出该实体其他属性:起止记录数,总页数,更新当前页数,
		 */
		log.debug("******分页前参数输出:" + CommonUtil.Object2String(this));
		int pageSize = this.getRows();
		// 计算总页数
		if (pageSize == 0) {// 如果没有传递页面容量则按照默认10条记录的页面容量来显示
			this.setRows(10);
		} else {// 有页面容量的话,就根据页面容量和总记录数计算其他属性并赋值
			int all = this.getTotalRecordNumber();
			if (all % pageSize == 0) {
				this.setTotalPageNumber(all / pageSize);
			} else {
				this.setTotalPageNumber(all / pageSize + 1);
			}
		}
		// 计算起止记录数
		String tmpAction = this.getPageAction();
		if (tmpAction != null && tmpAction.equals(PageUtil.FIRST_PAGE)) {// 首页
			this.setStartRecord(1);
			this.setEndRecord(pageSize);
			this.setPage(1);
			this.setGoToPageNumber(1);
		} else if (tmpAction != null && tmpAction.equals(PageUtil.LAST_PAGE)) {// 尾页
			this.setStartRecord((this.getTotalPageNumber() - 1) * pageSize + 1);
			this.setEndRecord(this.totalRecordNumber);
			this.setPage(this.totalPageNumber);
			this.setGoToPageNumber(this.totalPageNumber);
		} else if (tmpAction != null && tmpAction.equals(PageUtil.PREV_PAGE)) {// 上一页
			if (this.page <= 1) {// 判断是否已经到首页,如果是首页则不需要再次进行计算
				this.setStartRecord(1);
				this.setEndRecord(pageSize);
				this.setPage(1);
				this.setGoToPageNumber(1);
			} else {// 如果不是首页则进行计算
				int oldCurrPage = this.getPage();
				this.setStartRecord((oldCurrPage - 2) * pageSize + 1);
				this.setEndRecord((oldCurrPage - 1) * pageSize);
				this.setPage(oldCurrPage - 1);
				this.setGoToPageNumber(oldCurrPage - 1);
			}

		} else if (tmpAction != null && tmpAction.equals(PageUtil.NEXT_PAGE)) {// 下一页
			if (this.page >= this.getTotalPageNumber()) {// 如果大于等于尾页的话,则不需要再次进行计算
				this.setStartRecord((this.getTotalPageNumber() - 1) * pageSize
						+ 1);
				this.setEndRecord(this.totalRecordNumber);
				this.setPage(this.totalPageNumber);
				this.setGoToPageNumber(this.totalPageNumber);
			} else {// 如果不是尾页则进行计算
				int oldCurrPage = this.getPage();
				this.setPage(oldCurrPage + 1);
				this.setGoToPageNumber(oldCurrPage + 1);
				this.setStartRecord(oldCurrPage * pageSize + 1);
				this.setEndRecord((oldCurrPage + 1) * pageSize >= this.totalRecordNumber ? this.totalRecordNumber
						: (oldCurrPage + 1) * pageSize);
			}
		} else if (tmpAction != null && tmpAction.equals(PageUtil.GOTO_PAGE)) {// 定位页
			if (this.goToPageNumber >= this.totalPageNumber) {// 大于等于最大页,默认加载最大页
				this.setStartRecord((this.getTotalPageNumber() - 1) * pageSize
						+ 1);
				this.setEndRecord(this.totalRecordNumber);
				this.setPage(this.totalPageNumber);
				this.setGoToPageNumber(this.totalPageNumber);
			} else if (this.goToPageNumber <= 1) {// 小于等于首页,默认加载首页
				this.setStartRecord(1);
				this.setEndRecord(pageSize);
				this.setPage(1);
				this.setGoToPageNumber(1);
			} else {// 中间正常取值,则计算位置
				this.setStartRecord((this.goToPageNumber - 1) * pageSize + 1);
				this.setEndRecord(this.goToPageNumber * pageSize);
				this.setPage(this.goToPageNumber);
			}
		} else if (tmpAction != null && tmpAction.equals(PageUtil.DEFAULT_PAGE)) {// 默认查询操作
			this.setStartRecord(1);
			this.setEndRecord(pageSize);
			this.setPage(1);
			this.setGoToPageNumber(1);
		} else if (tmpAction != null
				&& tmpAction.equals(PageUtil.RESET_PAGESIZE)) {// 选择页面容量
			/**
			 * 暂时定为重计页面容量的时候,默认从第一页开始显示
			 */
			this.setStartRecord(1);
			this.setEndRecord(pageSize);
			this.setPage(1);
			this.setGoToPageNumber(1);
		} else if (tmpAction != null && tmpAction.equals(PageUtil.AUTO_PAGE)) {// 分页查询操作错误
			this.setStartRecord((this.page - 1) * this.rows + 1);
			this.setEndRecord(this.page * this.rows);
			this.setGoToPageNumber(this.page);
		} else {
			return false;
		}
		log.debug("******分页后参数输出:" + CommonUtil.Object2String(this));
		return true;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	public int getEndRecord() {
		return endRecord;
	}

	public void setEndRecord(int endRecord) {
		this.endRecord = endRecord;
	}

	public String getPageAction() {
		return pageAction;
	}

	public void setPageAction(String pageAction) {
		this.pageAction = pageAction;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setTotalPageNumber(int totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}

	public int getTotalRecordNumber() {
		return totalRecordNumber;
	}

	public void setTotalRecordNumber(int totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getGoToPageNumber() {
		return goToPageNumber;
	}

	public void setGoToPageNumber(int goToPageNumber) {
		this.goToPageNumber = goToPageNumber;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getShowPageSize() {
		return showPageSize;
	}

	public void setShowPageSize(String showPageSize) {
		this.showPageSize = showPageSize;
	}

}
