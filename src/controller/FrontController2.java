package controller;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

import exception.CampoEmBrancoExcepetion;
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
import model.BO.DisciplinaBO;
import model.VO.DisciplinaVO;
import view.Telas;

public class FrontController2 implements Initializable {
	DisciplinaBO bo = new DisciplinaBO();

	@FXML
	private Label error2;

	@FXML
	private TableView<DisciplinaVO> tabelaDisciplinas;

	@FXML
	private TableColumn<DisciplinaVO, Long> id;

	@FXML
	private TableColumn<DisciplinaVO, String> nome;

	@FXML
	private TableColumn<DisciplinaVO, String> codigo;

	@FXML
	private Label error;

	@FXML
	private TextField editarNome;

	@FXML
	private TextField editarCodigo;
	
	@FXML
    private Label errorDisciplina;

	private static DisciplinaVO lastSelected;

	ObservableList<DisciplinaVO> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadData();
	}

	public void loadData() {
		List<DisciplinaVO> disciplinas = bo.listar();
		list.addAll(disciplinas);

		id.setCellValueFactory(new PropertyValueFactory<DisciplinaVO, Long>("id"));
		nome.setCellValueFactory(new PropertyValueFactory<DisciplinaVO, String>("nome"));
		codigo.setCellValueFactory(new PropertyValueFactory<DisciplinaVO, String>("codigo"));

		tabelaDisciplinas.setItems(list);
	}

	public void novaDisciplina(ActionEvent event) throws Exception {
		Telas.telaNovaDisciplina();
	}

	public void removerDisciplina(ActionEvent event) {
		DisciplinaBO bo = new DisciplinaBO();
		try {
			if (tabelaDisciplinas.getSelectionModel().getSelectedItem() == null) {
				throw new Exception();
			}
			bo.remover(tabelaDisciplinas.getSelectionModel().getSelectedItem());
			tabelaDisciplinas.getItems().removeAll(tabelaDisciplinas.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			error2.setText("Disciplina nao selecionada!");
			error2.setVisible(true);
		}
	}

	public static DisciplinaVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(DisciplinaVO lastSelected) {
		FrontController2.lastSelected = lastSelected;
	}

	public void assuntos(ActionEvent event) {
		lastSelected = tabelaDisciplinas.getSelectionModel().getSelectedItem();
		try {
			if (lastSelected == null) {
				throw new Exception();
			} else {
				Telas.telaAssuntos();
			}
		} catch (Exception e) {
			error.setText("Discip. nao selecionada!");
			error.setVisible(true);
		}
	}

	public void editarDisciplina(ActionEvent event) {
		DisciplinaVO disciplina = tabelaDisciplinas.getSelectionModel().getSelectedItem();
		try {
			if (disciplina == null) {
				throw new Exception();
			} else {
				if (editarNome.getText().length() < 1) {
					throw new CampoEmBrancoExcepetion();
				}
				disciplina.setNome(editarNome.getText());
				disciplina.setCodigo(editarCodigo.getText());
				bo.alterar(disciplina);
				int posicao = 0;
				for (int i = 0; i < list.size(); i++) {
					if (disciplina.getId() == list.get(i).getId()) {
						posicao = i;
					}
				}
				list.set(posicao, disciplina);
			}
		} catch (InsertException e) {
			errorDisciplina.setText("Ja existe disciplina com esse nome ou codigo!");
			errorDisciplina.setVisible(true);
		} catch (CampoEmBrancoExcepetion f) {
			errorDisciplina.setText("Nome em branco!");
			errorDisciplina.setVisible(true);
		} catch (Exception e) {
			errorDisciplina.setText("Disciplina nao selecionada!");
			errorDisciplina.setVisible(true);
		} 
	}
}
