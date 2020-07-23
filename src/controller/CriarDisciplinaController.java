package controller;

import exception.DisciplinaJaExisteException;
import exception.NomeEmBrancoExcepetion;
import exception.NomeMuitoLongException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import model.BO.BaseInterBO;
import model.BO.DisciplinaBO;
import model.VO.DisciplinaVO;
import view.Telas;

public class CriarDisciplinaController {

	@FXML
	private TextField nomeDisciplina;

	@FXML
	private TextField codigoDisciplina;

	@FXML
	private Label error;
	
	BaseInterBO<DisciplinaVO> bo = new DisciplinaBO();

	public void cadastrarDisciplina(ActionEvent event) {


		try {
			DisciplinaVO disciplina = new DisciplinaVO();
			if (nomeDisciplina.getText().length() < 1) {
				throw new NomeEmBrancoExcepetion();
			}

			disciplina.setCodigo(codigoDisciplina.getText());
			disciplina.setNome(nomeDisciplina.getText());
			try {
				bo.cadastrar(disciplina);
			} catch (NomeMuitoLongException e) {
				throw new NomeMuitoLongException();
			} catch (Exception e) {
				throw new DisciplinaJaExisteException();
			} 

			try {
				Telas.telaDisciplinas();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NomeMuitoLongException e) {
			error.setText("Nome ou codigo muito longo!");
			error.setVisible(true);
		} catch (NomeEmBrancoExcepetion e) {
			// TODO Auto-generated catch block
			error.setText("Nome em branco!");
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		} catch (DisciplinaJaExisteException e) {
			error.setText("Já existe uma disciplina com esse nome ou codigo!");
			error.setVisible(true);
			error.setVisible(true);
			error.setTextAlignment(TextAlignment.CENTER);
		} 
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
