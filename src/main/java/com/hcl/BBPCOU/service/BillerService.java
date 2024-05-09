package com.hcl.BBPCOU.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hcl.BBPCOU.entity.BillFetchEntity.Biller;
import com.hcl.BBPCOU.entity.BillFetchEntity.Search;
import com.hcl.BBPCOU.entity.BillFetchEntity.BillFetchResponse.BillFetchResponse;
import com.hcl.BBPCOU.repository.BillerRepository;
import com.hcl.BBPCOU.xml.XmlParser;

@Service
public class BillerService {

    @Autowired
    private BillerRepository billerRepository;

    public String processBillerRequest(String json) {
        try {
            // Gson gson = new Gson();
            // Search request = gson.fromJson(json, Search.class);
            // String xml = XmlParser.serialize(request);
            // System.out.println(xml);
            // Search response = sendXmlRequestAndGetResponse(xml);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            String category = rootNode.get("utility").asText();

            List<Biller> billers = billerRepository.findByBillerCatagory(category);

            String responseJson = objectMapper.writeValueAsString(billers);

            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Search sendXmlRequestAndGetResponse(String xmlRequest) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://bbps.com/get/biller"))
                .header("Content-Type", "application/xml")
                .POST(HttpRequest.BodyPublishers.ofString(xmlRequest))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String xmlContent = response.body();
            return XmlParser.parse(xmlContent, Search.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
