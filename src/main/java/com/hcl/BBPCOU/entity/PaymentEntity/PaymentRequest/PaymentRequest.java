package com.hcl.BBPCOU.entity.PaymentEntity.PaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest.Customer;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest.Device;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest.BillDetails;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.BillerResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "paymentRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentRequest {

    @XmlElement
    private Customer customer;

    @XmlElement
    private Device device;

    @XmlElement
    private BillDetails billDetails;

    @XmlElement
    private BillerResponse billerResponse;

    @XmlElement
    private PaymentMethod paymentMethod;

    @XmlElement
    private Amount amount;

}
