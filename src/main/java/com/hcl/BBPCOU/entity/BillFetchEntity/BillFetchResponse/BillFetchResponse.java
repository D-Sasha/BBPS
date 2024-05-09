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
@XmlRootElement(name = "BillFetchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillFetchResponse {

    @XmlElement
    private Reason reason;

    @XmlElement
    private BillerResponse billerResponse;

}
