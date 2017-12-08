package br.com.proodonto.view.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.proodonto.controller.LoginController;
import br.com.proodonto.model.Dentista;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginViewController extends Application implements Initializable {
	
	private static Stage loginSt;
	
	@FXML
	TextField txt_cpf;
	
	@FXML
	PasswordField txt_senha;

	@Override
	public void start(Stage loginSt) throws Exception {
		this.setLoginSt(loginSt);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../Login.fxml"));
			Scene scene = new Scene(root, 246, 265);
			loginSt.setTitle("Entrar");
			loginSt.setScene(scene);
			loginSt.show();
		} catch(Exception e) {
			e.printStackTrace();
		}				
	}
	
	public static void main(String[] args) {
			
			launch(args);
	
		}
	
public void abrirMain() {
		 // New window (Stage)
        Stage newWindow = new Stage();
        MainViewController main = new MainViewController();
        main.start(newWindow);
        getLoginSt().close();	
	}
	
	public Stage getStage() {
		return getLoginSt();		
	}

	public static Stage getLoginSt() {
		return loginSt;
	}

	public static void setLoginSt(Stage loginSt) {
		LoginViewController.loginSt = loginSt;
	}
	
	public void botaoEntrar(ActionEvent event) {
		LoginController login = new LoginController();
		if(login.acessarSistema(txt_cpf.getText(), txt_senha.getText())) {
			abrirMain();
		}
		else {
			alertaMensagem("Usuario Invalido");
		}
	}
	
public void abirConfigForm() {
		
		try {
			Parent configForm = FXMLLoader.load(getClass().getResource("../ConfigBanco.fxml"));
			Scene configScene = new Scene(configForm, 293, 312);
			configScene.getStylesheets().add(getClass().getResource("../Proodonto.css").toExternalForm());
			 // New window (Stage)
	        Stage newWindow = new Stage();
	        newWindow.setTitle("Configurar Banco");
	        newWindow.setScene(configScene);
	        newWindow.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

public void abrirConfigJanela(ActionEvent event) {		
	abirConfigForm(); 
   }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	private void alertaMensagem(String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Logar");
		alert.setContentText(mensagem);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

}
