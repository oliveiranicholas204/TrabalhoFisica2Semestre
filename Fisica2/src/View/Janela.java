package View;

//Todos os pacotes importados para a criação do código;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Janelas.Janela2;

public class Janela extends JFrame {

    private static final long serialVersionUID = 1L;

    //Cria a janela;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Janela frame = new Janela();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // * Cria todos os elementos da janela;
    public Janela() {
    	//Configurações da janela;
        setTitle("Esferas Condutoras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setMinimumSize(new Dimension(600, 500));
        setLocationRelativeTo(null);
        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        
        // Configurações da imagem;
        JPanel painelSuperior = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        PainelTriangulo painel = new PainelTriangulo();
        painel.setPreferredSize(new Dimension(400, 180));
        painelSuperior.add(painel);
        contentPane.add(painelSuperior, BorderLayout.NORTH);

        //Mostra o contexto do programa
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));

        JLabel texto = new JLabel(
            "<html>" +
            "Três pequenas esferas estão posicionadas de forma que formam um triângulo, " +
            "onde todas estão à mesma distância umas das outras. Cada esfera possui uma carga elétrica inicial. " +
            "Como cargas elétricas exercem forças umas sobre as outras, é possível calcular a intensidade dessas forças " +
            "usando a Lei de Coulomb.<br><br>" +

            "Em seguida, algumas esferas são conectadas por fios condutores e a esfera B é ligada à Terra. " +
            "Esses procedimentos fazem com que as cargas elétricas se redistribuam entre as esferas, alterando os valores " +
            "das cargas e, consequentemente, as forças eletrostáticas entre elas.<br><br>" +

            "O programa consiste em analisar como as forças elétricas entre três esferas mudam quando suas cargas " +
            "são redistribuídas por meio de ligações feitas com fios condutores." +
            "</html>"
        );

        texto.setAlignmentX(LEFT_ALIGNMENT);

        centro.add(texto);
        centro.add(Box.createVerticalGlue());

        contentPane.add(centro, BorderLayout.CENTER);

        JPanel sul = new JPanel(new BorderLayout());

        JButton botao = new JButton("Continuar");
        // Ao clicar no botão, abre a próxima janela;
        botao.addActionListener(e -> Abrir());

        sul.add(botao, BorderLayout.EAST);

        contentPane.add(sul, BorderLayout.SOUTH);
    }

    //Fecha a janela atual e abre a próxima janela;
    private void Abrir() {
        dispose();
        Janela2 segunda = new Janela2();
        segunda.setVisible(true);
    }
}