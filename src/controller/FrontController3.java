package controller;

import java.util.List;

import exception.AssuntoEmBrancoException;
import exception.DisciplinaJaExisteException;
import exception.NomeEmBrancoExcepetion;
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
		AssuntoBO bo2 = new AssuntoBO();

		try {
			
			if (textAssunto.getText().length() < 1) {
				throw new AssuntoEmBrancoException();
			}
			for (AssuntoVO assunt : list) {
				if (textAssunto.getText().equals(assunt.getConteudo())) {
					throw new Exception();
				}
			}

			for (AssuntoVO assunt : bo2.listar()) {
				if (textAssunto.getText().equals(assunt.getConteudo())) {
					throw new DisciplinaJaExisteException();
				}
			}

			assunto.setConteudo(textAssunto.getText());
			list.add(assunto);
			conteudo.setCellValueFactory(new PropertyValueFactory<AssuntoVO, String>("conteudo"));
			tabelaAssuntos.setItems(list);
		} catch (AssuntoEmBrancoException s) {
			error.setText("Assunto em branco!");
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		} catch (DisciplinaJaExisteException f) {
			error.setText("Já tem assunto no db!");
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		} catch (Exception e) {
			error.setText("Já tem assunto com esse conteudo da tabela!");
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		} 
	}

	public void cadastrarDisciplina(ActionEvent event) {

		DisciplinaVO disciplina = null;
		DisciplinaBO bo = new DisciplinaBO();
		AssuntoBO bo2 = new AssuntoBO();

		try {
			if (nomeDisciplina.getText().length() < 1) {
				throw new NomeEmBrancoExcepetion();
			}
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
				bo.cadastrar(disciplina);
			} catch (Exception e) {
				throw new DisciplinaJaExisteException();
			}

			List<DisciplinaVO> disciplinas = bo.listar();
			for (AssuntoVO assuntosAdicionados : list) {
				assuntosAdicionados.setIdDisciplina(new Long(disciplinas.get(disciplinas.size()-1).getId()));
				try {
					bo2.cadastrar(assuntosAdicionados);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				Telas.telaDisciplinas();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NomeEmBrancoExcepetion e) {
			// TODO Auto-generated catch block
			error.setText("Nome em branco!");
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		} catch (TipoErradoExcepetion e) {
			error.setText("Tipo errado!");
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		} catch (DisciplinaJaExisteException e) {
			error.setText("Já existe uma disciplina com esse nome ou codigo!");
			error.setVisible(true);
			error.setVisible(true);
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
