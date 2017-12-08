package br.com.proodonto.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {
	 
	 
	   // Connect to MySQL
	   public static Connection getMySQLConnection() throws SQLException,
	           ClassNotFoundException {
		   
		   String config[] = obterConfig();
		   
		   System.out.println(config[0]+" "+config[1]+" "+config[2]+" "+config[3]);
	       
		   String hostName = config[0];	 
	       String dbName = config[1];
	       String userName = config[2];
	       String password = config[3];
	 
	       return getMySQLConnection(hostName, dbName, userName, password);
	   }
	 
	   public static Connection getMySQLConnection(String hostName, String dbName,
	           String userName, String password){
		   try {
		       Class.forName("com.mysql.jdbc.Driver");
		 
		       String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName +"?verifyServerCertificate=false&useSSL=true";
		 
		       Connection conn = DriverManager.getConnection(connectionURL, userName,password);
		       return conn;
		   } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return null;
	   }
	   
	   public static  String [] obterConfig() {
		   try {
			   String [] config = null;
			   int i = 0;
			   FileReader arq = new FileReader("DataBaseConfig.txt");
			      BufferedReader lerArq = new BufferedReader(arq);
			 
			      String linha = lerArq.readLine();
			      
			      while (linha != null) {
			        System.out.printf("%s\n", linha);
			        config[i] = linha;
			        i = i + 1;
			        linha = lerArq.readLine(); // lê da segunda até a última linha
			      }
			 
			      arq.close();
			      return config;
			    } catch (IOException e) {
			        System.err.printf("Erro na abertura do arquivo: %s.\n",
			          e.getMessage());
			    }	
		   return null;
	   }
	   
	}
