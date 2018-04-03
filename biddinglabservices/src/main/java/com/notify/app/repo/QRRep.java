package com.notify.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notify.app.model.QRBean;
@Repository
public interface QRRep extends JpaRepository<QRBean, Integer>{

}
