import javax.swing.*;
import java.awt.*;

public class EmptyChip extends JLabel implements Chip{
    private Color c;

    public EmptyChip() {
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
    public Color getColor() {
        return c;
    }
}
