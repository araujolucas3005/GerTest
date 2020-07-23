package controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import exception.CampoEmBrancoException;
import exception.DisciplinaJaExisteException;
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
import model.BO.BaseInterBO;
import model.BO.DisciplinaBO;
import model.BO.QuestaoBO;
import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.QuestaoVO;
import view.Telas;

public class DisciplinasController implements Initializable {
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

	@FXML
	private Label errorProva;

	private static DisciplinaVO lastSelectedDisciplina;

	ObservableList<DisciplinaVO> list = FXCollections.observableArrayList();
	BaseInterBO<DisciplinaVO> bo = new DisciplinaBO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadData();
	}

	public void loadData() {
		List<DisciplinaVO> disciplinas = new ArrayList<>();
		try {
			disciplinas = bo.listar();
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		BaseInterBO<QuestaoVO> bo2 = new QuestaoBO<>();
		BaseInterBO<AssuntoVO> bo3 = new AssuntoBO();
		try {
			if (tabelaDisciplinas.getSelectionModel().getSelectedItem() == null) {
				throw new Exception();
			}
			bo.remover(tabelaDisciplinas.getSelectionModel().getSelectedItem());
			List<QuestaoVO> questoes = bo2.listar();
			for (QuestaoVO questao : questoes) {
				if (questao.getIdDisciplina() == tabelaDisciplinas.getSelectionModel().getSelectedItem().getId()) {
					bo2.remover(questao);
				}
			}
			for (AssuntoVO assunto : bo3.listar()) {
				if (assunto.getIdDisciplina() == tabelaDisciplinas.getSelectionModel().getSelectedItem().getId()) {
					System.out.println(assunto.getConteudo());
					bo3.remover(assunto);
				}
			}
			tabelaDisciplinas.getItems().removeAll(tabelaDisciplinas.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			error2.setText("Disciplina nao selecionada!");
			error2.setVisible(true);
		}
	}

	public static DisciplinaVO getLastSelectedDisciplina() {
		return lastSelectedDisciplina;
	}

	public static void setLastSelectedDisciplina(DisciplinaVO lastSelected) {
		DisciplinasController.lastSelectedDisciplina = lastSelected;
	}

	public void assuntos(ActionEvent event) {
		try {
			if (tabelaDisciplinas.getSelectionModel().getSelectedItem() == null) {
				throw new Exception();
			} else {
				lastSelectedDisciplina = tabelaDisciplinas.getSelectionModel().getSelectedItem();
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
					throw new CampoEmBrancoException();
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
				lastSelectedDisciplina = tabelaDisciplinas.getSelectionModel().getSelectedItem();
				list.set(posicao, disciplina);
			}
		} catch (DisciplinaJaExisteException e) {
			errorDisciplina.setText("Ja existe disciplina com esse nome ou codigo!");
			errorDisciplina.setVisible(true);
		} catch (CampoEmBrancoException f) {
			errorDisciplina.setText("Nome em branco!");
			errorDisciplina.setVisible(true);
		} catch (Exception e) {
			errorDisciplina.setText("Disciplina nao selecionada!");
			errorDisciplina.setVisible(true);
		}
	}

	public void verProvas(ActionEvent event) {
		lastSelectedDisciplina = tabelaDisciplinas.getSelectionModel().getSelectedItem();
		try {
			if (tabelaDisciplinas.getSelectionModel().getSelectedItem() == null) {
				throw new Exception();
			} else {
				try {
					Telas.telaDasProvas();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			errorProva.setText("Dis. nao selecionada!");
			errorProva.setVisible(true);
		}

	}

	public void verQuestoes(ActionEvent event) {
		try {
			Telas.telaQuestoes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}