package com.biddinglab.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biddinglab.app.model.BidderBean;

@Repository
public interface BidderRep extends JpaRepository<BidderBean, Integer>{

}
