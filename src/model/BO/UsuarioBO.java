package model.BO;

import java.util.ArrayList;
import java.util.List;

import model.VO.UsuarioVO;

public class UsuarioBO {
	private List<UsuarioVO> bancoDeDados = new ArrayList<>();
	
	public UsuarioVO[] listar() {
		UsuarioVO usu1 = new UsuarioVO();
		UsuarioVO usu2 = new UsuarioVO();
		
		UsuarioVO[] usuarios = new UsuarioVO[2];
		usuarios[0] = usu1;
		usuarios[1] = usu2;
		
		return usuarios;
	}
	
	// soh pode o Helielcio
	public void cadastrar(UsuarioVO usuario) {
		if (usuario != null) {
			bancoDeDados.add(usuario);
		}
	}
	
	// soh pode o Helielcio
	public void remover(UsuarioVO usuario) {
		if (usuario != null) {
			bancoDeDados.remove(usuario);
		}
	}
	
	public void editar(UsuarioVO usuario, String senha) {
		if (senha != null) {
			usuario.setSenha(senha);
		}
	}
	
	public UsuarioVO buscar(UsuarioVO usuario) {
		if (usuario != null) {
			for (UsuarioVO usu : bancoDeDados) {
				if (usu == usuario) {
					return usuario;
				}
			}
		}
		return null;
	}
}
