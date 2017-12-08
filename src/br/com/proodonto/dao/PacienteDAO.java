package br.com.proodonto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import br.com.proodonto.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PacienteDAO {
	
	Connection con;
	
	public PacienteDAO() {
		con = DBUtil.getInstance().getConnection();
	}
	
	public void adicionarPaciente(Paciente paciente) {
		try {
			
			Calendar calendar = Calendar.getInstance();
		    java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			
			String cmd = "INSERT INTO pacientes (cpf, nome, sobrenome ,telefone_residencial, telefone_comercial, celular, data_nascimento, data_cadastro) VALUES (?,?,?,?,?,?,?,?)";
			    PreparedStatement preparedStmt = con.prepareStatement(cmd);

			    preparedStmt.setString(1, paciente.getCpf());
			    preparedStmt.setString(2, paciente.getNome());
			    preparedStmt.setString(3, paciente.getSobrenome());
			    preparedStmt.setString(4, paciente.getTelefone_residencial());
			    preparedStmt.setString(5, paciente.getTelefone_comercial());
			    preparedStmt.setString(6, paciente.getCelular());
			    preparedStmt.setDate(7, paciente.getData_nascimento());
			    preparedStmt.setDate(8, startDate);
			    
			    preparedStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public boolean atualizarPaciente(Paciente paciente) {
		try {
			
			String cmd = "UPDATE pacientes SET nome = ?, sobrenome = ?, telefone_residencial = ?, telefone_comercial = ?, celular = ?, data_nascimento = ? WHERE cpf = ?";
			    PreparedStatement preparedStmt = con.prepareStatement(cmd);
			    
			    preparedStmt.setString(1, paciente.getNome());
			    preparedStmt.setString(2, paciente.getSobrenome());
			    preparedStmt.setString(3, paciente.getTelefone_residencial());
			    preparedStmt.setString(4, paciente.getTelefone_comercial());
			    preparedStmt.setString(5, paciente.getCelular());
			    preparedStmt.setDate(6, paciente.getData_nascimento());
			    preparedStmt.setString(7, paciente.getCpf());
			    
			    preparedStmt.executeUpdate();
			    return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}				
	}
	
	public ObservableList<Paciente> listarPacientes(){		
		try {
			Statement stmt = con.createStatement();
			
			String cmd = "SELECT * FROM pacientes";
			ResultSet rs = stmt.executeQuery(cmd);
			ObservableList<Paciente> pacientes =  FXCollections.observableArrayList();
			while (rs.next()) {
				Paciente paciente = new Paciente();				
				paciente.setCpf(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setSobrenome(rs.getString("sobrenome"));
				paciente.setTelefone_residencial(rs.getString("telefone_residencial"));
				paciente.setTelefone_comercial(rs.getString("telefone_comercial"));
				paciente.setCelular(rs.getString("celular"));
				paciente.setData_nascimento(rs.getDate("data_nascimento"));
				paciente.setData_cadastro(rs.getDate("data_cadastro"));
				pacientes.add(paciente);
			}
			return pacientes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Paciente buscarPaciente(String cpf) {
		
		try {
			Statement stmt = con.createStatement();			
			String cmd = "SELECT * FROM pacientes WHERE cpf = '"+cpf+"'";
			ResultSet rs = stmt.executeQuery(cmd);
			Paciente paciente = new Paciente();	
			while (rs.next()) {							
				paciente.setCpf(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setSobrenome(rs.getString("sobrenome"));
				paciente.setTelefone_residencial(rs.getString("telefone_residencial"));
				paciente.setTelefone_comercial(rs.getString("telefone_comercial"));
				paciente.setCelular(rs.getString("celular"));
				paciente.setData_nascimento(rs.getDate("data_nascimento"));
				paciente.setData_cadastro(rs.getDate("data_cadastro"));
			}
			return paciente;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
public ObservableList<String> buscarPacienteNome(String nome) {
		
		try {
			Statement stmt = con.createStatement();			
			String cmd = "SELECT * FROM pacientes WHERE nome like '"+nome+"%'";
			ResultSet rs = stmt.executeQuery(cmd);	
			String item;
			ObservableList<String> itens = FXCollections.observableArrayList();
			while (rs.next()) {			
				Paciente paciente = new Paciente();
				paciente.setCpf(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setSobrenome(rs.getString("sobrenome"));
				item = paciente.getNome()+" "+paciente.getSobrenome()+"-"+paciente.getCpf();
				itens.add(item);				
			}
			return itens;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	
	

}
