package analogclockproject;

import javax.swing.JFrame;

/**
 *
 * @author ashu
 */
public class AnalogClockProject extends JFrame
{

    public static void main(String[] args) 
    {
       SetUp set = new SetUp("Analog Clock", 500);
       set.start();
    }
    
}