package com.kurokochu.smartshopfactorypattern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PotionRepository extends JpaRepository<Potion, Long> {

}
