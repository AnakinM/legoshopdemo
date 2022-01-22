package com.anikiej.legoshopdemo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSuccess_getEmptyTransaction() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/transaction/empty");
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                // comparison with .json() seems to be working way better, than with .string() if json response is expected
                .andExpect(content().json("{\"id\":null,\"time\":\"2022-01-01T00:00:00\",\"value\":0.0,\"currency\":\"GBP\",\"basket\":null,\"sent\":false,\"paid\":false,\"delivered\":false}"));
    }

    @Test
    void shouldReturnSuccess_getAllTransactions() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/transaction/all");
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnSuccess_getTransactionById() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/transaction/{id}", 1);
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void shouldReturnSuccess_applyDiscountInPercent() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/transaction/{id}/discount", 1).param("percent", "10");
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"time\":null,\"value\":0.0,\"currency\":null,\"basket\":null,\"paid\":false,\"sent\":false,\"delivered\":false}"));
    }

    @Test
    void shouldReturnSuccess_payForTransaction() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/transaction/{id}/pay", 1);
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"time\":null,\"value\":0.0,\"currency\":null,\"basket\":null,\"delivered\":false,\"paid\":false,\"sent\":false}"));
    }

    @Test
    void shouldReturnSuccess_sendTransaction() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/transaction/{id}/send", 1);
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":null,\"time\":null,\"value\":0.0,\"currency\":null,\"basket\":null,\"delivered\":false,\"paid\":false,\"sent\":false}"));
    }

    @Test
    void shouldReturnSuccess_isTransactionDelivered() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/transaction/{id}/isdelivered", 1);
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void shouldReturnSuccess_transactionExistsById() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/transaction/{id}/isdelivered", 1);
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

}
