package com.hcl.BBPCOU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hcl.BBPCOU.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/bbps")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/bills/payment/request", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handlePayBill(@RequestBody String json) {
        try {
            if (json != null) {
                String jsonResponse = paymentService.processPayBillRequest(json);

                if (jsonResponse != null) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(jsonResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
