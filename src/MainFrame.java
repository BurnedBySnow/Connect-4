import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
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
    }

    public void setPanel(String panel){
        ((CardLayout) mainPanel.getLayout()).show(mainPanel, panel);
    }
}
