package Ingame;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 3/22/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChatPanel extends JPanel {

    private TextArea chatTxt = new TextArea();
    private TextField chatField = new TextField();
    private JComboBox mode = new JComboBox(new String[]{"Team","Alll"});
    public ChatPanel(){
        setLayout(new BorderLayout());
        JPanel north = new JPanel();
        this.add(north);
        this.add(chatTxt);
        north.add(mode);
        north.add(Box.createHorizontalStrut(20));
        north.add(chatField);
        this.setPreferredSize(new Dimension(250,150));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }
}
