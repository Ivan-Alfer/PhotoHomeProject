package by.home.service;

import java.util.List;

import by.home.entity.Image;
import by.home.service.exception.ServiceException;

public interface ImageService {

	List<Image> getImages() throws ServiceException;
	
	void addImage(Image image) throws ServiceException;
}
