package it.data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Table(name="contocorrente")
@Entity
public class ContoCorrente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String numero;
	private String intestatario;
	private double saldo;
	
	public ContoCorrente() {}
	

	@Id
	@Column(name="numero", length=4)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name="intestatario", length=30)
	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	@Column(name="saldo")
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	

}
