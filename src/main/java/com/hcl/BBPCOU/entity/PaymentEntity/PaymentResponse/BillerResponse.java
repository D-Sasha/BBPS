package com.hcl.BBPCOU.entity.PaymentEntity.PaymentResponse;

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
    private long billId;

    @XmlElement
    private String customerName;

    @XmlElement
    private double amount;

    @XmlElement
    private String dueDate;

    @XmlElement
    private double custConvFee;

    @XmlElement
    private String billDate;

}
