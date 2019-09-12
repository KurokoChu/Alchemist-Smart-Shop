package com.kurokochu.alchemistsmartshop;

import javax.persistence.Entity;

@Entity
public class HealingPotion extends Potion {

	private String rarity;
	private String description;
	private int recoveryAmount;
	private int cooldownTime;
	private double price;
	private String img_url;

	public HealingPotion(){
		
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
		return this.recoveryAmount;
	}

	public void setRecoveryAmount(int amount) {
		this.recoveryAmount = amount;
	}

	public int getCooldownTime() {
		return this.cooldownTime;
	}

	public void setCooldownTime(int second) {
		this.cooldownTime = second;
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
		return String.format("%s Healing Potion", this.rarity);
	}

	public void use() {
		System.out.println(String.format("Use %s Healing Potion, You feel healed.", this.rarity));
	}

}
