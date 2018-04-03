package com.notify.app.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.biddinglab.app.model.JobBean;
@Repository
public interface DeploymentCustomerRep extends CrudRepository<JobBean, Integer>{

}
