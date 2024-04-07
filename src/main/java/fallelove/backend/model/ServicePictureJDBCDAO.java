package fallelove.backend.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;
import java.io.*;


public class ServicePictureJDBCDAO implements ServicePictureDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/fallElove?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "12345";
	
	private static final String INSERT_STMT =
		"INSERT INTO ServicePicture (servicePicNo, recordNo, servicePic) VALUES (?, ?, ?);";
	private static final String GET_ALL_STMT = 
		"SELECT servicePicNo, recordNo, servicePic FROM ServicePicture order by servicePicNo;";
	private static final String GET_PK_STMT =
		"SELECT servicePicNo, recordNo, servicePic FROM ServicePicture where servicePicNo = ?;";
	private static final String GET_RECORD_STMT =
			"SELECT servicePicNo, recordNo, servicePic FROM ServicePicture where recordNo = ?;";
	private static final String UPDATE = 
		"UPDATE ServicePicture set recordNo=?, servicePic=? where servicePicNo = ?;";
	
	
	@Override
	public void insert(ServicePictureVO servicePictureVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);
//			InputStream in = Files.newInputStream(Path.of("defectdress.jpg"));
			
			pstmt.setInt(1, servicePictureVO.getServicePicNo());
			pstmt.setInt(2, servicePictureVO.getRecordNo());
			pstmt.setBytes(3, servicePictureVO.getServicePic());
			
			pstmt.executeUpdate();
			System.out.println("新增成功");
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." 
					+ se.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
				
			}
		}
	}

	@Override
	public void update(ServicePictureVO servicePictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, servicePictureVO.getRecordNo());
			pstmt.setBytes(2, servicePictureVO.getServicePic());
			pstmt.setInt(3, servicePictureVO.getServicePicNo());
			
			pstmt.executeUpdate();
			System.out.println("修改成功");
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." 
					+ se.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
				
			}
		}
		
	}

	@Override
	public ServicePictureVO findByPrimaryKey(Integer servicePicNo) {
		
		ServicePictureVO servicePictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_PK_STMT);
			
			pstmt.setInt(1, servicePicNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				servicePictureVO = new ServicePictureVO();
				servicePictureVO.setServicePicNo(rs.getInt("servicePicNo"));
				servicePictureVO.setRecordNo(rs.getInt("recordNo"));
				if(!(rs.getBytes("servicePic") == null)) {
					servicePictureVO.setServicePic(rs.getBinaryStream("servicePic").readAllBytes());
				}				
			}
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." 
					+ se.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
				
			}
		}		
		
		return servicePictureVO;
	}
	
	@Override
	public ServicePictureVO findByRecordNo(Integer recordNo) {
		ServicePictureVO servicePictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_RECORD_STMT);
			
			pstmt.setInt(2, recordNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				servicePictureVO = new ServicePictureVO();
				servicePictureVO.setServicePicNo(rs.getInt("servicePicNo"));
				servicePictureVO.setRecordNo(rs.getInt("recordNo"));
				if(!(rs.getBytes("servicePic") == null)) {
					servicePictureVO.setServicePic(rs.getBinaryStream("servicePic").readAllBytes());
				}
			}
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." 
					+ se.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
				
			}
		}		
		
		return servicePictureVO;
	}

	@Override
	public List<ServicePictureVO> getAll() {
		List<ServicePictureVO> list = new ArrayList<ServicePictureVO>();
		ServicePictureVO servicePictureVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				servicePictureVO = new ServicePictureVO();
				servicePictureVO.setServicePicNo(rs.getInt("servicePicNo"));
				servicePictureVO.setRecordNo(rs.getInt("recordNo"));
				if(!(rs.getBytes("servicePic") == null)) {
					servicePictureVO.setServicePic(rs.getBinaryStream("servicePic").readAllBytes());
				}
				list.add(servicePictureVO);
			}		
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured." 
					+ se.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch (Exception e) {
					e.printStackTrace(System.err);
				}
				
			}
		}				
		return list;
	}

	@Override
	public List<ServicePictureVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte [] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) throws IOException {
		
		ServicePictureJDBCDAO dao = new ServicePictureJDBCDAO();
		
		//INSERT
//		ServicePictureVO picVO1 = new ServicePictureVO();
//		picVO1.setServicePicNo(25);
//		picVO1.setRecordNo(8);
//		picVO1.setServicePic(getPictureByteArray("src/main/webapp/backend/images/defectclothes.jpg"));
//		dao.insert(picVO1);

		
		//UPDATE
//		ServicePictureVO picVO2 = new ServicePictureVO();
//		picVO2.setServicePicNo(26);
//		picVO2.setRecordNo(3);
//		picVO2.setServicePic(null);
//		
//		dao.update(picVO2);
		
		//findByPrimaryKey
		ServicePictureVO picVO3 = dao.findByPrimaryKey(25);
		System.out.println(picVO3.getServicePicNo() + ",");
		System.out.println(picVO3.getRecordNo() + ",");
		System.out.println(picVO3.getServicePic() + ","); //印出來的是記憶體位址。
//		思考1:如何動態取得圖片路徑
//		思考2:客服紀錄圖片不一定每筆都有，且不一定同筆紀錄只有一張照片，可能有多張，如何依序存取方便動態取得?
		
		
	}	
}
