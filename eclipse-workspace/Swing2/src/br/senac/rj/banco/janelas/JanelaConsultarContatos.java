package br.senac.rj.banco.janelas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.rj.banco.modelo.Contatos;

/**
 * Classe que estabelece o método para a consulta dos contatos
 * @author elton
 */

public class JanelaConsultarContatos {
	public static JFrame criarJanelaConsultarContatos() {
		// Define a janela
		JFrame janelaConsulta = new JFrame("Consulta de Clientes"); // Janela Normal
		janelaConsulta.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaConsulta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaConsulta.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaConsulta.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelCPF = new JLabel("CPF: ");
		JLabel labelTelefone = new JLabel("Telefone: ");
		JLabel labelNome = new JLabel("Nome: ");
		// Posiciona os labels na janela
		labelCPF.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelTelefone.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextCPF = new JTextField();
		JTextField jTextTelefone = new JTextField();
		JTextField jTextNome = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextCPF.setEnabled(true);
		jTextTelefone.setEnabled(false);
		jTextNome.setEnabled(false);
		// Posiciona os input box
		jTextCPF.setBounds(180, 40, 50, 20);
		jTextTelefone.setBounds(180, 80, 50, 20);
		jTextNome.setBounds(180, 120, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaConsulta.add(labelCPF);
		janelaConsulta.add(labelTelefone);
		janelaConsulta.add(labelNome);
		janelaConsulta.add(jTextCPF);
		janelaConsulta.add(jTextTelefone);
		janelaConsulta.add(jTextNome);
		// Define bot�es e a localiza��o deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(50, 200, 100, 20);
		janelaConsulta.add(botaoConsultar);
		//JButton botaoRegistrar = new JButton("Registrar");
		//botaoRegistrar.setBounds(50, 200, 100, 20);
		//botaoRegistrar.setEnabled(false);
		//janelaCadastro.add(botaoRegistrar);

		// Define objeto conta para pesquisar no banco de dados
		Contatos contato = new Contatos();
		// Define a��es dos bot�es
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int cpf = Integer.parseInt(jTextCPF.getText());
					//botaoRegistrar.setEnabled(true);
					String nome;
					
					int telefone;
					
					if (!contato.consultarContato(cpf))
						nome = "";
						
						
					
					else
				
						nome = contato.getNome();
					
						telefone = contato.getTelefone();
					
					jTextNome.setText(nome);
					
					jTextTelefone.setText(Integer.toString(telefone));
					
					
					
					jTextCPF.setEnabled(false);
					jTextTelefone.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextNome.setEnabled(false);
					jTextNome.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaConsulta,
							"Preencha os campos ag�ncia e n�mero da conta corretamente!!");
				}
			}
		});

		return janelaConsulta;
	}
}
