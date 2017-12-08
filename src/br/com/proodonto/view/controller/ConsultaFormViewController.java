package br.com.proodonto.view.controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.proodonto.controller.ConsultaContoller;
import br.com.proodonto.dao.ConsultaDAO;
import br.com.proodonto.dao.DentistaDAO;
import br.com.proodonto.dao.PacienteDAO;
import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Dentista;
import br.com.proodonto.model.Paciente;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConsultaFormViewController extends Application implements Initializable  {
	
	@FXML
	private Button btn_salvar;
	@FXML
	private Button btn_buscar;
	@FXML
	private Button btn_fechar;
	
	@FXML
	private TextField txt_nome;
	@FXML
	private ComboBox comb_dentista;
	@FXML
	private ComboBox<String> comb_paciente;
	@FXML
	private TextField txt_procedimento;
	@FXML
	private TextField txt_horario;
	@FXML
	private TextField txt_preco;
	@FXML
	private TextArea txt_observacao; 
	@FXML
	private DatePicker datePicker;
	
	ConsultaDAO consultaDAO = new ConsultaDAO();
	PacienteDAO pacienteDAO = new PacienteDAO();
	DentistaDAO dentistaDAO = new DentistaDAO();
	ConsultaContoller consultaControl = new ConsultaContoller();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> list = dentistaDAO.listarNomesDentitas();
		comb_dentista.setItems(list);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void botaoSalvar(ActionEvent event) {
		Consulta consulta = new Consulta();
		consulta.setPaciente(new Paciente());
//		consulta.getPaciente().setCpf(txt_cpf.getText());
		consulta.setDentista(new Dentista());		
		consulta.setPreco(Float.parseFloat(txt_preco.getText()));
		consulta.setProcedimento(txt_procedimento.getText());
		consulta.setObservacao(txt_observacao.getText());
		System.out.println(datePicker.getValue());
		String[] nomeID = comb_dentista.getValue().toString().split("-");
		consulta.getDentista().setCpf(nomeID[1]);
		String[] cpf = comb_paciente.getValue().toString().split("-");
		consulta.getPaciente().setCpf(cpf[1]);
		consultaControl.cadastrarConsulta(consulta, datePicker.getValue().toString(), txt_horario.getText());		
	}
	
	public void buscarNome(ActionEvent event) {
		ObservableList<String> list = pacienteDAO.buscarPacienteNome(txt_nome.getText());
		comb_paciente.setItems(list);
	}
	
	@FXML
	public void fecharJanela() {
		Stage stage = (Stage) btn_fechar.getScene().getWindow();
		stage.close();
	}


}
