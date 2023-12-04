package GUIs;

import utility.DBConnection;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VotingGUI extends JFrame {
    JFrame frame = new JFrame();
    JPanel upPanel = new JPanel(new GridLayout(1, 1));
    JPanel midPanel = new JPanel(new GridLayout(4, 2));
    JPanel lowPanel = new JPanel(new GridLayout(1, 2));


    JTextField textField = new JTextField();
    JButton button = new JButton();


    public VotingGUI() {
        //frame settings
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(3,1));
        frame.setLocationRelativeTo(null);


        //Choices settings

        JRadioButton choice1 = new JRadioButton("Partiq 1");
        JRadioButton choice2 = new JRadioButton("Partiq 2");
        JRadioButton choice3 = new JRadioButton("Partiq 3");

        Font font = new Font("Arial",Font.PLAIN,20);
        choice1.setFont(font);

        choice1.setBorder(new LineBorder(Color.BLACK, 2));
        ButtonGroup group = new ButtonGroup();
        group.add(choice1);
        group.add(choice2);
        group.add(choice3);


        JButton submitButton = new JButton("Submit Vote");


        upPanel.setLayout(new GridLayout(1, 1));
        upPanel.setSize(500,10);
        midPanel.setLayout((new GridLayout(3, 1)));
        midPanel.setSize(500, 300);
        lowPanel.setLayout(new FlowLayout());


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JRadioButton selectedRadioButton = getSelectedRadioButton() ;
                String vote = String.valueOf(group.getSelection());
                String sql = "INSERT INTO VOTES (VOTE) VALUES (?) ";
                Connection conn = DBConnection.getConnection();
                try{
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(2,vote);
                    preparedStatement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            protected JRadioButton getSelectedRadioButton(){
                if(choice1.isSelected()){
                    return choice1;
                }
                else if(choice2.isSelected()){
                    return choice2;
                }
                else if (choice3.isSelected()){
                    return choice3;
                }
                else return null;
            }


            private String getCodeEnteredBefre(){
                return null;
            }


        });






        //adding components to frames

        //UpPanel
       upPanel.add(textField);


        //midPanel
        midPanel.add(choice1);
        midPanel.add(choice2);
        midPanel.add(choice3);


        //lowPanel
        lowPanel.add(submitButton);




        //adding components to frame
        frame.add(upPanel);

        frame.add(midPanel);

        frame.add(lowPanel);


        frame.setVisible(true);
    }


}