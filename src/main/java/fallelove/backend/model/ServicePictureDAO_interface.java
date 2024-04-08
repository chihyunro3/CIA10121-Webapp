package fallelove.backend.model;

import java.util.*;

public interface ServicePictureDAO_interface {
	public void insert(ServicePictureVO servicePictureVO);
	public void update(ServicePictureVO servicePictureVO);
	public ServicePictureVO findByPrimaryKey(Integer servicePicNo);
	public ServicePictureVO findByRecordNo(Integer recordNo);
	public List<ServicePictureVO> getAll();
//	public List<ServicePictureVO> getAll(Map<String, String[]> map); //複合查詢，用key取得value
}
