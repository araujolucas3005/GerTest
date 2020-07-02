package model.VO;

import java.util.Random;

public class DisciplinaVO {
	private String nome;
	private String codigo;
	String[] assuntos;

	public DisciplinaVO(String nome, String codigo, String[] assuntos) {
		this.setNome(nome);
		this.setCodigo(codigo);
		this.setAssuntos(assuntos);
	}
	
	public DisciplinaVO(String nome, String[] assuntos) {
		this.setNome(nome);
		this.setAssuntos(assuntos);
		this.codigo = gerarCodigoAleatorio();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.isEmpty()) {
			this.nome = "Disciplina sem nome";
		} else {
			this.nome = nome;
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (testeCodigoFormatoCorreto(codigo)) {
			this.codigo = codigo;
		} else {
			System.out.println("Formato errado, gerando um codigo aleatório...");
			this.codigo = gerarCodigoAleatorio();
		}
	}

	public String[] getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(String[] assuntos) {
		for (int i = 0; i < assuntos.length; i++) {
			if (assuntos[i].isEmpty()) {
				assuntos[i] = "Sem assunto";
			}
		}
		this.assuntos = assuntos;
	}

	private boolean testeCodigoFormatoCorreto(String codigo) {
		boolean testeFinal = false;
		if (codigo.length() == 7) {
			boolean[] testes = new boolean[7];
			for (int i = 0; i < 3; i++) {
				testes[i] = Character.isUpperCase(codigo.charAt(i)) & Character.isAlphabetic(codigo.charAt(i));
			}
			for (int i = 3; i < 7; i++) {
				testes[i] = Character.isDigit(codigo.charAt(i));
			}
			for (int i = 0; i < 7; i++) {
				if (testes[i] == false) {
					testeFinal = false;
					break;
				} else {
					testeFinal = true;
				}
			}
			return testeFinal;
		}
		return testeFinal;
	}
	
	private String gerarCodigoAleatorio() {
		String codigoGerado;
		Random gerador = new Random();
		
		int numeroGerado = gerador.nextInt(10000);
		String numeroGeradoString;
		if (numeroGerado < 10) {
			numeroGeradoString = "000" + String.valueOf(numeroGerado);
		} else if (numeroGerado < 100) {
			numeroGeradoString = "00" + String.valueOf(numeroGerado);
		} else if (numeroGerado < 1000) {
			numeroGeradoString = "0" + String.valueOf(numeroGerado);
		} else {
			numeroGeradoString = String.valueOf(numeroGerado);
		}
		
		String[] letrasGeradas = new String[3];
		for (int i = 0; i < 3; i++) {
			String letraGerada = String.valueOf((char)(gerador.nextInt(26) + 'A'));
			letrasGeradas[i] = letraGerada;
		}
		
		codigoGerado = letrasGeradas[0] + letrasGeradas[1] + letrasGeradas[2] + numeroGeradoString;
		return codigoGerado;
	}
	
	public String toString() {
		String modeloString;
		modeloString = "----Disciplina----" 
					 + "\nNome: " + this.nome
					 + "\nCodigo: " + this.codigo
					 + "\n----Assuntos----\n";
		
		for (int i = 0; i < this.assuntos.length; i++) {
			modeloString += String.valueOf(i) + ". " + this.assuntos[i] + "\n";
		}
		
		return modeloString;
	}
}
