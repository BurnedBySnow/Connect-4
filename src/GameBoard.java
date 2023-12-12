import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameBoard extends JPanel {
    private final Chip[][] board;
    private final Chip[] currentColumn;
    private final JPanel boardPanel;
    private JPanel columnPanel;
    private Color currentPlayerColor = Color.RED;
    private static GameBoard instance;
    int currentPlace = 0;
    boolean playerTurn = true;

    private GameBoard() {
        setLayout(new BorderLayout());
        columnPanel = new JPanel();
        columnPanel.setLayout(new GridLayout(1, 7));
        columnPanel.setPreferredSize(new Dimension(700, 100));
        columnPanel.setBackground(Color.white);
        boardPanel = new JPanel(new GridLayout(6, 7));
        boardPanel.setPreferredSize(new Dimension(700, 700));
        boardPanel.setBackground(Color.BLUE);
        boardPanel.setBorder(new LineBorder(Color.black));
        board = new Chip[6][7];
        currentColumn = new Chip[7];

        addChips();

        this.add(columnPanel, BorderLayout.PAGE_START);
        this.add(boardPanel, BorderLayout.CENTER);

    }

    public static GameBoard getInstance() {
        if (instance == null) {
            instance = new GameBoard();
        }
        return instance;
    }

    private void addChips() {
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
        currentColumn[currentPlace].setColor(Color.RED);

    }

    public static void keySorter(KeyEvent e) {
        int key = e.getKeyCode();


        if (key == KeyEvent.VK_RIGHT) {
            getInstance().rightKey();
        }
        if (key == KeyEvent.VK_LEFT) {
            getInstance().leftKey();
        }
        if (key == KeyEvent.VK_DOWN) {
            getInstance().playChip();
        }
    }

    private void rightKey() {
        if (currentPlace < 6) {
            currentColumn[currentPlace].setColor(Color.WHITE);
            currentPlace++;
            currentColumn[currentPlace].setColor(currentPlayerColor);
            repaint();
            revalidate();
        }
    }
    private void leftKey() {
        if (currentPlace > 0) {
            currentColumn[currentPlace].setColor(Color.WHITE);
            currentPlace--;
            currentColumn[currentPlace].setColor(currentPlayerColor);
            repaint();
            revalidate();
        }
    }
    private void playChip() {
        int i = 5;
        boolean endOFColumn = true;

        while (true) {
            for (int row = 0; row < 6; row++) {
                if (board[row][currentPlace].getColor() == null) {
                    endOFColumn = false;
                    break;
                }
            }

            if (board[i][currentPlace].getColor() == null) {
                board[i][currentPlace].setColor(currentPlayerColor);
                if (checkWin())
                    showResult(currentPlayerColor);
                break;
            }
            else if (i == 0) {
                System.out.println("The column is full");
                break;
            }
            else {
                i--;
            }
        }
        if (!endOFColumn) {
            if (currentPlayerColor == Color.RED) {
                currentPlayerColor = Color.GREEN;
                playerTurn = false;
            } else {
                currentPlayerColor = Color.RED;
                playerTurn = true;
            }
            currentColumn[currentPlace].setColor(currentPlayerColor);
        }
    }

    private boolean checkWin() {
        boolean win = false;

        //Horizontal
        for (int i = 0; i < 6 && !win; i++) {
            for (int j = 0; j < 4 && !win; j++) {
                Color c = board[i][j].getColor();
                for (int k = j + 1; k < j + 4; k++) {
                    if (board[i][k].getColor() == c && c != null)
                        win = true;
                    else {
                        win = false;
                        break;
                    }
                }
            }
        }
        //Vertical
        for (int i = 0; i < 7 && !win; i++) {
            for (int j = 0; j < 3 && !win; j++) {
                Color c = board[j][i].getColor();
                for (int k = j + 1; k < j + 4; k++) {
                    if (board[k][i].getColor() == c && c != null)
                        win = true;
                    else {
                        win = false;
                        break;
                    }
                }
            }
        }
        //Diagonal (\)
        for (int i = 0; i < 3 && !win; i++) {
            for (int j = 0; j < 4 && !win; j++) {
                Color c = board[i][j].getColor();
                for (int k = 1; k < 4; k++) {
                    if (board[i + k][j + k].getColor() == c && c != null)
                        win = true;
                    else {
                        win = false;
                        break;
                    }
                }
            }
        }
        //Diagonal (/)
        for (int i = 0; i < 3 && !win; i++) {
            for (int j = 3; j < 7 && !win; j++) {
                Color c = board[i][j].getColor();
                for (int k = 1; k < 4; k++) {
                    if (board[i + k][j - k].getColor() == c && c != null)
                        win = true;
                    else {
                        win = false;
                        break;
                    }
                }
            }
        }

        return win;
    }
    private void showResult(Color currentPlayerColor) {
        String winner;
        if (playerTurn)
            winner = "Player 1";
        else
            winner = "player 2";

        System.out.println("The winner is " + winner);

    }
}
