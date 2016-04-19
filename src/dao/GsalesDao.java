package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbClose;
import db.DbConn;
import entity.Gsales;

public final class GsalesDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/*
	 * 1.Daily Sales
	 * @return ArrayList<Gsales> goods information, allSum(sum of one kind of item)
	 */
	public ArrayList<Gsales> dailyGsales(){
		ArrayList<Gsales> GsalesList = new ArrayList<Gsales>();
		conn = DbConn.getconn();
		
		////售卖时间=当前时间 trunc(sdate) =trunc(sysdate) 单位：天
		String sql = "select gname,gprice,gnum, allSum from goods, (select gid as salesid,sum(snum) as allSum from gsales where trunc(sdate) =trunc(sysdate) group by gid) where gid = salesid"; 
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()){
				String gName = rs.getString(1);
				double gPrice = rs.getDouble(2);
				int gNum = rs.getInt(3);
				int allSum = rs.getInt("allSum");
				
				Gsales Gsales = new Gsales(gName, gPrice, gNum, allSum);
				GsalesList.add(Gsales);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbClose.queryClose(pstmt, rs, conn);
		}
		return GsalesList;
	}
	/*
	 * 2. insert goods information into table sales
	 * @param gSales
	 * @return boolean
	 */
	public boolean shoppingSettlement(Gsales gSales){
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "INSERT INTO GSALES(GID,SID,SNUM) VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gSales.getgId());
			pstmt.setInt(2, gSales.getsId());
			pstmt.setInt(3, gSales.getsNum());
			
			int rs = pstmt.executeUpdate();
			if(rs > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbClose.addClose(pstmt, conn);
		}
		return bool;
	}
}
