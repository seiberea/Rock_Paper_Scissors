import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    JPanel mainPnl;
    JPanel titlePnl;
    JPanel decidePnl;
    JPanel statsPnl;
    JPanel winnerPnl;

    JLabel titleLbl;

    JButton rockBtn;
    JButton paperBtn;
    JButton scissorsBtn;
    JButton quitBtn;
    ImageIcon rockImg;
    ImageIcon paperImg;
    ImageIcon scissorsImg;

    JLabel playerWinsL;
    JLabel computerWinsL;
    JLabel tiesL;
    JTextField statsTF;
    int playerWins;
    int computerWins;
    int ties;

    JTextArea displayTA;
    JScrollPane scroller;

    Random rnd = new Random();

    public RockPaperScissorsFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(4, 1));

        createTitlePnl();
        mainPnl.add(titlePnl);

        createDecidePnl();
        mainPnl.add(decidePnl);

        createStatsPnl();
        mainPnl.add(statsPnl);

        createWinnerPnl();
        mainPnl.add(winnerPnl);

        add(mainPnl);
        setSize(700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void createTitlePnl() {
        titlePnl = new JPanel();

        titleLbl = new JLabel("Welcome to Rock Paper Scissors!");

        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
    }


    private void createDecidePnl() {
        decidePnl = new JPanel();
        decidePnl.setLayout(new GridLayout(1, 4));
        decidePnl.setBorder(new TitledBorder(new EtchedBorder(), "Pick your move!"));

        rockImg = new ImageIcon("src/Rock.png");
        paperImg = new ImageIcon("src/Paper.png");
        scissorsImg = new ImageIcon("src/Scissors.png");

        rockBtn = new JButton(rockImg);
        paperBtn = new JButton(paperImg);
        scissorsBtn = new JButton(scissorsImg);
        quitBtn = new JButton("Quit Game");

        decidePnl.add(rockBtn);
        decidePnl.add(paperBtn);
        decidePnl.add(scissorsBtn);
        decidePnl.add(quitBtn);

        rockBtn.addActionListener((ActionEvent ae) -> {
            displayTA.append("You played Rock!\n");
            play(0);
        });

        paperBtn.addActionListener((ActionEvent ae) -> {
            displayTA.append("You played Paper!\n");
            play(1);
        });

        scissorsBtn.addActionListener((ActionEvent ae) -> {
            displayTA.append("You played Scissors!\n");
            play(2);
        });

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
    }


    private void createStatsPnl() {
        statsPnl = new JPanel();

        playerWinsL = new JLabel("Players Wins: 0");
        computerWinsL = new JLabel("Computers Wins: 0");
        tiesL = new JLabel("Ties: 0");

        statsPnl.add(playerWinsL);
        statsPnl.add(computerWinsL);
        statsPnl.add(tiesL);

        statsTF = new JTextField(15);
    }


    private void createWinnerPnl() {
        winnerPnl = new JPanel();
        displayTA = new JTextArea(10, 35);
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        winnerPnl.add(scroller);
    }


    private void play(int playerMove) {
        int computerMove = rnd.nextInt(3);
        /*
        Rock = 0
        Paper = 1
        Scissors = 2
         */

        if (playerMove == computerMove) {
            if (playerMove == 0) {
                displayTA.append("Computer played Rock!\n It's a tie!\n");
            }
            else if (playerMove ==1) {
                displayTA.append("Computer played Paper!\n It's a tie!\n");
            }
            else {
                displayTA.append("Computer played Scissors!\n It's a tie!\n");
            }
            ties++;
            tiesL.setText("Ties: " + ties);
        }
        else if (playerMove == 1 && computerMove == 0) {
            displayTA.append("Computer played Rock!\n Paper covers Rock (Player Wins)\n");
            playerWins++;
        }
        else if (playerMove == 0 && computerMove == 1) {
            displayTA.append("Computer played Paper!\n Paper covers Rocker (Computer Wins)\n");
            computerWins++;
        }
        else if (playerMove == 2 && computerMove == 0) {
            displayTA.append("Computer played Rock!\n Rock breaks Scissors (Computer Wins)\n");
            computerWins++;;
        }
        else if (playerMove == 0 && computerMove == 2) {
            displayTA.append("Computer played Scissors!\n Rock breaks Scissors (Player Wins)\n");
            playerWins++;
        }
        else if (playerMove == 2 & computerMove == 1) {
            displayTA.append("Computer played Paper!\n Scissors cuts Paper (Player Wins)\n");
            playerWins++;
        }
        else if (playerMove == 1 && computerMove == 2) {
            displayTA.append("Computer played Scissors!\n Scissors cuts Paper (Computer Wins)\n");
            computerWins++;
        }
        playerWinsL.setText("Player Wins: " + playerWins);
        computerWinsL.setText("Computer Wins: " + computerWins);
    }
}