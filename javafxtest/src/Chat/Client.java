package Chat;


import model.AccountSystem.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/20/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client extends JFrame implements Runnable {
    private Account me;
    private JButton send, close;
    private JTextField message;
    private JComboBox receiver;
    private JTextArea chatDialog;
    private Communicator server;
    private JLabel sender;
    private JRadioButton chatAll,chatTeam,df;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        initConn();
        initUI();

        new Thread(this).start();
    }

    public void initUI() {
        JPanel p = new JPanel();
        ButtonGroup btnGroup=new ButtonGroup();
        p.setLayout(new GridLayout(0, 2));
        chatAll=new JRadioButton("Chat All");
        chatTeam=new JRadioButton("Chat Team");
        df=new JRadioButton("Default");
        btnGroup.add(chatAll);
        btnGroup.add(chatTeam);
        btnGroup.add(df);
        p.add(chatAll);
        p.add(chatTeam);
        p.add(df);
        p.add(new JLabel("    "));
        p.add(new JLabel("Sender"));
        sender = new JLabel("Unknown");
        p.add(sender);
        p.add(new JLabel(("Receiver")));
        receiver = new JComboBox();
        p.add(receiver);
        p.add(new JLabel("Message:"));
        message = new JTextField();
        p.add(message);
        send = new JButton("Send");
        p.add(send);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                Message m=new Message(me, (Account)receiver.getSelectedItem(), message.getText());
                if(chatAll.isSelected()) m.setStatus(Status.all);
                else if(chatTeam.isSelected()) m.setStatus(Status.team);
                else m.setStatus(Status.def);

                server.write(m);
                chatDialog.append(me.getUsername() + ":" + message.getText() + "\n");
            }
        });
        chatDialog = new JTextArea(20, 20);
        setLayout(new BorderLayout());
        add(chatDialog, BorderLayout.NORTH);
        add(p,BorderLayout.SOUTH);
        setVisible(true);
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initConn() {
        try {
            Socket socket = new Socket("localhost", 2400);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ios = new ObjectInputStream(socket.getInputStream());
            server = new Communicator(socket, ios, oos);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
        Object obj = server.read();
        while (obj != null) {
            if (obj instanceof Account) {
                me = (Account) obj;
                sender.setText(me.getUsername());
            } else if (obj instanceof AccountList) {
                AccountList temp = (AccountList) obj;
                ArrayList<Account> list = temp.getList();
                for (Iterator i = list.iterator(); i.hasNext(); ) {
                    Account account = (Account) i.next();
                    receiver.addItem(account);
                }
            } else if (obj instanceof Message) {
                Message m = (Message) obj;
                chatDialog.append(m.getSender().getUsername() + ":" + m.getContent() + "\n");
            }
            obj = server.read();
        }
    }
}
