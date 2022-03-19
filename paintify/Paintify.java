import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paintify {

    JButton clearBtn, redBtn, blueBtn, greenBtn, saveBtn, loadBtn;
    DrawingArea drawify;
    ActionListener actionListener = new ActionListener() {
 
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {
              drawify.clear();
            } else if (e.getSource() == blueBtn) {
              drawify.blueBrush();
            } else if (e.getSource() == greenBtn) {
              drawify.greenBrush();
            } else if (e.getSource() == redBtn) {
              drawify.redBrush();
            } else if (e.getSource() == saveBtn) {
              drawify.saveImage(drawify);
            } else if (e.getSource() == loadBtn) {
              drawify.loadImage();
            }
          }
        };
    public static void main(String[] args) {
        new Paintify().showCanvas();
    }

    public void showCanvas() {
        JFrame myFrame = new JFrame("Paintify");
        Container content = myFrame.getContentPane();

        content.setLayout(new BorderLayout());
        drawify = new DrawingArea();

        content.add(drawify, BorderLayout.CENTER);

        JPanel toolBar = new JPanel();
        loadBtn = new JButton("Load");
        loadBtn.addActionListener(actionListener);
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(actionListener);
        redBtn = new JButton("Red");
        redBtn.addActionListener(actionListener);
        blueBtn = new JButton("Blue");
        blueBtn.addActionListener(actionListener);
        greenBtn = new JButton("Green");
        greenBtn.addActionListener(actionListener);

        toolBar.add(redBtn);
        toolBar.add(greenBtn);
        toolBar.add(blueBtn);
        toolBar.add(clearBtn);
        toolBar.add(saveBtn);
        toolBar.add(loadBtn);

        content.add(toolBar, BorderLayout.EAST);

        myFrame.setSize(1280,1024);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

    }
}
