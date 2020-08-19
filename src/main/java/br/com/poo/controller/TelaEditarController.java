package br.com.poo.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import br.com.poo.model.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaEditarController implements Initializable{

	@FXML private JFXTextField tfNomeEditar;
	@FXML private JFXTextField tfIdadeEditar;
	@FXML private JFXButton btnOk;
	@FXML private JFXButton btnCancelar;
	
	private static Paciente paciente;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initPaciente();
	}
	
	public static void setPaciente(Paciente paciente) {
		TelaEditarController.paciente = paciente;
	}
	
	private void initPaciente() {
		tfNomeEditar.setText(paciente.getNome().toString());
		tfIdadeEditar.setText(paciente.getIdade().toString());
	}
	
	@FXML
	void cancelarEdicao(ActionEvent event) {
		System.out.println("Botao cancelar");
	}

	@FXML
	void confirmarEdicao(ActionEvent event) {

	}

}
