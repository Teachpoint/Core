package com.teachpoint.Core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teachpoint.Core.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceMockRestServiceServerUnitTest {


    @Autowired
    private StudentService studentService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;


    private ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void init() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
public  void test () throws URISyntaxException {
//
//        mockRestServiceServer.expect(ExpectedCount.once(),
//                requestTo(new URI("http://TEACHPOINT-NOTIFICATION/api/v1/validateOtp/{studentId}"))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withStatus(HttpStatus.OK)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(mapper.writeValueAsString(emp))
//                );
    }



}
