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
@XmlRootElement(name = "reason")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reason {

    @XmlElement
    private String approvalNum;

    @XmlElement
    private int responseCode;

    @XmlElement
    private String reponseReason;

    @XmlElement
    private String complianceRespCd;

    @XmlElement
    private String complianceReason;
}
