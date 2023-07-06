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
 * Classe que estabelece o método para o registro dos contatos
 * @author elton
 */

public class JanelaRegistrarContatos {
	public static JFrame criarJanelaRegistrarContatos() {
		// Define a janela
		JFrame janelaRegistro = new JFrame("Registro de Clientes"); // Janela Normal
		janelaRegistro.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaRegistro.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaRegistro.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaRegistro.getContentPane();
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
		jTextTelefone.setEnabled(true);
		jTextNome.setEnabled(true);
		// Posiciona os input box
		jTextCPF.setBounds(180, 40, 50, 20);
		jTextTelefone.setBounds(180, 80, 50, 20);
		jTextNome.setBounds(180, 120, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaRegistro.add(labelCPF);
		janelaRegistro.add(labelTelefone);
		janelaRegistro.add(labelNome);
		janelaRegistro.add(jTextCPF);
		janelaRegistro.add(jTextTelefone);
		janelaRegistro.add(jTextNome);
		// Define bot�es e a localiza��o deles na janela
		
		JButton botaoRegistrar = new JButton("Registrar");
		botaoRegistrar.setBounds(50, 200, 100, 20);
		botaoRegistrar.setEnabled(true);
		janelaRegistro.add(botaoRegistrar);

		// Define objeto conta para pesquisar no banco de dados
		Contatos contato = new Contatos();
		// Define a��es dos bot�es

		botaoRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaRegistro, "Deseja registrar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int cpf = Integer.parseInt(jTextCPF.getText());
					int numero = Integer.parseInt(jTextTelefone.getText());
					String nome = jTextNome.getText().trim(); // Retira os espa�os em branco
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaRegistro, "Preencha o campo titular");
						jTextNome.requestFocus();
					} else {
						if (!contato.consultarContato(cpf)) {
							if (!contato.cadastrarContato(cpf, numero, nome))
								JOptionPane.showMessageDialog(janelaRegistro, "Erro no registro do cliente");
							else
								JOptionPane.showMessageDialog(janelaRegistro, "Registro realizado");
						} else {
							if (!contato.atualizarContato(cpf, numero, nome))
								JOptionPane.showMessageDialog(janelaRegistro, "Erro na atualiza��o do titular!");
							else
								JOptionPane.showMessageDialog(janelaRegistro, "Altera��o realizada!");
						}

					}

				}
			}
		});

		return janelaRegistro;
	}
}
