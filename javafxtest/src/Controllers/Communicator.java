package Controllers;



import model.AccountSystem.Account;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
//refer to the lecture code
public class Communicator{

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Account account;
    public Communicator(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        this.socket = socket;
        this.ois = ois;
        this.oos = oos;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void write(Object obj) {
        try {
            oos.writeObject(obj);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Object read() {
        try {
            return ois.readObject();
        } catch (IOException ex) {
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

    public void close() {
        try {
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
