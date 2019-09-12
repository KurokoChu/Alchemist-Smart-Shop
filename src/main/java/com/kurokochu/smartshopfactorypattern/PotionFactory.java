package com.kurokochu.smartshopfactorypattern;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PotionFactory {

	private static PotionFactory instance = null;

	@Autowired
	private PotionRepository potionRepository;

	public static PotionFactory getInstance() {
		if (instance == null) {
			instance = new PotionFactory();
		}

		return instance;
	}

	public void init() {
		savePotion(createPotion(PotionType.HEALING));
		savePotion(createPotion(PotionType.RESURRECTION));
		savePotion(createPotion(PotionType.RESURRECTION));
		savePotion(createPotion(PotionType.STRENGTH));
		savePotion(createPotion(PotionType.HEALING));
		savePotion(createPotion(PotionType.RESURRECTION));
		savePotion(createPotion(PotionType.STRENGTH));
		savePotion(createPotion(PotionType.HEALING));
		savePotion(createPotion(PotionType.HEALING));
	}

	public List<Potion> getShelf() {
		return potionRepository.findAll();
	}

	public Optional<Potion> getPotionById(long id) {
		return potionRepository.findById(id);
	}

	public Potion createPotion(PotionType type) {
		if (type == null) {
			return null;
		}

		Potion potion;
		switch (type) {
		case HEALING:
			potion = new HealingPotion();
			potion.setType(type);
			((HealingPotion) potion).setRarity("Beginner");
			((HealingPotion) potion).setDescription("A potion that is used to recover HP.");
			((HealingPotion) potion).setRecoveryAmount(1000);
			((HealingPotion) potion).setCooldownTime(60);
			((HealingPotion) potion).setPrice(200);
			((HealingPotion) potion).setImgUrl("https://elwiki.net/wiki/images/3/35/HP_Potion_Beginner.png");
			return potion;
		case RESURRECTION:
			potion = new ResurrectionPotion();
			potion.setType(type);
			((ResurrectionPotion) potion).setRarity("Elite");
			((ResurrectionPotion) potion)
					.setDescription("A mysterious potion giving vitality to a allies so that they can revive.");
			((ResurrectionPotion) potion).setCastTime(3);
			((ResurrectionPotion) potion).setCooldownTime(60);
			((ResurrectionPotion) potion).setPrice(8000);
			((ResurrectionPotion) potion).setImgUrl("https://elwiki.net/wiki/images/8/81/HQ_Shop_Item_78230.png");
			return potion;
		case STRENGTH:
			potion = new StrengthPotion();
			potion.setType(type);
			((StrengthPotion) potion).setStatus("Magical Attack");
			((StrengthPotion) potion)
					.setDescription("Mysterious potion which grants the user the magical attack power.");
			((StrengthPotion) potion).setEffect("Magical Attack +15%");
			((StrengthPotion) potion).setDuration(1800);
			((StrengthPotion) potion).setPrice(4000);
			((StrengthPotion) potion).setImgUrl("https://elwiki.net/wiki/images/3/3a/HQ_Shop_Item_78550.png");
			return potion;
		default:
			break;
		}

		return null;
	}

	public boolean savePotion(Potion potion) {
		if (potion == null) {
			return false;
		}

		potionRepository.save(potion);
		return true;
	}

	public boolean deletePotionById(long id) {
		if (!potionRepository.existsById(id)) {
			return false;
		}

		potionRepository.deleteById(id);
		return true;
	}

	public boolean updatePotion(Potion newPotion, Potion potion) {
		if (newPotion == null || potion == null || !newPotion.getClass().equals(potion.getClass())) {
			return false;
		}

		switch (newPotion.getType()) {
		case HEALING:
			((HealingPotion) newPotion).setRarity(((HealingPotion) potion).getRarity());
			((HealingPotion) newPotion).setDescription(((HealingPotion) potion).getDescription());
			((HealingPotion) newPotion).setRecoveryAmount(((HealingPotion) potion).getRecoveryAmount());
			((HealingPotion) newPotion).setCooldownTime(((HealingPotion) potion).getCooldownTime());
			((HealingPotion) newPotion).setPrice(((HealingPotion) potion).getPrice());
			((HealingPotion) newPotion).setImgUrl(((HealingPotion) potion).getImgUrl());
			break;
		case RESURRECTION:
			((ResurrectionPotion) newPotion).setRarity(((ResurrectionPotion) potion).getRarity());
			((ResurrectionPotion) newPotion).setDescription(((ResurrectionPotion) potion).getDescription());
			((ResurrectionPotion) newPotion).setCastTime(((ResurrectionPotion) potion).getCastTime());
			((ResurrectionPotion) newPotion).setCooldownTime(((ResurrectionPotion) potion).getCooldownTime());
			((ResurrectionPotion) newPotion).setPrice(((ResurrectionPotion) potion).getPrice());
			((ResurrectionPotion) newPotion).setImgUrl(((ResurrectionPotion) potion).getImgUrl());
			break;
		case STRENGTH:
			((StrengthPotion) newPotion).setStatus(((StrengthPotion) potion).getStatus());
			((StrengthPotion) newPotion).setDescription(((StrengthPotion) potion).getDescription());
			((StrengthPotion) newPotion).setEffect(((StrengthPotion) potion).getEffect());
			((StrengthPotion) newPotion).setDuration(((StrengthPotion) potion).getDuration());
			((StrengthPotion) newPotion).setPrice(((StrengthPotion) potion).getPrice());
			((StrengthPotion) newPotion).setImgUrl(((StrengthPotion) potion).getImgUrl());
			break;
		default:
			break;
		}

		return true;
	}

}
