package com.linxb.wechat.component.util;

import com.linxb.common.component.util.StringUtil;
import com.linxb.common.component.util.XmlUtil;
import com.linxb.wechat.component.constant.PublicAccountMessageType;
import com.linxb.wechat.component.constant.WechatMessageKey;
import com.linxb.wechat.component.dto.PublicAccountMessageDto;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Map;

public class PublicAccountMessageUtil {

    public static PublicAccountMessageDto xmlToMessageObject(String content) throws DocumentException {
        Map<String, String> map = XmlUtil.parseXml(content);
        String msgType = map.get(WechatMessageKey.MSG_TYPE);
        PublicAccountMessageType publicAccountMessageType = PublicAccountMessageType.valueOf(msgType);
        PublicAccountMessageDto publicAccountMessageDto = null;
        switch (publicAccountMessageType) {
            case text:
                publicAccountMessageDto = mapToTextMessageObject(map);
                break;
            default:
                break;
        }
        return publicAccountMessageDto;
    }

    public static PublicAccountMessageDto mapToTextMessageObject(Map<String, String> map) {
        PublicAccountMessageDto publicAccountMessageDto = new PublicAccountMessageDto();
        publicAccountMessageDto.setToUserName(map.get(WechatMessageKey.TO_USER_NAME));
        publicAccountMessageDto.setFromUserName(map.get(WechatMessageKey.FROM_USER_NAME));
        String createTime = map.get(WechatMessageKey.CREATE_TIME);
        publicAccountMessageDto.setCreateTime(StringUtil.parseLong(createTime));
        publicAccountMessageDto.setMsgType(PublicAccountMessageType.text);
        publicAccountMessageDto.setContent(map.get(WechatMessageKey.CONTENT));
        String msgId = map.get(WechatMessageKey.MSG_ID);
        publicAccountMessageDto.setMsgId(StringUtil.parseLong(msgId));
        return publicAccountMessageDto;
    }

    public static String messageObjectToXml(PublicAccountMessageDto publicAccountMessageDto) {
        PublicAccountMessageType msgType = publicAccountMessageDto.getMsgType();
        String xml = "";
        switch (msgType) {
            case text:
                xml = messageObjectToTextXml(publicAccountMessageDto);
                break;
            default:
                break;
        }
        return xml;
    }

    public static String messageObjectToTextXml(PublicAccountMessageDto publicAccountMessageDto) {
        Element xml = DocumentHelper.createElement(WechatMessageKey.XML);
        Element toUserName = xml.addElement(WechatMessageKey.TO_USER_NAME);
        Element fromUserName = xml.addElement(WechatMessageKey.FROM_USER_NAME);
        Element createTime = xml.addElement(WechatMessageKey.CREATE_TIME);
        Element msgType = xml.addElement(WechatMessageKey.MSG_TYPE);
        Element content = xml.addElement(WechatMessageKey.CONTENT);
        toUserName.setText(publicAccountMessageDto.getToUserName());
        fromUserName.setText(publicAccountMessageDto.getFromUserName());
        Long ct = publicAccountMessageDto.getCreateTime();
        createTime.setText(StringUtil.toString(ct));
        msgType.setText(PublicAccountMessageType.text.toString());
        content.setText(publicAccountMessageDto.getContent());

        String text = xml.asXML();
        return text;
    }

    public static String responseEncryptMessage(String toUser, String encryptMessage) {
        Element xml = DocumentHelper.createElement(WechatMessageKey.XML);
        Element toUserName = xml.addElement(WechatMessageKey.TO_USER_NAME);
        Element encrypt = xml.addElement(WechatMessageKey.ENCRYPT);
        toUserName.setText(toUser);
        encrypt.setText(encryptMessage);

        String text = xml.asXML();
        return text;
    }
}
