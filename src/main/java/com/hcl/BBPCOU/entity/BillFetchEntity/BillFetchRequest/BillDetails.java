package com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest;

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
@XmlRootElement(name = "billDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class BillDetails {

    @XmlElement
    private int billerId;

}
