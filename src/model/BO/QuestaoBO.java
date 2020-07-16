package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.VO.DiscursivaVO;
import model.VO.MultiplaEscolhaVO;
import model.VO.QuestaoVO;
import model.VO.VerdadeiroOuFalsoVO;

public class QuestaoBO {

	// simulando o banco de dados
	List<QuestaoVO> bancoDeDados = new ArrayList<>();

	public QuestaoVO[] listar() {
		QuestaoVO q1 = new MultiplaEscolhaVO();
		QuestaoVO q2 = new VerdadeiroOuFalsoVO();

		QuestaoVO[] questoes = new QuestaoVO[2];
		questoes[0] = q1;
		questoes[1] = q2;

		return questoes;
	}

	public void cadastrar(QuestaoVO questao) {
		if (questao != null) {
			boolean teste = true;
			while (teste) {
				for (int i = 0; i < bancoDeDados.size(); i++) {
					if (questao.getCodigo() == bancoDeDados.get(i).getCodigo()) {
						teste = false;
					}
				}
			}
			if (teste) {
				bancoDeDados.add(questao);
			}
		}
	}

	public void adicionar(QuestaoVO questao, String codigoEnunciadoAssuntoOuGabarito, String tipo) {
		if (questao != null && codigoEnunciadoAssuntoOuGabarito != null && tipo != null) {
			switch (tipo) {
			case "codigo":
				if (questao.getCodigo() == null)
					questao.setCodigo(codigoEnunciadoAssuntoOuGabarito);
			case "enunciado":
				if (questao.getEnunciado() == null)
					questao.setEnunciado(codigoEnunciadoAssuntoOuGabarito);
			case "gabarito":
				if (questao.getGabarito() == null)
					questao.setGabarito(codigoEnunciadoAssuntoOuGabarito);
			}
		}
	}

	public void adicionar(QuestaoVO questao, String opcao) {
		if (questao != null && opcao != null) {
			if (questao instanceof VerdadeiroOuFalsoVO) {
				VerdadeiroOuFalsoVO vf = (VerdadeiroOuFalsoVO) questao;
				vf.addOpcao(opcao);
				questao = vf;
			} else {
				MultiplaEscolhaVO me = (MultiplaEscolhaVO) questao;
				me.addOpcao(opcao);
				questao = me;
			}
		}
	}

	public void editar(QuestaoVO questao, String codigoEnunciadoAssuntoOuGabarito, String tipo) {
		if (questao != null && codigoEnunciadoAssuntoOuGabarito != null && tipo != null) {
			switch (tipo) {
			case "codigo":
				questao.setCodigo(codigoEnunciadoAssuntoOuGabarito);
			case "enunciado":
				questao.setEnunciado(codigoEnunciadoAssuntoOuGabarito);
			case "gabarito":
				questao.setGabarito(codigoEnunciadoAssuntoOuGabarito);
			}
		}
	}

	public void editar(QuestaoVO questao, String opcao, int numeroDaOpcao) {
		if (questao != null && opcao != null && numeroDaOpcao != 0) {
			numeroDaOpcao--;
			char letraOpcao = 'a';
			letraOpcao += numeroDaOpcao;
			if (questao instanceof VerdadeiroOuFalsoVO) {
				VerdadeiroOuFalsoVO vf = (VerdadeiroOuFalsoVO) questao;
				vf.getOpcoes().set(numeroDaOpcao, String.valueOf(letraOpcao) + ") " + opcao);
				questao = vf;
			} else {
				MultiplaEscolhaVO me = (MultiplaEscolhaVO) questao;
				me.getOpcoes().set(numeroDaOpcao, String.valueOf(letraOpcao) + ") " + opcao);
				questao = me;
			}
		}
	}

	public void remover(QuestaoVO questao) {
		if (questao != null) {
			for (QuestaoVO q : bancoDeDados) {
				if (questao == q) {
					bancoDeDados.remove(questao);
					break;
				}
			}
		}
	}

	public void remover(QuestaoVO questao, int numeroOpcao) {
		if (questao != null && numeroOpcao > 0) {
			numeroOpcao--;
			if (questao instanceof VerdadeiroOuFalsoVO) {
				VerdadeiroOuFalsoVO vf = (VerdadeiroOuFalsoVO) questao;
				if (numeroOpcao < vf.getOpcoes().size())
					vf.removeOpcao(numeroOpcao);
				questao = vf;
			} else {
				MultiplaEscolhaVO me = (MultiplaEscolhaVO) questao;
				if (numeroOpcao < me.getOpcoes().size())
					me.removeOpcao(numeroOpcao);
				questao = me;
			}
		}
	}

	public QuestaoVO buscar(QuestaoVO questao) {
		if (questao != null) {
			for (QuestaoVO q : bancoDeDados) {
				if (questao == q) {
					return questao;
				}
			}
		}
		return null;
	}
	
	/*public QuestaoVO buscarByNivel(int nivel) {
		String sql = "select * from questao where nivel = " + nivel;
		Statement st;
		ResultSet rs;
		QuestaoVO vo1 = new DiscursivaVO();
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				vo1.setCodigo(rs.getString("codigo"));
				vo1.setEnunciado(rs.getString("enunciado"));
				vo1.setGabarito(rs.getString("gabarito"));
				vo1.setNivel(rs.getInt("nivel"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo1;}*/
	
	/*public QuestaoVO buscarByCodigo(String codigo) {
		String sql = "select * from questao where codigo = " + "'" + codigo + "'";
		Statement st;
		ResultSet rs;
		QuestaoVO vo1 = new DiscursivaVO();
		try {		
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				vo1.setCodigo(rs.getString("codigo"));
				vo1.setEnunciado(rs.getString("enunciado"));
				vo1.setGabarito(rs.getString("gabarito"));
				vo1.setNivel(rs.getInt("nivel"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo1;
	}*/
		
}
