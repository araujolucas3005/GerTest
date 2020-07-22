package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.VO.QuestaoVO;
import view.Telas;

public class EnunciadoQuestaoController implements Initializable{
	@FXML private TextArea enunciado;
	
	private static QuestaoVO lastSelected;

	@Override
	public void initialize(URL local, ResourceBundle resources) {
		loadData();
	}

	public void loadData() {
		setLastSelected(QuestoesController.getLastSelected());
		enunciado.setText(lastSelected.getEnunciado());
		enunciado.setEditable(false);
	}	
	
	public static QuestaoVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(QuestaoVO lastSelected) {
		EnunciadoQuestaoController.lastSelected = lastSelected;
	}

	public void retorno() throws Exception {
		Telas.telaQuestoes();
	}

}
