package br.senac.rj.banco.teste;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.banco.janelas.JanelaAlterarContatos;
import br.senac.rj.banco.janelas.JanelaConsultarContatos;
import br.senac.rj.banco.janelas.JanelaDeletarContatos;
import br.senac.rj.banco.janelas.JanelaRegistrarContatos;

/**
 * Classe que cria a janela principal da aplicação 
 * @author elton
 */

public class TesteSwing {

	public static void apresentarMenu() {
		// Define a janela
		JFrame janelaPrincipal = new JFrame("Cadastro de cliente"); // Janela Normal
		janelaPrincipal.setResizable(false); // A janela n�o poder� ter o tamanho ajustado
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300); // Define tamanho da janela
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "Não");
		// Cria uma barra de menu para a janela principal
		JMenuBar menuBar = new JMenuBar();
		// Adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);
		// Define e adiciona menu na barra de menu
		JMenu menuAtualizar = new JMenu("Arquivo");
		menuBar.add(menuAtualizar);
		// Cria e adiciona um item simples para o menu
		JMenuItem menuConsulta = new JMenuItem("Consultar");
		JMenuItem menuRegistro = new JMenuItem("Registrar");
		JMenuItem menuAlteracao = new JMenuItem("Alterar");
		JMenuItem menuExclusao = new JMenuItem("Excluir");
		menuAtualizar.add(menuConsulta);
		menuAtualizar.add(menuRegistro);
		menuAtualizar.add(menuAlteracao);
		menuAtualizar.add(menuExclusao);
		
		// Criar a janela de atualiza��o da conta
		JFrame janelaRegistrarContatos = JanelaRegistrarContatos.criarJanelaRegistrarContatos();
		
		JFrame janelaConsultarContatos = JanelaConsultarContatos.criarJanelaConsultarContatos();
		
		JFrame janelaAlterarContatos = JanelaAlterarContatos.criarJanelaAlterarContatos();
		
		JFrame janelaDeletarContatos = JanelaDeletarContatos.criarJanelaDeletarContatos();
		
		
		
		// Adiciona a��o para o item do menu
		
		menuConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaConsultarContatos.setVisible(true);
			}
		});
		
		menuRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaRegistrarContatos.setVisible(true);
			}
		});
		
		menuAlteracao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaAlterarContatos.setVisible(true);
			}
		});
		
		menuExclusao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaDeletarContatos.setVisible(true);
			}
		});
		
		
		janelaPrincipal.setVisible(true);
	}

	public static void main(String[] args) {
		apresentarMenu();
	}
}
