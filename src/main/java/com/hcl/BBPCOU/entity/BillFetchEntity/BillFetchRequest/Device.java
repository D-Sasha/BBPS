package com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "device")
@XmlAccessorType(XmlAccessType.FIELD)
public class Device {

    @XmlElement
    @Id
    private int id;

    @XmlElement
    private long mobile;

    @XmlElement
    private String geocode;

    @XmlElement
    private String ip;

    @XmlElement
    private String initiatingChannel;

    @XmlElement
    private long terminalId;

    @XmlElement
    private String imei;

    @XmlElement
    private String ifsc;

    @XmlElement
    private String mac;

    @XmlElement
    private String os;

    @XmlElement
    private String app;

}
