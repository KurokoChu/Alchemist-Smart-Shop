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
		SpringApplication.run(SmartShopApplicationDemo.class, args);
	}

	@GetMapping("/product")
	public ResponseEntity<List<Potion>> index() {
		List<Potion> potions = factory.getShelf();
		if (potions == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<Potion>>(potions, HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Potion> viewPotion(@PathVariable String id) {
		long productId = Long.parseLong(id);
		Potion potion;
		try {
			potion = factory.getPotionById(productId).get();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Potion>(potion, HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<Potion> create(@Valid @RequestBody Potion potion) {
		Potion newPotion = factory.createPotion(potion.getType());
		factory.updatePotion(newPotion, potion);
		factory.savePotion(newPotion);
		return new ResponseEntity<Potion>(newPotion, HttpStatus.OK);
	}

	@PutMapping("/product/healing/{id}")
	public ResponseEntity<Potion> update(@PathVariable String id, @Valid @RequestBody HealingPotion potion) {
		long productId = Long.parseLong(id);
		Potion newPotion;
		try {
			newPotion = factory.getPotionById(productId).get();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		factory.updatePotion(newPotion, potion);
		factory.savePotion(newPotion);
		return new ResponseEntity<Potion>(newPotion, HttpStatus.OK);
	}

	@PutMapping("/product/resurrection/{id}")
	public ResponseEntity<Potion> update(@PathVariable String id, @Valid @RequestBody ResurrectionPotion potion) {
		long productId = Long.parseLong(id);
		Potion newPotion;
		try {
			newPotion = factory.getPotionById(productId).get();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		factory.updatePotion(newPotion, potion);
		factory.savePotion(newPotion);
		return new ResponseEntity<Potion>(newPotion, HttpStatus.OK);
	}

	@PutMapping("/product/strength/{id}")
	public ResponseEntity<Potion> update(@PathVariable String id, @Valid @RequestBody StrengthPotion potion) {
		long productId = Long.parseLong(id);
		Potion newPotion;
		try {
			newPotion = factory.getPotionById(productId).get();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		factory.updatePotion(newPotion, potion);
		factory.savePotion(newPotion);
		return new ResponseEntity<Potion>(newPotion, HttpStatus.OK);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Potion> delete(@PathVariable String id) {
		long productId = Long.parseLong(id);
		Potion potion;
		try {
			potion = factory.getPotionById(productId).get();
			factory.deletePotionById(productId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Potion>(potion, HttpStatus.OK);

	}

}
