package com.logistica.entityBeans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Est")
public class Estandar extends Modulo {

	private String urlEnvioRankingBestSellers; // Solo aplicaria para los Portales Web

	public Estandar() {
		
	}

	public String getUrlEnvioRankingBestSellers() {
		return urlEnvioRankingBestSellers;
	}

	public void setUrlEnvioRankingBestSellers(String urlEnvioRankingBestSellers) {
		this.urlEnvioRankingBestSellers = urlEnvioRankingBestSellers;
	}

}
