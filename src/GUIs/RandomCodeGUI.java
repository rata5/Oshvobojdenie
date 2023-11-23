package GUIs;

import utility.DBConnection;
import utility.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Logic.RandomCodeGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RandomCodeGUI extends JFrame {


    //Components
    private JTextField textField;
    private JLabel messageLabel;
    private JButton button;
    private JLabel topLabel;
    private JPanel upPanel = new JPanel(new GridLayout(1,1));
    private JPanel midPanel = new JPanel(new GridLayout(2,2));
    private JPanel lowPanel = new JPanel(new GridLayout(2,2));

    JFrame randomCodeFrame = new JFrame("Random Code Generator");


    //database
    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;
    User user = new User();


    public RandomCodeGUI(){

        //GUI settings ============================================
        randomCodeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        randomCodeFrame.setSize(300, 150);
        textField = new JTextField(20);
        button = new JButton("Get Code");
        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        randomCodeFrame.setLayout(new FlowLayout());

        topLabel = new JLabel("Enter your EGN:");



        //Adding the components ============================================
        randomCodeFrame.add(upPanel);
        randomCodeFrame.add(midPanel);
        randomCodeFrame.add(lowPanel);

        upPanel.add(topLabel);
        midPanel.add(textField);
        midPanel.add(messageLabel);
        lowPanel.add(button);

//        randomCodeFrame.add(textField);
//        randomCodeFrame.add(button);
//        randomCodeFrame.add(messageLabel);



        //default properties ============================================
        randomCodeFrame.setVisible(true);



        //actions ============================================
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputText = textField.getText().trim();
                Boolean isGenerated = false;
                if (!inputText.isEmpty()) {

                    String generatedCode = RandomCodeGenerator.generateRandomCode();
                    isGenerated = true;
                    messageLabel.setText(generatedCode);

                    VotingGUI votingGUI = new VotingGUI();

                } else {

                    messageLabel.setText("Please input your EGN!");

                }


            }
        });

    }

    //Methods ============================================

    //dobavq koda i egnto kym bazata danni
    public void insertData(String egn, String code){
        String sql = "INSERT INTO INFO (EGN, CODE) VALUES (?, ?) ";
        Connection conn = DBConnection.getConnection();
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,egn);
            preparedStatement.setString(2,code);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
