import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    JPanel mainPanel;
    GameBoard gameBoard;
    StartPanel startPanel;
    CardLayout cardLayout;
    private static MainFrame instance;

    private MainFrame(){
        setPreferredSize(new Dimension(700, 750));
        setResizable(false);
        pack();
        setTitle("Connect-4");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        gameBoard = new GameBoard();
        startPanel = new StartPanel();
        mainPanel.add(startPanel, "START_PANEL");
        mainPanel.add(gameBoard, "GAME_BOARD");

        setPanel("START_PANEL");

        add(mainPanel);

        this.addKeyListener(this);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
        }
        return instance;
    }

    public void setPanel(String panel){
        cardLayout.show(mainPanel, panel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gameBoard.keySorter(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
