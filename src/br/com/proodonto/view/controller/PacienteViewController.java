package br.com.proodonto.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.proodonto.dao.PacienteDAO;
import br.com.proodonto.model.Paciente;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class PacienteViewController extends Application implements Initializable {
	
	@FXML
	private Button myButton;
	
	@FXML
	private TableView<Paciente> table_paciente;
	@FXML
	TableColumn column_nome;
	@FXML
	TableColumn column_sobrenome;
	
	@FXML
	private Label label_nome; 
	@FXML
	private Label label_telefone; 
	@FXML
	private Label label_data; 
	@FXML
	private Label label_cpf; 
	
	private PacienteDAO pacienteDAO = new PacienteDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<Paciente> list = pacienteDAO.listarPacientes();
//	      for(Paciente elemento: list){
//			   System.out.println(elemento.getNome());
//			}
	     column_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	     column_sobrenome.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));
	     table_paciente.setItems(list);
	     
	     table_paciente.getSelectionModel().selectedItemProperty().addListener(
	             (observable, oldValue, newValue) -> mostrarDetalhesPaciente(newValue));
		
	}
	
	private void mostrarDetalhesPaciente(Paciente paciente) {
		if(paciente != null) {
			label_nome.setText(paciente.getNome()+" "+paciente.getSobrenome());
			label_telefone.setText(paciente.getCelular());
			label_data.setText(paciente.getData_nascimento().toString());
			label_cpf.setText(paciente.getCpf());
		}
		
	}
	
public static void main(String[] args) {
		
		launch(args);

	}
	

	@Override
	public void start(Stage arg0) throws Exception {
		// Add MenuItem to ContextMenu  
		
	}

}
