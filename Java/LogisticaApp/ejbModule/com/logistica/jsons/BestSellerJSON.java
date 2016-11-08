package com.logistica.jsons;

import java.util.List;

public class BestSellerJSON {

	//	{"ranking":[{"codigo":"1857363",nombreDeposito:"GXX","posicion":"3"}, 
	private List<ItemBestSellerJSON> ranking;

	public BestSellerJSON() {
	}

	public List<ItemBestSellerJSON> getRanking() {
		return ranking;
	}

	public void setRanking(List<ItemBestSellerJSON> ranking) {
		this.ranking = ranking;
	}
	
	
}
