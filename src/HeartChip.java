import javax.swing.*;
import java.awt.*;

public class HeartChip extends JLabel implements Chip{
    private Color c;

    public HeartChip() {
        c = Color.WHITE;
        setForeground(Color.WHITE);
        setFont(new Font("Monsterrat", Font.PLAIN, 90));
        setText("🖤");
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
