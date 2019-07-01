package views;

import controllers.MessageController;
import models.Message;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MessageTextView {
    private Message msgToDisplay;


    private static final MessageTextView INSTANCE = new MessageTextView();


    private MessageTextView(){}


    public MessageTextView(Message msgToDisplay) {
        this.msgToDisplay = msgToDisplay;
    }

    public String printMessages(){
        ArrayList<Message> msgs = MessageController.getINSTANCE().getMessageList();
        StringBuilder builder = new StringBuilder();
        for (Message msg : msgs){
            this.msgToDisplay = msg;
            builder.append(toString());
        }
        return builder.toString();
    }


    public static MessageTextView getINSTANCE() {
        return INSTANCE;
    }




    @Override public String toString() {
        Timestamp ts = msgToDisplay.getTimestamp();
        LocalDateTime date = ts.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formattedTime = date.format(formatter);

        String builder = "\n===============================================\n" +
                "From '" + msgToDisplay.getFromid()
                + "' to '" + msgToDisplay.getToid() + "'"
                + "\nSent: " + formattedTime
                + "\nMessage: " + msgToDisplay.getMessage() +
                "\n===============================================";
        return builder;
    } 
}