package com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "billerResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillerResponse {

    @XmlElement
    private String customerName;

    @XmlElement
    private long amount;

    @XmlElement
    private String dueDate;

    @XmlElement
    private String billDate;

    @XmlElement
    private long billNumber;

    @XmlElement
    private String billPeriod;

}
