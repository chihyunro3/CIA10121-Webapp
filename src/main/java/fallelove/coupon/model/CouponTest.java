package fallelove.coupon.model;

import java.math.BigDecimal;
import java.util.List;

public class CouponTest {

	public static void main(String[] args) {
		
//			CouponJNDIDAO dao = new CouponJNDIDAO();
			CouponJDBCDAO dao = new CouponJDBCDAO();
			
			CouponVO testadd = new CouponVO();
			testadd.setCoupName("五月壽星禮");
			testadd.setCoupCond("滿千打95折");
			testadd.setCoupDisc(BigDecimal.valueOf(0.95));
			testadd.setCoupExpDate(java.sql.Date.valueOf("2024-05-31"));
			testadd.setCoupRelDate(java.sql.Date.valueOf("2024-05-01"));
			testadd.setCoupRelStat(0);
			dao.insert(testadd);
			
			
			CouponVO testupdate = new CouponVO();
			testupdate.setCoupName("六月壽星禮");
			testupdate.setCoupCond("滿千打9折");
			testupdate.setCoupDisc(BigDecimal.valueOf(0.9));
			testupdate.setCoupExpDate(java.sql.Date.valueOf("2024-06-30"));
			testupdate.setCoupRelDate(java.sql.Date.valueOf("2024-06-01"));
			testupdate.setCoupRelStat(0);
			testupdate.setCoupNo(7);
			dao.update(testupdate);
			
			CouponVO testdelete = new CouponVO();
			dao.delete(7);
			
			CouponVO testfindByPK = dao.findByPK(8);
			System.out.println(testfindByPK.getCoupNo());
			System.out.println(testfindByPK.getCoupName());
			System.out.println(testfindByPK.getCoupCond());
			System.out.println(testfindByPK.getCoupDisc());
			System.out.println(testfindByPK.getCoupAddDate());
			System.out.println(testfindByPK.getCoupExpDate());
			System.out.println(testfindByPK.getCoupRelDate());
			System.out.println(testfindByPK.getCoupRelStat());
			
			List<CouponVO> list =dao.getAll();
			for(CouponVO acoupon : list) {
				System.out.println(acoupon.getCoupNo());
				System.out.println(acoupon.getCoupName());
				System.out.println(acoupon.getCoupCond());
				System.out.println(acoupon.getCoupDisc());
				System.out.println(acoupon.getCoupAddDate());
				System.out.println(acoupon.getCoupExpDate());
				System.out.println(acoupon.getCoupRelDate());
				System.out.println(acoupon.getCoupRelStat());
			}
			

	}

}
