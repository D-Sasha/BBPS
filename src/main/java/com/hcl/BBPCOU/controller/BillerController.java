package com.hcl.BBPCOU.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.hcl.BBPCOU.service.BillerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/bbps")
public class BillerController {

    @Autowired
    private BillerService billerService;

    @PostMapping(value = "/biller/fetch/request", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleBillerRequest(@RequestBody String json) {
        try {

            if (json != null) {
                String respose = billerService.processBillerRequest(json);

                if (respose != null) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(respose);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
