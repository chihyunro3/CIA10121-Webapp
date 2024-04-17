package fallelove.coupon.model;

import java.util.List;

public interface CouponDAO_interface {
	public void insert(CouponVO couponVO);
	public void update(CouponVO couponVO);
	public void delete(Integer coupNo);
	public CouponVO findByPK(Integer coupNo);
	public List<CouponVO> getAll();

}
