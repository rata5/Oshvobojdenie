package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ChoiceGUI {
    JFrame frame = new JFrame();
    JLabel choice = new JLabel("Choose option");

    JButton voteButton = new JButton("Vote");
    JButton checkVoteButton = new JButton("Check Vote");
    JPanel choicePanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    public ChoiceGUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,3));
        frame.setSize(300,150);
        frame.setLocationRelativeTo(null);


        choicePanel.add(choice);
        buttonPanel.add(voteButton);
        buttonPanel.add(checkVoteButton);

        frame.add(choicePanel);
        frame.add(buttonPanel);

        frame.setVisible(true);



        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomCodeGUI randomCodeGUI = new RandomCodeGUI();
            }
        });

        checkVoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VoteCheckGUI voteCheckGUI = new VoteCheckGUI();
            }
        });
    }
}
