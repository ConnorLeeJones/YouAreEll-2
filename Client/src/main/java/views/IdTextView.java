package views;

import controllers.IdController;
import models.Id;

import java.util.ArrayList;

public class IdTextView {
    private Id idToDisplay;


    private static final IdTextView INSTANCE = new IdTextView();


    private IdTextView(){}

    public IdTextView(Id idToDisplay) {
        this.idToDisplay = idToDisplay;
    }


//    public String makeLookGood(){
//        StringBuilder builder = new StringBuilder();
//        builder.append("\n===============================================\n" +
//                "ID : " + idToDisplay.getUserid()
//                + "\nName: " + idToDisplay.getName()
//                + "\nGithubID: " + idToDisplay.getGithub() +
//                "\n===============================================");
//        return builder.toString();
//    }


    public String printIds(){
        ArrayList<Id> ids = IdController.getINSTANCE().getIdList();
        StringBuilder builder = new StringBuilder();
        for (Id id : ids){
            this.idToDisplay = id;
            builder.append(toString());
        }
        return builder.toString();
    }

    public static IdTextView getINSTANCE() {
        return INSTANCE;
    }



    @Override public String toString()
    {
        String builder = "\n===============================================\n" +
                "ID : " + idToDisplay.getUserid()
                + "\nName: " + idToDisplay.getName()
                + "\nGithubID: " + idToDisplay.getGithub() +
                "\n===============================================";
        return builder;
    } 
}