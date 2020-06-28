package com.cusc.cuscai.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class AIIEService {

    public void getQAModels() {

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
            e.printStackTrace();
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
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject postResponse(String Url, String subUrl, JSONObject params) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        String url = Url + subUrl;
        try {
            HttpEntity<String> formEntity = new HttpEntity<>(params.toJSONString(), headers);
            ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, formEntity, JSONObject.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
