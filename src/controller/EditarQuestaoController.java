package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.AssuntoBO;
import model.BO.AssuntoInterBO;
import model.DAO.QuestaoDAO;
import model.VO.AssuntoVO;
import model.VO.QuestaoVO;
import view.Telas;

public class EditarQuestaoController implements Initializable {
	
	@FXML private TableView<AssuntoVO> tabelaAssuntos;
	@FXML private TableColumn<AssuntoVO, String> colunaAssuntos;
	@FXML private TextField nivelEdicao;
	@FXML private TextField gabaritoEdicao;
	@FXML private TextArea enunciadoEdicao;
	
	ObservableList<AssuntoVO> list = FXCollections.observableArrayList();
	AssuntoInterBO<AssuntoVO> boA = new AssuntoBO<>();
	QuestaoDAO<QuestaoVO> qDAO = new QuestaoDAO<QuestaoVO>();
	private static AssuntoVO lastSelected;
	private static QuestaoVO lastSelected2;

	public void initialize(URL local, ResourceBundle resources) {
		try {
			loadData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void loadData() throws SQLException {
	lastSelected2 = QuestoesController.getLastSelected();
	if (lastSelected2.getTipo().equals("Discursiva")) {
		gabaritoEdicao.setText("Subjetivo");
		gabaritoEdicao.setEditable(false);
	}
	List<AssuntoVO> assuntos = boA.listarPorDisciplina(lastSelected2);
	list.addAll(assuntos);
	
	colunaAssuntos.setCellValueFactory(new PropertyValueFactory<AssuntoVO, String>("conteudo"));
	tabelaAssuntos.setItems(list);
		
	}
	
	public static AssuntoVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(AssuntoVO lastSelected) {
		EditarQuestaoController.lastSelected = lastSelected;
	}

	public void editar() throws Exception {
		if (lastSelected2.getTipo().equals("Discursiva")) {
			if (nivelEdicao.getText().equals("1") || nivelEdicao.getText().equals("2") || nivelEdicao.getText().equals("3") || nivelEdicao.getText().equals("4")) {
				lastSelected2.setNivel(Integer.parseInt(nivelEdicao.getText()));
			}
			if (!enunciadoEdicao.getText().equals("")) {
				lastSelected2.setEnunciado(enunciadoEdicao.getText());
			}
			if (tabelaAssuntos.getSelectionModel().getSelectedItem() != null) {
				AssuntoVO aVO = new AssuntoVO();
				aVO = tabelaAssuntos.getSelectionModel().getSelectedItem();
				lastSelected2.setIdAssunto(aVO.getId());
			}	
			qDAO.atualizar(lastSelected2);
			Telas.telaQuestoes();
		}
		if (lastSelected2.getTipo().equals("V ou F")) {
			if (!nivelEdicao.getText().equals("")) {
				lastSelected2.setNivel(Integer.parseInt(nivelEdicao.getText()));
			}
			if (!gabaritoEdicao.getText().equals("")) {
				lastSelected2.setGabarito(gabaritoEdicao.getText());
			}
			if (!enunciadoEdicao.getText().equals("")) {
				lastSelected2.setEnunciado(enunciadoEdicao.getText());
			}
			if (tabelaAssuntos.getSelectionModel().getSelectedItem() != null) {
				AssuntoVO aVO = new AssuntoVO();
				aVO = tabelaAssuntos.getSelectionModel().getSelectedItem();
				lastSelected2.setIdAssunto(aVO.getId());
			}
			qDAO.atualizar(lastSelected2);
			Telas.telaQuestoes();
		}
		if (lastSelected2.getTipo().equals("Multipla Escolha")) {
			if (!nivelEdicao.getText().equals("")) {
				lastSelected2.setNivel(Integer.parseInt(nivelEdicao.getText()));
			}
			if (!gabaritoEdicao.getText().equals("")) {
				lastSelected2.setGabarito(gabaritoEdicao.getText());
			}
			if (!enunciadoEdicao.getText().equals("")) {
				lastSelected2.setEnunciado(enunciadoEdicao.getText());
			}
			if (tabelaAssuntos.getSelectionModel().getSelectedItem() != null) {
				AssuntoVO aVO = new AssuntoVO();
				aVO = tabelaAssuntos.getSelectionModel().getSelectedItem();
				lastSelected2.setIdAssunto(aVO.getId());
			}
			qDAO.atualizar(lastSelected2);
			Telas.telaQuestoes();
		}
	}
	
	public void retorno() throws Exception {
		Telas.telaQuestoes();
	}

}
