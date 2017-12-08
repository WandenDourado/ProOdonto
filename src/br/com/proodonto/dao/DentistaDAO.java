package br.com.proodonto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Dentista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DentistaDAO {

	private Connection con;
	private PacienteDAO pacienteDAO = new PacienteDAO();
		
		public DentistaDAO() {
			con = DBUtil.getInstance().getConnection();
		}
		
		public void adicionarConsulta(Consulta consulta) {
			try {
				
				String cmd = "INSERT INTO consultas (data_consulta, observacao ,cpf_paciente, cpf_dentista, procedimento, preco) VALUES (?,?,?,?,?,?)";
				    PreparedStatement preparedStmt = con.prepareStatement(cmd);

				    preparedStmt.setTimestamp(1, consulta.getData_consulta());
				    preparedStmt.setString(2, consulta.getObservacao());
				    preparedStmt.setString(3, consulta.getPaciente().getCpf());
				    preparedStmt.setString(4, consulta.getDentista().getCpf());
				    preparedStmt.setString(5, consulta.getProcedimento());
				    preparedStmt.setFloat(6, consulta.getPreco());
				    
				    preparedStmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		
		public Dentista validarAcesso(String cpf, String senha){		
			try {
				Statement stmt = con.createStatement();
				
				String cmd = "SELECT * FROM dentistas WHERE cpf = '"+cpf+"' AND senha = '"+senha+"'";
				ResultSet rs = stmt.executeQuery(cmd);
				while (rs.next()) {
					Dentista dentista = new Dentista();
					dentista.setNome(rs.getString("nome"));
					dentista.setCpf(rs.getString("cpf"));
					dentista.setSobrenome(rs.getString("sobrenome"));
					return dentista;
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public ObservableList<String> listarNomesDentitas(){		
			try {
				Statement stmt = con.createStatement();
				
				String cmd = "SELECT * FROM dentistas";
				ResultSet rs = stmt.executeQuery(cmd);
				ObservableList<String> dentistas =  FXCollections.observableArrayList();
				String nomeID;
				while (rs.next()) {
					Dentista dentista = new Dentista();
					dentista.setNome(rs.getString("nome"));
					dentista.setCpf(rs.getString("cpf"));
					nomeID = dentista.getNome() +"-"+ dentista.getCpf();
					dentistas.add(nomeID);
				}
				return dentistas;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	
}
