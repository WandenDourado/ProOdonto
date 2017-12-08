package br.com.proodonto.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.proodonto.dao.ConsultaDAO;
import br.com.proodonto.dao.PagamentoDAO;
import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Pagamento;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PagamentoController {
	
private PagamentoDAO pagamentoDAO = new PagamentoDAO();
	
	public void cadastrarPagamento(Pagamento pagamento, String datapicker) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateStr = format.parse(datapicker);
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			pagamento.setData_pagamento(dateDB);		
			System.out.println(dateDB);
			alertaMensagem("Cadastro de Pagamento Efeturado com sucesso!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alertaMensagem("Ocorreu um erro no cadastramento! :(");
		}		
		pagamentoDAO.adicionarPagamento(pagamento);		
	}
	
	private void alertaMensagem(String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cadastro Pagamento");
		alert.setContentText(mensagem);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

}
