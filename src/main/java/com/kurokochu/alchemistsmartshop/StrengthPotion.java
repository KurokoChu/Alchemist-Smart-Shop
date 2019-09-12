package com.kurokochu.alchemistsmartshop;

import javax.persistence.Entity;

@Entity
public class StrengthPotion extends Potion {

	private String status;
	private String description;
	private String effect;
	private int duration;
	private double price;
	private String img_url;

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

	public String getImgUrl() {
		return img_url;
	}

	public void setImgUrl(String img_url) {
		this.img_url = img_url;
	}

	@Override
	public String toString() {
		return String.format("%s Strength Potion", this.status);
	}

	public void use() {
		System.out.println(String.format("Use %s Strength Potion, You got %s buff.", this.status));
	}

}
