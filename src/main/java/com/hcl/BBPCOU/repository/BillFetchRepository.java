package com.hcl.BBPCOU.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.BBPCOU.entity.BillFetchEntity.Bill;

public interface BillFetchRepository extends JpaRepository<Bill, Integer> {

    Bill findByBillerIdAndPhoneNo(int billerId, long phoneNo);

    Bill findByBillerId(int billerId);

    Bill findByBillerIdAndCustomerId(int billerId, int id);

}
