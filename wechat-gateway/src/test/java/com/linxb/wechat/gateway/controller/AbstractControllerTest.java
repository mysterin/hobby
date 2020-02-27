package com.linxb.wechat.gateway.controller;

import com.linxb.common.component.util.CollectionUtil;
import com.linxb.common.component.util.JsonUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractControllerTest {

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    public void sendUrlEncoded(String url, Map<String, Object> map) throws Exception {
        RequestBuilder requestBuilder = urlEncodedRequestBuilder(url, map);
        assetResult(requestBuilder);
    }

    public void sendJson(String url, Map<String, Object> map) throws Exception {
        RequestBuilder requestBuilder = jsonRequestBuilder(url, map);
        assetResult(requestBuilder);
    }

    public RequestBuilder urlEncodedRequestBuilder(String url, Map<String, Object> map) {
        String params = CollectionUtil.formParams(map);
        return MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(params)
                .accept(MediaType.ALL);
    }

    public RequestBuilder jsonRequestBuilder(String url, Map<String, Object> map) {
        String params = JsonUtil.toJsonString(map);
        return MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(params)
                .accept(MediaType.APPLICATION_JSON);
    }

    public void assetResult(RequestBuilder requestBuilder) throws Exception {
        ResultActions resultActions =mockMvc.perform(requestBuilder);
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        resultActions.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
