package models;

/* 
 * POJO for an Message object
 */
public class Message {
    private String timestamp;
    private String sequence;
    private String message;
    private String fromid;
    private String toid;

    public Message(){}

    public Message (String message, String fromid, String toid) {
        this.message = message;
        this.fromid = fromid;
        this.toid = toid;
        //this.timestamp = "_";
        this.sequence = "-";
    }


    public Message (String message, String fromid, String toid, String timestamp, String sequence) {
        this.message = message;
        this.fromid = fromid;
        this.toid = toid;
        //this.timestamp = "-";
        this.sequence = "-";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}