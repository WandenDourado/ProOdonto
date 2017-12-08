package br.com.proodonto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConsultaDAO {
	
private Connection con;
private PacienteDAO pacienteDAO = new PacienteDAO();
	
	public ConsultaDAO() {
		con = DBUtil.getInstance().getConnection();
		System.out.println("Conectado");
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
	
	public ObservableList<Consulta> listarConsultas(String cpf){		
		try {
			Statement stmt = con.createStatement();
			
			String cmd = "SELECT * FROM consultas WHERE cpf_dentista = '"+cpf+"'";
			ResultSet rs = stmt.executeQuery(cmd);
			ObservableList<Consulta> consultas =  FXCollections.observableArrayList();
			while (rs.next()) {
				Consulta consulta = new Consulta();
				consulta.setId(rs.getInt("id"));
				consulta.setData_consulta(rs.getTimestamp("data_consulta"));
				consulta.setPaciente(pacienteDAO.buscarPaciente(rs.getString("cpf_paciente")));
				consulta.setProcedimento(rs.getString("procedimento"));
				consultas.add(consulta);
			}
			return consultas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ObservableList<Consulta> listarConsultasPaciente(String cpf, String nome){		
		try {
			Statement stmt = con.createStatement();
			
			String cmd = "SELECT * FROM consultas INNER JOIN pacientes ON consultas.cpf_paciente = pacientes.cpf WHERE consultas.cpf_dentista = '"+cpf+"' AND pacientes.nome like '"+nome+"%'";
			ResultSet rs = stmt.executeQuery(cmd);
			ObservableList<Consulta> consultas =  FXCollections.observableArrayList();
			while (rs.next()) {
				Consulta consulta = new Consulta();
				consulta.setId(rs.getInt("id"));
				consulta.setData_consulta(rs.getTimestamp("data_consulta"));
				consulta.setPaciente(pacienteDAO.buscarPaciente(rs.getString("cpf_paciente")));
				consulta.setProcedimento(rs.getString("procedimento"));
				consultas.add(consulta);
			}
			return consultas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
public Consulta buscarConsulta(int id) {
		
		try {
			Statement stmt = con.createStatement();			
			String cmd = "SELECT * FROM consultas WHERE id = '"+id+"'";
			ResultSet rs = stmt.executeQuery(cmd);
			Consulta consulta = new Consulta();	
			while (rs.next()) {							
				consulta.setData_consulta(rs.getTimestamp("data_consulta"));
				consulta.setPaciente(pacienteDAO.buscarPaciente(rs.getString("cpf_paciente")));
				consulta.setProcedimento(rs.getString("procedimento"));
			}
			return consulta;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	

}
