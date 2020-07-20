package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.text.TextAlignment;
import model.BO.AssuntoBO;
import model.BO.DisciplinaBO;
import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import view.Telas;

public class FrontController4 implements Initializable {

	@FXML
    private TableView<AssuntoVO> tabelaAssuntos;
	
	@FXML
	private TableColumn<AssuntoVO, Long> id;

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
		
		id.setCellValueFactory(new PropertyValueFactory<AssuntoVO, Long>("id"));
		conteudo.setCellValueFactory(new PropertyValueFactory<AssuntoVO, String>("conteudo"));
		
		tabelaAssuntos.setItems(list);
	}
	
    public void removerAssunto(ActionEvent event) {
    	if (tabelaAssuntos.getSelectionModel().getSelectedItem().getId() == 0) {
    		tabelaAssuntos.getItems().removeAll(tabelaAssuntos.getSelectionModel().getSelectedItem());
    	} else {
    		try {
				bo.remover(tabelaAssuntos.getSelectionModel().getSelectedItem());
				tabelaAssuntos.getItems().removeAll(tabelaAssuntos.getSelectionModel().getSelectedItem());
			} catch (InsertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public void novoAssunto(ActionEvent event) {
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
				assunto.setIdDisciplina(FrontController2.getLastSelected().getId());
				List<AssuntoVO> assuntos = bo.listarPorDisciplina(assunto);
				assunto.setId(new Long((assuntos.get(assuntos.size() -1).getId())+1));
				conteudo.setCellValueFactory(new PropertyValueFactory<AssuntoVO, String>("id"));
				conteudo.setCellValueFactory(new PropertyValueFactory<AssuntoVO, String>("conteudo"));
				list.add(assunto);
				try {
					bo.cadastrar(assunto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			error.setText("Já tem assunto com esse conteudo ou assunto em branco!");
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		}
		tabelaAssuntos.setItems(list);
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
