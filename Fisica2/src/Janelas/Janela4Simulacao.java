package Janelas;

//Todos os pacotes importados para a criação do código;
import javax.swing.*;
import java.awt.*;

//Cria a janela;
public class Janela4Simulacao extends JFrame {

    private Esfera A, B, C;
    private Boolean AB = false, AC = false, BC = false;
    private Boolean terraB = false;
    private Double cargaInicialA;
    private Double cargaInicialB;
    private Double cargaInicialC;
    private Double distancia;
    private PainelSimulacao painel;
    private JLabel ResultadoAB;
    private JLabel ResultadoAC;
    private JLabel ResultadoBC;

    //Cria os elementos da janela e da simulação;
    public Janela4Simulacao(Janela2 origem) {

        setTitle("Simulação - Ligações entre Esferas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        A = new Esfera();
        B = new Esfera();
        C = new Esfera();

        cargaInicialA = origem.getq1();
        cargaInicialB = origem.getq2();
        cargaInicialC = origem.getq3();
        distancia = origem.getd() / 100.0;

        A.carga = cargaInicialA;
        B.carga = cargaInicialB;
        C.carga = cargaInicialC;

        A.x = 100; A.y = 100;
        B.x = 200; B.y = 200;
        C.x = 300; C.y = 100;

        painel = new PainelSimulacao(A, B, C);
        add(painel, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new BorderLayout());

        JPanel painelForcas = new JPanel(new GridLayout(2, 3, 20, 5));

        ResultadoAB = new JLabel("--- N", SwingConstants.CENTER);
        ResultadoAC = new JLabel("--- N", SwingConstants.CENTER);
        ResultadoBC = new JLabel("--- N", SwingConstants.CENTER);

        painelForcas.add(new JLabel("A-B", SwingConstants.CENTER));
        painelForcas.add(new JLabel("A-C", SwingConstants.CENTER));
        painelForcas.add(new JLabel("B-C", SwingConstants.CENTER));

        painelForcas.add(ResultadoAB);
        painelForcas.add(ResultadoAC);
        painelForcas.add(ResultadoBC);
        painelInferior.add(painelForcas, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton BotaoAB = new JButton("Ligar A-B");
        JButton BotaoAC = new JButton("Ligar A-C");
        JButton BotaoBC = new JButton("Ligar B-C");
        JButton BotaoTerra = new JButton("Aterrar B");
        JButton BotaoReset = new JButton("Reset");
        JButton BotaoFinalizar = new JButton("Finalizar");

        painelBotoes.add(BotaoAB);
        painelBotoes.add(BotaoAC);
        painelBotoes.add(BotaoBC);
        painelBotoes.add(BotaoTerra);
        painelBotoes.add(BotaoReset);

        painelInferior.add(painelBotoes, BorderLayout.CENTER);

        JPanel painelFinal = new JPanel();
        painelFinal.add(BotaoFinalizar);

        painelInferior.add(painelFinal, BorderLayout.SOUTH);

        add(painelInferior, BorderLayout.SOUTH);

        BotaoAB.addActionListener(e -> {
            AB = true;
            simular();
        });

        BotaoAC.addActionListener(e -> {
            AC = true;
            simular();
        });

        BotaoBC.addActionListener(e -> {
            BC = true;
            simular();
        });

        BotaoTerra.addActionListener(e -> {
            terraB = true;
            simular();
        });

        BotaoReset.addActionListener(e -> reset());

        BotaoFinalizar.addActionListener(e -> FinalizarSimulação());

        atualizarForcas();

        setSize(800, 600);
        setLocationRelativeTo(null);
    }
    
    //Executa a simulação;
    private void simular() {

        if (terraB) {
            B.carga = 0.0;
            terraB = false;
        }

        if (AB) {
            Double media = (A.carga + B.carga) / 2.0;
            A.carga = media;
            B.carga = media;
            AB = false;
        }

        if (AC) {
            Double media = (A.carga + C.carga) / 2.0;
            A.carga = media;
            C.carga = media;
            AC = false;
        }

        if (BC) {
            Double media = (B.carga + C.carga) / 2.0;
            B.carga = media;
            C.carga = media;
            BC = false;
        }

        painel.repaint();
        atualizarForcas();
    }

    // Calcula e atualiza os valores;
    private void atualizarForcas() {

        Double k = 9e9;

        Double q1 = A.carga * 1e-9;
        Double q2 = B.carga * 1e-9;
        Double q3 = C.carga * 1e-9;

        Double forcaAB = k * Math.abs(q1 * q2) / Math.pow(distancia, 2);
        Double forcaAC = k * Math.abs(q1 * q3) / Math.pow(distancia, 2);
        Double forcaBC = k * Math.abs(q2 * q3) / Math.pow(distancia, 2);

        ResultadoAB.setText(formatar(forcaAB) + " N");
        ResultadoAC.setText(formatar(forcaAC) + " N");
        ResultadoBC.setText(formatar(forcaBC) + " N");
    }

    //Converte os valores para notação científica;
    private String formatar(Double valor) {

        if (Math.abs(valor) < 1e-15 || valor.isInfinite() || valor.isNaN()) {
            return "0,0";
        }

        int expoente = (int) Math.floor(Math.log10(Math.abs(valor)));
        double mantissa = valor / Math.pow(10, expoente);

        return String.format("%.1f × 10^%d", mantissa, expoente);
    }

    //Reseta os valores para os valores iniciais;
    private void reset() {

        AB = false;
        AC = false;
        BC = false;
        terraB = false;

        A.carga = cargaInicialA;
        B.carga = cargaInicialB;
        C.carga = cargaInicialC;

        painel.repaint();
        atualizarForcas();
    }

    //Termina a simulaão e vai para a próxima janela;
    private void FinalizarSimulação() {
        this.dispose();
        Janela5 janela = new Janela5(A.carga, B.carga, C.carga, distancia);
        janela.setVisible(true);
    }
    
    //Cria a classe esfera;
    static class Esfera {
        Double carga;
        int x, y;
    }
    
    //Cria a imagem da simulação;
    static class PainelSimulacao extends JPanel {

        private final Esfera A;
        private final Esfera B;
        private final Esfera C;

        public PainelSimulacao(Esfera a, Esfera b, Esfera c) {
            this.A = a;
            this.B = b;
            this.C = c;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));

            int w = getWidth();
            int h = getHeight();

            A.x = (int) (w * 0.20);
            A.y = (int) (h * 0.50);

            B.x = (int) (w * 0.50);
            B.y = (int) (h * 0.75);

            C.x = (int) (w * 0.80);
            C.y = (int) (h * 0.50);

            g2.setColor(Color.GRAY);
            g2.drawLine(A.x, A.y, B.x, B.y);
            g2.drawLine(A.x, A.y, C.x, C.y);
            g2.drawLine(B.x, B.y, C.x, C.y);

            desenhar(g2, A, "A");
            desenhar(g2, B, "B");
            desenhar(g2, C, "C");
        }

        //Muda a cor e exibe os valor da carga de cada esfera;
        private void desenhar(Graphics2D g2, Esfera e, String nome) {

            int raio = 18;

            if (e.carga > 0)
                g2.setColor(Color.RED);
            else if (e.carga < 0)
                g2.setColor(Color.BLUE);
            else
                g2.setColor(Color.GRAY);

            g2.fillOval(e.x - raio, e.y - raio, raio * 2, raio * 2);

            g2.setColor(Color.BLACK);
            g2.drawString(
                nome + ": " + String.format("%.2f nC", e.carga),
                e.x - raio,
                e.y - raio - 8
            );
        }
    }
}