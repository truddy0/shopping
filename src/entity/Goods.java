package entity;

public final class Goods {
	//Database Goods 
	private int gid;//primary key
	private String gname;
	private double gprice;
	private int gnum;
	
	/*add goods information
	 * @param gname, gprice, gum
	 */
	public Goods(String gname, double gprice, int gum) {
		this.gname = gname;
		this.gprice = gprice;
		this.gnum = gum;
	}
	/*show goods information
	 * @param gid, gname, gprice, gum
	 */
	public Goods(int gid, String gname, double gprice, int gum) {
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.gnum = gum;
	}
	/*
	 * according to gid, change goods number
	 * @param gid, gum
	 */
	public Goods (int gid, int gnum) {
		this.gid = gid;
		this.gnum = gnum;
	}
	/*
	 * change goods price
	 */
	public Goods (int gid, double gprice) {
		this.gid = gid;
		this.gprice = gprice;
	}
	/*
	 * change goods name
	 */
	public Goods (int gid, String gname) {
		this.gid = gid;
		this.gname= gname;
	}
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public double getGprice() {
		return gprice;
	}
	public void setGprice(double gprice) {
		this.gprice = gprice;
	}
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	

}
