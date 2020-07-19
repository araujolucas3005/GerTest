package exception;

import java.sql.SQLException;

public class NotFoundException extends SQLException{

	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super("Entidade n�o encontrada!");
	}
}