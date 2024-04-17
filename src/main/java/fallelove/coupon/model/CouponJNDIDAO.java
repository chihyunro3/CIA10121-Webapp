//package fallelove.coupon.model;
//
//import java.math.BigDecimal;
//import java.sql.*;
//import java.util.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class CouponJNDIDAO implements CouponDAO_interface{
//	
//	private static DataSource ds = null;
//	static {
//		try {
//	
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/fallelove");
//		
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private static final String INSERT =
//			"INSERT INTO Coupon (coupName,coupCond,coupDisc,coupExpDate,coupRelDate,coupRelStat) VALUES (?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL =
//			"SELECT * FROM Coupon order by coupNo";
//	private static final String GET_BY_PK = 
//			"SELECT * FROM Coupon where coupNo =? order by coupNo";
//	private static final String DELETE =
//			"DELETE FROM Coupon where coupNo = ?";
//	private static final String UPDATE =
//			"UPDATE Coupon set coupName=?, coupCond=?, coupDisc=?, coupExpDate=?, coupRelDate=?, coupRelStat=?";
//	
//	public void insert(CouponVO couponVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT);
//			
//			pstmt.setString(1, couponVO.getCoupName());
//			pstmt.setString(2, couponVO.getCoupCond());
//			pstmt.setBigDecimal(3, couponVO.getCoupDisc());
////			pstmt.setDate(4, couponVO.getCoupAddDate());
//			pstmt.setDate(4, couponVO.getCoupExpDate());
//			pstmt.setDate(5, couponVO.getCoupRelDate());
//			pstmt.setInt(6, couponVO.getCoupRelStat());
//			
//			pstmt.executeUpdate();
//
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}		
//	}
//	
//
//	
//	public void update(CouponVO couponVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT);
//			
//			pstmt.setString(1, couponVO.getCoupName());
//			pstmt.setString(2, couponVO.getCoupCond());
//			pstmt.setBigDecimal(3, couponVO.getCoupDisc());
////			pstmt.setDate(4, couponVO.getCoupAddDate());
//			pstmt.setDate(4, couponVO.getCoupExpDate());
//			pstmt.setDate(5, couponVO.getCoupRelDate());
//			pstmt.setInt(6, couponVO.getCoupRelStat());
//			
//			pstmt.executeUpdate();
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//	
//	
//	public void delete(Integer coupNo) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, coupNo);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}		
//	}
//
//	
//	public CouponVO findByPK(Integer coupNo) {
//		
//		CouponVO couponVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			con = ds. getConnection();
//			pstmt = con.prepareStatement(GET_BY_PK);
//			
//			pstmt.setInt(1, coupNo);
//			
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				
//				couponVO = new CouponVO();
//				
//				couponVO.setCoupNo(rs.getInt(0));
//				couponVO.setCoupName(rs.getString(1));
//				couponVO.setCoupCond(rs.getString(2));
//				couponVO.setCoupDisc(rs.getBigDecimal(3));
//				couponVO.setCoupAddDate(rs.getDate(4));
//				couponVO.setCoupExpDate(rs.getDate(5));
//				couponVO.setCoupRelDate(rs.getDate(6));
//				couponVO.setCoupRelStat(rs.getInt(7));
//			}
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}		
//		return couponVO;
//	}
//	
//	public List<CouponVO> getAll(){
//		List<CouponVO> list = new ArrayList<CouponVO>();
//		CouponVO couponVO = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			
//			con = ds. getConnection();
//			pstmt = con.prepareStatement(GET_ALL);			
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				
//				couponVO = new CouponVO();				
//				couponVO.setCoupNo(rs.getInt(0));
//				couponVO.setCoupName(rs.getString(1));
//				couponVO.setCoupCond(rs.getString(2));
//				couponVO.setCoupDisc(rs.getBigDecimal(3));
//				couponVO.setCoupAddDate(rs.getDate(4));
//				couponVO.setCoupExpDate(rs.getDate(5));
//				couponVO.setCoupRelDate(rs.getDate(6));
//				couponVO.setCoupRelStat(rs.getInt(7));
//				list.add(couponVO);
//			}
//			
//		}catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}		
//		return list;
//	}	
//}