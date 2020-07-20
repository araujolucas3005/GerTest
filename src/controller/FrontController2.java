package controller;

import java.util.List;
import java.net.URL;
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
import model.BO.DisciplinaBO;
import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import view.Telas;

public class FrontController2 implements Initializable {
	DisciplinaBO bo = new DisciplinaBO();

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
		bo.remover(tabelaDisciplinas.getSelectionModel().getSelectedItem());
		tabelaDisciplinas.getItems().removeAll(tabelaDisciplinas.getSelectionModel().getSelectedItem());
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
}
