package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.net.httpserver.Headers;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;


public class App {


    public static void main(String[] args) throws JsonProcessingException {

        final String url = "http://91.241.64.178:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                url,
                String.class
        );

        String cookies = (responseEntity.getHeaders().get("Set-Cookie").get(0));
        String[] cookie = cookies.split(";");
        //System.out.println(cookies);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Cookie", cookie[0]);

        //httpHeaders.add("Cookie", cookie[0]);
        //httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        StringBuilder sb = new StringBuilder();
        //ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        User user1 = new User(3, "James", "Brown", 10);

        HttpEntity<User> httpEntity1 = new HttpEntity<User>(user1, httpHeaders);

        ResponseEntity<String> responseEntityPost = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity1,
                String.class
        );


        //sb.append(responseEntityPost.getBody());

        //System.out.println(sb);

    }
}
