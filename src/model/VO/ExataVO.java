package model.VO;

import java.util.Random;

public class ExataVO extends DisciplinaVO {
	
	public ExataVO() {
		super();
	}
	
	public ExataVO(String nome, String codigo) {
		super(nome, codigo);
	}
	
	public void setCodigo(String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
			if (this.testeCodigoFormatoCorreto(codigo)) {
				super.setCodigo(codigo);
			} else {
				super.setCodigo(this.gerarCodigoAleatorio());
			}
		} else {
			super.setCodigo(this.gerarCodigoAleatorio());
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
}