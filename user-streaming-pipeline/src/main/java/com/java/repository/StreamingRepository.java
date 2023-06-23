package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.Message;
import com.java.model.StreamEntity;

@Repository
public interface StreamingRepository extends JpaRepository<StreamEntity, Long>  {

}
