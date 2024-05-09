package com.hcl.BBPCOU.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hcl.BBPCOU.entity.BillFetchEntity.*;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest.BillDetails;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest.BillFetchRequest;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest.Customer;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.BillFetchResponse;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.BillerResponse;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.Reason;
import com.hcl.BBPCOU.repository.BillFetchRepository;
import com.hcl.BBPCOU.xml.XmlParser;

@Service
public class BillFetchService {

    @Autowired
    private BillFetchRepository billFetchRequestRepository;

    public String processBillFetchRequest(String json) {
        try {
            Gson gson = new Gson();
            BillFetchRequest request = gson.fromJson(json, BillFetchRequest.class);

            String xml = XmlParser.serialize(request);
            System.out.println(xml);
            // BillFetchResponse response = sendXmlRequestAndGetResponse(xml);

            BillDetails billDetails = request.getBillDetails();
            Customer customer = request.getCustomer();

            int billerId = billDetails.getBillerId();

            Long phoneNo = customer.getPhoneNo();
            Integer custId = customer.getId();

            Bill bill = findBillByBillerIdAndCustomerIdOrPhoneNo(billerId, custId, phoneNo);

            if (bill != null) {
                return createSuccessfulResponse(bill);
            } else {
                return createFailureResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return createFailureResponse();
        }
    }

    public BillFetchResponse sendXmlRequestAndGetResponse(String xmlRequest) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://bbps.com/get/bill"))
                .header("Content-Type", "application/xml")
                .POST(HttpRequest.BodyPublishers.ofString(xmlRequest))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String xmlContent = response.body();
            return XmlParser.parse(xmlContent, BillFetchResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bill findBillByBillerIdAndCustomerIdOrPhoneNo(int billerId, Integer custId, Long phoneNo) {
        Bill bill = null;

        if (custId != null) {
            bill = billFetchRequestRepository.findByBillerIdAndCustomerId(billerId, custId);
        }

        if (bill == null && phoneNo != null) {
            bill = billFetchRequestRepository.findByBillerIdAndPhoneNo(billerId, phoneNo);
        }
        return bill;
    }

    private String createSuccessfulResponse(Bill bill) {
        BillFetchResponse response = new BillFetchResponse();

        BillerResponse billerResponse = new BillerResponse();

        billerResponse.setAmount(bill.getAmount());
        billerResponse.setBillDate(bill.getBillDate());
        billerResponse.setBillNumber(bill.getBillNumber());
        billerResponse.setBillPeriod(bill.getBillPeriod());
        billerResponse.setCustomerName(bill.getCustomerName());
        billerResponse.setDueDate(bill.getDueDate());

        response.setBillerResponse(billerResponse);

        Reason reason = new Reason();

        reason.setApprovalNum("AB123456");
        reason.setResponseCode(200);
        reason.setReponseReason("Succcesful");
        reason.setComplianceRespCd("BFR001");
        reason.setComplianceReason("Valid");

        response.setReason(reason);

        Gson gson = new Gson();
        return gson.toJson(response);
    }

    private String createFailureResponse() {
        BillFetchResponse failResponse = new BillFetchResponse();

        Reason reason = new Reason();

        reason.setApprovalNum("00000000");
        reason.setResponseCode(000);
        reason.setReponseReason("Failure");
        reason.setComplianceRespCd("000000");
        reason.setComplianceReason("Invalid");

        failResponse.setReason(reason);

        Gson gson = new Gson();
        return gson.toJson(failResponse);
    }

}
