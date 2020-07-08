package model.VO;

import java.util.Random;

public class ExataVO extends DisciplinaVO {
	private String codigo;
	
	public ExataVO() {
		super();
		this.setCodigo(null);
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
			if (this.testeCodigoFormatoCorreto(codigo)) {
				this.codigo = codigo;
			} else {
				this.codigo = this.gerarCodigoAleatorio();
			}
		} else {
			this.codigo = this.gerarCodigoAleatorio();
		}
	}
	
	private boolean testeCodigoFormatoCorreto(String codigo) {
		if (codigo != null && codigo.length() == 7) {
			String codigoInicial = codigo.substring(0, 3);
			if (codigoInicial.equals("EXA")) {
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

	private String gerarCodigoAleatorio() {
		String codigoGerado = "EXA";
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
		String modeloString = "----Disciplina Exata----\n";
		modeloString += "Codigo: "  + this.codigo;
		modeloString += super.toString();
		return modeloString;
	}
}
