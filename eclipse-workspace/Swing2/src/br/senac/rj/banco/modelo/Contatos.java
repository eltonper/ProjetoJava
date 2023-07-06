package br.senac.rj.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Classe que estabelece as funções de registro, alteração, exclusão e consulta dos contatos
 * @author elton
 */

public class Contatos {
	private int cpf;
	private int telefone;
	private String nome;
	public static int totalContatos;

	public Contatos() {
		this.nome = "";
		Contatos.totalContatos++;
	}

	Contatos(int cpf, int telefone) {
		this();
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCPF() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	

	public boolean cadastrarContato(int cpf, int telefone, String nome) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "insert into conta set cpf=?, telefone=?, nome=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, cpf); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
			ps.setInt(2, telefone); // Substitui o segundo par�metro da consulta pela conta informada
			ps.setString(3, nome); // Substitui o terceiro par�metro da consulta pelo titular informado
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar cliente: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	
	
	public boolean consultarContato(int cpf) {
		// Define a conex�o
		Connection conexao = null;
		try {
			conexao = Conexao.conectaBanco();
			// Define a consulta
			String sql = "select * from conta where cpf=?";
			// Prepara a consulta
			PreparedStatement ps = conexao.prepareStatement(sql);
			// Define os par�metros da consulta
			ps.setInt(1, cpf); // Substitui o primeiro par�metro da consulta pela ag�ncia informada
	
			// Executa a consulta, resultando em um objeto da classe ResultSet
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { // Verifica se n�o est� antes do primeiro registro
				System.out.println("Cliente não cadastrado");
				return false; // Conta n�o cadastrada
			} else {
				// Efetua a leitura do registro da tabela
				while (rs.next()) {
					this.cpf = rs.getInt("cpf");
					this.telefone = rs.getInt("telefone");
					this.nome = rs.getString("nome");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar a conta: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
	
	public boolean excluirContato(int cpf) {
		// Define a conex�o
		Connection conexao = null;
		try {
	        conexao = Conexao.conectaBanco();
	        String sql = "DELETE FROM conta WHERE cpf = ?";
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, cpf);
	        int totalRegistrosAfetados = ps.executeUpdate();
	        if (totalRegistrosAfetados == 0) {
	            System.out.println("Cliente não cadastrado");
	            return false;
	        } else {
	            System.out.println("Registro excluído com sucesso!");
	            return true;
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao excluir o registro: " + erro.toString());
	        return false;
	    } finally {
	        Conexao.fechaConexao(conexao);
		}
	}
	
	
	

	public boolean atualizarContato(int cpf, int telefone, String nome) {
		if (!consultarContato(cpf))
			return false;
		else {
			// Define a conex�o
			Connection conexao = null;
			try {
				// Define a conex�o
				conexao = Conexao.conectaBanco();
				// Define a consulta
				String sql = "update conta set cpf=?, telefone=?, nome=?";
				// Prepara a consulta
				PreparedStatement ps = conexao.prepareStatement(sql);
				// Define os par�metros da atualiza��o
				
				ps.setInt(1, cpf);
				ps.setInt(2, telefone);
				ps.setString(3, nome);
				
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("N�o foi feita a atualiza��o!");
				else
					System.out.println("Atualiza��o realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar a conta: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
}
