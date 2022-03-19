// Dependencias para contener otros componentes AWT y delimitar sus bordes
import java.awt.BorderLayout;
import java.awt.Container;
// Dependencias para capturar eventos del mouse
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Dependencias para incluir elementos a la interfaz (panel del lienzo y botones)
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Clase principal de la aplicación
public class Paintify {

    // Variables de botones y área de dibujo
    JButton clearBtn, colorBtn, saveBtn, loadBtn;
    DrawingArea drawify;

    // Captura de eventos del mouse sobre los botones de acción
    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadBtn){
          drawify.loadImage();
        } else if (e.getSource() == saveBtn){
          drawify.saveImage(drawify);
        } else if (e.getSource() == colorBtn) {
          drawify.setColor();
        } else if (e.getSource() == clearBtn) {
          drawify.clear();
        }
      }
    };

    // Método principal para mostrar el área de dibujo y su menú
    public void showCanvas() {
        JFrame myFrame = new JFrame("Paintify");
        Container content = myFrame.getContentPane();

        content.setLayout(new BorderLayout());
        drawify = new DrawingArea();

        content.add(drawify, BorderLayout.CENTER);

        // Construcción de los botones y de la barra de menú
        JPanel toolBar = new JPanel();
        loadBtn = new JButton("Load");
        loadBtn.addActionListener(actionListener);
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(actionListener);
        colorBtn = new JButton("Set Color");
        colorBtn.addActionListener(actionListener);
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);

        // Adición de botones a la barra de menú
        toolBar.add(loadBtn);
        toolBar.add(saveBtn);
        toolBar.add(colorBtn);
        toolBar.add(clearBtn);

        // Posicionamiento de los botones en la parte superior de la ventana
        content.add(toolBar, BorderLayout.NORTH);

        // Construcción de la ventana de trabajo
        myFrame.setSize(1280,1024);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

    }

    // Método principal Main
    public static void main(String[] args) {
      new Paintify().showCanvas();
  }
}
