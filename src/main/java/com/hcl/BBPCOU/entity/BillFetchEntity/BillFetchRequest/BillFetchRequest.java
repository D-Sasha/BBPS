package com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "billFetchRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillFetchRequest {

    @XmlElement
    private Customer customer;

    @XmlElement
    private Device device;

    @XmlElement
    private BillDetails billDetails;

}
