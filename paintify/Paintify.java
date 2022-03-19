import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paintify {

    JButton clearBtn, colorBtn, saveBtn, loadBtn;
    DrawingArea drawify;
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

    public void showCanvas() {
        JFrame myFrame = new JFrame("Paintify");
        Container content = myFrame.getContentPane();

        content.setLayout(new BorderLayout());
        drawify = new DrawingArea();

        content.add(drawify, BorderLayout.CENTER);

        JPanel toolBar = new JPanel();
        loadBtn = new JButton("Load");
        loadBtn.addActionListener(actionListener);
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(actionListener);
        colorBtn = new JButton("Set Color");
        colorBtn.addActionListener(actionListener);
        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);

        toolBar.add(loadBtn);
        toolBar.add(saveBtn);
        toolBar.add(colorBtn);
        toolBar.add(clearBtn);

        content.add(toolBar, BorderLayout.NORTH);

        myFrame.setSize(1280,1024);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

    }

    public static void main(String[] args) {
      new Paintify().showCanvas();
  }
}
