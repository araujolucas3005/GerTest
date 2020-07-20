package model.BO;


import exception.AutenticationException;
import model.VO.UsuarioVO;

public interface UsuarioInterBO<VO extends UsuarioVO> extends BaseInterBO<VO> {
	public UsuarioVO autenticar(VO vo) throws AutenticationException;

}
