package br.com.proodonto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;


import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Dentista;
import br.com.proodonto.model.Pagamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PagamentoDAO {
	
	private Connection con;
	private ConsultaDAO consultaDAO = new ConsultaDAO();
		
		public PagamentoDAO() {
			con = DBUtil.getInstance().getConnection();
		}
		
		public void adicionarPagamento(Pagamento pagamento) {
			try {
				
				String cmd = "INSERT INTO pagamentos (data_pagamento, observacao ,id_consulta, forma_pagamento, valor) VALUES (?,?,?,?,?)";
				    PreparedStatement preparedStmt = con.prepareStatement(cmd);

				    preparedStmt.setDate(1, pagamento.getData_pagamento());
				    preparedStmt.setString(2, pagamento.getObservacao());
				    preparedStmt.setInt(3, pagamento.getConsulta().getId());
				    preparedStmt.setString(4, pagamento.getForma_pagamento());
				    preparedStmt.setFloat(5, pagamento.getValor());
				    
				    preparedStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		public ObservableList<Pagamento> listarPagamentos(){		
			try {
				Statement stmt = con.createStatement();
				
				String cmd = "SELECT * FROM pagamentos";
				ResultSet rs = stmt.executeQuery(cmd);
				ObservableList<Pagamento> pagamentos =  FXCollections.observableArrayList();
				while (rs.next()) {
					Pagamento pagamento = new Pagamento();	
					pagamento.setData_pagamento(rs.getDate("data_pagamento"));
					pagamento.setForma_pagamento(rs.getString("forma_pagamento"));
					pagamento.setConsulta(consultaDAO.buscarConsulta(rs.getInt("id_consulta")));
					pagamento.setValor(rs.getFloat("valor"));
					pagamentos.add(pagamento);
				}
				return pagamentos;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public ObservableList<Pagamento> listarPagamentosPaciente(String nome){		
			try {
				Statement stmt = con.createStatement();
				
				String cmd = "SELECT * FROM pagamentos \r\n" + 
						"INNER JOIN consultas ON pagamentos.id_consulta = consultas.id\r\n" + 
						"INNER JOIN pacientes on pacientes.cpf = consultas.cpf_paciente\r\n" + 
						" WHERE pacientes.nome like '"+nome+"%'";
				ResultSet rs = stmt.executeQuery(cmd);
				ObservableList<Pagamento> pagamentos =  FXCollections.observableArrayList();
				while (rs.next()) {
					Pagamento pagamento = new Pagamento();	
					pagamento.setData_pagamento(rs.getDate("data_pagamento"));
					pagamento.setForma_pagamento(rs.getString("forma_pagamento"));
					pagamento.setConsulta(consultaDAO.buscarConsulta(rs.getInt("id_consulta")));
					pagamento.setValor(rs.getFloat("valor"));
					pagamentos.add(pagamento);
				}
				return pagamentos;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public ObservableList<String> listaFormasPagamento(){
			ObservableList<String> formas =  FXCollections.observableArrayList();
			formas.add("cheque");
			formas.add("dinheiro");
			formas.add("debito");
			formas.add("credito");
			return formas;
		}

}
