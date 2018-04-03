package com.notify.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.NotificationBean;
@Repository
public interface NotificationRep extends JpaRepository<NotificationBean, Integer>{

}
