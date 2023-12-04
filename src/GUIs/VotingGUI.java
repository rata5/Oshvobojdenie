package GUIs;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utility.DBConnection;

public class VotingGUI extends JFrame {
    JFrame frame = new JFrame();
    JPanel upPanel = new JPanel(new GridLayout(4, 1));
    JPanel midPanel = new JPanel(new GridLayout(2, 2));
    JPanel lowPanel = new JPanel(new GridLayout(2, 2));

    JLabel label = new JLabel("Enter code before voting");


    JTextField textField = new JTextField();
    JButton button = new JButton();


    public VotingGUI() {

        //frame settings=================================================
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(3,1));
        frame.setLocationRelativeTo(null);



        //Frame elements==================================================
        JTextField votingCode  = new JTextField(20);
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
        upPanel.setLayout(new GridLayout(2,3));
        midPanel.setLayout(new GridLayout(3, 1));
        midPanel.setSize(500,300);
        lowPanel.setLayout(new FlowLayout());



        //Button Methods =============================================================


        //Checks the code form textfield with the generated code
        //TODO


            //Adds vote into table
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JRadioButton selectedRadioButton = getSelectedRadioButton() ;
                String vote = String.valueOf(group.getSelection());
                String sql = "INSERT INTO CODEVOTE (VOTE) VALUES (?) ";
                Connection conn = DBConnection.getConnection();
                try{
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setString(2,vote);
                    preparedStatement.executeUpdate();
                    } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            //stores the selection from the radio buttons
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
//
//            private String getCode(String code) {
//
//                Connection conn = DBConnection.getConnection();
//
//                String sql = "SELECT 1 FROM INFO WHERE CODE = ?";
//
//            }



            //updates the DB
            //TODO
           private void updateDatabase(String code, String vote){
                String update  = "UPDATE CODEVOTE SET VOTE = ? WHERE CODE = ?";

               Connection conn = DBConnection.getConnection();
               try{
                   PreparedStatement preparedStatement = conn.prepareStatement(update);
                   preparedStatement.setString(1,code);
                   preparedStatement.setString(2,vote);
                  int rowsAffected =  preparedStatement.executeUpdate();

                  if(rowsAffected > 0){
                      System.out.println("Your vote is saved");
                  }
                  else{
                      System.out.println("Failed to save vote");
                  }
               } catch (SQLException throwables) {
                   throwables.printStackTrace();
               }
           }


        });







        //adding components to frames


        //upPanel
        upPanel.add(label);
        upPanel.add(votingCode);



        //midPanel
        midPanel.add(choice2);
        midPanel.add(choice3);
        midPanel.add(choice1);


        //lowPanel
        lowPanel.add(submitButton);




        //adding components to frame
        frame.add(upPanel);
        frame.add(midPanel);
        frame.add(lowPanel);


        frame.setVisible(true);
    }


}