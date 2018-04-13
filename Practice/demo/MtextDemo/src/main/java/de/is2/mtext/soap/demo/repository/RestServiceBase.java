package de.is2.mtext.soap.demo.repository;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class RestServiceBase {
    
    private RestTemplate restTemplate;
    
    public String request(String url) {
//        long startReadXMLTime = System.currentTimeMillis();
        String value = restTemplate.getForObject(url, String.class);
//        long endReadXMLTime = System.currentTimeMillis();
//        System.out.println("Spend time: " + (endReadXMLTime-startReadXMLTime) + " milliseconds");
        return value;
    }
    
    @PostConstruct
    public void init() {
        this.restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new HttpMessageConverter[]{new FormHttpMessageConverter(), new StringHttpMessageConverter()}));
    }
}
