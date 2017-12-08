package br.com.proodonto.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Consulta {
	
	private int id;
	private Timestamp data_consulta;
	private String observacao;
	private Paciente paciente;
	private Dentista dentista;
	private String procedimento;
	private float preco;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getData_consulta() {
		return data_consulta;
	}
	public void setData_consulta(Timestamp data_consulta) {
		this.data_consulta = data_consulta;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Dentista getDentista() {
		return dentista;
	}
	public void setDentista(Dentista dentista) {
		this.dentista = dentista;
	}
	public String getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	
 

}
