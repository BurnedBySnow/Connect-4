import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    JPanel mainPanel = new JPanel(new CardLayout());
    JPanel gameBoard;
    JPanel startPanel;

    MainFrame(){
        setPreferredSize(new Dimension(700, 750));
        setResizable(false);
        pack();
        setTitle("Connect-4");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameBoard = GameBoard.getInstance();
        startPanel = new StartPanel();
        mainPanel.add(startPanel, "START_PANEL");
        mainPanel.add(gameBoard, "GAME_BOARD");

        setPanel("GAME_BOARD");

        add(mainPanel);

        this.addKeyListener(this);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void setPanel(String panel){
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, panel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        GameBoard.keySorter(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("released");
    }


}
