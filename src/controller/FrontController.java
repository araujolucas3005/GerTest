package controller;

import exception.AutenticationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.BO.UsuarioBO;
import model.BO.UsuarioInterBO;
import model.VO.UsuarioVO;

public class FrontController {
	@FXML private Label falhaAut;
	@FXML private TextField login;
	@FXML private PasswordField senha;
	
	private static UsuarioInterBO<UsuarioVO> usuBO = new UsuarioBO<UsuarioVO>();
	
	public void autenticar(ActionEvent event) throws Exception{
		UsuarioVO vo = new UsuarioVO();
		vo.setLogin(login.getText());
		vo.setSenha(senha.getText());
		try {
			UsuarioVO autenticado = usuBO.autenticar(vo);
			if(autenticado instanceof UsuarioVO ) {
				System.out.println("chegou aqui!");
			}
		}
		catch(AutenticationException e) {
			falhaAut.setText("Usuário ou senha inválidos");
			falhaAut.setVisible(true);
		}
		
	}
}