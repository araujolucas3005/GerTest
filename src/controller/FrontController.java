package controller;

import exception.AutenticationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.BO.UsuarioBO;
import model.BO.UsuarioInterBO;
import model.DAO.UsuarioDAO;
import model.VO.UsuarioVO;
import view.Telas;

public class FrontController {
	@FXML private Label falhaAut;
	@FXML private TextField login;
	@FXML private PasswordField senha;
	@FXML private TextField loginCadastro;
	@FXML private TextField senhaCadastro;
	@FXML private TextField emailCadastro;
	@FXML private TextField nomeCadastro;
	@FXML private TextField cpfCadastro;
	
	private static UsuarioInterBO<UsuarioVO> usuBO = new UsuarioBO<UsuarioVO>();
	private static UsuarioDAO<UsuarioVO> usuDAO = new UsuarioDAO<UsuarioVO>();
	
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
	
	public void cadastroButton(ActionEvent event) throws Exception{
		Telas.telaCadastro();
	}
	
	public void loginButton(ActionEvent event) throws Exception{
		Telas.telaLogin();
	}
	
	public void cadastrar(ActionEvent event) throws Exception{
		UsuarioVO vo = new UsuarioVO();
		vo.setLogin(loginCadastro.getText());
		vo.setSenha(senhaCadastro.getText());
		vo.setEmail(emailCadastro.getText());
		vo.setCpf(cpfCadastro.getText());
		vo.setNome(nomeCadastro.getText());
		usuDAO.inserir(vo);
		Telas.telaLogin();
	}
	
}