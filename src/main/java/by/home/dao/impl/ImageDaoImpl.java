package by.home.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import by.home.dao.ImageDao;
import by.home.dao.exception.DaoException;
import by.home.entity.Image;

@Component
public class ImageDaoImpl implements ImageDao{

	protected Session session;
	
	public ImageDaoImpl() {
		session = HibernateUtil.getSessionFactory().openSession();
	}
	
	@Override
	public List<Image> getImages() throws DaoException {
		List<Image> images = new ArrayList<Image>();
		try {
		images = session.createQuery("from Image").list();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw new DaoException("Database server not responding. Entities not received" + e.getMessage());
		}
		return images;
	}

}
