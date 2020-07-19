package model.BO;

import java.util.List;

import exception.AutenticationException;
import exception.InsertException;
import exception.NotFoundException;
import model.VO.UsuarioVO;

public interface UsuarioInterBO<VO extends UsuarioVO> extends BaseInterBO<VO> {
	public UsuarioVO autenticar(VO vo) throws AutenticationException;

}
