package controllers;

import models.Id;
import okhttp3.*;
import views.IdTextView;

import java.io.IOException;
import java.util.ArrayList;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient client = new OkHttpClient();
    private Request request;
    private String mainurl;
    private String method;
    private String jpayload;
    private String responseString;
    private RequestBody requestBody;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public TransactionController(){}


    public TransactionController(String mainurl, String method, String jpayload){
        this.mainurl = mainurl;
        this.method = method;
        this.jpayload = jpayload;
        responseMaker();
    }


    public void responseMaker() {
        try {
            this.request = new Request.Builder().url(rootURL + this.mainurl).build();
            Call call = client.newCall(request);
            Response response = call.execute();
            this.responseString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public String doAction(){
        if (this.method.equals("GET") && this.mainurl.equals("/ids")) {
            return getIdList();
        } else if (this.method.equals("POST") && this.mainurl.equals("/ids")){
            return postID();
        } else if (this.method.equals("PUT") && this.mainurl.equals("/ids")){
            return putID();




        } else {
            return responseString;
        }
    }


    public String getIdList(){
        String results;
        IdController controller = IdController.getINSTANCE();
        ArrayList<Id> ids = controller.getIds(this.responseString);
        IdTextView idTextView = new IdTextView();
        results = idTextView.printIds(ids);
        return this.responseString;
    }



    public String postID() {
        try {
            this.requestBody = RequestBody.create(JSON, this.jpayload);
            this.request = new Request.Builder().url(rootURL + this.mainurl).post(requestBody).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String putID() {
        try {
            this.requestBody = RequestBody.create(JSON, this.jpayload);
            this.request = new Request.Builder().url(rootURL + this.mainurl).put(requestBody).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }







    public String getResponseString() {
        return responseString;
    }







}
