package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import exception.CampoEmBrancoExcepetion;
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
import model.BO.ProvaBO;
import model.VO.ProvaVO;
import view.Telas;

public class FrontController5 implements Initializable {

    @FXML
    private TableColumn<ProvaVO, Integer> nivel4;

    @FXML
    private TableColumn<ProvaVO, Integer> nivel3;

    @FXML
    private TableColumn<ProvaVO, Integer> nivel2;
    
    @FXML
    private TableColumn<ProvaVO, Integer> nivel1;

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
    
    private static ProvaVO lastSelected;
    
    private ProvaBO bo = new ProvaBO();
    
    ObservableList<ProvaVO> list = FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	provasDe.setText(provasDe.getText() + FrontController2.getLastSelected().getNome());
		ProvaVO prova = new ProvaVO();
		prova.setId(FrontController2.getLastSelected().getId());
		List<ProvaVO> provas = bo.listarPorDisciplina(prova);
		list.addAll(provas);

		id.setCellValueFactory(new PropertyValueFactory<ProvaVO, Long>("id"));
		nivel1.setCellValueFactory(new PropertyValueFactory<ProvaVO, Integer>("nivel1"));
		nivel2.setCellValueFactory(new PropertyValueFactory<ProvaVO, Integer>("nivel2"));
		nivel3.setCellValueFactory(new PropertyValueFactory<ProvaVO, Integer>("nivel3"));
		nivel4.setCellValueFactory(new PropertyValueFactory<ProvaVO, Integer>("nivel4"));

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
    void gerarProva(ActionEvent event) {
    	ProvaVO prova = new ProvaVO();
    	prova.setIdDisciplina(FrontController2.getLastSelected().getId());
    	
    	try {
    		if (gerarNivel1.getText().length() < 1) {
    			throw new CampoEmBrancoExcepetion();
    		} else if (gerarNivel1.getText().length() < 1) {
    			throw new CampoEmBrancoExcepetion();
    		} else if (gerarNivel1.getText().length() < 1) {
    			throw new CampoEmBrancoExcepetion();
    		} else if (gerarNivel1.getText().length() < 1) {
    			throw new CampoEmBrancoExcepetion();
    		}
    		
    		bo.cadastrar(prova);
    	} catch (CampoEmBrancoExcepetion e) {
    		error.setText("Algum campo vazio!");
    		error.setVisible(true);
    	} catch (Exception e) {
    		error.setText("Nao tem essa quantidade de questoes na disciplina!");
    		error.setVisible(true);
    	}
    }

    @FXML
    void verQuestoes(ActionEvent event) {
    	setLastSelected(tabelaProvas.getSelectionModel().getSelectedItem());
    	// temporario
    	try {
			Telas.telaQuestoesDaProva();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    void retornar(ActionEvent event) {
    	try {
			Telas.telaDisciplinas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static ProvaVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(ProvaVO lastSelected) {
		FrontController5.lastSelected = lastSelected;
	}
}

