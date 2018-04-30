package analogclockproject;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author ashu
 */
public class DrawClock extends JFrame
{
    private String title;
    private int size;
    private JFrame frame;
    public static Canvas canvas;
    
    public DrawClock(String title, int size)
    {
        this.title = title;
        this.size = size;
        
        createClock();
    }
    
    public void createClock()
    {
        frame = new JFrame(title);
        frame.setSize(size,size);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(size,size));
        canvas.setBackground(Color.lightGray);
        frame.add(canvas);
        frame.pack();
    }
}
