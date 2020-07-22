package exception;

import java.sql.SQLException;

public class UsuarioExistenteException extends SQLException{
private static final long serialVersionUID = 1L;
	
	public UsuarioExistenteException() {
		super("Login já cadastrado!");
	}
}
