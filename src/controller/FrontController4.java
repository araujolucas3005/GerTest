package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import exception.AssuntoEmBrancoException;
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
import model.VO.AssuntoVO;
import view.Telas;

public class FrontController4 implements Initializable {

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

	AssuntoBO bo = new AssuntoBO();
	ObservableList<AssuntoVO> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadData();
	}

	public void loadData() {
		assuntosDaDisciplina.setText("Assuntos de " + FrontController2.getLastSelected().getNome());
		AssuntoVO assunto = new AssuntoVO();
		assunto.setIdDisciplina(FrontController2.getLastSelected().getId());
		List<AssuntoVO> assuntos = bo.listarPorDisciplina(assunto);
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
			assunto.setIdDisciplina(FrontController2.getLastSelected().getId());
			bo.cadastrar(assunto);
			list.add(assunto);
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