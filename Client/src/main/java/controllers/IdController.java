package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jdk.internal.org.objectweb.asm.TypeReference;
import models.Id;
import views.IdTextView;

public class IdController {
    Id myId;
    List<Id> ids = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();


    private static final IdController INSTANCE = new IdController();


    private IdController(){}


    public ArrayList<Id> getIdList(){
        return (ArrayList<Id>) ids;
    }



    public ArrayList<Id> getIds(String jsonString) {
        TypeFactory typeFactory = mapper.getTypeFactory();
        ArrayList<Id> list = new ArrayList<>();
        try {
            list = mapper.readValue(jsonString, typeFactory.constructCollectionType(ArrayList.class, Id.class));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.ids = list;
        return list;
    }


    public Id listContains(String gitHub){
        for (Id id : ids){
            if (id.getGithub().equals(gitHub)){
                return id;
            }
        }
        return null;
    }






    public Id postId(Id id) {
        return null;
    }

    public Id putId(Id id) {
        return null;
    }



    public static IdController getINSTANCE() {
        return INSTANCE;
    }


}