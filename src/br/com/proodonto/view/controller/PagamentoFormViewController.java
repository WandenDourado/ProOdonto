package br.com.proodonto.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.proodonto.controller.PagamentoController;
import br.com.proodonto.dao.PagamentoDAO;
import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Dentista;
import br.com.proodonto.model.Paciente;
import br.com.proodonto.model.Pagamento;
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

public class PagamentoFormViewController implements Initializable {
	
	@FXML
	private Button btn_salvar;
	@FXML
	private Button btn_buscar;
	@FXML
	private Button btn_fechar;
	
	@FXML
	private TextField txt_consulta;
	@FXML
	private ComboBox comb_forma;
	@FXML
	private TextField txt_preco;
	@FXML
	private TextArea txt_observacao; 
	@FXML
	private DatePicker datePicker;
	
	PagamentoController pagamentoControl = new PagamentoController();
	PagamentoDAO pagamentoDAO = new PagamentoDAO();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
		ConsultarViewController consulta = new ConsultarViewController();
		txt_consulta.setText(consulta.id_consulta);
		ObservableList<String> list = pagamentoDAO.listaFormasPagamento();
		comb_forma.setItems(list);
		
	}
	
	public void botaoSalvar(ActionEvent event) {
		Pagamento pagamento = new Pagamento();
		pagamento.setConsulta(new Consulta());
		pagamento.getConsulta().setId(Integer.parseInt(txt_consulta.getText()));	
		pagamento.setValor(Float.parseFloat(txt_preco.getText()));
		pagamento.setForma_pagamento(comb_forma.getValue().toString());
		pagamento.setObservacao(txt_observacao.getText());
		pagamentoControl.cadastrarPagamento(pagamento, datePicker.getValue().toString());		
	}
	
	@FXML
	public void fecharJanela() {
		Stage stage = (Stage) btn_fechar.getScene().getWindow();
		stage.close();
	}

}
