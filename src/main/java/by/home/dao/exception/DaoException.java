package by.home.dao.exception;

public class DaoException extends Exception{
	private static final long serialVersionUID = 1L;

	public DaoException() {
	}

	public DaoException(String massage) {
		super(massage);
	}
}