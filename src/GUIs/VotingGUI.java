package GUIs;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import utility.DBConnection;

public class VotingGUI extends JFrame {
    JFrame frame = new JFrame();
    JPanel upPanel = new JPanel(new GridLayout(4, 1));
    JPanel midPanel = new JPanel(new GridLayout(2, 2));
    JPanel lowPanel = new JPanel(new GridLayout(2, 2));
    JLabel label = new JLabel("Enter code before voting");
    JLabel codeLabel = new JLabel();
    JTextField textField = new JTextField();
    JButton button = new JButton();

    protected JRadioButton getSelectedRadioButton(ButtonGroup group) {
        Enumeration<AbstractButton> buttons = group.getElements();
        while (buttons.hasMoreElements()) {
            JRadioButton button = (JRadioButton) buttons.nextElement();
            if (button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    public VotingGUI() {

        //frame settings=================================================

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(3, 1));
        frame.setLocationRelativeTo(null);

        //Frame elements==================================================

        JButton submitButton = new JButton("Submit Vote");
        label.setHorizontalAlignment(JLabel.CENTER);

        //Choices settings

        JRadioButton choice1 = new JRadioButton("Partiq 1");
        JRadioButton choice2 = new JRadioButton("Partiq 2");
        JRadioButton choice3 = new JRadioButton("Partiq 3");
        ButtonGroup group = new ButtonGroup();
        group.add(choice1);
        group.add(choice2);
        group.add(choice3);

        //Panels settings

        upPanel.setLayout(new GridLayout(2, 3));
        midPanel.setLayout(new GridLayout(3, 1));
        midPanel.setSize(500, 300);
        lowPanel.setLayout(new FlowLayout());


        //Button Methods =============================================================
        //Adds vote into table

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                VoteCheckGUI checkVote = new VoteCheckGUI();
                String votingCode = textField.getText();
                JRadioButton selectedRadioButton = getSelectedRadioButton(group);
                saveVoteToDB(selectedRadioButton, votingCode);
            }
        });

        //adding components to frames
        //upPanel

        upPanel.add(label);
        upPanel.add(textField);

        //midPanel

        midPanel.add(choice2);
        midPanel.add(choice3);
        midPanel.add(choice1);

        //lowPanel

        lowPanel.setLayout(new FlowLayout());
        lowPanel.add(submitButton);
        lowPanel.add(codeLabel);

        //adding components to frame

        frame.add(upPanel);
        frame.add(midPanel);
        frame.add(lowPanel);
        frame.setVisible(true);
    }

    private void saveVoteToDB(JRadioButton selectedRadioButton, String votingCode) {
        if (!isCodeValidInDatabase(votingCode)) {
            codeLabel.setText("Invalid Code, please try again");
            return;
        }
        if (isCodeAlreadyVoted(votingCode)) {
            codeLabel.setText("You have already voted with this code.");
            return;
        }
        String vote = selectedRadioButton.getText().trim();
        String sql = "INSERT INTO VOTES (VOTE, CRYPTING) VALUES (?, ?)";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, vote);
            preparedStatement.setString(2, votingCode);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isCodeValidInDatabase(String votingCode) {
        String query = "SELECT * FROM EGNRAN WHERE CRYPTING = ?";
        Connection conn = DBConnection.getConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, votingCode);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to check if the code is present in the VOTES table

    private boolean isCodeAlreadyVoted(String votingCode) {
        String query = "SELECT * FROM VOTES WHERE CRYPTING = ?";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, votingCode);
            return preparedStatement.executeQuery().next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}