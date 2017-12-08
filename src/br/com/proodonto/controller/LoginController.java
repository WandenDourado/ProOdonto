package br.com.proodonto.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.proodonto.dao.CreateDAO;
import br.com.proodonto.dao.DBUtil;
import br.com.proodonto.dao.DentistaDAO;
import br.com.proodonto.model.Dentista;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController{
	
	private static Dentista logado;
	
	public boolean acessarSistema(String cpf, String senha) {
		DentistaDAO denstitaDAO = new DentistaDAO();
		Dentista dentista = new Dentista();
		dentista = denstitaDAO.validarAcesso(cpf, senha);
		if(dentista != null) {
			setLogado(dentista);
			return true;
		}
		return false;
	}

	public static Dentista getLogado() {
		return logado;
	}

	public static void setLogado(Dentista logado) {
		LoginController.logado = logado;
	}
	
	public void gravarConfig(String host, String user, String senha) {
		try {
			FileWriter arq = new FileWriter("DataBaseConfig.txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			 
		    gravarArq.printf(host+"-proodonto-"+user+"-"+senha);
		 
		    arq.close();
		    alertaMensagem("Banco Configurado com Sucesso!");
		    CreateDAO bd = new CreateDAO();
		    bd.createBanco();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alertaMensagem("Erro ao configurar o banco! :(");
		}
	    
	}
	
	private void alertaMensagem(String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Configurar Banco");
		alert.setContentText(mensagem);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

}
