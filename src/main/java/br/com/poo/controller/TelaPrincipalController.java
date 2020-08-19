package br.com.poo.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import br.com.poo.application.TelaEditar;
import br.com.poo.dao.PacienteDAO;
import br.com.poo.model.Paciente;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TelaPrincipalController implements Initializable {

	@FXML private JFXTextField tfNome;
	@FXML private JFXTextField tfIdade;
	@FXML private JFXButton btnNovo;
    @FXML private TableView<Paciente> tblTabela;
	@FXML private TableColumn<Paciente, Integer> tcId;
	@FXML private TableColumn<Paciente, String> tcNome;
	@FXML private TableColumn<Paciente, Integer> tcIdade;
	@FXML private JFXButton btnListar;
	@FXML private JFXButton btnEditar;
	@FXML private JFXButton btnApagar;
	
	private Paciente pacienteSelecionado;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Capturar o objeto selecionado na tabela.
		tblTabela.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				pacienteSelecionado = (Paciente) newValue;
				System.out.println(pacienteSelecionado.toString());
			}	
		});
		
	}

	@FXML
	void apagarPaciente(ActionEvent event) {
		
		PacienteDAO.getInstance().remove(pacienteSelecionado);
		
		mostrarPacientes();
		
	}

	@FXML
	void cadastrarNovoPaciente(ActionEvent event) {
		
		String nome = tfNome.getText();
		Integer idade = Integer.parseInt(tfIdade.getText());
		
		Paciente paciente = new Paciente(null, nome, idade);
		
		PacienteDAO.getInstance().persist(paciente);
		
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setContentText("Paciente cadastrado!");
		alerta.showAndWait();
		
		tfNome.clear();
		tfIdade.clear();
		
	}

	@FXML
	void editarPacientes(ActionEvent event) {
		
		TelaEditar telaEditar = new TelaEditar(pacienteSelecionado);
		try {
			telaEditar.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@FXML
	void listarPacientes(ActionEvent event) {
		mostrarPacientes();
	}
	
	private void mostrarPacientes() {
		List<Paciente> pacientesList = PacienteDAO.getInstance().findAll();
		
		ObservableList<Paciente> pacientesOBL = FXCollections.observableArrayList(pacientesList);
		
		tcId.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("nome"));
		tcIdade.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("idade"));
		
		tblTabela.setItems(pacientesOBL);
	}

}
