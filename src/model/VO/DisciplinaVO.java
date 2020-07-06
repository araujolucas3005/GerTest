package model.VO;

import java.util.Random;

public abstract class DisciplinaVO {
	private String nome;
	private String codigo;
	private String[] assuntos;

	public DisciplinaVO() {
		this.setNome(null);
		this.setCodigo(null);
		this.setAssuntos(null);
	}

	public DisciplinaVO(String nome, String codigo, String[] assuntos) {
		this.setNome(nome);
		this.setCodigo(codigo);
		this.setAssuntos(assuntos);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			this.nome = "Disciplina sem nome";
		} else {
			this.nome = nome;
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String[] getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(String[] assuntos) {
		if (assuntos != null) {
			for (int i = 0; i < assuntos.length; i++) {
				if (assuntos[i] == null || assuntos[i].isEmpty()) {
					assuntos[i] = "Sem assunto";
				}
			}
			this.assuntos = assuntos;
		} else {
			this.assuntos = new String[1];
			this.assuntos[0] = "Disciplina sem assunto";
		}
	}

	protected boolean testeCodigoFormatoCorreto(String codigo, String codigoInicialTipo) {
		if (codigo != null && codigo.length() == 7) {
			String codigoInicial = codigo.substring(0, 3);
			if (codigoInicial.equals(codigoInicialTipo)) {
				boolean testeFinal = true;
				int i = 3;
				while (testeFinal && i < 7) {
					boolean testeNumero = Character.isDigit(codigo.charAt(i));
					if (testeNumero == false) {
						testeFinal = false;
					}
					++i;
				}
				return testeFinal;
			}
		}
		return false;
	}

	protected String gerarCodigoAleatorio(String codigoInicialTipo) {
		String codigoGerado = codigoInicialTipo;
		Random gerador = new Random();
		// Gera aleatoriamente o número após as 3 letras do código
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

		codigoGerado += numeroGeradoString;
		return codigoGerado;
	}

	public String toString() {
		String modeloString;
		modeloString = "----Disciplina----";
		modeloString += "\nNome: " + this.nome;
		modeloString += "\nCodigo: " + this.codigo;
		modeloString += "\nAssuntos: \n";

		for (int i = 0; i < this.assuntos.length; i++) {
			modeloString += String.valueOf(i + 1) + ". " + this.assuntos[i];
		}

		return modeloString;
	}
}
