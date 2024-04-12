package fallelove.backend.controller;

import java.util.LinkedList;
import java.util.List;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import fallelove.backend.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ServicePicServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded";
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("servicePicNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入客服圖片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer servicePicNo = null;
				try {
					servicePicNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("客服圖片編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ServicePictureService servicePicSvc = new ServicePictureService();
				ServicePictureVO servicePictureVO = servicePicSvc.getOneServicePic(servicePicNo);
				if (servicePicNo == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("servicePictureVO", servicePictureVO); // 資料庫取出的VO物件,存入req
				String url = "/backend/listOneServicPic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneServicPic.jsp
				successView.forward(req, res);		
		}
		
if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer servicePicNo = null;
			try {
				servicePicNo = Integer.valueOf(req.getParameter("servicePicNo").trim());
			}catch(NumberFormatException e){
				servicePicNo = 0;
				errorMsgs.add("請填整數");
			}
			
			Integer recordNo = null;
			try {
				recordNo = Integer.valueOf(req.getParameter("recordNo").trim());
			}catch(NumberFormatException e){
				recordNo = 0;
				errorMsgs.add("請填整數");
			}
			
			byte[] servicePic = null;
			
//			String realPath = getServletContext().getRealPath(saveDirectory);
//			System.out.println("realPath="+realPath);
//			File fsaveDirectory = new File(realPath);
//			if (!fsaveDirectory.exists())
//				 fsaveDirectory.mkdirs();
			
			InputStream in = req.getPart("servicePic").getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			
			try {
				if(in != null) {
					
					byte[] buf = in.readAllBytes();
					int len;
					while((len = bis.read(buf)) != -1) {
						os.write(buf, 0 ,len);
					}
					servicePic = os.toByteArray();					
				}
				
			}catch(IOException ie) {
				System.err.print(ie);
			}finally {
				in.close();
				bis.close();
				os.close();
			}
			

			ServicePictureVO servicePictureVO = new ServicePictureVO();
			
			servicePictureVO.setServicePicNo(servicePicNo);
			servicePictureVO.setRecordNo(recordNo);
			servicePictureVO.setServicePic(servicePic);
		

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("servicePictureVO", servicePictureVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/addServicePic.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ServicePictureService servicePictureServiceSvc = new ServicePictureService();
				servicePictureVO = servicePictureServiceSvc.addServicePicture(servicePicNo, recordNo, servicePic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/listAllServicePic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		
		
	}
}
