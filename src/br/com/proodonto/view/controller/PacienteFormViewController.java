package br.com.proodonto.view.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.com.proodonto.controller.PacienteController;
import br.com.proodonto.dao.PacienteDAO;
import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Paciente;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PacienteFormViewController extends Application implements Initializable {
	
	@FXML
	private Button btn_cadastrar;
	@FXML
	private Button btn_cancelarBusca;
	@FXML
	private Button btn_busca;
	@FXML
	private Button btn_fechar;
	
	@FXML
	private TextField txt_cpf;
	@FXML
	private TextField txt_nome;
	@FXML
	private TextField txt_sobrenome;
	@FXML
	private TextField txt_tel_resi;
	@FXML
	private TextField txt_tel_comer;
	@FXML
	private TextField txt_celular; 
	@FXML
	private DatePicker datePicker;
	@FXML
	private ComboBox<String> comb_paciente;
	
	PacienteDAO pacienteDAO = new PacienteDAO();
	PacienteController pacienteController = new PacienteController();
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void cadastrarPaciente(ActionEvent event) {
		Paciente paciente = gerarPaciente();		
		pacienteController.cadastrarPaciente(paciente, datePicker.getValue().toString());
		
	}
	
	public void atualizarPaciente(ActionEvent event) {
		Paciente paciente = gerarPaciente();
		pacienteController.atualizarPaciente(paciente, datePicker.getValue().toString());
		
	}
	
	public void buscarNome(ActionEvent event) {
		comb_paciente.setItems(null);
		ObservableList<String> list = pacienteDAO.buscarPacienteNome(txt_nome.getText());
		if(list.size() > 0) {			
			txt_nome.setVisible(false);
			comb_paciente.setVisible(true);
			comb_paciente.setItems(list);
			btn_busca.setVisible(false);
			btn_cancelarBusca.setVisible(true);
		}
		else {
			alertaMensagem("Paciente não cadastrado");
		}
		
	}
	
	public void cancelarBusca(ActionEvent event) {
		txt_nome.setVisible(true);
		comb_paciente.setVisible(false);	
		btn_busca.setVisible(true);
		btn_cancelarBusca.setVisible(false);
	}
	
	public void selecionarCombo(ActionEvent event) {
		if(comb_paciente.getValue().toString().split("-").length > 0) {
			String[] cpf = comb_paciente.getValue().toString().split("-");
			Paciente paciente = pacienteDAO.buscarPaciente(cpf[1]);
			txt_cpf.setText(paciente.getCpf());
			txt_nome.setText(paciente.getNome());
			txt_sobrenome.setText(paciente.getSobrenome());
			txt_tel_resi.setText(paciente.getTelefone_residencial());
			txt_tel_comer.setText(paciente.getTelefone_comercial());
			txt_celular.setText(paciente.getCelular());
			datePicker.setValue(paciente.getData_nascimento().toLocalDate());
		}
		
	}
	
	public Paciente gerarPaciente() {
		Paciente paciente = new Paciente();
		paciente.setCpf(txt_cpf.getText());
		paciente.setNome(txt_nome.getText());
		paciente.setSobrenome(txt_sobrenome.getText());
		paciente.setTelefone_residencial(txt_tel_resi.getText());
		paciente.setTelefone_comercial(txt_tel_comer.getText());
		paciente.setCelular(txt_celular.getText());
		return paciente;
		
	}
	
	
	private void alertaMensagem(String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Formulario Paciente");
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
	
	@FXML
	public void fecharJanela() {
		Stage stage = (Stage) btn_fechar.getScene().getWindow();
		stage.close();
	}


}
