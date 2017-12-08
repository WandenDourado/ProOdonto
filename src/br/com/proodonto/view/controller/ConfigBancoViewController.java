package br.com.proodonto.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.proodonto.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConfigBancoViewController extends Application implements Initializable {
	
	@FXML
	TextField txt_hostname;
	
	@FXML
	TextField txt_username;
	
	@FXML
	PasswordField txt_senha;
	
	@FXML
	private Button btn_fechar;
	
	LoginController loginController = new LoginController();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void gravar() {
		loginController.gravarConfig(txt_hostname.getText(), txt_username.getText(), txt_senha.getText());		
	}
	
	@FXML
	public void fecharJanela() {
		Stage stage = (Stage) btn_fechar.getScene().getWindow();
		stage.close();
	}

}
