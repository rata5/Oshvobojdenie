package GUIs;

import Logic.RandomCodeGenerator;
import utility.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class RandomCodeGUI extends JFrame {

    //Components
    //First GUI

    private final JTextField textField;
    protected final JLabel messageLabel;
    JFrame randomCodeFrame = new JFrame("Random Code Generator");
    JFrame votingFrame = new JFrame("Please Vote");

    //database

    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;

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

        //default properties ===============================================

        randomCodeFrame.setVisible(true);
        randomCodeFrame.setLocationRelativeTo(null);    //Center the Frame

        //actions ============================================

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String egn = textField.getText().trim();
                if (!egn.isEmpty()) {
                    String generatedCode = RandomCodeGenerator.generateRandomCode();
                    messageLabel.setText(generatedCode);
                    checkAndInsertData(egn, generatedCode);
                    VotingGUI voting = new VotingGUI();
                } else {
                    messageLabel.setText("Please input your EGN!");
                }
            }

            public void insertData(String egn, String generatedCode) {
                String encodedEgn = Base64.getEncoder().encodeToString(egn.getBytes());
                String sql = "INSERT INTO EGNRAN (EGN, CRYPTING) VALUES (?, ?)";
                Connection conn = DBConnection.getConnection();
                try {
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(1, encodedEgn);
                    preparedStatement.setString(2, generatedCode);
                    preparedStatement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            public boolean isRecordExists(String egn) {
                String encodedEgn = Base64.getEncoder().encodeToString(egn.getBytes());
                String sql = "SELECT 1 FROM EGNRAN WHERE EGN = ?";
                try (Connection conn = DBConnection.getConnection();
                     PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, encodedEgn);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        return resultSet.next();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return false;
            }

            public void checkAndInsertData(String egn, String code) {
                String encodedEgn = Base64.getEncoder().encodeToString(egn.getBytes());
                if (!isRecordExists(encodedEgn)) {
                    insertData(encodedEgn, code);
                } else {
                    messageLabel.setText("You have already voted!");
                }
            }
        });
    }
}