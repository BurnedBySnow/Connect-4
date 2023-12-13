import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameBoard extends JPanel {
    private final Chip[][] board;
    private final Chip[] currentColumn;
    private final JPanel boardPanel;
    private JPanel columnPanel;
    private Color currentPlayer = Color.RED;
    private Color player1Color = Color.RED;
    private Color player2Color = Color.GREEN;
    private JLabel playerTurn;
    private static GameBoard instance;
    int currentPlace = 0;
    boolean gameFinished = false;

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

        playerTurn = new JLabel("Player 1's turn");
        playerTurn.setHorizontalAlignment(SwingConstants.CENTER);
        playerTurn.setPreferredSize(new Dimension(700, 50));
        playerTurn.setFont(new Font("Fantasy", Font.BOLD, 20));
        this.add(playerTurn, BorderLayout.SOUTH);

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
                board[i][j] = new EmptyChip();
                ((JLabel) board[i][j]).setHorizontalAlignment(SwingConstants.CENTER);
                ((JLabel) board[i][j]).setVerticalAlignment(SwingConstants.CENTER);
                ((JLabel) board[i][j]).setBorder(new LineBorder(Color.black));
                boardPanel.add((Component) board[i][j]);
            }
        }

        for (int i = 0; i < 7; i++) {
            currentColumn[i] = new EmptyChip();
            columnPanel.add((Component) currentColumn[i]);
        }
        currentColumn[0].setColor(player1Color);
    }

    public void keySorter(KeyEvent e) {
        int key = e.getKeyCode();
        GameBoard gB = getInstance();


        if (key == KeyEvent.VK_RIGHT) {
            gB.rightKey();
        }
        if (key == KeyEvent.VK_LEFT) {
            gB.leftKey();
        }
        if (key == KeyEvent.VK_DOWN && !getInstance().gameFinished) {
            gB.playChip();
        }
        if (key == KeyEvent.VK_ENTER && getInstance().gameFinished) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    gB.board[i][j].setColor(Color.white);
                }
            }
            gB.gameFinished = false;
            gB.currentPlayer = player1Color;
            gB.playerTurn.setText("Player 1's turn");
            gB.currentColumn[gB.currentPlace].setColor(player1Color);


        }
    }

    private void rightKey() {
        if (currentPlace < 6) {
            currentColumn[currentPlace].setColor(Color.WHITE);
            currentPlace++;
            currentColumn[currentPlace].setColor(currentPlayer);
            repaint();
            revalidate();
        }
    }

    private void leftKey() {
        if (currentPlace > 0) {
            currentColumn[currentPlace].setColor(Color.WHITE);
            currentPlace--;
            currentColumn[currentPlace].setColor(currentPlayer);
            repaint();
            revalidate();
        }
    }

    private void playChip() {
        int i = 5;
        boolean endOFColumn = true;

        while (true) {
            for (int row = 0; row < 6; row++) {
                if (board[row][currentPlace].getColor() == Color.WHITE) {
                    endOFColumn = false;
                    break;
                }
            }
            if (board[i][currentPlace].getColor() == Color.WHITE) {
                board[i][currentPlace].setColor(currentPlayer);
                if (checkWin())
                    showResult(currentPlayer);
                break;
            } else if (i == 0) {
                System.out.println("The column is full");
                break;
            } else {
                i--;
            }
        }
        if (!endOFColumn && !gameFinished) {
            if (currentPlayer == player1Color) {
                playerTurn.setText("Player 2's turn");
                currentPlayer = player2Color;
            } else {
                playerTurn.setText("Player 1's turn");
                currentPlayer = player1Color;
            }
            currentColumn[currentPlace].setColor(currentPlayer);
        }
    }

    private boolean checkWin() {
        boolean win = false;

        //Horizontal
        for (int i = 0; i < 6 && !win; i++) {
            for (int j = 0; j < 4 && !win; j++) {
                Color c = board[i][j].getColor();
                for (int k = j + 1; k < j + 4; k++) {
                    if (board[i][k].getColor() == c && c != null && c != Color.white)
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
                    if (board[k][i].getColor() == c && c != null && c != Color.white)
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
                    if (board[i + k][j + k].getColor() == c && c != null && c != Color.white)
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
                    if (board[i + k][j - k].getColor() == c && c != null && c != Color.white)
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
        if (currentPlayerColor == Color.RED) {
            winner = "Player 1";
        } else {
            winner = "Player 2";
        }
        playerTurn.setText(winner + " won!\n Press ENTER to play again.");
        gameFinished = true;
    }
}
