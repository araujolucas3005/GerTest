package exception;

import java.sql.SQLException;

public class InsertException extends SQLException {
	
	private static final long serialVersionUID = 1L;
	
	public InsertException() {
		super("Entidade não cadastrada!");
	}
	
}