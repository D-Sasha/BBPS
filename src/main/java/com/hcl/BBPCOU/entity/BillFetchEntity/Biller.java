package com.hcl.BBPCOU.entity.BillFetchEntity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "biller")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Biller {

    @Id
    @Column(name = "biller_id")
    private int billerId;

    @Column(name = "biller_name")
    private String billerName;

    @Column(name = "biller_category")
    private String billerCatagory;

}
