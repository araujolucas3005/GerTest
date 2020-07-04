package model.VO;
import java.util.Random;

public class DisciplinaVO {
	private String nome;
	private String codigo;
	private String[] assuntos;

	public DisciplinaVO() {
		this.nome = "Disciplina sem nome";
		this.codigo = gerarCodigoAleatorio();
		this.assuntos = new String[1];
		this.assuntos[0] = "Disciplina sem assunto";
	}

	public DisciplinaVO(String nome, String codigo, String[] assuntos) {
		this.setNome(nome);
		this.setCodigo(codigo);
		this.setAssuntos(assuntos);
	}
	
	// Quando o usuário não define o código da disciplina
	public DisciplinaVO(String nome, String[] assuntos) {
		this.setNome(nome);
		this.codigo = gerarCodigoAleatorio();
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
		if (testeCodigoFormatoCorreto(codigo)) {
			this.codigo = codigo;
		} else {
			this.codigo = gerarCodigoAleatorio();
		}
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

	private boolean testeCodigoFormatoCorreto(String codigo) {
		if (codigo != null && codigo.length() == 7) {
			boolean testeFinal = true;
			int i = 0;
			while (testeFinal && i<7) {
				boolean testeLetra = Character.isUpperCase(codigo.charAt(i));
				boolean testeNumero = Character.isDigit(codigo.charAt(i));
				if ((i < 3 && testeLetra == false) || (i > 3 && testeNumero == false)) {
					testeFinal = false;
				}
				++i;
			}
			return testeFinal;
		}
		return false;
	}

	private String gerarCodigoAleatorio() {
		String codigoGerado = new String();
		Random gerador = new Random();

		// Gera aleatoriamente as primeiras 3 letras do código
		for (int i = 0; i < 3; i++) {
			String letraGerada = String.valueOf((char) (gerador.nextInt(26) + 'A'));
			codigoGerado += letraGerada;
		}
		
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
		modeloString = "\nNome: " + this.nome;
		modeloString = "\nCodigo: "+ this.codigo;
		modeloString = "\nAssuntos: \n";

		for (int i = 0; i < this.assuntos.length; i++) {
			modeloString += String.valueOf(i + 1) + ". " + this.assuntos[i] + "\n";
		}

		return modeloString;
	}
}
