package bean;

public class HistoryBean {
	/**
	 * 历史预订记录的数据表的bean
	 */
	private int hid;//预订记录的id
	private int aid;//用户的id
	private int bid;//客房的id
	private String card;//客房号
	private String jiudianname;//客房名称
	private String adminname;//用户的账号
	private String username;//用户的姓名
	private String begintime;//预订时间
	private String endtime;//退房时间
	private int status;//表示预订状态,1为正在预订,2是已经退房
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getJiudianname() {
		return jiudianname;
	}
	public void setJiudianname(String jiudianname) {
		this.jiudianname = jiudianname;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
