package com.notify.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.RestaurantBean;
@Repository
public interface RestaurantRep extends JpaRepository<RestaurantBean, Integer>{

}
