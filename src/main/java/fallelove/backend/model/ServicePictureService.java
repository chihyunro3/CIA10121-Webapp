package fallelove.backend.model;

import java.util.List;

public class ServicePictureService {
	
	private ServicePictureDAO_interface dao;
	
	public ServicePictureService() {
		dao = new ServicePictureJDBCDAO();
	}
	
	public ServicePictureVO addServicePicture(Integer servicePicNo, Integer recordNo, byte[] servicePic) {
		
		ServicePictureVO servicePictureVO = new ServicePictureVO();
		
		servicePictureVO.setServicePicNo(servicePicNo);
		servicePictureVO.setRecordNo(recordNo);
		servicePictureVO.setServicePic(servicePic);
		dao.insert(servicePictureVO);
		
		return servicePictureVO;
	}
	
	public ServicePictureVO updateServicePicture(Integer servicePicNo, Integer recordNo, byte[] servicePic) {
			
		ServicePictureVO servicePictureVO = new ServicePictureVO();
		
		servicePictureVO.setServicePicNo(servicePicNo);
		servicePictureVO.setRecordNo(recordNo);
		servicePictureVO.setServicePic(servicePic);
		dao.update(servicePictureVO);
		
		return servicePictureVO;		
	}
	
	public ServicePictureVO getOneServicePic(Integer servicePicNo) {
		return dao.findByPrimaryKey(servicePicNo);		
	}
	
	public ServicePictureVO getRecord(Integer recordNo) {
		return dao.findByRecordNo(recordNo);
	}
	
	public List<ServicePictureVO> getAll() {
		return dao.getAll();		
	}	
}
