package com.kurokochu.smartshopfactorypattern;

public class StrengthPotion implements Potion {

	private int id;
	private String status;
	private String description;
	private String effect;
	private int duration;
	private double price;
	private String imgURL;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEffect() {
		return this.effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int second) {
		this.duration = second;
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
		return String.format("%s Strength Potion", this.status);
	}

	public void use() {
		System.out.println(String.format("Use %s Strength Potion, You got %s buff.", this.status));
	}

}
