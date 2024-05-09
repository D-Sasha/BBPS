package com.hcl.BBPCOU.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.hcl.BBPCOU.entity.BillFetchEntity.Bill;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest.BillDetails;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchRequest.Customer;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.BillerResponse;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.Reason;

import com.hcl.BBPCOU.entity.PaymentEntity.PaymentRequest.PaymentRequest;
import com.hcl.BBPCOU.entity.PaymentEntity.PaymentResponse.PaymentResponse;
import com.hcl.BBPCOU.repository.PaymentRepository;
import com.hcl.BBPCOU.xml.XmlParser;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void deleteBillByNumber(long billNumber) {
        paymentRepository.deleteByBillNumber(billNumber);
    }

    public String processPayBillRequest(String json) {
        try {
            Gson gson = new Gson();
            PaymentRequest request = gson.fromJson(json, PaymentRequest.class);

            String xml = XmlParser.serialize(request);
            System.out.println(xml);
            // PaymentResponse response = sendXmlRequestAndGetResponse(xml);

            BillDetails billDetails = request.getBillDetails();
            Customer customer = request.getCustomer();

            int billerId = billDetails.getBillerId();

            Long phoneNo = customer.getPhoneNo();
            Integer custId = customer.getId();

            Bill bill = findBillByBillerIdAndCustomerIdOrPhoneNo(billerId, custId, phoneNo);

            if (bill != null) {
                long billId = bill.getBillNumber();
                String response = createSuccessfulResponse(bill);

                //deleteBillByNumber(billId);

                return response;
            } else {
                return createFailedResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return createFailedResponse();
        }
    }

    public PaymentResponse sendXmlRequestAndGetResponse(String xmlRequest) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://bbps.com/pay/bill"))
                .header("Content-Type", "application/xml")
                .POST(HttpRequest.BodyPublishers.ofString(xmlRequest))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String xmlContent = response.body();
            return XmlParser.parse(xmlContent, PaymentResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bill findBillByBillerIdAndCustomerIdOrPhoneNo(int billerId, Integer custId, Long phoneNo) {
        Bill bill = null;

        if (custId != null) {
            bill = paymentRepository.findByBillerIdAndCustomerId(billerId, custId);
        }

        if (bill == null && phoneNo != null) {
            bill = paymentRepository.findByBillerIdAndPhoneNo(billerId, phoneNo);
        }
        return bill;
    }

    private String createSuccessfulResponse(Bill bill) {
        PaymentResponse response = new PaymentResponse();

        BillerResponse billerResponse = new BillerResponse();

        billerResponse.setAmount(bill.getAmount());
        billerResponse.setBillDate(bill.getBillDate());
        billerResponse.setBillNumber(bill.getBillNumber());
        billerResponse.setBillPeriod(bill.getBillPeriod());
        billerResponse.setCustomerName(bill.getCustomerName());
        billerResponse.setDueDate(bill.getDueDate());

        Reason reason = new Reason();
        reason.setApprovalNum("AB123456");
        reason.setResponseCode(200);
        reason.setReponseReason("Successful");
        reason.setComplianceRespCd("BFR001");
        reason.setComplianceReason("Valid");

        response.setReason(reason);
        response.setBillerResponse(billerResponse);

        Gson gson = new Gson();
        return gson.toJson(response);
    }

    private String createFailedResponse() {
        Reason reason = new Reason();
        reason.setApprovalNum("00000000");
        reason.setResponseCode(000);
        reason.setReponseReason("Failure");
        reason.setComplianceRespCd("000000");
        reason.setComplianceReason("Invalid");

        PaymentResponse failResponse = new PaymentResponse();
        failResponse.setReason(reason);

        Gson gson = new Gson();
        return gson.toJson(failResponse);
    }

}
