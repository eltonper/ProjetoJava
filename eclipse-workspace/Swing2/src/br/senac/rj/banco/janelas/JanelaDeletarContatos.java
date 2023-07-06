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
 * Classe que estabelece o método para a exclusão dos contatos
 * @author elton
 */
public class JanelaDeletarContatos {
	public static JFrame criarJanelaDeletarContatos() {
		// Define a janela
		JFrame janelaExclusao = new JFrame("Exclusão de Clientes"); // Janela Normal
		janelaExclusao.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaExclusao.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaExclusao.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaExclusao.getContentPane();
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
		janelaExclusao.add(labelCPF);
		janelaExclusao.add(labelTelefone);
		janelaExclusao.add(labelNome);
		janelaExclusao.add(jTextCPF);
		janelaExclusao.add(jTextTelefone);
		janelaExclusao.add(jTextNome);
		// Define bot�es e a localiza��o deles na janela
		
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(200, 200, 100, 20);
		botaoExcluir.setEnabled(true);
		
		
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(50, 200, 100, 20);
		
		janelaExclusao.add(botaoExcluir);
		janelaExclusao.add(botaoConsultar);

		// Define objeto conta para pesquisar no banco de dados
		Contatos contato = new Contatos();
		// Define a��es dos bot�es

		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaExclusao, "Deseja excluir?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				
				int cpf = Integer.parseInt(jTextCPF.getText());
				
				if (resposta == JOptionPane.YES_OPTION) {
					
					contato.excluirContato(cpf);
					
					JOptionPane.showMessageDialog(janelaExclusao, "Registro excluído com sucesso");
					
					} 
//				else {
//						if (!contato.excluirContato(cpf)) {
//							if (!contato.cadastrarContato(cpf, numero, titular))
//								JOptionPane.showMessageDialog(janelaExclusao, "Erro no registro do cliente");
//							else
//								JOptionPane.showMessageDialog(janelaExclusao, "Exclusão realizada");
//						} else {
//							if (!contato.atualizarContato(cpf, numero, titular))
//								JOptionPane.showMessageDialog(janelaExclusao, "Erro na atualiza��o do titular!");
//							else
//								JOptionPane.showMessageDialog(janelaExclusao, "Altera��o realizada!");
//						}
//
//					}
//
//				}
			}
		});
		
		
		
		
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
					JOptionPane.showMessageDialog(janelaExclusao,
							"Preencha os campos ag�ncia e n�mero da conta corretamente!!");
				}
			}
		});
		
		
		

		return janelaExclusao;
	}
}
