package Janelas;

//Todos os pacotes importados para a criação do código;
import Janelas.Janela4Simulacao;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Janela3 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Double q1;
    private Double q2;
    private Double q3;
    private Double d;
    private Janela2 valor;
    private JLabel E12;
    private JLabel E13;
    private JLabel E23;
    private final HashMap<Component, Rectangle> proporcoes = new HashMap<>();
    private final int larguraBase = 450;
    private final int alturaBase = 300;

    //Cria a janela;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    Janela2 j2 = new Janela2();

                    Janela3 frame = new Janela3(j2);
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Cria os elementos da janela;
    public Janela3(Janela2 valor) {

    	//utilizado para acessar os valores inseridos anteriormente;
        this.valor = valor;

        //Confingurações da tela de dos elementos;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Módulo da força eletrostática entre as esferas");
        lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
        lblNewLabel.setBounds(35, 10, 370, 20);
        contentPane.add(lblNewLabel);

        JLabel L12 = new JLabel("Esferas 1 e 2:");
        L12.setHorizontalAlignment(JLabel.CENTER);
        L12.setBounds(20, 50, 120, 20);
        contentPane.add(L12);

        JLabel L13 = new JLabel("Esferas 1 e 3:");
        L13.setHorizontalAlignment(JLabel.CENTER);
        L13.setBounds(160, 50, 120, 20);
        contentPane.add(L13);

        JLabel L23 = new JLabel("Esferas 2 e 3:");
        L23.setHorizontalAlignment(JLabel.CENTER);
        L23.setBounds(300, 50, 120, 20);
        contentPane.add(L23);

        E12 = new JLabel("");
        E12.setHorizontalAlignment(JLabel.CENTER);
        E12.setBounds(20, 80, 120, 20);
        contentPane.add(E12);

        E13 = new JLabel("");
        E13.setHorizontalAlignment(JLabel.CENTER);
        E13.setBounds(160, 80, 120, 20);
        contentPane.add(E13);

        E23 = new JLabel("");
        E23.setHorizontalAlignment(JLabel.CENTER);
        E23.setBounds(300, 80, 120, 20);
        contentPane.add(E23);

        JButton Botao = new JButton("Começar Simulação");
        Botao.setBounds(267, 230, 159, 22);
        contentPane.add(Botao);

        // Calcula as forças eletrostáticas iniciais;
        Calcular();
        
        // Ao clicar no botão, abre a janela da simulação;
        Botao.addActionListener(e -> Próximo());

        // Salva as posições originais dos componentes;
        salvarProporcoes();

        // Atualiza automaticamente o layout quando a janela é redimensionada
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                redimensionar();
            }
        });

    }

    //Converte os valores e calcula os módulos de força eletrostática entre cada esfera;
    private void Calcular() {

    	// Constante eletrostática;
        Double k = 9e9;

        // Conversão das unidades para o Sistema Internacional (SI)
        q1 = valor.getq1() * 1e-9;
        q2 = valor.getq2() * 1e-9;
        q3 = valor.getq3() * 1e-9;
        d = valor.getd() / 100.0;

     // Cálculo das forças entre cada par de esferas;
        Double R12 = k * Math.abs(q1 * q2) / Math.pow(d, 2);
        Double R13 = k * Math.abs(q1 * q3) / Math.pow(d, 2);
        Double R23 = k * Math.abs(q2 * q3) / Math.pow(d, 2);

     // Exibição dos resultados na interface;
        E12.setText(formatar(R12) + " N");
        E13.setText(formatar(R13) + " N");
        E23.setText(formatar(R23) + " N");
    }

    //Formata os valores para notação científica;
    private String formatar(Double valor) {
        if (Math.abs(valor) < 1e-15 || valor.isInfinite() || valor.isNaN()) {
            return "0";
        }

        Integer expoente = (int) Math.floor(Math.log10(Math.abs(valor)));
        Double mantissa = valor / Math.pow(10, expoente);

        return String.format("%.1f × 10^%d", mantissa, expoente);
    }

    //Vai para a próxima janela (a janela de simulação);
    private void Próximo() {
        this.dispose();
        Janela4Simulacao j4 = new Janela4Simulacao(valor);
        j4.setVisible(true);
    }
//Ajuda no redimensionamento dos elementos ao mudar tamanaho da tela;
    private void salvarProporcoes() {

        for (Component c : contentPane.getComponents()) {
            proporcoes.put(c, c.getBounds());
        }

    }

    //Função que redimensiona os elementos proporcionalmente;
    private void redimensionar() {

        double escalaX = contentPane.getWidth() / (double) larguraBase;
        double escalaY = contentPane.getHeight() / (double) alturaBase;

        for (Component c : contentPane.getComponents()) {

            Rectangle r = proporcoes.get(c);

            int x = (int) (r.x * escalaX);
            int y = (int) (r.y * escalaY);
            int w = (int) (r.width * escalaX);
            int h = (int) (r.height * escalaY);

            c.setBounds(x, y, w, h);

            float tamanhoFonte = (float) (12 * Math.min(escalaX, escalaY));
            if (tamanhoFonte < 12) {
                tamanhoFonte = 12;
            }

            c.setFont(c.getFont().deriveFont(tamanhoFonte));
        }

    }

}