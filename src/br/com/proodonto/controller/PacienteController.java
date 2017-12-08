package br.com.proodonto.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.proodonto.dao.PacienteDAO;
import br.com.proodonto.model.Paciente;
import br.com.proodonto.model.Pagamento;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PacienteController {
	
	PacienteDAO pacienteDAO = new PacienteDAO();
	
	public void cadastrarPaciente(Paciente paciente, String datapicker) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateStr = format.parse(datapicker);
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			paciente.setData_nascimento(dateDB);
			pacienteDAO.adicionarPaciente(paciente);
			alertaMensagem("Cadastro de Paciente Efeturado com Sucesso!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alertaMensagem("Ocorreu um erro no cadastramento! :(");
		}			
	}
	
	public void atualizarPaciente(Paciente paciente, String datepicker) {
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateStr;
			dateStr = format.parse(datepicker);
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			paciente.setData_nascimento(dateDB);
			if(pacienteDAO.atualizarPaciente(paciente)) {
				alertaMensagem("Paciente Atualizado com Sucesso!");
			}
			else {
				alertaMensagem("Erro na Atualização do Paciente! :(");
			}		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alertaMensagem("Erro com a data do Paciente! :(");
		}	
		
	}
	
	
	private void alertaMensagem(String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cadastro Paciente");
		alert.setContentText(mensagem);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

}
