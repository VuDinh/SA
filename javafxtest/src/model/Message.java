package model;


import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/1/13
 * Time: 2:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class Message implements Serializable,Comparable {
    private Account sender,receiver;
    private String content;
    private MessageStatus status;

    public Message(Account sender, Account receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Object o) {
        return content.compareTo(((Message)o).getContent());
    }
}
