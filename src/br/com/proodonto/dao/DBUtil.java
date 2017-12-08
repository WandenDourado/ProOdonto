package br.com.proodonto.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	private static DBUtil instancia;
	private static Connection con;
	
	private DBUtil() { 
		try {
			String[] config = obterConfig();
			System.out.println(config[0]+" "+config[1]+" "+config[2]+" "+config[3]);
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://"+config[0]+":3306/"+config[1]+"";
			con = DriverManager.getConnection(url, config[2], config[3]);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static DBUtil getInstance() {
		if (instancia == null) { 
			instancia = new DBUtil();
		}
		return instancia;
	}
	
	public static Connection getConnection() { 
		return con;
	}
	
	public static  String [] obterConfig() {
		   try {
			   FileReader arq = new FileReader("DataBaseConfig.txt");
			      BufferedReader lerArq = new BufferedReader(arq);
			 
			      String linha = lerArq.readLine();
			      String[] config = linha.split("-");
			      arq.close();
			      return config;
			    } catch (IOException e) {
			        System.err.printf("Erro na abertura do arquivo: %s.\n",
			          e.getMessage());
			    }	
		   return null;
	   }

}
