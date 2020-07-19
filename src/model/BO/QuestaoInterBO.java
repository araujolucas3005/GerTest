package model.BO;

import java.util.List;

import model.VO.QuestaoVO;

public interface QuestaoInterBO<VO extends QuestaoVO> extends BaseInterBO<VO> {
	public List<QuestaoVO> listarTodos() throws Exception;
}
