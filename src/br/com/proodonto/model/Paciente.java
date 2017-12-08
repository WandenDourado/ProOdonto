package br.com.proodonto.model;

import java.sql.Date;

public class Paciente {
	
	private String cpf;
	private String nome;
	private String sobrenome;
	private String telefone_residencial;
	private String telefone_comercial;
	private String celular;
	private Date data_nascimento;
	private Date data_cadastro;
	private int numero_ficha;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getTelefone_residencial() {
		return telefone_residencial;
	}
	public void setTelefone_residencial(String telefone_residencial) {
		this.telefone_residencial = telefone_residencial;
	}
	public String getTelefone_comercial() {
		return telefone_comercial;
	}
	public void setTelefone_comercial(String telefone_comercial) {
		this.telefone_comercial = telefone_comercial;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Date getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}
	public int getNumero_ficha() {
		return numero_ficha;
	}
	public void setNumero_ficha(int numero_ficha) {
		this.numero_ficha = numero_ficha;
	}
	
	
	


}
