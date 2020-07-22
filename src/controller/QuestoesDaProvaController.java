
package controller;

import java.io.IOException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.BO.ProvaBO;
import model.BO.QuestaoBO;
import model.VO.QuestaoVO;
import view.Telas;

public class QuestoesDaProvaController implements Initializable {

	@FXML
	private Pane questoesDaProva;

	@FXML
	private TableColumn<QuestaoVO, String> codigo;

	@FXML
	private TableColumn<QuestaoVO, String> gabarito;

	@FXML
	private TableColumn<QuestaoVO, Long> id;

	@FXML
	private TableColumn<QuestaoVO, Integer> nivel;

	@FXML
	private Label provasDe;

	@FXML
	private TextArea enunciado;

	@FXML
	private TableView<QuestaoVO> tabelaDasQuestoes;

	@FXML
	private Label errorEnunciado;

	@FXML
	private Label errorAdicionar;

	@FXML
	private Label errorRemover;

	@FXML
	private TableColumn<QuestaoVO, String> tipo;

	ObservableList<QuestaoVO> list = FXCollections.observableArrayList();

	private ProvaBO bo = new ProvaBO();
	private QuestaoBO<QuestaoVO> boQ = new QuestaoBO<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		provasDe.setText(provasDe.getText() + ProvasController.lastSelectedProva().getId());
		List<QuestaoVO> questoes = bo.listarQuestoes(ProvasController.lastSelectedProva());
		list.addAll(questoes);

		
		nivel.setCellValueFactory(new PropertyValueFactory<QuestaoVO, Integer>("nivel"));
		gabarito.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("gabarito"));
		codigo.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("codigo"));
		tipo.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("tipo"));
		
		tabelaDasQuestoes.setItems(list);
	}

	public void removerQuestao(ActionEvent event) {
		try {
			if (tabelaDasQuestoes.getSelectionModel().getSelectedItem().getEnunciado() != null) {
				boQ.removerDaProva(tabelaDasQuestoes.getSelectionModel().getSelectedItem());
				list.remove(tabelaDasQuestoes.getSelectionModel().getSelectedItem());
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			errorRemover.setText("Questao nao selecionada!");
			errorRemover.setVisible(true);
		}
		if (list.size() == 0) {
			bo.remover(ProvasController.lastSelectedProva());
			try {
				Telas.telaDasProvas();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void verEnunciado(ActionEvent event) {
		try {
			if (tabelaDasQuestoes.getSelectionModel().getSelectedItem().getEnunciado() != null) {
				enunciado.setWrapText(true);
				enunciado.setText(tabelaDasQuestoes.getSelectionModel().getSelectedItem().getEnunciado());
				enunciado.setVisible(true);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			errorEnunciado.setText("Questao nao selecionada!");
			errorEnunciado.setVisible(true);
		}
	}

	public void retornar(ActionEvent event) {
		try {
			Telas.telaDasProvas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adicionarQuestao(ActionEvent event) {
		try {
			Telas.telaDeAdicaoQuestoesNaProva();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}