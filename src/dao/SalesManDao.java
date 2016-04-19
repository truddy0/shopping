package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DbClose;
import db.DbConn;
import entity.SalesMan;

public final class SalesManDao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/*
	 * 1. check-out login
	 * @param sName 
	 * @return ArrayList<SalesMan> sPassword, sId
	 */
	public ArrayList<SalesMan> checkstandLog(String sName){
		ArrayList<SalesMan> salesManInfo = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		String sql = "SELECT SID,SPASSWORD FROM SALESMAN WHERE SNAME=?";
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String sPassWord = rs.getString("spassword");
				int sId = rs.getInt("sId");
				SalesMan salesMan = new SalesMan(sId, sPassWord);
				salesManInfo.add(salesMan);
			}
		}catch (SQLException e1){
			e1.printStackTrace();
		}finally{
			DbClose.queryClose(pstmt, rs, conn);
		}
		return salesManInfo;
	}
	/*
	 * 2. add Salesman
	 * @param sName
	 * @return boolean
	 */
	public boolean addSalesMan(SalesMan sName){
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "INSERT INTO SALESMAN(SNAME,SPASSWORD) VALUES(?,?)";
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName.getsName());
			pstmt.setString(2, sName.getsPassWord());
			
			int rs = pstmt.executeUpdate();
			if(rs > 0){
				bool = true;
			}
		}catch (SQLException e){
			e.printStackTrace();
		} finally{
			DbClose.addClose(pstmt,conn);
		}
		return bool;
	}
	/*
	 * 3. change salesman information
	 * @param key 
	 * @param sName
	 * @return boolean
	 */
	public boolean updateSalesMan(int key, SalesMan sName){
		boolean bool = false;
		conn = DbConn.getconn();
		switch(key){
		case 1: //change salesman name
			String sqlName = "UPDATE SALESMAN SET SNAME=? WHERE SID =?";
			try{
				pstmt = conn.prepareStatement(sqlName);
				pstmt.setString(1, sName.getsName());
				pstmt.setInt(2, sName.getsId());
				int rs = pstmt.executeUpdate();
				if (rs > 0){
					bool = true;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DbClose.addClose(pstmt, conn);
			}
			break;
		case 2: //change salesman password
			String sqlpassword = "UPDATE SALESMAN SET SPASSWORD=? WHERE SID = ?";
			try{
				pstmt = conn.prepareStatement(sqlpassword);
				pstmt.setString(1, sName.getsPassWord());
				pstmt.setInt(2, sName.getsId());
				
				int rs = pstmt.executeUpdate();
				if(rs > 0){
					bool = true;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DbClose.addClose(pstmt, conn);
			}
			break;
		default:
			break;
		}
		return bool;
	}
	/*
	 * 4. delete salesman
	 * @param sName
	 * return boolean
	 */
	public boolean deleteSalesMan(String sName){
		boolean bool = false;
		conn = DbConn.getconn();
		String sql = "DELETE FORM SALESMAN WHERE SNAME = ?";
		try{
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				bool = true;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.addClose(pstmt, conn);
		}
		return bool;
	}
	/*
	 * 5. fuzzy query
	 * @param sName
	 * return ArrayList<SalesMan>
	 */
	public ArrayList<SalesMan> querySalesMan(String sName){
		ArrayList<SalesMan> SalesManList = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		
		sName = "%"+sName+"%";//从用户处获取的字符串加上 % 符号，来达到模糊查询的目的
		String sql = "SELECT * FROM SALESMAN WHERE SNAME LIKE ?";
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sName);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int sid = rs.getInt("sid");
				String sname = rs.getString(2);
				String sPassWord = rs.getString(3);
				
				SalesMan salesMan = new SalesMan(sid,sname,sPassWord);
				SalesManList.add(salesMan);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(pstmt, rs, conn);
		}
		return SalesManList;
	}
	/*
	 * 6.display all salesman
	 * @return ArrayLsit<SalesMan>
	 */
	public ArrayList<SalesMan> displaySalesMan(){
		ArrayList<SalesMan> salesManList = new ArrayList<SalesMan>();
		conn = DbConn.getconn();
		String sql = "SELECT * FROM SALESMAN";
		try{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int sId = rs.getInt(1);
				String sName = rs.getString(2);
				String sPassWord = rs.getString(3);
				
				SalesMan salesMan = new SalesMan(sId,sName, sPassWord);
				salesManList.add(salesMan);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DbClose.queryClose(pstmt, rs, conn);
		}
		return salesManList;
	}
}
