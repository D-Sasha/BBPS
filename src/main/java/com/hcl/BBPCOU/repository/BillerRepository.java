package com.hcl.BBPCOU.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.BBPCOU.entity.BillFetchEntity.Biller;

import java.util.List;

public interface BillerRepository extends JpaRepository<Biller, Integer> {

    List<Biller> findByBillerCatagory(String category);
}
