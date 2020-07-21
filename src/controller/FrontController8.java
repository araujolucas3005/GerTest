package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.VO.QuestaoVO;
import view.Telas;

public class FrontController8 implements Initializable{
	@FXML private TextArea enunciado;
	
	private static QuestaoVO lastSelected;

	@Override
	public void initialize(URL local, ResourceBundle resources) {
		loadData();
	}

	public void loadData() {
		setLastSelected(FrontController5.getLastSelected());
		enunciado.setText(lastSelected.getEnunciado());
		enunciado.setEditable(false);
	}	
	
	public static QuestaoVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(QuestaoVO lastSelected) {
		FrontController8.lastSelected = lastSelected;
	}

	public void retorno() throws Exception {
		Telas.telaQuestoes();
	}

}
