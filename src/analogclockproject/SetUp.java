package analogclockproject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import analogclockproject.DrawClock;


public class SetUp implements Runnable
{
    private String title;
    private int size;
    private DrawClock drawclock;
    private Thread thread;
    
    private BufferStrategy buffer;
    private Graphics2D g;
    
    public SetUp(String title,int size)
    {
        this.title = title;
        this.size = size;
    }
    
    //thread 
    public void init()
    {
        drawclock = new DrawClock(title,size);
    }
    
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop()
    {
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SetUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run()
    {
       init(); 
       
       while(true)
       {
           draw();
       }
    }

    //thread Over
    public void draw()
    {
        buffer = DrawClock.canvas.getBufferStrategy();
        
        if(buffer == null)
        {
            DrawClock.canvas.createBufferStrategy(3);
            return;
        }
        
        int center = size/2;
        int angleX, angleY;
        int angleW, angleH;
        int radius;
        double position;
        double time = System.currentTimeMillis();
        
        g = (Graphics2D) buffer.getDrawGraphics();
        g.clearRect(0,0,size,size);
        
        //draw
        
        g.setColor(Color.black);
        g.fillOval(10, 10, size-20, size-20);
        g.setColor(Color.white);
        g.fillOval(20, 20, size-40, size-40);
        
        //draw numbers
        for(int i=1; i<=12; i++)
        {
            position = i/12.0*Math.PI*2;
            radius = center-50;
            
            angleX = (int)((center) + (Math.sin(position)*radius));
            angleY = (int)((center) - (Math.cos(position)*radius));
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("arial",Font.BOLD,25));
            String a = Integer.toString(i);
            g.drawString(a, angleX, angleY);
        }
        
        //Ticks
        for(int i=1;i<=60;i++)
        { 
            position = i/60.0*Math.PI*2;
            radius = center-10;
            angleX = (int)((center)+Math.sin(position)*radius);
            angleY = (int)((center)-Math.cos(position)*radius);
            
            if(i%15==0)
            {
                radius = center-50;
            }
            else
            {
                radius = center-30;
            }
               
            angleW = (int)((center) + Math.sin(position)*radius);
            angleH = (int)((center) - Math.cos(position)*radius);
            
            g.setColor(Color.black);
            g.drawLine(angleW, angleH,angleX, angleY);
        }
        
        //Hour Hand
        radius = center-130;
        time = System.currentTimeMillis()/(60*60*12*1000.0)*Math.PI*2;
        angleX = (int)((center) + Math.sin(time)*radius);
        angleY = (int)((center) - Math.cos(time)*radius);
        
        g.setColor(Color.blue);
        g.setStroke(new BasicStroke(10));
        g.drawLine(center, center, angleX, angleY);
        g.setStroke(new BasicStroke(0));
       
         //Minute Hand
        radius = center-90;
        time = System.currentTimeMillis()/(60*60*1000.0)*Math.PI*2;
        angleX = (int)((center) + Math.sin(time)*radius);
        angleY = (int)((center) - Math.cos(time)*radius);
        
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(6));
        g.drawLine(center, center, angleX, angleY);
        g.setStroke(new BasicStroke(0));
        
        //Second Hand
        radius = center-50;
        time = System.currentTimeMillis()/(60*1000.0)*Math.PI*2;
        angleX = (int)((center) + Math.sin(time)*radius);
        angleY = (int)((center) - Math.cos(time)*radius);
        
        g.setColor(Color.red);
        g.drawLine(center, center, angleX, angleY);
        
       
        //center oval
        g.setColor(Color.RED);
        g.fillOval(center-10,center-10, 15,15);
        
        //drawing over
        
        buffer.show();
        g.dispose();
    }
}
