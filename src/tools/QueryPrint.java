package tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GoodsDao;
import db.DbClose;
import db.DbConn;
import entity.Goods;
import entity.SalesMan;

public final class QueryPrint {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/*
	 * Fuzzy Query and display
	 */
	public static int query(String oper) {
		int gid = -1;
		String shopping = ScannerChoice.ScannerInfoString();
		ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(-2, shopping);
		if (goodsList == null || goodsList.size() == 0) {
			System.err.println("No Goods Found");
		} else {
			Goods goods = goodsList.get(0);
			System.out.println("\t\t\t\t\tGoods List\n\n");
			System.out.println("\tGoods ID\t\tGoods Name\t\tGoods Price\t\tGoods Amount\t\tNote");
			System.out.println("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
			if (goods.getGnum() == 0){
				System.out.println("The Goods is Out of Stock!");
			} else if (goods.getGnum() < 10){
				System.out.println("\t\tThe goods amount is less than 10");
			} else {
				System.out.println("\t\t-");
			}
			gid = goods.getGid();
		}
		return gid;
	}
	/*
	 * Fuzzy Query
	 */
	public static int querySettlement() {
		int gid = -1;
		ArrayList<Goods> goodsSettlement = new GoodsDao().queryGoods(3);
		if (goodsSettlement == null || goodsSettlement.size() <= 0) {
			System.err.println("No Goods Found!");
		}else {
			System.out.println("\t\t\t\t\tGoods List\n\n");
			System.out.println("\tGoods ID\t\tGoods Name\t\tGoods Price\t\tGoods Amount\t\tNote");
			for (int i = 0; i < goodsSettlement.size(); i++)
			{
				Goods goods = goodsSettlement.get(i);
				if (goods.getGnum() > 0)
				{
					System.out.print("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+"\t\t"+goods.getGnum());
						
					if (goods.getGnum()==0)
					{
						System.out.println("\t\tthe Goods is Out of Stock");
					}else if (goods.getGnum()<10)
							{
								System.out.println("\t\tThe goods amount is less than 10");
							}else 
								{
									System.out.println("\t\t-");
								}
						if (goodsSettlement.size()==1)
						{
							gid = goods.getGid(); 
						}else 
							{
								gid = -2;
							}
				}
			}
		}
		return gid; 

	}

	public ArrayList<Goods> queryGoodsKey(int gId, String gName) {
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		conn = DbConn.getconn();
		String sql = "SELECT * FROM WHERE GID = ? OR GNAME = ?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gId);
			pstmt.setString(2, gName);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int gid = rs.getInt("gId");
				String gname = rs.getString(2);
				double gprice = rs.getDouble(3);
				int gnum = rs.getInt(4);
				
				Goods goods = new Goods(gid,gname,gprice,gnum);
				goodsList.add(goods);
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DbClose.queryClose(pstmt, rs, conn);
			}
		return goodsList;
	}

	/*
	 * Query salesman name 
	 */
	public ArrayList<SalesMan> querySalesMan(String sName) {
		ArrayList<SalesMan> SalesManList = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		String sql = "SELECT * FROM SAMLESMAN WHERE SNAME=?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			while (rs.next()){
				int sid = rs.getInt("sid");
				String sname = rs.getString(2);
				String sPassword = rs.getString(3);
				
				SalesMan salesMan = new SalesMan(sid,sname,sPassword);
				SalesManList.add(salesMan);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(pstmt, rs, conn);
		}
		return SalesManList;
	}
}
