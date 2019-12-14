package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.example.demo.repository.IbkdataRepository;
import com.example.demo.entity.Ibkdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sqladmin.SQLAdminScopes;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    IbkdataRepository ibkdataRespository;

    @GetMapping("/test")
    public String test() {
        // return "Primer Aplicativo";
        RestTemplate rt = new RestTemplate();
        String result = rt.getForObject("https://ghibliapi.herokuapp.com/films", String.class);
        return result;
    }

    @GetMapping("/sql")
    public String sql() {

        GoogleCredentials credentials;

        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("C:/Users/B34300/Desktop/BPE/CIMA/aceleraton/cima/src/main/resources/chatbot-grupo-4-xulbcw-493a253d2ae7.json"));
            if (credentials.createScopedRequired()) {
                credentials = credentials.createScoped(Collections.singletonList("https://www.googleapis.com/auth/dialogflow"));
            }
            
            credentials.refreshIfExpired();
            AccessToken token = credentials.getAccessToken();
            return token.getTokenValue();
        } catch (IOException e) {
            return "Hubo un error";
        }

        

        /*
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:/Users/B34300/Desktop/BPE/CIMA/aceleraton/cima/src/main/resources/chatbot-grupo-4-xulbcw-493a253d2ae7.json");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //return ibkdataRespository.findAll();
        GoogleCredential credential = GoogleCredential.fromStream(fis).createScoped(Collections.singleton(SQLAdminScopes.SQLSERVICE_ADMIN));
        //.fromStream(new FileInputStream("/src/test/resources/chatbot-grupo-4-xulbcw-493a253d2ae7.json"))
        return credential;
        */

    }
}