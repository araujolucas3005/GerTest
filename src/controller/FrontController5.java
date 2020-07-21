package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.AssuntoBO;
import model.BO.QuestaoBO;
import model.VO.AssuntoVO;
import model.VO.QuestaoVO;
import view.Telas;

public class FrontController5 implements Initializable{
	QuestaoBO<QuestaoVO> boQ = new QuestaoBO<QuestaoVO>();
	AssuntoBO boA = new AssuntoBO();
	
	@FXML private TableView<QuestaoVO> tabelaQuestoes;
	@FXML private TableColumn<QuestaoVO, Long> idQuestao;
	@FXML private TableColumn<QuestaoVO, String> codigoQuestao;
	@FXML private TableColumn<QuestaoVO, Integer> nivelQuestao;
	@FXML private TableColumn<QuestaoVO, String> tipoQuestao;
	@FXML private TableColumn<QuestaoVO, String> gabaritoQuestao;
	@FXML private TableColumn<QuestaoVO, Long> disciplinaQuestao;
	@FXML private TableColumn<QuestaoVO, Long> assuntoQuestao;
	@FXML private Label error;
	@FXML private Label error2;
	
	private static QuestaoVO lastSelected;
	ObservableList<QuestaoVO> list = FXCollections.observableArrayList();
	ObservableList<AssuntoVO> list2 = FXCollections.observableArrayList();

	@Override
	public void initialize(URL local, ResourceBundle resources) {
		// TODO Auto-generated method stub
				loadData();
	}
	
	public void loadData() {
		List<QuestaoVO> questoes =  boQ.listarTodos();
		list.addAll(questoes);
		
		idQuestao.setCellValueFactory(new PropertyValueFactory<QuestaoVO, Long>("idQuestao"));
		codigoQuestao.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("codigo"));
		nivelQuestao.setCellValueFactory(new PropertyValueFactory<QuestaoVO, Integer>("nivel"));
		tipoQuestao.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("tipo"));	
		gabaritoQuestao.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("gabarito"));
		disciplinaQuestao.setCellValueFactory(new PropertyValueFactory<QuestaoVO,Long>("idDisciplina"));
		assuntoQuestao.setCellValueFactory(new PropertyValueFactory<QuestaoVO, Long>("idAssunto"));

		tabelaQuestoes.setItems(list);
	}

	public static QuestaoVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(QuestaoVO lastSelected) {
		FrontController5.lastSelected = lastSelected;
	}
	
	public void removerQuestao() {
		QuestaoBO<QuestaoVO> bo = new QuestaoBO<QuestaoVO>();
		try {
			if (tabelaQuestoes.getSelectionModel().getSelectedItem() == null) {
				throw new Exception();
			}
			bo.remover(tabelaQuestoes.getSelectionModel().getSelectedItem());
			tabelaQuestoes.getItems().removeAll(tabelaQuestoes.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			error.setVisible(true);
		}
	}
	
	public void cadastroQuestao(ActionEvent event) throws Exception {
		Telas.telaCadastroQuestao();
	}
	
	public void editarQuestao() throws Exception{
		try {
			if (tabelaQuestoes.getSelectionModel().getSelectedItem() == null) {
				throw new Exception();
			}	
				lastSelected = tabelaQuestoes.getSelectionModel().getSelectedItem();
				Telas.telaEdicaoQuestao();
		}
		catch (Exception e) {
			error2.setVisible(true);
		}
	}
	
	public void verEnunciado() {
		try {
			if (tabelaQuestoes.getSelectionModel().getSelectedItem() == null) {
				throw new Exception();
			}	
				lastSelected = tabelaQuestoes.getSelectionModel().getSelectedItem();
				Telas.telaEnunciado();
		}
		catch (Exception e) {
			error2.setVisible(true);
		}
	}
	
	public void disciplinas() throws Exception {
		Telas.telaDisciplinas();
	}
	
}
