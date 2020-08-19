package br.com.poo.application;

import java.io.IOException;

import br.com.poo.controller.TelaEditarController;
import br.com.poo.model.Paciente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaEditar extends Application{

	public TelaEditar(Paciente paciente) {
		TelaEditarController.setPaciente(paciente);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/br/com/poo/view/telaEditar.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("Editar Paciente");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
}
