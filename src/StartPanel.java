import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    JLabel titlePanel = new JLabel("Connect-4");
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

        add(titlePanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.setLayout(new GridLayout(1,3));
        centerPanel.add(changeButtonShape1);
        centerPanel.add(changeButtonShape2);
        centerPanel.add(changeButtonShape3);
        add(startGame, BorderLayout.PAGE_END);

        repaint();
        revalidate();
        setVisible(true);




    }
}
