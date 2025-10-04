package Ex2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import javax.swing.*;

public class ImageViewer extends JFrame {

    private JLabel imageLabel;   // zona central (imagem ou mensagem)
    private JLabel nameLabel;    // topo (nome da imagem)
    private JButton prevButton, nextButton, updateButton;
    private File[] imageFiles;
    private int currentIndex = 0;
    private String folderPath;

    public ImageViewer(String folderPath) {
        super("Visualizador de Imagens");
        this.folderPath = folderPath;

        // Layout principal
        setLayout(new BorderLayout());

        // Nome da imagem no topo
        nameLabel = new JLabel("Nenhuma imagem carregada", SwingConstants.CENTER);
        add(nameLabel, BorderLayout.NORTH);

        // Área da imagem
        imageLabel = new JLabel("Fim das imagens triste", SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Botões
        prevButton = new JButton("Anterior");
        nextButton = new JButton("Próximo");
        updateButton = new JButton("Update");

        add(prevButton, BorderLayout.WEST);
        add(nextButton, BorderLayout.EAST);
        add(updateButton, BorderLayout.SOUTH);

        // Ações
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (prevImage()) {
                    showImage();
                } else {
                    showEndMessage();
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nextImage()) {
                    showImage();
                } else {
                    showEndMessage();
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadImages();
                if (imageFiles.length > 0) {
                    currentIndex = 0;
                    showImage();
                } else {
                    showEndMessage();
                }
            }
        });

        // Primeira carga
        loadImages();
        if (imageFiles.length > 0) {
            showImage();
        }

        // Configura janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadImages() {
        File dir = new File(folderPath);
        if (!dir.exists() || !dir.isDirectory()) {
            JOptionPane.showMessageDialog(this, "Pasta inválida: " + folderPath);
            imageFiles = new File[0];
            return;
        }

        imageFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isFile()) {
                    String name = f.getName().toLowerCase();
                    return name.endsWith(".png") || name.endsWith(".jpg")
                            || name.endsWith(".jpeg") || name.endsWith(".gif");
                }
                return false;
            }
        });

        if (imageFiles == null) {
            imageFiles = new File[0];
        }
    }

    private void showImage() {
        if (imageFiles.length == 0) {
            showEndMessage();
            return;
        }
        ImageIcon icon = new ImageIcon(imageFiles[currentIndex].getAbsolutePath());
        imageLabel.setIcon(icon);
        imageLabel.setText(null); // limpa texto
        nameLabel.setText(imageFiles[currentIndex].getName());
    }

    private void showEndMessage() {
        imageLabel.setIcon(null);
        imageLabel.setText("Fim das imagens triste");
        nameLabel.setText("Nenhuma imagem");
    }

    private boolean nextImage() {
        if (imageFiles.length == 0) return false;
        if (currentIndex < imageFiles.length - 1) {
            currentIndex++;
            return true;
        }
        return false;
    }

    private boolean prevImage() {
        if (imageFiles.length == 0) return false;
        if (currentIndex > 0) {
            currentIndex--;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java ImageViewer <pasta das imagens>");
            System.exit(1);
        }
        new ImageViewer(args[0]);
    }
}