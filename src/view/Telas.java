package view;

import java.io.IOException;

import exception.InsertException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.BO.QuestaoBO;
import model.BO.QuestaoInterBO;
import model.VO.QuestaoVO;

public class Telas extends Application {
	private static Stage primaryStage;

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Telas.primaryStage = primaryStage;
	}

	public void start(Stage pS) throws Exception {
		setPrimaryStage(pS);
		pS.setTitle("GerTest");
		telaLogin();
	}

	public static void telaLogin() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaDeLogin.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaCadastro() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/TelaDeCadastro.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaDisciplinas() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaDasDisciplinas.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaNovaDisciplina() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaNovaDisciplina.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaAssuntos() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaAssuntos.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaQuestoes() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaDasQuestoes.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaCadastroQuestao() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaCadastroQuestao.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaEdicaoQuestao() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaEdicaoQuestoes.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaEnunciado() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaEnunciado.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaDasProvas() throws IOException {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaDasProvas.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaQuestoesDaProva() throws IOException {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaQuestoesDaProva.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void telaDeAdicaoQuestoesNaProva() throws IOException {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaDeAdicaoQuestoesNaProva.fxml"));

		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
		QuestaoInterBO<QuestaoVO> bo = new QuestaoBO<>();
		try {
			for (QuestaoVO questao : bo.listar()) {
				System.out.println(questao.getIdQuestao());
			}
		} catch (InsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}