package Ex2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class CavaloGUI {
    private JFrame frame;

    public CavaloGUI() {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addFrameContent();
        frame.pack();
    }

    public void open() {
        frame.setVisible(true);
    }

    private void addFrameContent() {
        //frame.setLayout(new GridLayout(2,3));
        frame.setLayout(new FlowLayout());
        JTextField cavalo1text = new JTextField("30");
        frame.add(cavalo1text);
        JTextField cavalo2text = new JTextField("30");
        frame.add(cavalo2text);
        JTextField cavalo3text = new JTextField("30");
        frame.add(cavalo3text);
        JButton button = new JButton("Inicia");

        Cavalo cavalo1 = new Cavalo(cavalo1text);
        Cavalo cavalo2 = new Cavalo(cavalo2text);
        Cavalo cavalo3 = new Cavalo(cavalo3text);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cavalo1.start();
                cavalo2.start();
                cavalo3.start();
            }
        });
        frame.add(button);
    }

    public static void main(String[] args) {
        CavaloGUI window = new CavaloGUI();
        window.open();
    }
}
