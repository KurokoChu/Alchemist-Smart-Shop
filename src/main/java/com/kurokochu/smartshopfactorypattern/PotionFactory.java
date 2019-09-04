package com.kurokochu.smartshopfactorypattern;

import java.util.ArrayList;
import java.util.List;

public class PotionFactory {

	private List<Potion> potions;
	private int primary_id;

	public PotionFactory() {

	}

	public Potion createPotion(PotionType type) {
		if (type == null) {
			return null;
		}

		Potion potion;
		switch (type) {
		case HEALING:
			potion = new HealingPotion();
			potion.setId(++primary_id);
			((HealingPotion) potion).setRarity("Beginner");
			((HealingPotion) potion).setDescription("A potion that is used to recover HP.");
			((HealingPotion) potion).setRecoveryAmount(1000);
			((HealingPotion) potion).setCooldownTime(60);
			((HealingPotion) potion).setPrice(200);
			((HealingPotion) potion).setImgURL("https://elwiki.net/wiki/images/3/35/HP_Potion_Beginner.png");
			return potion;
		case RESURRECTION:
			potion = new ResurrectionPotion();
			potion.setId(++primary_id);
			((ResurrectionPotion) potion).setRarity("Elite");
			((ResurrectionPotion) potion).setDescription("A mysterious potion giving vitality to a allies so that they can revive.");
			((ResurrectionPotion) potion).setCastTime(3);
			((ResurrectionPotion) potion).setCooldownTime(60);
			((ResurrectionPotion) potion).setPrice(8000);
			((ResurrectionPotion) potion).setImgURL("https://elwiki.net/wiki/images/8/81/HQ_Shop_Item_78230.png");
			return potion;
		case STRENGTH:
			potion = new StrengthPotion();
			potion.setId(++primary_id);
			((StrengthPotion) potion).setStatus("Magical Attack");
			((StrengthPotion) potion).setDescription("Mysterious potion which grants the user the magical attack power.");
			((StrengthPotion) potion).setEffect("Magical Attack +15%");
			((StrengthPotion) potion).setDuration(1800);
			((StrengthPotion) potion).setPrice(4000);
			((StrengthPotion) potion).setImgURL("https://elwiki.net/wiki/images/3/3a/HQ_Shop_Item_78550.png");
			return potion;
		default:
			break;
		}

		return null;
	}

	public List<Potion> getShelf() {
		return potions;
	}

	public void init() {
		potions = new ArrayList<Potion>();
		primary_id = -1;
	}

	public boolean add(Potion potion) {
		if (potion == null) {
			return false;
		}

		potion.setId(++this.primary_id);
		this.potions.add(potion);
		return true;
	}

	public boolean remove(int id) {
		if (id < 0) {
			return false;
		}

		for (Potion potion: potions) {
			if (potion.getId() == id) {
				potions.remove(potion);
				return true;
			}
		}

		return false;
	}

	public Potion getPotionById(int id) {
		if (id < 0) {
			return null;
		}

		for (Potion potion: potions) {
			if (potion == null) {
				continue;
			}

			if (potion.getId() == id) {
				return potion;
			}
		}

		return null;
	}

}
