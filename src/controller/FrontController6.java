package controller;

import java.awt.Label;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.BO.ProvaBO;
import model.VO.QuestaoVO;

public class FrontController6 implements Initializable {

	@FXML
	private Pane questoesDaProva;

	@FXML
	private TableColumn<QuestaoVO, String> codigo;

	@FXML
	private TableColumn<QuestaoVO, String> enunciado;

	@FXML
	private TableColumn<QuestaoVO, String> gabarito;

	@FXML
	private TableColumn<QuestaoVO, Long> id;
	
    @FXML
    private TableColumn<QuestaoVO, Integer> nivel;

	@FXML
	private Label questoesDaProvaID;

	@FXML
	private TableView<QuestaoVO> tabelaDasQuestoes;

	ObservableList<QuestaoVO> list = FXCollections.observableArrayList();
	
	private ProvaBO bo = new ProvaBO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		questoesDaProvaID.setText(questoesDaProvaID.getText() + FrontController5.getLastSelected());
		List<QuestaoVO> questoes = bo.listarQuestoes(FrontController5.getLastSelected());
		list.addAll(questoes);

		nivel.setCellValueFactory(new PropertyValueFactory<QuestaoVO, Integer>("nivel"));
		enunciado.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("enunciado"));
		enunciado.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("nivel2"));
		gabarito.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("nivel3"));
		nivel.setCellValueFactory(new PropertyValueFactory<QuestaoVO, Integer>("nivel4"));

		tabelaDasQuestoes.setItems(list);
	}

}
