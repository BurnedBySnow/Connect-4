import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener {
    JLabel title = new JLabel("Connect-4");
    JButton changeButtonShape1 = new JButton();
    JButton changeButtonShape2 = new JButton();
    JButton changeButtonShape3 = new JButton();
    JPanel centerPanel = new JPanel();
    JButton startGame = new JButton("Start Game");

    public StartPanel() {
        setLayout(new BorderLayout());
        changeButtonShape1.setText("⚫");
        changeButtonShape2.setText("⯁");
        changeButtonShape3.setText("🖤");

        add(title, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(1,3));
        centerPanel.add(changeButtonShape1);
        centerPanel.add(changeButtonShape2);
        centerPanel.add(changeButtonShape3);
        add(startGame, BorderLayout.PAGE_END);

        changeButtonShape1.addActionListener(this);
        changeButtonShape2.addActionListener(this);
        changeButtonShape3.addActionListener(this);
        startGame.addActionListener(this);

        repaint();
        revalidate();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();
        if(e.getSource() == startGame){
            mf.setPanel("GAME_BOARD");
        }
        if(e.getSource() == changeButtonShape1){
            mf.gameBoard.addChips(ChipType.ROUND);
        }
        if(e.getSource() == changeButtonShape2){
            mf.gameBoard.addChips(ChipType.DIAMOND);
        }
        if(e.getSource() == changeButtonShape3){
            mf.gameBoard.addChips(ChipType.HEART);
        }
    }
}
