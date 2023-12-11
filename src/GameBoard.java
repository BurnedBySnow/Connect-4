import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameBoard extends JPanel {
    private final Chip[][] board;
    private final Chip[] currentColumn;
    private final JPanel boardPanel;
    private JPanel columnPanel;

    private static GameBoard instance;

    private GameBoard(){
        setLayout(new BorderLayout());
        columnPanel = new JPanel();
        columnPanel.setLayout(new GridLayout(1, 7));
        columnPanel.setPreferredSize(new Dimension(700, 100));
        columnPanel.setBackground(Color.white);
        boardPanel = new JPanel(new GridLayout(6,7));
        boardPanel.setPreferredSize(new Dimension(700, 700));
        boardPanel.setBackground(Color.BLUE);
        boardPanel.setBorder(new LineBorder(Color.black));
        board = new Chip[6][7];
        currentColumn = new Chip[7];

        addChips();

        this.add(columnPanel, BorderLayout.PAGE_START);
        this.add(boardPanel, BorderLayout.CENTER);
    };
    public static GameBoard getInstance(){
        if(instance == null){
            instance =  new GameBoard();
        }
        return instance;
    }

    private void addChips(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = new ColoredChip();
                ((JLabel) board[i][j]).setHorizontalAlignment(SwingConstants.CENTER);
                ((JLabel) board[i][j]).setVerticalAlignment(SwingConstants.CENTER);
                ((JLabel) board[i][j]).setBorder(new LineBorder(Color.black));
                boardPanel.add((Component) board[i][j]);
            }
        }

        for (int i = 0; i < 7; i++) {
            currentColumn[i] = new ColoredChip();
            columnPanel.add((Component) currentColumn[i]);
        }
        currentColumn[0].setColor(Color.RED);
    }
}
