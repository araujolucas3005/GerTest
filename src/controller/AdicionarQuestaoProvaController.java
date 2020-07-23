package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import exception.InsertException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.BaseInterBO;
import model.BO.ProvaBO;
import model.BO.ProvaInterBO;
import model.BO.QuestaoBO;
import model.VO.ProvaVO;
import model.VO.QuestaoVO;
import view.Telas;
import javafx.fxml.Initializable;

public class AdicionarQuestaoProvaController implements Initializable {

	@FXML
	private TableColumn<QuestaoVO, String> codigo;

	@FXML
	private TableColumn<QuestaoVO, String> tipo;

	@FXML
	private TableView<QuestaoVO> tabelaQuestoes;

	@FXML
	private TableColumn<QuestaoVO, String> gabarito;

	@FXML
	private TableColumn<QuestaoVO, Integer> nivel;
	
	@FXML
	private TextArea enunciado;
	
	@FXML
	private Label error;
	
	@FXML
	private Label error2;
	
	private BaseInterBO<QuestaoVO> bo = new QuestaoBO<>();
	private ProvaInterBO<ProvaVO> bo2 = new ProvaBO();

	private ObservableList<QuestaoVO> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO Auto-generated method stub
		List<QuestaoVO> questoes = null;
		try {
			questoes = bo.listar();
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<QuestaoVO> questoesDaDisciplina = new ArrayList<>();
		for (int i = 0; i < questoes.size(); i++) {
			if (questoes.get(i).getIdDisciplina() == DisciplinasController.getLastSelectedDisciplina().getId()) {
				questoesDaDisciplina.add(questoes.get(i));
			}
		}
		
		List<QuestaoVO> questoesDaProva = bo2.listarQuestoes(ProvasController.getLastSelectedProva());
		for (QuestaoVO questao : questoesDaProva) {
			// se os codigos forem iguais, a questao ja esta na prova
			questoesDaDisciplina.removeIf(q -> q.getCodigo().equals(questao.getCodigo()));
		}
		list.addAll(questoesDaDisciplina);
		
		nivel.setCellValueFactory(new PropertyValueFactory<QuestaoVO, Integer>("nivel"));
		gabarito.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("gabarito"));
		codigo.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("codigo"));
		tipo.setCellValueFactory(new PropertyValueFactory<QuestaoVO, String>("tipo"));

		tabelaQuestoes.setItems(list);
	}
	
	public void retornar(ActionEvent event) {
		try {
			Telas.telaQuestoesDaProva();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adicionarNaProva(ActionEvent event) {
		try {
			if (tabelaQuestoes.getSelectionModel().getSelectedItem() == null) {
				throw new Exception();
			} else {
				bo2.cadastrarQuestaoAvulsa(ProvasController.getLastSelectedProva(), tabelaQuestoes.getSelectionModel().getSelectedItem());
				list.remove(tabelaQuestoes.getSelectionModel().getSelectedItem());
			}
		} catch (Exception e) {
			error.setText("Questao nao selecionada!");
			error.setVisible(true);
		}
	}
	
	public void verEnunciado(ActionEvent event) {
		try {
			if (tabelaQuestoes.getSelectionModel().getSelectedItem().getEnunciado() == null) {
				throw new Exception();
			} else {
				enunciado.setWrapText(true);
				enunciado.setText(tabelaQuestoes.getSelectionModel().getSelectedItem().getEnunciado());
				enunciado.setVisible(true);
			} 
		} catch (Exception e) {
			error2.setText("Questao nao selecionada!");
			error2.setVisible(true);
		}
	}
}
