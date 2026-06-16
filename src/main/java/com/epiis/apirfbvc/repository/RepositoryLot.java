package com.epiis.apirfbvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epiis.apirfbvc.entity.EntityLot;

@Repository
public interface RepositoryLot extends JpaRepository<EntityLot, String> {
    List<EntityLot> findByProduct_IdProduct(String idProduct);

}
