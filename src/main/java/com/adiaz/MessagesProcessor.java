package com.adiaz;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessagesProcessor {

    private List<MessageInterface> messagesList = new ArrayList<>();
    private SalesStock salesStock = new SalesStock();

    public String newMessage(String messageStr){
        String returnCode;
        if (messagesList.size()<50) {
            try {
                MessageInterface message = readMessage(messageStr);
                messagesList.add(message);
                message.process(salesStock);
                if (messagesList.size()%10 == 0) {
                    System.out.println(salesStock.generateLog());
                }
                returnCode = LocalDateTime.now().toString();
            } catch (Exception e) {
                returnCode = e.getMessage();
            }
        } else {
            System.out.println("System pause");
            for (MessageInterface messageInterface : messagesList) {
                if (messageInterface instanceof MessageType03) {
                    MessageType03 message = (MessageType03) messageInterface;
                    System.out.println(message.getOperation() + " - " + message.getPrice());
                }
            }
            returnCode = "STOP";
        }
        return returnCode;
    }

    private MessageInterface readMessage(String inputMessage) throws Exception {
        MessageInterface message;
        try {
            JsonParser parser = new JsonParser();
            Integer type = parser.parse(inputMessage).getAsJsonObject().get("type").getAsInt();
            Gson gson = new Gson();
            switch (type) {
                case 1:
                    message = gson.fromJson(inputMessage, MessageType01.class);
                    break;
                case 2:
                    message = gson.fromJson(inputMessage, MessageType02.class);
                    break;
                case 3:
                    message = gson.fromJson(inputMessage, MessageType03.class);
                    break;
                default:
                    throw new Exception("ErrorWrong format");
            }
        } catch (Exception e) {
            throw new Exception("ErrorWrong format");
        }
        return message;
    }

    public SalesStock getSalesStock() {
        return salesStock;
    }

    public List<MessageInterface> getMessagesList() {
        return messagesList;
    }
}
