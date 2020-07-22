package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import exception.CampoEmBrancoException;
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

public class FrontController9 implements Initializable {

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
    
    private Label verQuestoesErro;
    
    private static ProvaVO lastSelectedProva;
    
    private ProvaBO bo = new ProvaBO();
    
    ObservableList<ProvaVO> list = FXCollections.observableArrayList();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	provasDe.setText(provasDe.getText() + FrontController2.lastSelectedDisciplina().getNome());
		ProvaVO prova = new ProvaVO();
		prova.setIdDisciplina(FrontController2.lastSelectedDisciplina().getId());
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
    void gerarProva(ActionEvent event) {
    	ProvaVO prova = new ProvaVO();
    	try {
    		if (gerarNivel1.getText().length() == 0) {
    			throw new CampoEmBrancoException();
    		} else if (gerarNivel2.getText().length() == 0) {
    			throw new CampoEmBrancoException();
    		} else if (gerarNivel3.getText().length() == 0) {
    			throw new CampoEmBrancoException();
    		} else if (gerarNivel4.getText().length() == 0) {
    			throw new CampoEmBrancoException();
    		}
    		prova.setIdDisciplina(FrontController2.lastSelectedDisciplina().getId());
    		prova.setNivel1(Integer.valueOf(gerarNivel1.getText()));
    		prova.setNivel2(Integer.valueOf(gerarNivel2.getText()));
    		prova.setNivel3(Integer.valueOf(gerarNivel3.getText()));
    		prova.setNivel4(Integer.valueOf(gerarNivel4.getText()));
    		bo.cadastrar(prova);
    		list.add(prova);
    	} catch (CampoEmBrancoException e) {
    		error.setText("Algum campo vazio!");
    		error.setVisible(true);
    	} catch (Exception e) {
    		error.setText("Nao tem essa quantidade de questoes na disciplina!");
    		error.setVisible(true);
    		bo.remover(prova);
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
    	lastSelectedProva = tabelaProvas.getSelectionModel().getSelectedItem();
    	try {
    		if (tabelaProvas.getSelectionModel().getSelectedItem() == null) {
    			throw new Exception();
    		}
			Telas.telaQuestoesDaProva();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			verQuestoesErro.setText("Questao nao selecionada!");
			verQuestoesErro.setVisible(true);
		}
    }

	public static ProvaVO lastSelectedProva() {
		return lastSelectedProva;
	}

	public static void lastSelectedProva(ProvaVO lastSelected) {
		FrontController9.lastSelectedProva = lastSelected;
	}
}