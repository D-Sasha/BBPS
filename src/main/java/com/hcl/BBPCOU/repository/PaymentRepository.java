package com.hcl.BBPCOU.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.BBPCOU.entity.BillFetchEntity.Bill;

public interface PaymentRepository extends JpaRepository<Bill, Integer> {

    Bill findByBillerIdAndCustomerId(int billerId, int id);

    Bill findByBillerIdAndPhoneNo(int billerId, long phoneNo);

    @Transactional
    void deleteByBillNumber(long billNumber);

    // @Modifying
    // @Query("UPDATE bills b SET b.amount = b.amount - :deductedAmount WHERE
    // b.billNumber = :billNumber")
    // void deductAmountFromBill(@Param("billNumber") long billNumber,
    // @Param("deductedAmount") double deductedAmount);

}
