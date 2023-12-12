import javax.swing.*;
import java.awt.*;

public class ColoredChip extends JLabel implements Chip{
    Color c;

    public ColoredChip() {
        setForeground(Color.WHITE);
        setFont(new Font("Monsterrat", Font.PLAIN, 113));
        setText("⚫");
    };

    @Override
    public void setColor(Color color) {
        setForeground(color);
        c = color;
    }
    public Color getColor() {
        return c;
    }
}
