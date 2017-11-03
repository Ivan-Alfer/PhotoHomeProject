package by.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import by.home.dao.ImageDao;
import by.home.dao.exception.DaoException;
import by.home.entity.Image;
import by.home.service.ImageService;
import by.home.service.exception.ServiceException;

@Component
@ComponentScan("by.home")
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImageDao imageDao;
	
	@Override
	public List<Image> getImages() throws ServiceException {
		List<Image> images;
		try {
			images = imageDao.getImages();
		} catch (DaoException e) {
			throw new ServiceException("Something happend in DAO" + e.getMessage());
		}
		return images;
	}	

	
}
