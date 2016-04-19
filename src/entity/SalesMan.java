package entity;

public final class SalesMan {
	private int sId;
	private String sName;
	private String sPassWord;
	
	/*
	 * user login with password
	 * @param sId, sPassword
	 */
	public SalesMan(int sId, String spassword) {
		this.sId = sId;
		this.sPassWord = spassword;
	}
	/*
	 * search user, change user password
	 */
	public SalesMan (int sId, String sName, String spassword) {
		this.sId = sId;
		this.sName = sName;
		this.sPassWord = spassword;
	}
	/*
	 * add user
	 * @param NsNameame, sPassWord
	 */
	public SalesMan (String NsNameame, String sPassWord) {
		this.sName = NsNameame;
		this.sPassWord = sPassWord;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsPassWord() {
		return sPassWord;
	}
	public void setsPassWord(String sPassWord) {
		this.sPassWord = sPassWord;
	}
	

}
