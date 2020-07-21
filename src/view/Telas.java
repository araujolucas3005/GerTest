package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application{
	private static Stage primaryStage;

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Telas.primaryStage = primaryStage;
	}

	public void start (Stage pS) throws Exception{
		setPrimaryStage(pS);
		pS.setTitle("GerTest");
		telaLogin();
	}
	
	public static void telaLogin() throws Exception{
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
	
	public static void telaQuestoesDaProva() throws Exception {
		Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaQuestoesDaProva.fxml"));
		
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}