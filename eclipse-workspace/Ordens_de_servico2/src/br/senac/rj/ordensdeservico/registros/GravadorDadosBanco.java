package br.senac.rj.ordensdeservico.registros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;

import br.senac.rj.ordensdeservico.janelas.JanelaIncluirOS;

public class GravadorDadosBanco {
	
	private int local;
	private int tipoDeServico;
	private int Descricao;

    public static void gravarDados(int local, int tipoDeServico, String descricao) {
        String url = "jdbc:mysql://localhost:3306/banco"; // substitua "nome_do_banco" pelo nome do seu banco de dados
        String usuario = "root";
        String senha = "";

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO ordem_de_servico (local, tipo_servico, descricao) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, local);
            statement.setInt(2, tipoDeServico);
            statement.setString(3, descricao);
            statement.executeUpdate();
            System.out.println("Dados gravados com sucesso no banco de dados!");
        } catch (SQLException e) {
            System.out.println("Erro ao gravar os dados no banco de dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame janelaIncluirOS = JanelaIncluirOS.criarNovaJanela();
        janelaIncluirOS.setVisible(true);
        
        // Supondo que você tenha algum código que capture os valores dos campos da janela
        int local = 1; // Exemplo de valor para o campo "Local"
        int tipoDeServico = 4; // Exemplo de valor para o campo "Tipo de Serviço"
        String descricao = "Descrição da ordem de serviço"; // Exemplo de valor para o campo "Descrição"
        
        gravarDados(local, tipoDeServico, descricao);
    }
}