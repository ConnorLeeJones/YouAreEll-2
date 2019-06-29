package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import models.Id;
import models.Message;

public class MessageController {


    private ArrayList<Message> messageList;
    private HashSet<Message> messagesSeen;     // why a HashSet??
    private TransactionController transactionController;
    private ObjectMapper mapper = new ObjectMapper();

    private static final MessageController INSTANCE = new MessageController();

    private MessageController(){}




    public void updateMessages() {
        transactionController = new TransactionController("/messages", "GET", "");
        String jsonString = transactionController.executeResponse();
        TypeFactory typeFactory = mapper.getTypeFactory();
        ArrayList<Message> list = new ArrayList<>();
        try {
            list = mapper.readValue(jsonString, typeFactory.constructCollectionType(ArrayList.class, Message.class));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.messageList = list;
    }





    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }




    public List<Message> getMessageList() {
        updateMessages();
        return messageList;
    }

    public static MessageController getINSTANCE() {
        return INSTANCE;
    }
 
}