package com.biddinglab.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biddinglab.app.model.JobBean;
@Repository
public interface JobRep extends JpaRepository<JobBean, Integer>{

}
