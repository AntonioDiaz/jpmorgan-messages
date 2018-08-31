package com.adiaz;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessagesProcessorTest {



    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void newMessageType1() throws Exception {
        MessagesProcessor messagesProcessor = new MessagesProcessor();
        messagesProcessor.newMessage(generateMessageType1("apple", 12.2D));
        Assert.assertEquals(1, messagesProcessor.getMessagesList().size());
    }

    @Test
    public void newMessageType2() throws Exception {
        MessagesProcessor messagesProcessor = new MessagesProcessor();
        messagesProcessor.newMessage(generateMessageType2("apple", 12.2D, 200));
        Assert.assertEquals(1, messagesProcessor.getMessagesList().size());
        MessageInterface messageInterface = messagesProcessor.getMessagesList().get(0);
        Assert.assertTrue(messageInterface instanceof  MessageType02);
    }


    private String generateMessageType1(String type, Double price) {
        Gson gson = new Gson();
        MessageType01 messageType01 = new MessageType01();
        messageType01.setType("1");
        messageType01.setName(type);
        messageType01.setPrice(price);
        return  gson.toJson(messageType01);
    }


    private String generateMessageType2(String type, Double price, Integer amount) {
        Gson gson = new Gson();
        MessageType02 messageType02 = new MessageType02();
        messageType02.setType("2");
        messageType02.setName(type);
        messageType02.setPrice(price);
        messageType02.setOccurrences(amount);
        return  gson.toJson(messageType02);
    }
}