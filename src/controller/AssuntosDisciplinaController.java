package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import exception.AssuntoEmBrancoException;
import exception.AssuntoMuitoLongoException;
import exception.AssuntoNaoSelecionadoExcepetion;
import exception.InsertException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.AssuntoBO;
import model.BO.AssuntoInterBO;
import model.VO.AssuntoVO;
import view.Telas;

public class AssuntosDisciplinaController implements Initializable {

	@FXML
	private Label errorRemoverAssunto;

	@FXML
	private TableView<AssuntoVO> tabelaAssuntos;

	@FXML
	private TableColumn<AssuntoVO, String> conteudo;

	@FXML
	private TextField textAssunto;

	@FXML
	private Label error;

	@FXML
	private Label assuntosDaDisciplina;

	AssuntoInterBO<AssuntoVO> bo = new AssuntoBO();
	ObservableList<AssuntoVO> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadData();
	}

	public void loadData() {
		assuntosDaDisciplina.setText("Assuntos de " + DisciplinasController.getLastSelectedDisciplina().getNome());
		AssuntoVO assunto = new AssuntoVO();
		assunto.setIdDisciplina(DisciplinasController.getLastSelectedDisciplina().getId());
		List<AssuntoVO> assuntos = null;
		try {
			assuntos = bo.listarPorDisciplina(assunto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.addAll(assuntos);

		conteudo.setCellValueFactory(new PropertyValueFactory<AssuntoVO, String>("conteudo"));
		tabelaAssuntos.setItems(list);
	}

	public void removerAssunto(ActionEvent event) {
		try {
			if (tabelaAssuntos.getSelectionModel().getSelectedItem() == null) {
				throw new AssuntoNaoSelecionadoExcepetion();
			}
			bo.remover(tabelaAssuntos.getSelectionModel().getSelectedItem());
			list.remove(tabelaAssuntos.getSelectionModel().getSelectedItem());
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AssuntoNaoSelecionadoExcepetion e) {
			// TODO Auto-generated catch block
			errorRemoverAssunto.setText("Assunto nao selecionado!");
			errorRemoverAssunto.setVisible(true);
		}
	}

	public void novoAssunto(ActionEvent event) {
		AssuntoVO assunto = new AssuntoVO();
		assunto.setConteudo(textAssunto.getText());

		try {
			if (textAssunto.getText().length() < 1) {
				throw new AssuntoEmBrancoException();
			}
			List<AssuntoVO> assuntos = bo.listar();
			if (assunto.getConteudo().length() > 0) {
				for (AssuntoVO assunt : assuntos) {
					if (assunto.getConteudo().equals(assunt.getConteudo())) {
						throw new Exception();
					}
				}
			}
			assunto.setIdDisciplina(DisciplinasController.getLastSelectedDisciplina().getId());
			try {
				bo.cadastrar(assunto);
			} catch (AssuntoMuitoLongoException e) {
				throw new AssuntoMuitoLongoException();
			}
			list.add(assunto);
		} catch (AssuntoMuitoLongoException e){
			error.setText("Assunto muito longo!");
			error.setVisible(true);
		} catch (AssuntoEmBrancoException e) {
			error.setText("Assunto em branco!");
			error.setVisible(true);
		} catch (Exception e) {
			error.setText("Assunto ja existe!");
			error.setVisible(true);
		}
	}

	public void retornar(ActionEvent event) {
		try {
			Telas.telaDisciplinas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}