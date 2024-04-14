package com.omvl.omvl.domain;

public class Item {

	//상품 인덱스용 id
	private Long id;
	//상품이 사용할 이름
	private String itemName;
	//상품이 사용할 가격
	private int price;
	//상품이 사용할 종류
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
