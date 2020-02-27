package com.linxb.wechat.gateway.controller;

import com.linxb.common.component.util.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicAccountControllerTest extends AbstractControllerTest {

    private String token = "hobby";

    @Test
    public void receive() throws Exception {
        String url = "/wechat/public-account/receive";

        String timestamp = StringUtil.toString(DateUtil.timestamp());
        String nonce = StringUtil.toString(RandomUtil.getRandomNumber());
        String echostr = RandomUtil.getRandomString(8);

        List<String> list = new ArrayList<>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        String appendStr = CollectionUtil.sortAndAppend(list);
        String signature = SecurityUtil.sha1(appendStr);

        Map<String, Object> map = new HashMap<>();
        map.put("signature", signature);
        map.put("timestamp", timestamp);
        map.put("nonce", nonce);
        map.put("echostr", echostr);

        sendUrlEncoded(url, map);
    }
}
