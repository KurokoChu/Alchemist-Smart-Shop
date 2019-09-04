package com.kurokochu.smartshopfactorypattern;

public class HealingPotion implements Potion {

	private int id;
	private String rarity;
	private String description;
	private int recovery_amount;
	private int cooldown_time;
	private double price;
	private String imgURL;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRarity() {
		return this.rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRecoveryAmount() {
		return this.recovery_amount;
	}

	public void setRecoveryAmount(int amount) {
		this.recovery_amount = amount;
	}

	public int getCooldownTime() {
		return this.cooldown_time;
	}

	public void setCooldownTime(int second) {
		this.cooldown_time = second;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String url) {
		this.imgURL = url;
	}

	@Override
	public String toString() {
		return String.format("%s Healing Potion", this.rarity);
	}

	public void use() {
		System.out.println(String.format("Use %s Healing Potion, You feel healed.", this.rarity));
	}

}
