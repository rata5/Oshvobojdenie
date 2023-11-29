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
    //First GUI
    private final JTextField textField;
    private final JLabel messageLabel;

    JFrame randomCodeFrame = new JFrame("Random Code Generator");

    String[] parties = {"Select From Here","BSP","DPS","GERB","PP","DB","SDS"};

    JFrame votingFrame = new JFrame("Please Vote");

    //database
    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;
    User user = new User();


    public RandomCodeGUI() {

        //First GUI settings ============================================
        randomCodeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        randomCodeFrame.setSize(300, 150);
        textField = new JTextField(20);
        JButton button = new JButton("Get Code");
        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        randomCodeFrame.setLayout(new FlowLayout());

        JLabel topLabel = new JLabel("Enter your EGN:");

        //Adding the components ============================================
        JPanel upPanel = new JPanel(new GridLayout(1, 1));
        randomCodeFrame.add(upPanel);
        JPanel midPanel = new JPanel(new GridLayout(2, 2));
        randomCodeFrame.add(midPanel);
        JPanel lowPanel = new JPanel(new GridLayout(2, 2));
        randomCodeFrame.add(lowPanel);
        upPanel.add(topLabel);
        midPanel.add(textField);
        midPanel.add(messageLabel);
        lowPanel.add(button);

        //Secong GUI settings ==============================================
        votingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        votingFrame.setSize(800, 800);
        votingFrame.setLayout(new GridLayout(2, 1));
        JPanel midPanel2 = new JPanel(new GridLayout(2, 1));
        votingFrame.add(midPanel2);
        JPanel bottomPanel2 = new JPanel(new GridLayout(1, 1));
        votingFrame.add(bottomPanel2);
        JLabel voteLabel = new JLabel("Choose Your Vote");
        midPanel2.add(voteLabel);
        JComboBox<String> choisesBox = new JComboBox<>(parties);
        midPanel2.add(choisesBox);
        JButton buttonBox = new JButton("Confirm Vote");
        bottomPanel2.add(buttonBox);

        //default porperties ===============================================
        randomCodeFrame.setVisible(true);


        //actions ============================================
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                votingFrame.setVisible(true);
                String inputText = textField.getText().trim();
                boolean isGenerated = false;
                if (!inputText.isEmpty()) {

                    String generatedCode = RandomCodeGenerator.generateRandomCode();
                    isGenerated = true;
                    messageLabel.setText(generatedCode);
                    String sql = "INSERT INTO EGNOMER (EGN, CRYPTING) VALUES (?, ?) ";
                    Connection conn = DBConnection.getConnection();
                    try {
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, inputText);
                        preparedStatement.setString(2, generatedCode);
                        preparedStatement.executeUpdate();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                } else {

                    messageLabel.setText("Please input your EGN!");
                }
            }
        });

    }
}