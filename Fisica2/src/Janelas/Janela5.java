package Janelas;

//Todos os pacotes importados para a criação do código;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//Cria a janela
public class Janela5 extends JFrame {

	//Cria os elementos da janela;
    public Janela5(double cargaA, double cargaB, double cargaC, double distancia) {

    	//Configurações da janela e dos elementos;
        setTitle("Resultado Final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 350);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel titulo = new JLabel("Resultado Final", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        contentPane.add(titulo, BorderLayout.NORTH);

        //Calculo e conversão dos  ultimos valores mostrados na simulação;
        double k = 9e9;
        double q1 = cargaA * 1e-9;
        double q2 = cargaB * 1e-9;
        double q3 = cargaC * 1e-9;
        double FAB = k * Math.abs(q1 * q2) / Math.pow(distancia, 2);
        double FAC = k * Math.abs(q1 * q3) / Math.pow(distancia, 2);
        double FBC = k * Math.abs(q2 * q3) / Math.pow(distancia, 2);

        //Organiza os resultados;
        JPanel centro = new JPanel(new GridLayout(2, 3, 20, 15));

        //Mostra os valores;
        centro.add(criarBloco(
                "Carga final da esfera A",
                String.format("%.2f nC", cargaA)
        ));

        centro.add(criarBloco(
                "Carga final da esfera B",
                String.format("%.2f nC", cargaB)
        ));

        centro.add(criarBloco(
                "Carga final da esfera C",
                String.format("%.2f nC", cargaC)
        ));

        centro.add(criarBloco(
                "Força entre A e B",
                formatar(FAB) + " N"
        ));

        centro.add(criarBloco(
                "Força entre A e C",
                formatar(FAC) + " N"
        ));

        centro.add(criarBloco(
                "Força entre B e C",
                formatar(FBC) + " N"
        ));

        //Mais configurações das posições dos resultados;
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(Box.createVerticalStrut(20), BorderLayout.NORTH);
        wrapper.add(centro, BorderLayout.CENTER);

        contentPane.add(wrapper, BorderLayout.CENTER);

        JButton recomeçar = new JButton("Recomeçar");
        JButton finalizar = new JButton("Finalizar");

        JPanel botoes = new JPanel(new BorderLayout());
        botoes.add(recomeçar, BorderLayout.WEST);
        botoes.add(finalizar, BorderLayout.EAST);

        contentPane.add(botoes, BorderLayout.SOUTH);

        //Retorna para a janela 2 para que novos valores possam ser inseridos;
        recomeçar.addActionListener(e -> Recomeçar());
        //Finaliza o programa;
        finalizar.addActionListener(e -> this.dispose());
    }

    //Abre a janela 2 para que novos valores possam ser inseridos e simulados;
   public void Recomeçar() {
	   Janela2 segunda = new Janela2();
       segunda.setVisible(true);
       this.dispose(); 
   }
   //Função que auxilia nas configurações e posições dos valores;
    private JPanel criarBloco(String label, String valor) {

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel(label, SwingConstants.CENTER);
        JLabel l2 = new JLabel(valor, SwingConstants.CENTER);

        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(l1);
        p.add(l2); 
        return p;
    }

    //Formata os números para notação cientifica;
    private String formatar(Double valor) {

        if (Math.abs(valor) < 1e-15 || valor.isInfinite() || valor.isNaN())
            return "0,0";

        int exp = (int) Math.floor(Math.log10(Math.abs(valor)));
        double man = valor / Math.pow(10, exp);

        return String.format("%.1f × 10^%d", man, exp);
    }
}