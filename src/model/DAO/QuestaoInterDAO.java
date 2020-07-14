package model.DAO;

import java.util.List;

import model.VO.QuestaoVO;

public interface QuestaoInterDAO {
	public void editar(QuestaoVO questao);
	public void remover(QuestaoVO questao);
	public QuestaoVO buscarByCodigo(String codigo);
	public QuestaoVO buscarByNivel(int nivel);
	public List<QuestaoVO> listar();
	public void inserir(QuestaoVO questao, String optativa);
}
