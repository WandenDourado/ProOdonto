package br.com.proodonto.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.proodonto.dao.ConsultaDAO;
import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Dentista;
import br.com.proodonto.model.Paciente;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

public class ConsultaContoller {
	
	private ConsultaDAO consultaDAO = new ConsultaDAO();
	
	public void cadastrarConsulta(Consulta consulta, String datapicker, String horario) {
		try {
			String dataStr = datapicker+" "+horario;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dataNova = format.parse(dataStr);
			java.sql.Timestamp data_consulta = new java.sql.Timestamp(dataNova.getTime());
			consulta.setData_consulta(data_consulta);	
			alertaMensagem("Cadastro de Consulta Efeturado com Sucesso!");
			consultaDAO.adicionarConsulta(consulta);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alertaMensagem("Ocorreu um erro no cadastramento! :(");
		}		
					}
	
	public String getCpfCombo(ComboBox<String> comb_cpf) {
		String[] cpf = comb_cpf.getValue().toString().split("-");
		return cpf[1];
	}
	
	private void alertaMensagem(String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cadastro Consulta");
		alert.setContentText(mensagem);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

}
