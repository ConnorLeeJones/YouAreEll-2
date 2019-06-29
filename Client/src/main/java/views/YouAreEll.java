package views;

import controllers.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class YouAreEll {

    private MessageController msgCtrl;
    private IdController idCtrl;
    private TransactionController transactionController;

    public YouAreEll (MessageController m, IdController j) {
        // used j because i seems awkward
        this.msgCtrl = m;
        this.idCtrl = j;
        this.transactionController = new TransactionController();
    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(MessageController.getINSTANCE(), IdController.getINSTANCE());
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids() {
        return MakeURLCall("/ids", "GET", "");
    }

    public String post_id(String jpayload) {
        return MakeURLCall("/ids", "POST", jpayload);
    }

    public String put_id(String jpayload) {
        return MakeURLCall("/ids", "PUT", jpayload);
    }




    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }

    public String get_your_messages(String mainurl) {
        return MakeURLCall(mainurl, "GET", "");
    }



    public String post_message(String mainUrl, String jpayload) {
        return MakeURLCall(mainUrl, "POST", jpayload);
    }




    public String MakeURLCall(String mainurl, String method, String jpayload) {
        this.transactionController = new TransactionController(mainurl, method, jpayload);
        return transactionController.executeResponse();
        //return "nada";
    }
}
