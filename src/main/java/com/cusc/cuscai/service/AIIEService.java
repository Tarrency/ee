package com.cusc.cuscai.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class AIIEService {

    public void getQAModels(){

    }

    public JSONObject getResponse(String Url, String subUrl) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Url + subUrl;
        try {
            ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(url, JSONObject.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public JSONObject getResponse(String Url, String subUrl, Map<String, Object> params) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Url + subUrl;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        params.forEach(builder::queryParam);
        url = builder.build().encode().toString();
        try {
            ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(url, JSONObject.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
