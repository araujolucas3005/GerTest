package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import exception.CampoEmBrancoException;
import exception.TipoErradoExcepetion;
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
import model.BO.BaseInterBO;
import model.BO.ProvaBO;
import model.VO.ProvaVO;
import view.Telas;

public class ProvasController implements Initializable {

    @FXML
    private TableColumn<ProvaVO, Long> id;

    @FXML
    private Label provasDe;

    @FXML
    private TableView<ProvaVO> tabelaProvas;
    
    @FXML 
    private Label error;
    
    @FXML
    private TextField gerarNivel1;

    @FXML
    private TextField gerarNivel2;

    @FXML
    private TextField gerarNivel3;

    @FXML
    private TextField gerarNivel4;
    
    @FXML
    private Label verQuestoesErro;
    
    private static ProvaVO lastSelectedProva;
    
    private BaseInterBO<ProvaVO> bo = new ProvaBO();
    
    ObservableList<ProvaVO> list = FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	provasDe.setText(provasDe.getText() + DisciplinasController.lastSelectedDisciplina().getNome());
		ProvaVO prova = new ProvaVO();
		prova.setIdDisciplina(DisciplinasController.lastSelectedDisciplina().getId());
		List<ProvaVO> provas = bo.listarPorDisciplina(prova);
		list.addAll(provas);

		id.setCellValueFactory(new PropertyValueFactory<ProvaVO, Long>("id"));

		tabelaProvas.setItems(list);
	}

    @FXML
    void removerProva(ActionEvent event) {
    	ProvaVO prova = tabelaProvas.getSelectionModel().getSelectedItem();
    	try {
    		if (prova == null) {
    			throw new Exception();
    		} else {
    			bo.remover(prova);
    	    	tabelaProvas.getItems().removeAll(prova);
    		}
    	} catch (Exception e) {
    		error.setText("Prova nao selectionada!");
    	}
    }

    @FXML
    void gerarProva(ActionEvent event) throws Exception {
    	ProvaVO prova = new ProvaVO();
    	try {
    		if (gerarNivel1.getText().length() == 0 ) {
    			throw new CampoEmBrancoException();
    		} else if (gerarNivel2.getText().length() == 0) {
    			throw new CampoEmBrancoException();
    		} else if (gerarNivel3.getText().length() == 0) {
    			throw new CampoEmBrancoException();
    		} else if (gerarNivel4.getText().length() == 0) {
    			throw new CampoEmBrancoException();
    		}
    		for (int i = 0; i < gerarNivel1.getText().length(); i++) {
    			if (!Character.isDigit(gerarNivel1.getText().charAt(i))) {
    				throw new TipoErradoExcepetion();
    			}
    		}
    		for (int i = 0; i < gerarNivel2.getText().length(); i++) {
    			if (!Character.isDigit(gerarNivel1.getText().charAt(i))) {
    				throw new TipoErradoExcepetion();
    			}
    		}
    		for (int i = 0; i < gerarNivel2.getText().length(); i++) {
    			if (!Character.isDigit(gerarNivel1.getText().charAt(i))) {
    				throw new TipoErradoExcepetion();
    			}
    		}
    		for (int i = 0; i < gerarNivel2.getText().length(); i++) {
    			if (!Character.isDigit(gerarNivel1.getText().charAt(i))) {
    				throw new TipoErradoExcepetion();
    			}
    		}
    		prova.setIdDisciplina(DisciplinasController.lastSelectedDisciplina().getId());
    		prova.setNivel1(Integer.parseInt(gerarNivel1.getText()));
    		prova.setNivel2(Integer.parseInt(gerarNivel2.getText()));
    		prova.setNivel3(Integer.parseInt(gerarNivel3.getText()));
    		prova.setNivel4(Integer.parseInt(gerarNivel4.getText()));
    		bo.cadastrar(prova);
    		list.add(prova);
    	} catch (TipoErradoExcepetion e) {
    		error.setText("Apenas inteiros!");
    		error.setVisible(true);
    	} catch (CampoEmBrancoException e) {
    		error.setText("Algum campo vazio!");
    		error.setVisible(true);
    	} catch (Exception e) {
    		error.setText("Nao tem essa quantidade de questoes na disciplina!");
    		error.setVisible(true);
    		bo.remover(prova);
    		list.remove(prova);
    	}
    }

   /* @FXML
    void verQuestoes(ActionEvent event) {
    	setLastSelected(tabelaProvas.getSelectionModel().getSelectedItem());
    	// temporario
    	try {
			Telas.telaQuestoesDaProva();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
    
    public void retornar(ActionEvent event) {
    	try {
			Telas.telaDisciplinas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void verQuestoes(ActionEvent event) {
    	try {
    		if (tabelaProvas.getSelectionModel().getSelectedItem() == null) {
    			throw new Exception();
    		}
        	lastSelectedProva = tabelaProvas.getSelectionModel().getSelectedItem();
			Telas.telaQuestoesDaProva();
		} catch (Exception e) {
			verQuestoesErro.setText("Prova nao selecionada!");
			verQuestoesErro.setVisible(true);
		}
    }

	public static ProvaVO getLastSelectedProva() {
		return lastSelectedProva;
	}

	public static void setLastSelectedProva(ProvaVO lastSelected) {
		ProvasController.lastSelectedProva = lastSelected;
	}
}