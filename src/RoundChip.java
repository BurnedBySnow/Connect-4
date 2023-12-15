import javax.swing.*;
import java.awt.*;

public class RoundChip extends JLabel implements Chip{
    private Color c;

    public RoundChip() {
        c = Color.WHITE;
        setForeground(Color.WHITE);
        setFont(new Font("Monsterrat", Font.PLAIN, 113));
        setText("⚫");
    };

    @Override
    public void setColor(Color color) {
        setForeground(color);
        c = color;
    }
    @Override
    public Color getColor() {
        return c;
    }
}
