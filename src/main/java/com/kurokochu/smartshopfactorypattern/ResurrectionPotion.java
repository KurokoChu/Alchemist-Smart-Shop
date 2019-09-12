package com.kurokochu.smartshopfactorypattern;

public class ResurrectionPotion extends Potion {

	private String rarity;
	private String description;
	private int cast_time;
	private int cooldown_time;
	private double price;
	private String imgURL;

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

	public int getCastTime() {
		return this.cast_time;
	}

	public void setCastTime(int second) {
		this.cast_time = second;
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

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	@Override
	public String toString() {
		return String.format("%s Resurrection Potion", this.rarity);
	}

	public void use() {
		System.out.println(String.format("Use %s Resurrection Potion, Allies revived.", this.rarity));
	}

}
