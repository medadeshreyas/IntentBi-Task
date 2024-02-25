package com.inentbi.task.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inentbi.task.model.SalesData;

@Repository
public interface SalesDataRepository extends JpaRepository<SalesData, Long> {

}
