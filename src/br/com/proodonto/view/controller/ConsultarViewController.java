package br.com.proodonto.view.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import br.com.proodonto.controller.ConsultaContoller;
import br.com.proodonto.controller.LoginController;
import br.com.proodonto.dao.ConsultaDAO;
import br.com.proodonto.dao.DentistaDAO;
import br.com.proodonto.model.Consulta;
import br.com.proodonto.model.Paciente;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ConsultarViewController extends Application implements Initializable {
	
	@FXML
	private TableView<Consulta> table_consulta;
	@FXML
	private TableColumn<Consulta, String> column_paciente = new TableColumn<Consulta, String>();
	@FXML
	private TableColumn column_procedimento;
	@FXML
	private TableColumn<Consulta, String> column_data = new TableColumn<Consulta, String>();
	@FXML
	private TableColumn<Consulta, String> column_horario;
	@FXML
	private ComboBox<String> comb_dentista;
	@FXML
	private TextField txt_paciente;
	
	public static String id_consulta;
	
	ContextMenu contextMenu = new ContextMenu();
	MenuItem pagar = new MenuItem("Cadastrar Pagamento");
	
	DentistaDAO dentistaDAO = new DentistaDAO();
	ConsultaDAO consultaDAO = new ConsultaDAO();	
	MainViewController main = new MainViewController();
	LoginController login = new LoginController();
	ConsultaContoller consultaController = new ConsultaContoller();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> dentistaLista = dentistaDAO.listarNomesDentitas();
		comb_dentista.setItems(dentistaLista);			
		System.out.println(login.getLogado().getCpf());
		 ObservableList<Consulta> list = consultaDAO.listarConsultas(login.getLogado().getCpf());
		 
		 atualizarTabela(list);
		 
	      
	      table_consulta.getSelectionModel().selectedItemProperty().addListener(
		             (observable, oldValue, newValue) -> mostrarDetalhesConsulta(newValue));
		     
		  contextMenu.getItems().addAll(pagar);
		
	}
	
	private void mostrarDetalhesConsulta(Consulta consulta) {
			
	        pagar.setOnAction(new EventHandler<ActionEvent>() {	 
	            @Override
	            public void handle(ActionEvent event) {
	                System.out.println(consulta.getPaciente());
	                try {
	                	id_consulta = String.valueOf(consulta.getId());
						abirPagamentoForm(id_consulta);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        });   
	        
	        table_consulta.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
				  
		            @Override
		            public void handle(ContextMenuEvent event) {
		 
		                contextMenu.show(table_consulta, event.getScreenX(), event.getScreenY());
		            }
		        });
		}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void abirPagamentoForm(String id_consulta) throws Exception {
		
		try {
			Parent pagamentoForm = FXMLLoader.load(getClass().getResource("../Pagamento_Formulario.fxml"));
			Scene pagamentoScene = new Scene(pagamentoForm, 378, 415);
			pagamentoScene.getStylesheets().add(getClass().getResource("../Proodonto.css").toExternalForm());
			 // New window (Stage)
	        Stage newWindow = new Stage();
	        newWindow.setTitle("Pagamento");
	        newWindow.setScene(pagamentoScene);	       
	        newWindow.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void botaoBuscar() {
		if(comb_dentista.getValue()!= null && comb_dentista.getValue() != "null") {
			ObservableList<Consulta> list = consultaDAO.listarConsultasPaciente(consultaController.getCpfCombo(comb_dentista), txt_paciente.getText());		
			atualizarTabela(list);			
		}else {
			ObservableList<Consulta> list = consultaDAO.listarConsultasPaciente(login.getLogado().getCpf(), txt_paciente.getText());		
			atualizarTabela(list);
		}
	}
	
	public void selecionarCombo() {
		ObservableList<Consulta> list = consultaDAO.listarConsultas(consultaController.getCpfCombo(comb_dentista));
		atualizarTabela(list);
	}
	
	public void atualizarTabela( ObservableList<Consulta> list) {
		
		column_paciente.setCellValueFactory(new Callback<CellDataFeatures<Consulta, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Consulta, String> c) {
	        	String retorno = c.getValue().getPaciente().getNome() + " " + c.getValue().getPaciente().getSobrenome();
	            return new SimpleStringProperty(retorno);                
	        }
	});
	 
	 column_data.setCellValueFactory(new Callback<CellDataFeatures<Consulta, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Consulta, String> c) {
	        	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	            String retorno = format.format(c.getValue().getData_consulta());
	            return new SimpleStringProperty(retorno);                
	        }
	});
	 
	 column_horario.setCellValueFactory(new Callback<CellDataFeatures<Consulta, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Consulta, String> c) {
	        	SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	            String retorno = format.format(c.getValue().getData_consulta());
	            return new SimpleStringProperty(retorno);                
	        }
	});
	  
      column_procedimento.setCellValueFactory(new PropertyValueFactory<>("procedimento"));
      table_consulta.setItems(list);	
		
	}
	


}
