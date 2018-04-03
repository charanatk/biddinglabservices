package com.notify.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.MenuBean;

@Repository
public interface MenuRep extends JpaRepository<MenuBean, Integer> {

	List<MenuBean> findByUsername(final String username);

}
