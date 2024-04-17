package fallelove.coupon.model;

import java.math.BigDecimal;
import java.util.List;

public class CouponService {

	private CouponDAO_interface dao;
	
	public CouponService() {
		dao = new CouponJDBCDAO();
	}
	
	public CouponVO addCoupon(String coupName, String coupCond, BigDecimal coupDisc,
			java.sql.Date coupRelDate,
			java.sql.Date coupExpDate,
			Integer coupRelStat	) {
		
		CouponVO couponVO = new CouponVO();
		
		couponVO.setCoupName(coupName);
		couponVO.setCoupCond(coupCond);
		couponVO.setCoupDisc(coupDisc);
		couponVO.setCoupExpDate(coupRelDate);
		couponVO.setCoupRelDate(coupExpDate);
		couponVO.setCoupRelStat(coupRelStat);
		dao.insert(couponVO);
		
		return couponVO;
		}
	
	public void addCoupon(CouponVO couponVO) {
		dao.insert(couponVO);
	}
	
	public CouponVO updateCoupon(Integer coupNo, String coupName, String coupCond, BigDecimal coupDisc,
			java.sql.Date coupRelDate, java.sql.Date coupExpDate, Integer coupRelStat) {
		CouponVO couponVO = new CouponVO();
		
		couponVO.setCoupName(coupName);
		couponVO.setCoupCond(coupCond);
		couponVO.setCoupDisc(coupDisc);
		couponVO.setCoupExpDate(coupRelDate);
		couponVO.setCoupRelDate(coupExpDate);
		couponVO.setCoupRelStat(coupRelStat);
		couponVO.setCoupNo(coupNo);
		
		dao.update(couponVO);
		
		return dao.findByPK(coupNo);		
	}
	
	public void updateCoupon(CouponVO couponVO) {
		dao.update(couponVO);
	}
	
	public void deleteCoupon(Integer coupNo) {
		dao.delete(coupNo);
	}
	
	public CouponVO getOneCoupon(Integer coupNo) {
		return dao.findByPK(coupNo);
	}
	
	public List<CouponVO> getAll(){
		return dao.getAll();
	}
	
}
