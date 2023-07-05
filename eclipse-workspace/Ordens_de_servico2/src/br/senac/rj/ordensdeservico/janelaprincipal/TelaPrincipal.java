package br.senac.rj.ordensdeservico.janelaprincipal;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.ordensdeservico.janelas.JanelaIncluirOS;

public class TelaPrincipal {

	// Cria uma janela
	public static void apresentarJanelaPrincipal() {
		//Define a janela
		JFrame janelaPrincipal = new JFrame("Controle de Ordens de Serviço"); // Janela Normal
		janelaPrincipal.setResizable(true);
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300);
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "Não");
		
		//Cria uma barra de menu
		JMenuBar barraDeMenu = new JMenuBar();
		//Adiciona a barra de menu criada ao frame
		janelaPrincipal.setJMenuBar(barraDeMenu);
				
		//Define e adiciona um menu na barra de menu
		JMenu menuRegistros = new JMenu("Registros");
		//menuRegistros.add(barraDeMenu);
		barraDeMenu.add(menuRegistros);
		
		
		//Cria e adiciona um item simples no menu
		JMenuItem itemIncluirOS = new JMenuItem("Incluir Ordem de Serviço");
		menuRegistros.add(itemIncluirOS);
		
		JMenuItem itemConsultarOS = new JMenuItem("Consultar Ordem de Serviço");
		menuRegistros.add(itemConsultarOS);
		
		//Cria a janela que abrirá a partir do item do menu criado anteriormente
		JFrame janelaIncluirOS = JanelaIncluirOS.criarNovaJanela(); // o método criarJanela deve estar na classe ClaseDaJanelaASerCriada
		//*** Adiciona ação para o item do menu
		itemIncluirOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaIncluirOS.setVisible(true);
			}
		});
		
		janelaPrincipal.setVisible(true);
			}

	//***Inicia a execução da função

	public static void main(String[] args) {
		apresentarJanelaPrincipal();
	}
}
