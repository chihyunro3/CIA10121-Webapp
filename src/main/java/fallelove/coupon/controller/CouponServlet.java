package fallelove.coupon.controller;

import fallelove.coupon.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/coupon/couponServlet.do")
public class CouponServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("coupNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("coupNo", "請輸入優惠券編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer coupNo = null;
			try {
				coupNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("coupNo", "優惠券編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2.開始查詢資料 *****************************************/
			CouponService couponSvc = new CouponService();
			CouponVO couponVO = couponSvc.getOneCoupon(coupNo);
			if (couponVO == null) {
				errorMsgs.put("coupNo", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couponVO", couponVO);
			String url = "/coupon/listOneCoupon.jsp";
			System.out.println("1243");
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ****************************************/
			Integer coupNo = Integer.valueOf(req.getParameter("coupNo"));
			/*************************** 2.開始查詢資料 ****************************************/
			CouponService couponSvc = new CouponService();
			CouponVO couponVO = couponSvc.getOneCoupon(coupNo);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?coupNo=" + couponVO.getCoupNo() + "&coupName=" + couponVO.getCoupName() + "&coupCond="
					+ couponVO.getCoupCond() + "&coupDisc=" + couponVO.getCoupDisc() + "&coupExpDate="
					+ couponVO.getCoupExpDate() + "&coupRelDate=" + couponVO.getCoupRelDate() + "&coupRelStat="
					+ couponVO.getCoupRelStat();
			String url = "/coupon/update_coupon_input.jsp" + param;
			RequestDispatcher sucessView = req.getRequestDispatcher(url);
			sucessView.forward(req, res);
		}

		if ("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer coupNo = Integer.valueOf(req.getParameter("coupNo"));

			String coupName = req.getParameter("coupName");
			if (coupName == null || coupName.trim().length() == 0) {
				errorMsgs.put("coupName", "優惠券名稱請勿空白");
			}

			String coupCond = req.getParameter("coupCond");
			if (coupCond == null || coupCond.trim().length() == 0) {
				errorMsgs.put("coupCond", "請輸入優惠條件");
			}

			String coupDisc = req.getParameter("coupDisc");
			BigDecimal bd = null;
			try {
				bd = new BigDecimal(coupDisc);
				if (coupDisc == null || coupDisc.trim().length() == 0) {
					errorMsgs.put("coupDisc", "請輸入折扣(若為九折請輸入0.9)");
				} else if (bd.compareTo(BigDecimal.ONE) >= 0 || bd.compareTo(BigDecimal.ZERO) <= 0) {
					errorMsgs.put("coupDisc", "折扣比率請輸入0~1之間小數");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("coupDisc", "請填折扣比率");
			}

			java.sql.Date coupExpDate = null;
			try {
				coupExpDate = java.sql.Date.valueOf(req.getParameter("coupExpDate"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("coupExpDate", "請輸入優惠券失效日期");
			}

			java.sql.Date coupRelDate = null;
			try {
				coupRelDate = java.sql.Date.valueOf(req.getParameter("coupRelDate"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("coupRelDate", "請輸入優惠券發放日期");
			}

			String coupRelStat_str = req.getParameter("coupRelStat");
			Integer coupRelStat = null;
			if (coupRelStat_str == null || coupRelStat_str.trim().length() == 0) {
				errorMsgs.put("coupRelStat", "請選擇發放狀態");
			} else {
				try {
					coupRelStat = Integer.valueOf(coupRelStat_str);
				} catch (NumberFormatException e) {
					errorMsgs.put("coupRelStat", "請選擇發放狀態");
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/update_coupon_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			CouponService couponSvc = new CouponService();
			CouponVO couponVO = couponSvc.updateCoupon(coupNo, coupName, coupCond, bd, coupRelDate, coupExpDate,
					coupRelStat);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couponVO", couponVO);
			String url = "/coupon/listOneCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String coupName = req.getParameter("coupName");
			if (coupName == null || coupName.trim().length() == 0) {
				errorMsgs.put("coupName", "優惠券名稱請勿空白");
			}

			String coupCond = req.getParameter("coupCond");
			if (coupCond == null || coupCond.trim().length() == 0) {
				errorMsgs.put("coupCond", "請輸入優惠條件");
			}

			String coupDisc = req.getParameter("coupDisc");
			BigDecimal bd = null;

			try {
				bd = new BigDecimal(coupDisc);
				if (coupDisc == null || coupDisc.trim().length() == 0) {
					errorMsgs.put("coupDisc", "請輸入折扣(若為九折請輸入0.9)");
				} else if (bd.compareTo(BigDecimal.ONE) >= 0 || bd.compareTo(BigDecimal.ZERO) <= 0) {
					errorMsgs.put("coupDisc", "折扣比率請輸入0~1之間小數");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("coupDisc", "請填折扣比率");
			}

			java.sql.Date coupExpDate = null;
			try {
				coupExpDate = java.sql.Date.valueOf(req.getParameter("coupExpDate"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("coupExpDate", "請輸入優惠券失效日期");
			}

			java.sql.Date coupRelDate = null;
			try {
				coupRelDate = java.sql.Date.valueOf(req.getParameter("coupRelDate"));
			} catch (IllegalArgumentException e) {
				errorMsgs.put("coupRelDate", "請輸入優惠券發放日期");
			}

			String coupRelStat_str = req.getParameter("coupRelStat");
			Integer coupRelStat = null;
			if (coupRelStat_str == null || coupRelStat_str.trim().length() == 0) {
				errorMsgs.put("coupRelStat", "請選擇發放狀態");
			} else {
				try {
					coupRelStat = Integer.valueOf(coupRelStat_str);
				} catch (NumberFormatException e) {
					errorMsgs.put("coupRelStat", "請選擇發放狀態");
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/coupon/addCoupon.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			CouponService couponSvc = new CouponService();
			CouponVO couponVO = couponSvc.addCoupon(coupName, coupCond, bd, coupRelDate, coupExpDate, coupRelStat);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/coupon/listAllCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ***************************************/
			Integer coupNo = Integer.valueOf(req.getParameter("coupNo"));
			/*************************** 2.開始刪除資料 ***************************************/
			CouponService couponSvc = new CouponService();
			couponSvc.deleteCoupon(coupNo);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/coupon/listAllCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

	}

}
