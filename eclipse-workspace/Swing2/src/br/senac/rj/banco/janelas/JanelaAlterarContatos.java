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
 * Classe que estabelece o método para a alteração dos contatos
 * @author elton
 */

public class JanelaAlterarContatos {
	public static JFrame criarJanelaAlterarContatos() {
		// Define a janela
		JFrame janelaAltera = new JFrame("Registro de Clientes"); // Janela Normal
		janelaAltera.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaAltera.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaAltera.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaAltera.getContentPane();
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
		janelaAltera.add(labelCPF);
		janelaAltera.add(labelTelefone);
		janelaAltera.add(labelNome);
		janelaAltera.add(jTextCPF);
		janelaAltera.add(jTextTelefone);
		janelaAltera.add(jTextNome);
		// Define bot�es e a localiza��o deles na janela
		
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(240, 40, 100, 20);
		janelaAltera.add(botaoConsultar);
		
		
		JButton botaoAlterar = new JButton("Alterar");
		botaoAlterar.setBounds(50, 200, 100, 20);
		botaoAlterar.setEnabled(true);
		janelaAltera.add(botaoAlterar);

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
					jTextTelefone.setEnabled(true);
					botaoConsultar.setEnabled(false);
					jTextNome.setEnabled(true);
					jTextNome.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaAltera,
							"Preencha os campos ag�ncia e n�mero da conta corretamente!!");
				}
			}
		});
		
		botaoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaAltera, "Deseja alterar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int cpf = Integer.parseInt(jTextCPF.getText());
					int numero = Integer.parseInt(jTextTelefone.getText());
					String nome = jTextNome.getText().trim(); // Retira os espa�os em branco
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janelaAltera, "Preencha o campo titular");
						jTextNome.requestFocus();
					} else {
						if (!contato.consultarContato(cpf)) {
							if (!contato.atualizarContato(cpf, numero, nome))
								JOptionPane.showMessageDialog(janelaAltera, "Erro no registro do cliente");
							else
								JOptionPane.showMessageDialog(janelaAltera, "Registro realizado");
						} else {
							if (!contato.atualizarContato(cpf, numero, nome))
								JOptionPane.showMessageDialog(janelaAltera, "Erro na atualiza��o do titular!");
							else
								JOptionPane.showMessageDialog(janelaAltera, "Altera��o realizada!");
						}

					}

				}
			}
		});

		return janelaAltera;
	}
}
