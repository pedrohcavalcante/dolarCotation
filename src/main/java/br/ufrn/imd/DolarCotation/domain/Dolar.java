package br.ufrn.imd.DolarCotation.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;

public class Dolar {
	
	private String dollar;
	private String cotacaoCompra;
	private String cotacaoVenda;
	
	public String getDollar() {
		return dollar;
	}
	public String getCotacaoCompra() {
		return cotacaoCompra;
	}
	public String getCotacaoVenda() {
		return cotacaoVenda;
	}
	public void setDollar(String dollar) {
		this.dollar = dollar;
	}
	public void setCotacaoCompra(String cotacaoCompra) {
		this.cotacaoCompra = cotacaoCompra;
	}
	public void setCotacaoVenda(String cotacaoVenda) {
		this.cotacaoVenda = cotacaoVenda;
	}
	
	

	
	

}
