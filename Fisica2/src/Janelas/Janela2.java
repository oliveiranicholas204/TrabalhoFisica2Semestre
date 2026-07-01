package Janelas;

//Todos os pacotes importados para a criação do código;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Janela2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Carga1;
	private JTextField Carga2;
	private JTextField Carga3;
	private JTextField Distância;
	private JLabel Resposta;
	
	private JLabel lblTitulo;
	private JLabel lblCarga1;
	private JLabel lblCarga2;
	private JLabel lblCarga3;
	private JLabel lblDistancia;
	private JButton btnNewButton;	
	private Double q1;
	private Double q2;
	private Double q3;
	private Double d;

	
	//Cria a janela;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela2 frame = new Janela2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Cria todos os elementos da janela;
	public Janela2() {
		// Configurações da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Configurações do elementos;

		lblTitulo = new JLabel("Digite os valores", SwingConstants.CENTER);
		contentPane.add(lblTitulo);
		
		lblCarga1 = new JLabel("Carga inicial esfera 1(nC)");
		contentPane.add(lblCarga1);
		
		Carga1 = new JTextField();
		contentPane.add(Carga1);
		Carga1.setColumns(10);
		
		lblCarga2 = new JLabel("Carga inicial esfera 2(nC)");
		contentPane.add(lblCarga2);
		
		Carga2 = new JTextField();
		contentPane.add(Carga2);
		Carga2.setColumns(10);
		
		lblCarga3 = new JLabel("Carga inicial esfera 3(nC)");
		contentPane.add(lblCarga3);
		
		Carga3 = new JTextField();
		contentPane.add(Carga3);
		Carga3.setColumns(10);
		
		lblDistancia = new JLabel("Distância (cm)");
		contentPane.add(lblDistancia);
		
		Distância = new JTextField();
		contentPane.add(Distância);
		Distância.setColumns(10);
		
		btnNewButton = new JButton("Calcular");
		contentPane.add(btnNewButton);
		
		Resposta = new JLabel("");
		Resposta.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Resposta);
		
		   // Ao clicar no botão, chama o método de validação e vai para a próxima janela;
		btnNewButton.addActionListener(e -> Calcular());

		 // Sempre que a janela for redimensionada, os elementos terão suas posições e tamanhos atualizados.
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				redimensionarComponentesProporcionalmente();
			}
		});
	}
	// Redimensiona proporcionalmente todos os componentes da interface sempre que a janela sofre alteração de tamanho.
	private void redimensionarComponentesProporcionalmente() {
		double larguraAtual = contentPane.getWidth();
		double alturaAtual = contentPane.getHeight();

		if (larguraAtual <= 0 || alturaAtual <= 0) return;

		double escalaX = larguraAtual / 434.0; 
		double escalaY = alturaAtual / 261.0;
		
		int tamanhoFonteBase = (int) (11 * Math.min(escalaX, escalaY));
		if (tamanhoFonteBase < 11) tamanhoFonteBase = 11; 
		
		Font fonteAtualizada = new Font("Tahoma", Font.PLAIN, tamanhoFonteBase);
		Font fonteTitulo = new Font("Tahoma", Font.BOLD, (int)(tamanhoFonteBase * 1.2));

		lblTitulo.setFont(fonteTitulo);
		lblCarga1.setFont(fonteAtualizada);
		Carga1.setFont(fonteAtualizada);
		lblCarga2.setFont(fonteAtualizada);
		Carga2.setFont(fonteAtualizada);
		lblCarga3.setFont(fonteAtualizada);
		Carga3.setFont(fonteAtualizada);
		lblDistancia.setFont(fonteAtualizada);
		Distância.setFont(fonteAtualizada);
		Resposta.setFont(fonteAtualizada);
		btnNewButton.setFont(fonteAtualizada);

		lblTitulo.setBounds((int)(159 * escalaX), (int)(11 * escalaY), (int)(117 * escalaX), (int)(14 * escalaY));
		lblCarga1.setBounds((int)(10 * escalaX), (int)(54 * escalaY), (int)(152 * escalaX), (int)(14 * escalaY));
		Carga1.setBounds((int)(10 * escalaX), (int)(73 * escalaY), (int)(96 * escalaX), (int)(20 * escalaY));
		lblCarga3.setBounds((int)(10 * escalaX), (int)(133 * escalaY), (int)(152 * escalaX), (int)(14 * escalaY));
		Carga3.setBounds((int)(10 * escalaX), (int)(150 * escalaY), (int)(96 * escalaX), (int)(20 * escalaY));
		lblCarga2.setBounds((int)(300 * escalaX), (int)(54 * escalaY), (int)(152 * escalaX), (int)(14 * escalaY));
		Carga2.setBounds((int)(310 * escalaX), (int)(73 * escalaY), (int)(96 * escalaX), (int)(20 * escalaY));
		lblDistancia.setBounds((int)(310 * escalaX), (int)(133 * escalaY), (int)(96 * escalaX), (int)(14 * escalaY));
		Distância.setBounds((int)(310 * escalaX), (int)(150 * escalaY), (int)(96 * escalaX), (int)(20 * escalaY));
		Resposta.setBounds((int)(25 * escalaX), (int)(190 * escalaY), (int)(380 * escalaX), (int)(30 * escalaY));
		btnNewButton.setBounds((int)(315 * escalaX), (int)(225 * escalaY), (int)(105 * escalaX), (int)(26 * escalaY));
	}

	//Verifica se os valores são validos, e se forem, fecha a janela atual e abre a próxima
	private void Calcular() {    
		 if (Carga1.getText().trim().isEmpty() || Carga2.getText().trim().isEmpty() || Carga3.getText().trim().isEmpty() || Distância.getText().trim().isEmpty()) {
			        Resposta.setText("Preencha todos os campos!");
			        return;
		 }
		  try {
			  q1 = Double.parseDouble(Carga1.getText().trim().replace(",", "."));
			  q2 = Double.parseDouble(Carga2.getText().trim().replace(",", "."));
			  q3 = Double.parseDouble(Carga3.getText().trim().replace(",", "."));
			  d = Double.parseDouble(Distância.getText().trim().replace(",", "."));
		        
		        if (q1 < -1000 || q1 > 1000 || q2 < -1000 || q2 > 1000 || q3 < -1000 || q3 > 1000) {
		        	    Resposta.setText("As cargas devem estar entre -1000 e +1000 nC.");
		        	    return;
		        	}

		        if (d < 1 || d > 500) {
		            Resposta.setText("A distância deve estar entre 1 e 500 cm.");
		            return;
		        }
		        if (Double.isNaN(q1) || Double.isNaN(q2) || Double.isNaN(q3) || Double.isNaN(d)) {
		            Resposta.setText("Valores inválidos!");
		            return;
		        }

		        this.dispose();
		        Janela3 j3 = new Janela3(this);
		        j3.setVisible(true);

		    } catch (NumberFormatException e) {
		        Resposta.setText("Digite apenas números válidos!");
		    }
	}

	//retorna os valores;
	public Double getq1() { 
		return q1;
		}
	public Double getq2() { 
		return q2; 
		}
	public Double getq3() {
		return q3;
		}
	public Double getd() {
		return d; 
		}
}