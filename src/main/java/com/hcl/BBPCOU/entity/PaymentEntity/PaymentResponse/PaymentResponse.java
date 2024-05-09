package com.hcl.BBPCOU.entity.PaymentEntity.PaymentResponse;

import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.Reason;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.BillerResponse;

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
@XmlRootElement(name = "paymentResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentResponse {

    @XmlElement
    private Reason reason;

    @XmlElement
    private BillerResponse billerResponse;

}
