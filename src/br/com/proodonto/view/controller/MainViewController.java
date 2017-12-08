package br.com.proodonto.view.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainViewController extends Application implements Initializable{
	
	static private Stage primaryStage;	
	
	@FXML
	private Pane pane_main;
	
	@FXML
	private MenuItem menu_paciente;
	@FXML
	private MenuItem menu_consulta;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			pane_main.getChildren().setAll(getPanePaciente());
			menu_paciente.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
			menu_consulta.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
	}
	
	
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../Main.fxml"));
			Scene scene = new Scene(root, 470, 417);
			primaryStage.setTitle("ProOdonto");			
			scene.getStylesheets().add(getClass().getResource("../Proodonto.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public static void main(String[] args) {
		
		launch(args);

	}
	
	public void abirPacienteForm() {
		
		try {
			Parent pacinteForm = FXMLLoader.load(getClass().getResource("../Paciente_Formulario.fxml"));
			Scene pacienteScene = new Scene(pacinteForm, 378, 415);
			pacienteScene.getStylesheets().add(getClass().getResource("../Proodonto.css").toExternalForm());
			 // New window (Stage)
	        Stage newWindow = new Stage();
	        newWindow.setTitle("Paciente Formulario");
	        newWindow.setScene(pacienteScene);
	        newWindow.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
public void abirConsultaForm() {
		
		try {
			Parent consultaForm = FXMLLoader.load(getClass().getResource("../Consulta_Formulario.fxml"));
			Scene consultaScene = new Scene(consultaForm, 378, 415);
			consultaScene.getStylesheets().add(getClass().getResource("../Proodonto.css").toExternalForm());
			 // New window (Stage)
	        Stage newWindow = new Stage();
	        newWindow.setTitle("Nova Consulta");
	        newWindow.setScene(consultaScene);
	        newWindow.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@FXML
	public Pane getPanePaciente() {
		try {
			return (Pane) FXMLLoader.load(getClass().getResource("../Paciente.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FXML
	public Pane getPanePagamento() {
		try {
			return (Pane) FXMLLoader.load(getClass().getResource("../Pagamento.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@FXML
	public Pane getPaneConsulta() {
		try {
			return (Pane) FXMLLoader.load(getClass().getResource("../Consulta.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void abrirPacienteJanela(ActionEvent event) {		
		abirPacienteForm(); 
	   }
	
	public void abrirConsultaJanela(ActionEvent event) {		
		abirConsultaForm();
	   }
	
	public void abaPaciente(ActionEvent event) {
		pane_main.getChildren().setAll(getPanePaciente());
	   }
	
	public void abaPagamento(ActionEvent event) {
		pane_main.getChildren().setAll(getPanePagamento());
	   }
	
	public void abaConsulta(ActionEvent event) {
		pane_main.getChildren().setAll(getPaneConsulta());
	   }
	
	public Stage getStage() {
		return primaryStage;
	}


}
