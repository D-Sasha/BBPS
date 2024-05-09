package com.hcl.BBPCOU.entity.BillFetchEntity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_email")
    private String customerEmail;

    @Id
    @Column(name = "bill_number")
    private long billNumber;

    @Column(name = "biller_id")
    private int billerId;

    @Column(name = "phone_no")
    private long phoneNo;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "amount")
    private long amount;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "bill_date")
    private String billDate;

    @Column(name = "bill_period")
    private String billPeriod;

}
