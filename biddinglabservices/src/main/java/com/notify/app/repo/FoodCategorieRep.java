package com.notify.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.FoodCategorieBean;
@Repository
public interface FoodCategorieRep extends JpaRepository<FoodCategorieBean, Integer>{

}
