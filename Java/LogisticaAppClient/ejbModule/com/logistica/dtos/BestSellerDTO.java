package com.logistica.dtos;

import java.io.Serializable;
import java.util.List;

public class BestSellerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	//	{"ranking":[{"codigo":"1857363",nombreDeposito:"GXX","posicion":"3"}, 
	private List<ItemBestSellerDTO> itemsBestSeller;

	public BestSellerDTO() {
	}

	public List<ItemBestSellerDTO> getItems() {
		return itemsBestSeller;
	}

	public void setItems(List<ItemBestSellerDTO> ranking) {
		this.itemsBestSeller = ranking;
	}
	
	
}
