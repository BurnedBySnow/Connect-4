import javax.swing.*;
import java.awt.*;

public class DiamondChip extends JLabel implements Chip{
    private Color c;

    public DiamondChip() {
        c = Color.WHITE;
        setForeground(Color.WHITE);
        setFont(new Font("Monsterrat", Font.PLAIN, 105));
        setText("⯁");
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
