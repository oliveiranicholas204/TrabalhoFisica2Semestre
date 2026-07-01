package View;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class PainelTriangulo extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Coordenadas das esferas
        int xA = 200, yA = 30;   // topo
        int xB = 120, yB = 140;  // esquerda
        int xC = 280, yC = 140;  // direita

        int r = 30; // raio visual (tamanho do círculo)

        // Linhas do triângulo
        g2.setColor(Color.GRAY);
        g2.drawLine(xA, yA, xB, yB);
        g2.drawLine(xA, yA, xC, yC);
        g2.drawLine(xB, yB, xC, yC);

        // Fonte para letras
        g2.setFont(new Font("Arial", Font.BOLD, 16));

        // ===== Esfera A (topo) =====
        g2.setColor(new Color(255, 80, 80)); // vermelho claro
        g2.fillOval(xA - r/2, yA - r/2, r, r);

        g2.setColor(Color.BLACK);
        g2.drawString("A", xA - 5, yA + 5);

        // ===== Esfera B (esquerda) =====
        g2.setColor(new Color(80, 160, 255)); // azul
        g2.fillOval(xB - r/2, yB - r/2, r, r);

        g2.setColor(Color.BLACK);
        g2.drawString("B", xB - 5, yB + 5);

        // ===== Esfera C (direita) =====
        g2.setColor(new Color(80, 220, 120)); // verde
        g2.fillOval(xC - r/2, yC - r/2, r, r);

        g2.setColor(Color.BLACK);
        g2.drawString("C", xC - 5, yC + 5);
    }
}