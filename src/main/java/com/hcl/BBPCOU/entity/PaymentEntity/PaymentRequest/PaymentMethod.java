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
@XmlRootElement(name = "paymentMethod")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentMethod {

    @XmlElement
    private String quickPay;

    @XmlElement
    private String splitPay;

    @XmlElement
    private String offUSPay;

    @XmlElement
    private String paymentMode;

}
