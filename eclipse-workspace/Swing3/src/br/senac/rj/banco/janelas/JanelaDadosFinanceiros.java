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

public class JanelaDadosFinanceiros {
	public static JFrame criarJanelaDadosFinanceiros() {
		// Define a janela
		JFrame janelaDadosFinanceiros = new JFrame("Dados Financeiros"); // Janela Normal
		janelaDadosFinanceiros.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaDadosFinanceiros.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaDadosFinanceiros.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaDadosFinanceiros.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelAgencia = new JLabel("Agência: ");
		JLabel labelNumero = new JLabel("Número da conta: ");
		JLabel labelTitular = new JLabel("Titular: ");
		JLabel labelSaldo = new JLabel("Saldo: ");
		// Posiciona os labels na janela
		labelAgencia.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNumero.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelTitular.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho
		labelSaldo.setBounds(50, 130, 100, 20); // coluna, linha, largura, tamanho
		// Define os input box
		JTextField jTextAgencia = new JTextField();
		JTextField jTextNumero = new JTextField();
		JTextField jTextTitular = new JTextField();
		JTextField jTextSaldo = new JTextField();
		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextAgencia.setEnabled(true);
		jTextNumero.setEnabled(true);
		jTextTitular.setEnabled(false);
		jTextSaldo.setEnabled(false);
		
		// Posiciona os input box
		jTextAgencia.setBounds(180, 40, 50, 20);
		jTextNumero.setBounds(180, 80, 50, 20);
		jTextTitular.setBounds(180, 120, 150, 20);
		jTextSaldo.setBounds(180, 130, 150, 20);
		// Adiciona os r�tulos e os input box na janela
		janelaDadosFinanceiros.add(labelAgencia);
		janelaDadosFinanceiros.add(labelNumero);
		janelaDadosFinanceiros.add(labelTitular);
		janelaDadosFinanceiros.add(jTextAgencia);
		janelaDadosFinanceiros.add(jTextNumero);
		janelaDadosFinanceiros.add(jTextTitular);
		
		janelaDadosFinanceiros.add(jTextSaldo);
		
		// Define bot�es e a localiza��o deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 80, 100, 20);
		janelaDadosFinanceiros.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(false);
		janelaDadosFinanceiros.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janelaDadosFinanceiros.add(botaoLimpar);
		// Define objeto conta para pesquisar no banco de dados
		ContaCorrenteNormal conta = new ContaCorrenteNormal();
		// Define a��es dos bot�es
		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int agencia = Integer.parseInt(jTextAgencia.getText());
					int numero = Integer.parseInt(jTextNumero.getText());
					botaoGravar.setEnabled(true);
					String titular;
					if (!conta.consultarConta(agencia, numero))
						titular = "";
					else
						titular = conta.getTitular();
					jTextTitular.setText(titular);
					jTextAgencia.setEnabled(false);
					jTextNumero.setEnabled(false);
					botaoConsultar.setEnabled(false);
					jTextTitular.setEnabled(true);
					jTextTitular.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaDadosFinanceiros,
							"Preencha os campos agência e número da conta corretamente!!");
				}
			}
		});
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janelaDadosFinanceiros, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int agencia = Integer.parseInt(jTextAgencia.getText());
					int numero = Integer.parseInt(jTextNumero.getText());
					String titular = jTextTitular.getText().trim(); // Retira os espa�os em branco
					if (titular.length() == 0) {
						JOptionPane.showMessageDialog(janelaDadosFinanceiros, "Preencha o campo titular");
						jTextTitular.requestFocus();
					} else {
						if (!conta.consultarConta(agencia, numero)) {
							if (!conta.cadastrarConta(agencia, numero, titular))
								JOptionPane.showMessageDialog(janelaDadosFinanceiros, "Erro na inclusão do titular!");
							else
								JOptionPane.showMessageDialog(janelaDadosFinanceiros, "Inclusão realizada!");
						} else {
							if (!conta.atualizarConta(agencia, numero, titular))
								JOptionPane.showMessageDialog(janelaDadosFinanceiros, "Erro na atualização do titular!");
							else
								JOptionPane.showMessageDialog(janelaDadosFinanceiros, "Alteração realizada!");
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
		return janelaDadosFinanceiros;
	}
}
