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

import br.senac.rj.banco.modelo.ContaCorrenteNormal;

public class JanelaConta {
	public static JFrame criarJanelaConta() {
		// Define a janela
		JFrame janelaConta = new JFrame("Cadastro de clientes"); // Janela Normal
		janelaConta.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaConta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaConta.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaConta.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelAgencia = new JLabel("CPF: ");
		JLabel labelNumero = new JLabel("Telefone: ");
		JLabel labelTitular = new JLabel("Nome: ");
		// Posiciona os labels na janela
		labelAgencia.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNumero.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelTitular.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextAgencia = new JTextField();
		JTextField jTextNumero = new JTextField();
		JTextField jTextTitular = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextAgencia.setEnabled(true);
		jTextNumero.setEnabled(false);
		jTextTitular.setEnabled(false);
		// Posiciona os input box
		jTextAgencia.setBounds(180, 40, 100, 20);
		jTextNumero.setBounds(180, 80, 100, 20);
		jTextTitular.setBounds(180, 120, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaConta.add(labelAgencia);
		janelaConta.add(labelNumero);
		janelaConta.add(labelTitular);
		janelaConta.add(jTextAgencia);
		janelaConta.add(jTextNumero);
		janelaConta.add(jTextTitular);
		// Define bot�es e a localiza��o deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(280, 40, 90, 20);
		janelaConta.add(botaoConsultar);
		JButton botaoGravar = new JButton("Salvar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(true);
		janelaConta.add(botaoGravar);
		JButton botaoLimpar = new JButton("Deletar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janelaConta.add(botaoLimpar);
		botaoLimpar.setEnabled(false);
		// Define objeto conta para pesquisar no banco de dados
		ContaCorrenteNormal conta = new ContaCorrenteNormal();
		// Define a��es dos bot�es
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int agencia = Integer.parseInt(jTextAgencia.getText());
					botaoGravar.setEnabled(true);
					jTextNumero.setEnabled(true);
					String titular;
					if (!conta.consultarConta(agencia))
						titular = "";
					else
						titular = conta.getTitular();
					jTextTitular.setText(titular);
					jTextAgencia.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextTitular.setEnabled(true);
					jTextTitular.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaConta,
							"Preencha os campos agência e número da conta corretamente!!");
				}
			}
		});
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaConta, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int agencia = Integer.parseInt(jTextAgencia.getText());
					int numero = Integer.parseInt(jTextNumero.getText());
					String titular = jTextTitular.getText().trim(); // Retira os espa�os em branco
					if (titular.length() == 0) {
						JOptionPane.showMessageDialog(janelaConta, "Preencha o campo titular");
						jTextTitular.requestFocus();
					} else {
						if (!conta.consultarConta(agencia)) {
							if (!conta.cadastrarConta(agencia, numero, titular))
								JOptionPane.showMessageDialog(janelaConta, "Erro na inclusão do titular!");
							else
								JOptionPane.showMessageDialog(janelaConta, "Inclusão realizada!");
						} else {
							if (!conta.atualizarConta(agencia, numero, titular))
								JOptionPane.showMessageDialog(janelaConta, "Erro na atualização do titular!");
							else
								JOptionPane.showMessageDialog(janelaConta, "Alteração realizada!");
						}

					}

				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextAgencia.setText(""); // Limpar campo
				jTextNumero.setText(""); // Limpar campo
				jTextTitular.setText(""); // Limpar campo
				jTextAgencia.setEnabled(true);
				jTextNumero.setEnabled(true);
				jTextTitular.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextAgencia.requestFocus(); // Colocar o foco em um campo
			}
		});
		return janelaConta;
	}
}
