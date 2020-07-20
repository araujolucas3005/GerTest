package controller;

import java.util.List;

import exception.DisciplinaJaExisteException;
import exception.TipoErradoExcepetion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextAlignment;
import model.BO.AssuntoBO;
import model.BO.DisciplinaBO;
import model.VO.AssuntoVO;
import model.VO.BiologicaVO;
import model.VO.DisciplinaVO;
import model.VO.ExataVO;
import model.VO.HumanaVO;
import view.Telas;

public class FrontController3 {

	@FXML
	private TextField nomeDisciplina;

	@FXML
	private TextField codigoDisciplina;

	@FXML
	private TextField tipoDisciplina;

	@FXML
	private TextField textAssunto;

	@FXML
	private TableColumn<AssuntoVO, String> conteudo;

	@FXML
	private TableView<AssuntoVO> tabelaAssuntos;

	@FXML
	private Label error;

	ObservableList<AssuntoVO> list = FXCollections.observableArrayList();

	public void novoAssunto(ActionEvent event) throws Exception {
		AssuntoVO assunto = new AssuntoVO();

		// para testar se ja existe o conteudo na tabela
		boolean testeExisteAssunto = false;
		if (list.size() > 0) {
			for (AssuntoVO assunt : list) {
				if (textAssunto.getText().equals(assunt.getConteudo())) {
					testeExisteAssunto = true;
					break;
				}
			}
		}
		try {
			if (testeExisteAssunto == false && textAssunto.getText().length() > 1) {
				assunto.setConteudo(textAssunto.getText());
				list.add(assunto);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			error.setText("Já tem assunto com esse conteudo ou em branco!");
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		}
		conteudo.setCellValueFactory(new PropertyValueFactory<AssuntoVO, String>("conteudo"));

		tabelaAssuntos.setItems(list);
	}

	public void cadastrarDisciplina(ActionEvent event) {

		DisciplinaVO disciplina = null;
		DisciplinaBO bo = new DisciplinaBO();
		AssuntoBO bo2 = new AssuntoBO();

		try {
			if (tipoDisciplina.getText().equals("Exata")) {
				disciplina = new ExataVO();
			} else if (tipoDisciplina.getText().equals("Biologica")) {
				disciplina = new BiologicaVO();
			} else if (tipoDisciplina.getText().equals("Humana")) {
				disciplina = new HumanaVO();
			} else {
				throw new TipoErradoExcepetion();
			}

			disciplina.setCodigo(codigoDisciplina.getText());
			disciplina.setNome(nomeDisciplina.getText());

			try {
				try {
					bo.cadastrar(disciplina);
				} catch (Exception e) {
					throw new DisciplinaJaExisteException();
				}

				List<DisciplinaVO> disciplinas = bo.listar();

				for (AssuntoVO assunto : list) {
					assunto.setIdDisciplina(new Long(disciplinas.get(disciplinas.size()-1).getId()));
					try {
						bo2.cadastrar(assunto);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						error.setText("Ja tem esse assunto no bd!");
						error.setVisible(true);
						error.setTextAlignment(TextAlignment.CENTER);
					}
				}
			} catch (DisciplinaJaExisteException e) {
				error.setText("Já existe uma disciplina com esse nome ou codigo!");
				error.setVisible(true);
				error.setTextAlignment(TextAlignment.CENTER);
			}
			try {
				Telas.telaDisciplinas();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (TipoErradoExcepetion e) {
			error.setText("Tipo errado!");
			error.setTextAlignment(TextAlignment.CENTER);
		}
	}

	public void deletarAssunto(ActionEvent event) {
		tabelaAssuntos.getItems().removeAll(tabelaAssuntos.getSelectionModel().getSelectedItem());
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
