package com.kurokochu.smartshopfactorypattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@RestController
public class SmartShopApplicationDemo {

	private static PotionFactory factory;

	public static void main(String[] args) {
		factory = new PotionFactory();
		factory.init();

		factory.getShelf().add(factory.createPotion(PotionType.RESURRECTION));
		factory.getShelf().add(factory.createPotion(PotionType.RESURRECTION));
		factory.getShelf().add(factory.createPotion(PotionType.STRENGTH));
		factory.getShelf().add(factory.createPotion(PotionType.HEALING));
		factory.getShelf().add(factory.createPotion(PotionType.RESURRECTION));
		factory.getShelf().add(factory.createPotion(PotionType.STRENGTH));
		factory.getShelf().add(factory.createPotion(PotionType.HEALING));
		factory.getShelf().add(factory.createPotion(PotionType.HEALING));

		SpringApplication.run(SmartShopApplicationDemo.class, args);
	}

	@RequestMapping("/")
	String home() {
		String page = "";
		page += "<h1>Alchemist Shop API</h1>";
		page += "<p>Alchemist's shop that sell lot of magic potions. You can purchase the potions from store by using Gold</p>";
		page += "<h3>End points</h3>";
		page += "<ul>";
		page += "<li><p><strong><code>GET</code></strong> \\potion</p><p>Get all potions list</p></li>";
		page += "</ul>";
		page += "<ul>";
		page += "<li><p><strong><code>GET</code></strong> \\potion\\{id}</p><p>Get potion by ID</p></li>";
		page += "</ul>";
		page += "<ul>";
		page += "<li><p><strong><code>GET</code></strong> \\checkout\\{id}</p><p>Checkout potion by ID</p></li>";
		page += "</ul>";
		page += "<ul>";
		page += "<li><p><strong><code>POST</code></strong> \\potion\\add</p><p>Add potion to shop</p></li>";
		page += "</ul>";
		page += "<ul>";
		page += "<li><p><strong><code>POST</code></strong> \\potion\\remove</p><p>Remove potion from shop</p></li>";
		page += "</ul>";
		return page;
	}

	@RequestMapping("/potion")
	public String viewPotionList() {
		String page = "";

		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(factory.getShelf());
			page += "<b>result: </b>";
			page += jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		page += "<h2>List of Potions</h2>";
		for (Potion potion : factory.getShelf()) {
			if (potion instanceof HealingPotion) {
				page += "<ul>";
				page += String.format("<li style=\"list-style-type: none;\"><img src=\"%s\"></li>", ((HealingPotion) potion).getImgURL());
				page += String.format("<li><b>%s [ID: %d]</b></li>", potion, potion.getId());
				page += String.format("<li style=\"list-style-type: none;\">%s</li>", ((HealingPotion) potion).getDescription());
				page += String.format("<li style=\"list-style-type: none;\">Recovery %d HP</li>", ((HealingPotion) potion).getRecoveryAmount());
				page += String.format("<li style=\"list-style-type: none;\">Cooldown Time %d second</li>", ((HealingPotion) potion).getCooldownTime());
				page += String.format("<li style=\"list-style-type: none;\">Price %.0f G</li>", ((HealingPotion) potion).getPrice());
				page += "</ul><br>";
			} else if (potion instanceof ResurrectionPotion) {
				page += "<ul>";
				page += String.format("<li style=\"list-style-type: none;\"><img src=\"%s\"></li>", ((ResurrectionPotion) potion).getImgURL());
				page += String.format("<li><b>%s [ID: %d]</b></li>", potion, potion.getId());
				page += String.format("<li style=\"list-style-type: none;\">%s</li>", ((ResurrectionPotion) potion).getDescription());
				page += String.format("<li style=\"list-style-type: none;\">Cast Time %d second</li>", ((ResurrectionPotion) potion).getCastTime());
				page += String.format("<li style=\"list-style-type: none;\">Cooldown Time %d second</li>", ((ResurrectionPotion) potion).getCooldownTime());
				page += String.format("<li style=\"list-style-type: none;\">Price %.0f G</li>", ((ResurrectionPotion) potion).getPrice());
				page += "</ul><br>";
				
			} else if (potion instanceof StrengthPotion) {
				page += "<ul>";
				page += String.format("<li style=\"list-style-type: none;\"><img src=\"%s\"></li>", ((StrengthPotion) potion).getImgURL());
				page += String.format("<li><b>%s [ID: %d]</b></li>", potion, potion.getId());
				page += String.format("<li style=\"list-style-type: none;\">%s</li>", ((StrengthPotion) potion).getDescription());
				page += String.format("<li style=\"list-style-type: none;\">%s</li>", ((StrengthPotion) potion).getEffect());
				page += String.format("<li style=\"list-style-type: none;\">Duration %d second</li>", ((StrengthPotion) potion).getDuration());
				page += String.format("<li style=\"list-style-type: none;\">Price %.0f G</li>", ((StrengthPotion) potion).getPrice());
				page += "</ul><br>";
			}
		}
		return page;
	}

	@RequestMapping("/potion/{id}")
	public String viewPotionById(@PathVariable int id) {
		Potion potion = factory.getPotionById(id);
		if (potion == null) {
			return "Potion Not Found";
		}

		String page = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(potion);
			page += "<b>result: </b>";
			page += jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (potion instanceof HealingPotion) {
			page += "<ul>";
			page += String.format("<li style=\"list-style-type: none;\"><img src=\"%s\"></li>", ((HealingPotion) potion).getImgURL());
			page += String.format("<li><b>%s [ID: %d]</b></li>", potion, potion.getId());
			page += String.format("<li style=\"list-style-type: none;\">%s</li>", ((HealingPotion) potion).getDescription());
			page += String.format("<li style=\"list-style-type: none;\">Recovery %d HP</li>", ((HealingPotion) potion).getRecoveryAmount());
			page += String.format("<li style=\"list-style-type: none;\">Cooldown Time %d second</li>", ((HealingPotion) potion).getCooldownTime());
			page += String.format("<li style=\"list-style-type: none;\">Price %.0f G</li>", ((HealingPotion) potion).getPrice());
			page += "</ul><br>";
		} else if (potion instanceof ResurrectionPotion) {
			page += "<ul>";
			page += String.format("<li style=\"list-style-type: none;\"><img src=\"%s\"></li>", ((ResurrectionPotion) potion).getImgURL());
			page += String.format("<li><b>%s [ID: %d]</b></li>", potion, potion.getId());
			page += String.format("<li style=\"list-style-type: none;\">%s</li>", ((ResurrectionPotion) potion).getDescription());
			page += String.format("<li style=\"list-style-type: none;\">Cast Time %d second</li>", ((ResurrectionPotion) potion).getCastTime());
			page += String.format("<li style=\"list-style-type: none;\">Cooldown Time %d second</li>", ((ResurrectionPotion) potion).getCooldownTime());
			page += String.format("<li style=\"list-style-type: none;\">Price %.0f G</li>", ((ResurrectionPotion) potion).getPrice());
			page += "</ul><br>";
			
		} else if (potion instanceof StrengthPotion) {
			page += "<ul>";
			page += String.format("<li style=\"list-style-type: none;\"><img src=\"%s\"></li>", ((StrengthPotion) potion).getImgURL());
			page += String.format("<li><b>%s [ID: %d]</b></li>", potion, potion.getId());
			page += String.format("<li style=\"list-style-type: none;\">%s</li>", ((StrengthPotion) potion).getDescription());
			page += String.format("<li style=\"list-style-type: none;\">%s</li>", ((StrengthPotion) potion).getEffect());
			page += String.format("<li style=\"list-style-type: none;\">Duration %d second</li>", ((StrengthPotion) potion).getDuration());
			page += String.format("<li style=\"list-style-type: none;\">Price %.0f G</li>", ((StrengthPotion) potion).getPrice());
			page += "</ul>";
		}
		return page;
	}

	@RequestMapping("/potion/add")
    public String add(@RequestBody Potion potion){
		String page = "";
        if (factory.add(potion)) {
        	page += "\"status\": \"1\"";
            return page;
        }

        page += "\"status\": \"0\"";
        return page;
    }

	@RequestMapping("/potion/remove")
    public String remove(@RequestParam(value = "id") int id){
		String page = "";
        if(factory.remove(id)) {
        	page += "\"status\": \"1\"";
            return page;
        }
 
        page += "\"status\": \"0\"";
        return page;
    }
	
	@RequestMapping("/checkout/{id}")
	public String checkoutPotionById(@PathVariable int id) {
		Potion potion = factory.getPotionById(id);
		if(potion == null) {
            return "Potion Not Found";
        }
 
		String page = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(potion);
			page += "<b>result: </b>";
			page += jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		page += String.format("<p>%s [ID: %d]</p>", potion, potion.getId());
		page += "<h4>SOLD!</h4>";
		factory.remove(id);
		return page;
	}

}
