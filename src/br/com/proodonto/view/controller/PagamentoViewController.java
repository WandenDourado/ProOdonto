package br.com.proodonto.view.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import br.com.proodonto.dao.PagamentoDAO;
import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Paciente;
import br.com.proodonto.model.Pagamento;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PagamentoViewController implements Initializable {
	
	@FXML
	private TableView<Pagamento> table_pagamento;
	@FXML
	private TableColumn<Pagamento, String> column_paciente = new TableColumn<Pagamento, String>();
	@FXML
	private TableColumn column_forma;
	@FXML
	private TableColumn column_data;
	@FXML
	private TableColumn column_valor;
	@FXML
	private TextField txt_paciente;
	
	PagamentoDAO pagamentoDAO = new PagamentoDAO();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<Pagamento> list = pagamentoDAO.listarPagamentos();
		atualizarTabela(list);
		
	}
	
	public void botaoBuscar() {
			ObservableList<Pagamento> list = pagamentoDAO.listarPagamentosPaciente(txt_paciente.getText());		
			atualizarTabela(list);
	}
	
public void atualizarTabela( ObservableList<Pagamento> list) {
		
	column_paciente.setCellValueFactory(new Callback<CellDataFeatures<Pagamento, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Pagamento, String> c) {
        	String retorno = c.getValue().getConsulta().getPaciente().getNome() + " " + c.getValue().getConsulta().getPaciente().getSobrenome();
            return new SimpleStringProperty(retorno);                
        }
	});
	column_forma.setCellValueFactory(new PropertyValueFactory<>("forma_pagamento"));
	column_data.setCellValueFactory(new PropertyValueFactory<>("data_pagamento"));
	column_valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
	table_pagamento.setItems(list);	
		
	}

}
