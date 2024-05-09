package com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @XmlElement
    @Id
    private int id;

    @XmlElement
    private String name;

    @XmlElement
    private String email;

    @XmlElement
    private long aadhaar;

    @XmlElement
    private long phoneNo;

    @XmlElement
    private long pan;

}
