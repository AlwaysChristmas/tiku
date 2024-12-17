package tiku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class tiku extends JPanel implements MouseMotionListener {
    private BufferedImage image;
    private Graphics2D g2d;
    private int width = 800; // Ancho de la ventana
    private int height = 600; // Alto de la ventana

    public tiku() {
        // Inicializamos la imagen en blanco
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        g2d = image.createGraphics();
        g2d.setColor(Color.WHITE); // Fondo blanco
        g2d.fillRect(0, 0, width, height);

        // Configuramos el color para "dibujar" en negro
        g2d.setColor(Color.BLACK);

        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Dibuja un pequeño rectángulo en la posición del ratón
        int x = e.getX();
        int y = e.getY();
        g2d.fillRect(x, y, 2, 2); // Tamaño del "píxel" pintado
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // No hace nada, pero debe implementarse por la interfaz
    }

    public void saveImage(String filePath) {
        try {
            File file = new File(filePath);
            ImageIO.write(image, "jpg", file);
            JOptionPane.showMessageDialog(this, "Imagen guardada en: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la imagen.");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Paint - Blanco y Negro");
        tiku paintPanel = new tiku();

        // Botón para guardar la imagen
        JButton saveButton = new JButton("Guardar como JPG");
        saveButton.addActionListener(e -> {
            paintPanel.saveImage("simple_paint_output.jpg");
        });

        // Configuración de la interfaz
        frame.add(paintPanel, BorderLayout.CENTER);
        frame.add(saveButton, BorderLayout.SOUTH);
        frame.setSize(paintPanel.width, paintPanel.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
