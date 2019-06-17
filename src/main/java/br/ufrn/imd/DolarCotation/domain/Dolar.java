package br.ufrn.imd.DolarCotation.domain;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

public class Dolar {
	
	private String dollar;
	private Double cotacaoCompra;
	private Double cotacaoVenda;
	private String dataHoraCotacao;
	
	public String getDollar() {
		return dollar;
	}
	public Double getCotacaoCompra() {
		return cotacaoCompra;
	}
	public Double getCotacaoVenda() {
		return cotacaoVenda;
	}
	public void setDollar(String dollar) {
		this.dollar = dollar;
	}
	public void setCotacaoCompra(Double cotacaoCompra) {
		this.cotacaoCompra = cotacaoCompra;
	}
	public void setCotacaoVenda(Double cotacaoVenda) {
		this.cotacaoVenda = cotacaoVenda;
	}
	public String getDataHoraCotacao() {
		return dataHoraCotacao;
	}
	public void setDataHoraCotacao(String dataHoraCotacao) {
		this.dataHoraCotacao = dataHoraCotacao;
	}
	
	

	
	

}
