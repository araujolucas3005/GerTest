package model.VO;

import java.util.Random;

public class DiscursivaVO extends QuestaoVO {
	
	private Long idQuestao;

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public DiscursivaVO() {
		super();
	}
	
	public void setGabarito(String gabarito) {
		if (gabarito != null && !gabarito.isEmpty()) {
			super.setGabarito(gabarito);
		} else {
			super.setGabarito("Questao sem gabarito!");
		}
	}
	
	public void setCodigo(String codigo) {
		if (codigo != null && !codigo.isEmpty() && this.testeCodigoFormatoCorreto(codigo)) {
			super.setCodigo(codigo);
		} else {
			super.setCodigo(this.gerarCodigoAleatorio());
		}
	}
	
	private boolean testeCodigoFormatoCorreto(String codigo) {
		if (codigo != null && codigo.length() == 5) {
			boolean testeFinal = true;
			boolean testeLetra = codigo.charAt(0) == 'D';
			if (testeLetra) {
				int i = 1;
				while (testeFinal && i < 5) {
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
		String codigoGerado;
		codigoGerado = "D";
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

		codigoGerado += numeroGeradoString;
		return codigoGerado;
	}
	
	public String toString() {
		String saida;
		saida = "----Questao Discursiva----";
		saida += super.toString();
		return saida;
	}
}
