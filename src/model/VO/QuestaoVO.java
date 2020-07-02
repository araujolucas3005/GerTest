package model.VO;

public class QuestaoVO {
		private String discip;
		private int nivel;
		private String codigo;
		private String tipo;
		private String enunciado;
		private String gabarito;
		private String assunto;
		private String[] opcoes;

		public QuestaoVO() {
			
		}
		
		public QuestaoVO(String discip, int nivel, String codigo, String tipo, String enunciado, String gabarito, String assunto, String[] opcoes) {
			this.setDiscip(discip);
			this.setNivel(nivel);
			this.setAssunto(assunto);
			this.setCodigo(codigo);
			this.setEnunciado(enunciado);
			this.setGabarito(gabarito);
			this.setTipo(tipo);
			this.setOpcoes(opcoes);
		}
		
		public String getDiscip() {
			return discip;
		}
		public void setDiscip(String discip) {
			if ((discip.isEmpty()))
				this.discip = "Questão sem disciplina";
			else
				this.discip = discip;
		}
		public int getNivel() {
			return nivel;
		}
		public void setNivel(int nivel) {
			if (nivel < 0 && nivel >= 5)
				this.nivel = 0;
			else
				this.nivel = nivel;			
		}
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			if (tipo.isEmpty())
				this.tipo = "Questão sem tipo";
			else
				this.tipo = tipo;
		}
		public String getEnunciado() {
			return enunciado;
		}
		public void setEnunciado(String enunciado) {
			if (enunciado.isEmpty())
				this.enunciado = "Questão sem enunciado";
			else
				this.enunciado = enunciado;
		}
		public String getGabarito() {
			return gabarito;
		}
		public void setGabarito(String gabarito) {
			if (gabarito.isEmpty())
				this.gabarito = "Questão sem gabarito";
			else
				this.gabarito = gabarito;
		}
		public String getAssunto() {
			return assunto;
		}
		public void setAssunto(String assunto) {
			if (assunto.isEmpty())
				this.assunto = "Questão sem assunto";
			else 
				this.assunto = assunto;
		}
		public String[] getOpcoes() {
			return opcoes;
		}
		public void setOpcoes(String[] opcoes) {
			for (int i = 0; i < opcoes.length; i++) {
				if (opcoes[i].isEmpty()) {
					opcoes[i] = "Sem opção";
				}
				else
					this.opcoes[i] = opcoes[i];
			}
		}
		
		public String toString() {
			String modeloString;
			modeloString = "----Questão----" 
						 + "\nDisciplina: " + this.discip
						 + "\nCodigo: " + this.codigo
						 + "\nNivel: " + this.nivel
						 + "\nAssunto: " + this.assunto
						 + "\nEnunciado: " + this.enunciado
						 + "\nGabarito: " + this.gabarito
						 + "\n----Opções----\n";
			
			for (int i = 0; i < this.opcoes.length; i++) {
				modeloString += String.valueOf(i) + ". " + this.opcoes[i] + "\n";
			}
			
			return modeloString;	
			
		}
}