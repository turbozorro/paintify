import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Robot;

import java.io.File;
import java.io.IOException;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;

public class DrawingArea extends JComponent {
    private BufferedImage image;
    private Graphics2D gr;
    private int currentX, currentY, oldX, oldY;
    
    public DrawingArea() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                
                if (gr != null){
                    gr.drawLine(oldX, oldY, currentX, currentY);
                    repaint();
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_3BYTE_BGR);
            gr = (Graphics2D) image.getGraphics();
            gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        gr.setPaint(Color.white);
        gr.fillRect(0, 0, getSize().width, getSize().height);
        gr.setPaint(Color.black);
        repaint();
    }

    public void redBrush(){
        Color newColor = JColorChooser.showDialog(null, "Pick a Color", Color.red);
        gr.setPaint(newColor);
    }

    public void blueBrush(){
        gr.setPaint(Color.blue);
    }

    public void greenBrush(){
        gr.setPaint(Color.green);
    }

    public void saveImage(DrawingArea d){
        BufferedImage buffer = null;
        try {
            buffer = new Robot().createScreenCapture(d.getBounds());
        } catch (Exception e) {
            e.printStackTrace();
        }
  
        Graphics2D file = buffer.createGraphics();
        d.paint(file);
        try {
          ImageIO.write(buffer, "png", new File("myImage.png"));
        } catch (Exception e) {
          System.out.println("error");
        }
    }

    public void loadImage(){
        BufferedImage buffer = null;
        try {
            buffer = ImageIO.read(new File("myImage.png"));
        } catch (Exception e) {
            System.out.println("error");
        }
        gr.drawImage(buffer, 0, 0, null);
    }
}
