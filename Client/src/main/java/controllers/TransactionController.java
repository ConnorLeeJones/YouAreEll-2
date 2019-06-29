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
    private Response response;
    private Call call;
    private String formattedResponse;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public TransactionController(){}


    public TransactionController(String mainurl, String method, String jpayload){
        this.mainurl = mainurl;
        this.method = method;
        this.jpayload = jpayload;
        this.requestBody = RequestBody.create(JSON, this.jpayload);
        requestConstructor();
    }


    public void requestConstructor(){
        switch (this.method){
            case ("POST"):
                this.request = new Request.Builder().url(rootURL + this.mainurl).post(requestBody).build();
                break;
            case ("PUT"):
                this.request = new Request.Builder().url(rootURL + this.mainurl).put(requestBody).build();
                break;
            case ("GET"):
            default:
                this.request = new Request.Builder().url(rootURL + this.mainurl).build();
                break;
        }
        this.call = client.newCall(request);
    }


    public String executeResponse() {
        try {
            this.response = call.execute();
            this.responseString = response.body().string();
            //formatResponse();
            return responseString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }









//    public void formatResponse(){
//        switch (this.mainurl){
//            case ("/ids"):
//                this.responseString = IdTextView.getINSTANCE().printIds();
//                break;
//            default:
//                break;
//        }
//    }








    public String getResponseString() {
        return responseString;
    }







}
