package by.home.dao;

import java.util.List;

import by.home.dao.exception.DaoException;
import by.home.entity.Image;

public interface ImageDao {

	List<Image> getImages() throws DaoException;
	
	void addImage(Image image) throws DaoException;
}
