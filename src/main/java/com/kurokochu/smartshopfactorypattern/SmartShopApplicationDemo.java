package com.kurokochu.smartshopfactorypattern;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class SmartShopApplicationDemo {
	
	@Autowired
    private PotionFactory factory = PotionFactory.getInstance();

	public static void main(String[] args) {
//		factory.getShelf().add(factory.createPotion(PotionType.RESURRECTION));
//		factory.getShelf().add(factory.createPotion(PotionType.RESURRECTION));
//		factory.getShelf().add(factory.createPotion(PotionType.STRENGTH));
//		factory.getShelf().add(factory.createPotion(PotionType.HEALING));
//		factory.getShelf().add(factory.createPotion(PotionType.RESURRECTION));
//		factory.getShelf().add(factory.createPotion(PotionType.STRENGTH));
//		factory.getShelf().add(factory.createPotion(PotionType.HEALING));
//		factory.getShelf().add(factory.createPotion(PotionType.HEALING));

		SpringApplication.run(SmartShopApplicationDemo.class, args);
//		System.out.println(potionRepository.findAll());
//		factory = PotionFactory.getInstance();
//
//		factory.savePotion(factory.createPotion(PotionType.HEALING));
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

	@GetMapping("/product")
	public ResponseEntity<List<Potion>> index() {
		List<Potion> potions = factory.getShelf();
		if (potions == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Potion>>(potions, HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Potion> viewPotion(@PathVariable String id) {
		long productId = Long.parseLong(id);
		Potion potion = factory.getPotionById(productId).get();
		if (potion == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Potion>(potion, HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<Potion> create(@Valid @RequestBody Potion potion) {
		Potion newPotion = factory.createPotion(PotionType.HEALING);
		factory.savePotion(newPotion);
		return new ResponseEntity<Potion>(newPotion, HttpStatus.OK);
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Potion> update(@PathVariable String id, @Valid @RequestBody Potion potion) {
		long productId = Long.parseLong(id);
		Potion potionInstance = factory.getPotionById(productId).get();
		if (potionInstance instanceof HealingPotion) {
			((HealingPotion) potionInstance).setRarity(((HealingPotion) potion).getRarity());
			((HealingPotion) potionInstance).setDescription(((HealingPotion) potion).getDescription());
			((HealingPotion) potionInstance).setRecoveryAmount(((HealingPotion) potion).getRecoveryAmount());
			((HealingPotion) potionInstance).setCooldownTime(((HealingPotion) potion).getCooldownTime());
		}

		factory.savePotion(potionInstance);
		if (potionInstance == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Potion>(potionInstance, HttpStatus.OK);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Potion> delete(@PathVariable String id) {
		long productId = Long.parseLong(id);
		if (factory.deletePotionById(productId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
