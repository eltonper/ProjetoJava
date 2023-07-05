package br.senac.rj.ordensdeservico.janelas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class JanelaIncluirOS {
	public static JFrame criarNovaJanela() {
		// Define a janela
		JFrame janelaIncluirOS = new JFrame("Inclusão de Ordem de Serviço"); // Janela Normal
		janelaIncluirOS.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaIncluirOS.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaIncluirOS.setSize(400, 300); // Define tamanho da janela
		// Define o layout da janela
		Container caixa = janelaIncluirOS.getContentPane();
		caixa.setLayout(null);
		// Define os labels dos campos
		JLabel labelLocal = new JLabel("Local: ");
		JLabel labelTipoDeServico = new JLabel("Tipo de serviço: ");
		JLabel labelDescricao = new JLabel("Descrição: ");
		// Posiciona os labels na janela
		labelLocal.setBounds(50, 40, 100, 20); // coluna, linha, largura, tamanho
		labelTipoDeServico.setBounds(50, 80, 150, 20); // coluna, linha, largura, tamanho
		labelDescricao.setBounds(50, 120, 100, 20); // coluna, linha, largura, tamanho

		JComboBox<String> comboBoxLocal = new JComboBox<>();
		comboBoxLocal.addItem("");
		comboBoxLocal.addItem("1");
		comboBoxLocal.addItem("2");
		comboBoxLocal.addItem("3");
		// Posiciona o combobox
		comboBoxLocal.setBounds(180, 40, 150, 20);
		// Adiciona os rótulos e o combobox na janela
		janelaIncluirOS.add(labelLocal);
		janelaIncluirOS.add(labelTipoDeServico);
		janelaIncluirOS.add(labelDescricao);
		janelaIncluirOS.add(comboBoxLocal);

		JComboBox<String> comboBoxTipoDeServico = new JComboBox<>();
		comboBoxTipoDeServico.addItem("");
		comboBoxTipoDeServico.addItem("4");
		comboBoxTipoDeServico.addItem("5");
		comboBoxTipoDeServico.addItem("6");
		// Posiciona o combobox
		comboBoxTipoDeServico.setBounds(180, 80, 150, 20);
		// Adiciona os rótulos e o combobox na janela
		janelaIncluirOS.add(labelLocal);
		janelaIncluirOS.add(labelTipoDeServico);
		janelaIncluirOS.add(labelDescricao);
		janelaIncluirOS.add(comboBoxTipoDeServico);

		// Define o input box
		JTextField jTextDescricao = new JTextField();

		// Define se os campos est�o habilitados ou n�o no in�cio
		jTextDescricao.setEnabled(true);

		// Posiciona os input box
		jTextDescricao.setBounds(180, 120, 150, 20);

		// Adiciona os r�tulos e os input box na janela
		janelaIncluirOS.add(labelLocal);
		janelaIncluirOS.add(labelTipoDeServico);
		janelaIncluirOS.add(labelDescricao);

		janelaIncluirOS.add(jTextDescricao);

		// Define bot�es e a localiza��o deles na janela
		JButton botaoEmitir = new JButton("Emitir OS");
		botaoEmitir.setBounds(50, 200, 100, 20);
		botaoEmitir.setEnabled(true);
		janelaIncluirOS.add(botaoEmitir);
		JButton botaoCancelar = new JButton("Limpar");
		botaoCancelar.setBounds(250, 200, 100, 20);
		janelaIncluirOS.add(botaoCancelar);

		// Define objeto conta para pesquisar no banco de dados

		// Define a��es dos bot�es
		botaoEmitir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int local = Integer.parseInt((String) comboBoxLocal.getSelectedItem());
					int tipoDeServico = Integer.parseInt((String) comboBoxTipoDeServico.getSelectedItem());
					int descricao = Integer.parseInt(jTextDescricao.getText());

					botaoEmitir.setEnabled(true);


				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janelaIncluirOS, "Falha na emissão da OS");
				}
			}
		});

		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxLocal.setSelectedItem(null); // Limpar campo
				comboBoxTipoDeServico.setSelectedItem(null); // Limpar campo
				jTextDescricao.setText(""); // Limpar campo
			}
		});
		return janelaIncluirOS;
	}
}
