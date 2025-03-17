package com.loadManagement.load.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loadManagement.load.entity.Load;

import java.util.List;
import java.util.UUID;

public interface LoadRepository extends JpaRepository<Load, UUID>, JpaSpecificationExecutor<Load> {
    List<Load> findByShipperId(String shipperId);
}
