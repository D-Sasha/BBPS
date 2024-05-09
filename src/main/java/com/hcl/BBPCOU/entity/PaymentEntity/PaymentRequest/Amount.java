package com.hcl.BBPCOU.entity.PaymentEntity.PaymentRequest;

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
@XmlRootElement(name = "amount")
@XmlAccessorType(XmlAccessType.FIELD)
public class Amount {

    @XmlElement
    private double amount;

    @XmlElement
    private double custConvFee;

    @XmlElement
    private double COUcustConvFee;

    @XmlElement
    private long curency;
}
