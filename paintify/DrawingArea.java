// Dependencias para gestionar el dibujo de las líneas
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

// Dependencias para capturar los eventos del mouse
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// Dependencias para almacenar la imagen dibujada
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Robot;
import javax.imageio.ImageIO;

// Dependencias para generar el área de dibujo y utilizar el selector de colores
import javax.swing.JColorChooser;
import javax.swing.JComponent;

// Clase principal de dibujo, hereda de JComponent
public class DrawingArea extends JComponent {
    // Declaración de los elementos de lienzo y de "pincel"
    private BufferedImage image;
    private Graphics2D gr;
    // Declaración de los puntos de corrdenadas que capturarán las del mouse
    private int currentX, currentY, oldX, oldY;
    // Método constructor 
    public DrawingArea() {
        // Método de captura de coordenadas por clic
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });
        // Método de captura de coordenadas por arrastre y dibujo de línea
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
    // Sobrecarga del método paintComponent para vincular la imagen con el objeto Graphics2D
    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_3BYTE_BGR);
            gr = (Graphics2D) image.getGraphics();
            gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        
        g.drawImage(image, 0, 0, null);
    }

    // Método para cargar la imagen myImage.png
    public void loadImage(){
        BufferedImage buffer = null;
        try {
            buffer = ImageIO.read(new File("myImage.png"));
        } catch (Exception e) {
            System.out.println("error");
        }
        gr.drawImage(buffer, 0, 0, null);
        repaint();
    }

    // Método para guardar lo dibujado en myImage.png
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

    // Método para establecer el color desde el color-picker
    public void setColor(){
        Color newColor = JColorChooser.showDialog(null, "Pick a Color", Color.red);
        gr.setPaint(newColor);
    }

    // Método para limpiar la pantalla
    public void clear() {
        gr.setPaint(Color.white);
        gr.fillRect(0, 0, getSize().width, getSize().height);
        gr.setPaint(Color.black);
        repaint();
    }
}
