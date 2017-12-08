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

public class CreateDAO {
	
	private Connection con;
		
		public CreateDAO() {
			con = DBUtil.getInstance().getConnection();
			System.out.println("Conectado");
		}
		
		public void createBanco() {		
			
			try {
				
				Statement stmt = con.createStatement();
				
			 String	sql =
					 "Create Table pacientes (\r\n" + 
					 "cpf varchar(11) NOT NULL,\r\n" + 
					 "nome varchar(250) NOT NULL,\r\n" + 
					 "sobrenome varchar(250) NOT NULL,\r\n" + 
					 "telefone_residencial varchar(250),\r\n" + 
					 "telefone_comercial varchar(250),\r\n" + 
					 "celular varchar(250),\r\n" + 
					 "data_nascimento date NOT NULL,\r\n" + 
					 "data_cadastro date,\r\n" + 
					 "numero_ficha int NOT NULL\r\n" + 
					 ");\r\n";
						
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "ALTER TABLE pacientes ADD CONSTRAINT pk_paciente PRIMARY KEY (cpf)";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "ALTER TABLE pacientes ADD CONSTRAINT k_ficha UNIQUE KEY (numero_ficha);";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "ALTER TABLE pacientes CHANGE numero_ficha numero_ficha INT AUTO_INCREMENT";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "Create Table dentistas(\r\n" + 
					 "cpf varchar(11),\r\n" + 
					 "nome varchar(250),\r\n" + 
					 "sobrenome varchar(250),\r\n" + 
					 "senha varchar(250)\r\n" + 
					 ");\r\n";
			 stmt.executeUpdate(sql);
			 
			 sql = 
					 "ALTER TABLE dentistas ADD CONSTRAINT pk_consultas PRIMARY KEY(cpf);";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "Create Table consultas (\r\n" + 
					 "id int NOT NULL,\r\n" + 
					 "data_consulta datetime NOT NULL,\r\n" + 
					 "observacao varchar(250),\r\n" + 
					 "cpf_paciente varchar(11),\r\n" + 
					 "cpf_dentista varchar(11),\r\n" + 
					 "procedimento varchar(250),\r\n" + 
					 "preco double NOT NULL\r\n" + 
					 ");\r\n";
			 stmt.executeUpdate(sql);
			 
			 sql = 
					 "ALTER TABLE consultas ADD CONSTRAINT pk_consultas PRIMARY KEY (id);";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "ALTER TABLE consultas CHANGE id id INT AUTO_INCREMENT;";
			 stmt.executeUpdate(sql);
			 
			 sql=
					 "ALTER TABLE consultas ADD CONSTRAINT fk_consultas_pacientes FOREIGN KEY (cpf_paciente ) REFERENCES pacientes(cpf);";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "ALTER TABLE consultas ADD CONSTRAINT fk_consultas_dentistas FOREIGN KEY (cpf_dentista) REFERENCES dentistas(cpf);";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "Create Table pagamentos(\r\n" + 
					 "Id int NOT NULL,\r\n" + 
					 "data_pagamento date NOT NULL,\r\n" + 
					 "observacao varchar(250),\r\n" + 
					 "id_consulta int NOT NULL,\r\n" + 
					 "forma_pagamento varchar(250) NOT NULL,\r\n" + 
					 "valor double\r\n" + 
					 ");\r\n";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "ALTER TABLE pagamentos ADD CONSTRAINT pk_pagamento PRIMARY KEY(id);";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "ALTER TABLE pagamentos CHANGE id id INT AUTO_INCREMENT;";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "ALTER TABLE pagamentos ADD CONSTRAINT fk_pagamento_consulta FOREIGN KEY (id_consulta) REFERENCES consultas (id);";
			 stmt.executeUpdate(sql);
			 
			 sql =
					 "INSERT INTO dentistas(cpf, nome, sobrenome, senha) VALUES ('admin', 'admin', 'admin', 'admin');";
			 stmt.executeUpdate(sql);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

}
