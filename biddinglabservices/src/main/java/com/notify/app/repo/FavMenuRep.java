package com.notify.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.FavMenuBean;
@Repository
public interface FavMenuRep extends JpaRepository<FavMenuBean, Integer>{

}
