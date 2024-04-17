package fallelove.coupon.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class CouponJDBCDAO implements CouponDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/fallElove";
	String userid = "root";
	String password = "12345";
	
	private static final String INSERT =
			"INSERT INTO Coupon (coupName,coupCond,coupDisc,coupExpDate,coupRelDate,coupRelStat) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL =
			"SELECT * FROM Coupon order by coupNo";
	private static final String GET_BY_PK = 
			"SELECT * FROM Coupon where coupNo =?";
	private static final String DELETE =
			"DELETE FROM Coupon where coupNo = ?";
	private static final String UPDATE =
			"UPDATE Coupon set coupName=?, coupCond=?, coupDisc=?, coupExpDate=?, coupRelDate=?, coupRelStat=? where coupNo=?";
	
	public void insert(CouponVO couponVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, couponVO.getCoupName());
			pstmt.setString(2, couponVO.getCoupCond());
			pstmt.setBigDecimal(3, couponVO.getCoupDisc());
//			pstmt.setDate(4, couponVO.getCoupAddDate());
			pstmt.setDate(4, couponVO.getCoupExpDate());
			pstmt.setDate(5, couponVO.getCoupRelDate());
			pstmt.setInt(6, couponVO.getCoupRelStat());
			
			pstmt.executeUpdate();
			System.out.println("優惠券新增成功");

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
			// Clean up JDBC resources
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}
	

	
	public void update(CouponVO couponVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, couponVO.getCoupName());
			pstmt.setString(2, couponVO.getCoupCond());
			pstmt.setBigDecimal(3, couponVO.getCoupDisc());
//			pstmt.setDate(4, couponVO.getCoupAddDate());
			pstmt.setDate(4, couponVO.getCoupExpDate());
			pstmt.setDate(5, couponVO.getCoupRelDate());
			pstmt.setInt(6, couponVO.getCoupRelStat());
			pstmt.setInt(7, couponVO.getCoupNo());
			
			pstmt.executeUpdate();
			
			System.out.println("優惠券修改成功");
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	public void delete(Integer coupNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, coupNo);

			pstmt.executeUpdate();
			
			System.out.println("優惠券刪除成功");

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
	}

	
	public CouponVO findByPK(Integer coupNo) {
		
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_BY_PK);
			
			pstmt.setInt(1, coupNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				couponVO = new CouponVO();
				
				couponVO.setCoupNo(rs.getInt(1));
				couponVO.setCoupName(rs.getString(2));
				couponVO.setCoupCond(rs.getString(3));
				couponVO.setCoupDisc(rs.getBigDecimal(4));
				couponVO.setCoupAddDate(rs.getDate(5));
				couponVO.setCoupExpDate(rs.getDate(6));
				couponVO.setCoupRelDate(rs.getDate(7));
				couponVO.setCoupRelStat(rs.getInt(8));
			}
			System.out.println("優惠券以PK查詢");
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
		return couponVO;
	}
	
	public List<CouponVO> getAll(){
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL);			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				couponVO = new CouponVO();				
				couponVO.setCoupNo(rs.getInt(1));
				couponVO.setCoupName(rs.getString(2));
				couponVO.setCoupCond(rs.getString(3));
				couponVO.setCoupDisc(rs.getBigDecimal(4));
				couponVO.setCoupAddDate(rs.getDate(5));
				couponVO.setCoupExpDate(rs.getDate(6));
				couponVO.setCoupRelDate(rs.getDate(7));
				couponVO.setCoupRelStat(rs.getInt(8));
				list.add(couponVO);
			}
			System.out.println("優惠券全部查詢");
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}		
		return list;
	}
	
//	public static void main(String[] args) {
//		
//		CouponJDBCDAO dao = new CouponJDBCDAO();
		
//		CouponVO testadd = new CouponVO();
//		testadd.setCoupName("五月壽星禮");
//		testadd.setCoupCond("滿千打95折");
//		testadd.setCoupDisc(BigDecimal.valueOf(0.95));
//		testadd.setCoupExpDate(java.sql.Date.valueOf("2024-05-31"));
//		testadd.setCoupRelDate(java.sql.Date.valueOf("2024-05-01"));
//		testadd.setCoupRelStat(0);
//		dao.insert(testadd);
//		
		
//		CouponVO testupdate = new CouponVO();
//		testupdate.setCoupName("六月壽星禮");
//		testupdate.setCoupCond("滿千打9折");
//		testupdate.setCoupDisc(BigDecimal.valueOf(0.9));
//		testupdate.setCoupExpDate(java.sql.Date.valueOf("2024-06-30"));
//		testupdate.setCoupRelDate(java.sql.Date.valueOf("2024-06-01"));
//		testupdate.setCoupRelStat(0);
//		testupdate.setCoupNo(7);
//		dao.update(testupdate);
		
//		CouponVO testdelete = new CouponVO();
//		dao.delete(7);
		
//		CouponVO testfindByPK = dao.findByPK(8);
//		System.out.println(testfindByPK.getCoupNo());
//		System.out.println(testfindByPK.getCoupName());
//		System.out.println(testfindByPK.getCoupCond());
//		System.out.println(testfindByPK.getCoupDisc());
//		System.out.println(testfindByPK.getCoupAddDate());
//		System.out.println(testfindByPK.getCoupExpDate());
//		System.out.println(testfindByPK.getCoupRelDate());
//		System.out.println(testfindByPK.getCoupRelStat());
		
//		List<CouponVO> list =dao.getAll();
//		for(CouponVO acoupon : list) {
//			System.out.println(acoupon.getCoupNo());
//			System.out.println(acoupon.getCoupName());
//			System.out.println(acoupon.getCoupCond());
//			System.out.println(acoupon.getCoupDisc());
//			System.out.println(acoupon.getCoupAddDate());
//			System.out.println(acoupon.getCoupExpDate());
//			System.out.println(acoupon.getCoupRelDate());
//			System.out.println(acoupon.getCoupRelStat());
//		}
		
		
//	}
	
}


	