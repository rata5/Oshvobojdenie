package GUIs;

import javax.swing.*;
import java.awt.*;
import utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteCheckGUI extends JFrame {

    //Components

    private final JTextField textField;
    private final JLabel messageLabel;
    static JFrame checkVoteFrame = new JFrame("Check Your Vote");

    public VoteCheckGUI() {
        checkVoteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        checkVoteFrame.setSize(300, 150);
        textField = new JTextField(20);
        JButton button = new JButton("Check Vote");
        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        checkVoteFrame.setLayout(new FlowLayout());
        JLabel topLabel = new JLabel("Enter your Code:");

        //Adding the components ============================================

        JPanel upPanel = new JPanel(new GridLayout(1, 1));
        checkVoteFrame.add(upPanel);
        JPanel midPanel = new JPanel(new GridLayout(2, 2));
        checkVoteFrame.add(midPanel);
        JPanel lowPanel = new JPanel(new GridLayout(2, 2));
        checkVoteFrame.add(lowPanel);
        upPanel.add(topLabel);
        midPanel.add(textField);
        midPanel.add(messageLabel);
        lowPanel.add(button);

        //default properties ============================================

        checkVoteFrame.setVisible(true);
        button.addActionListener(e -> {
            String inputText = textField.getText().trim();
            if (!inputText.isEmpty()) {
                String vote = getVoteByCode(inputText);
                if (vote != null) {
                    messageLabel.setText("Your vote: " + vote);
                } else {
                    messageLabel.setText("No vote found for the entered code.");
                }
            } else {
                messageLabel.setText("Please input your Generated Code!");
            }
        });
    }

    private String getVoteByCode(String votingCode) {
        String query = "SELECT VOTE FROM VOTES WHERE CRYPTING = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, votingCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("VOTE");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

        //Comment
    }
}