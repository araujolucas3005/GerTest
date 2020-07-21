package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import exception.AssuntoNaoPertenceException;
import exception.CampoEmBrancoException;
import exception.DisciplinaNaoSelecionadaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.BO.AssuntoBO;
import model.BO.DisciplinaBO;
import model.BO.DiscursivaBO;
import model.BO.MultiplaEscolhaBO;
import model.BO.VerdadeiroOuFalsoBO;
import model.VO.AssuntoVO;
import model.VO.DisciplinaVO;
import model.VO.DiscursivaVO;
import model.VO.MultiplaEscolhaVO;
import model.VO.VerdadeiroOuFalsoVO;
import view.Telas;

public class FrontController6 implements Initializable{	
	@FXML private TextField codigoQuestao;
	@FXML private TextField nivelQuestao;
	@FXML private TextField assuntoQuestao;
	@FXML private TextField gabaritoQuestao;
	@FXML private TableView<DisciplinaVO> tabelaSelecao;
	@FXML private TableColumn<DisciplinaVO, String> disciplinaColuna;
	@FXML private TextArea enunciadoQuestao;	
	@FXML private RadioButton discursiva;
	@FXML private RadioButton objetiva;
	@FXML private RadioButton VoF;
	@FXML private RadioButton ME;
	@FXML private Label error2;
	
	ObservableList<DisciplinaVO> list2 = FXCollections.observableArrayList();
	AssuntoBO boA = new AssuntoBO();
	DisciplinaBO boD = new DisciplinaBO();
	DiscursivaBO discBO = new DiscursivaBO();
	MultiplaEscolhaBO meBO = new MultiplaEscolhaBO();
	VerdadeiroOuFalsoBO vofBO = new VerdadeiroOuFalsoBO();
	private static DisciplinaVO lastSelected;
	
	@Override
	public void initialize(URL local, ResourceBundle resources) {
		loadData();
	}
	
	public void loadData() {
		if (discursiva.isSelected()) {
			gabaritoQuestao.setText("Subjetivo");
			gabaritoQuestao.setEditable(false);
		}
		List<DisciplinaVO> disciplinas = boD.listar();
		list2.addAll(disciplinas);
		
		disciplinaColuna.setCellValueFactory(new PropertyValueFactory<DisciplinaVO, String>("nome"));
		tabelaSelecao.setItems(list2);
	}
	
	public static DisciplinaVO getLastSelected() {
		return lastSelected;
	}

	public static void setLastSelected(DisciplinaVO lastSelected) {
		FrontController6.lastSelected = lastSelected;
	}
	
	public void cadastro() throws Exception, SQLException  {
		if (!discursiva.isSelected() && !ME.isSelected() &&!VoF.isSelected()) {
			error2.setText("Tipo de questao nao selecionado!");
			error2.setVisible(true);
		}
		
		//Cadastro de Discursiva;
		if (discursiva.isSelected()) {
			DiscursivaVO discVO = new DiscursivaVO();
			
			//Teste para todos campos preenchidos;
			try {
			if (codigoQuestao.getText().length() > 1) {
				discVO.setCodigo(codigoQuestao.getText());	
			}
			else {
				throw new CampoEmBrancoException();
			}
			
			if (nivelQuestao.getText().length() >= 1) {
				discVO.setNivel(Integer.parseInt(nivelQuestao.getText()));			
			}			
			else {
				throw new CampoEmBrancoException();
			}

			if (enunciadoQuestao.getText().length() > 1) {
				discVO.setEnunciado(enunciadoQuestao.getText());			
			}
			else {
				throw new CampoEmBrancoException();
			}

			if (gabaritoQuestao.getText().length() >= 1) {
				discVO.setGabarito(gabaritoQuestao.getText());		
				}
			else {
				throw new CampoEmBrancoException();
			}
			
			discVO.setTipo("Discursiva");
			
			//Verificação para disciplina selecionada;
			if (tabelaSelecao.getSelectionModel().getSelectedItem() == null) {
				throw new DisciplinaNaoSelecionadaException();
			}
			else {
				discVO.setIdDisciplina((tabelaSelecao.getSelectionModel().getSelectedItem().getId()));
				
				//Teste para assunto cadastrado e membro de Disciplina;
				if (assuntoQuestao.getText().length() > 1) {
					try {
						List<AssuntoVO> assuntos = boA.listarPorDisciplina(tabelaSelecao.getSelectionModel().getSelectedItem());
						int tamanho = assuntos.size();
						for (int i = 0; i < tamanho; i++) {
							if (assuntos.get(i).getConteudo().equals(assuntoQuestao.getText())) {
								discVO.setIdAssunto(assuntos.get(i).getId());
								discBO.cadastrar(discVO);
								Telas.telaQuestoes();
							}
							else {
								throw new AssuntoNaoPertenceException();
								}
							}					
						} 
					catch (DisciplinaNaoSelecionadaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}			
				}
				
				else {
					throw new CampoEmBrancoException();
				}				
			}				
		}
		catch (CampoEmBrancoException e) {
			error2.setText("Há campos em branco!");
			error2.setVisible(true);
		}
		catch (AssuntoNaoPertenceException e) {
			error2.setText("Assunto nao existe ou não pertence a disciplina selecionada!");
			error2.setVisible(true);
		}
		catch (DisciplinaNaoSelecionadaException e) {
			error2.setText("Disciplina nao selecionada!");
			error2.setVisible(true);
			}		
		}
		
		//Cadastro de ME;
		if (ME.isSelected()) {
			MultiplaEscolhaVO meVO = new MultiplaEscolhaVO();
			
			//Teste para todos campos preenchidos;
			try {
			if (codigoQuestao.getText().length() > 1) {
				meVO.setCodigo(codigoQuestao.getText());	
			}
			else {
				throw new CampoEmBrancoException();
			}
			
			if (nivelQuestao.getText().length() >= 1) {
				meVO.setNivel(Integer.parseInt(nivelQuestao.getText()));			
			}			
			else {
				throw new CampoEmBrancoException();
			}

			if (enunciadoQuestao.getText().length() > 1) {
				meVO.setEnunciado(enunciadoQuestao.getText());			
			}
			else {
				throw new CampoEmBrancoException();
			}

			if (gabaritoQuestao.getText().length() >= 1) {
				meVO.setGabarito(gabaritoQuestao.getText());		
				}
			else {
				throw new CampoEmBrancoException();
			}
			
			meVO.setTipo("Multipla Escolha");
			
			//Verificação para disciplina selecionada;
			if (tabelaSelecao.getSelectionModel().getSelectedItem() == null) {
				throw new DisciplinaNaoSelecionadaException();
			}
			else {
				meVO.setIdDisciplina((tabelaSelecao.getSelectionModel().getSelectedItem().getId()));
				
				//Teste para assunto cadastrado e membro de Disciplina;
				if (assuntoQuestao.getText().length() > 1) {
					try {
						List<AssuntoVO> assuntos = boA.listarPorDisciplina(tabelaSelecao.getSelectionModel().getSelectedItem());
						int tamanho = assuntos.size();
						for (int i = 0; i < tamanho; i++) {
							if (assuntos.get(i).getConteudo().equals(assuntoQuestao.getText())) {
								meVO.setIdAssunto(assuntos.get(i).getId());
								meBO.cadastrar(meVO);
								Telas.telaQuestoes();
							}
							else {
								throw new AssuntoNaoPertenceException();
								}
							}					
						} 
					catch (DisciplinaNaoSelecionadaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}			
				}
				
				else {
					throw new CampoEmBrancoException();
				}				
			}				
		}
		catch (CampoEmBrancoException e) {
			error2.setText("Há campos em branco!");
			error2.setVisible(true);
		}
		catch (AssuntoNaoPertenceException e) {
			error2.setText("Assunto nao existe ou não pertence a disciplina selecionada!");
			error2.setVisible(true);
		}
		catch (DisciplinaNaoSelecionadaException e) {
			error2.setText("Disciplina nao selecionada!");
			error2.setVisible(true);
			}		
		}
		
		//Cadastro VoF;
		if (VoF.isSelected()) {
			VerdadeiroOuFalsoVO vofVO = new VerdadeiroOuFalsoVO();
			
			//Teste para todos campos preenchidos;
			try {
			if (codigoQuestao.getText().length() > 1) {
				vofVO.setCodigo(codigoQuestao.getText());	
			}
			else {
				throw new CampoEmBrancoException();
			}
			
			if (nivelQuestao.getText().length() >= 1) {
				vofVO.setNivel(Integer.parseInt(nivelQuestao.getText()));			
			}			
			else {
				throw new CampoEmBrancoException();
			}

			if (enunciadoQuestao.getText().length() > 1) {
				vofVO.setEnunciado(enunciadoQuestao.getText());			
			}
			else {
				throw new CampoEmBrancoException();
			}

			if (gabaritoQuestao.getText().length() >= 1) {
				vofVO.setGabarito(gabaritoQuestao.getText());		
				}
			else {
				throw new CampoEmBrancoException();
			}
			
			vofVO.setTipo("V ou F");
			
			//Verificação para disciplina selecionada;
			if (tabelaSelecao.getSelectionModel().getSelectedItem() == null) {
				throw new DisciplinaNaoSelecionadaException();
			}
			else {
				vofVO.setIdDisciplina((tabelaSelecao.getSelectionModel().getSelectedItem().getId()));
				
				//Teste para assunto cadastrado e membro de Disciplina;
				if (assuntoQuestao.getText().length() > 1) {
					try {
						List<AssuntoVO> assuntos = boA.listarPorDisciplina(tabelaSelecao.getSelectionModel().getSelectedItem());
						int tamanho = assuntos.size();
						for (int i = 0; i < tamanho; i++) {
							if (assuntos.get(i).getConteudo().equals(assuntoQuestao.getText())) {
								vofVO.setIdAssunto(assuntos.get(i).getId());
								vofBO.cadastrar(vofVO);
								Telas.telaQuestoes();
							}
							else {
								throw new AssuntoNaoPertenceException();
								}
							}					
						} 
					catch (DisciplinaNaoSelecionadaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}			
				}
				
				else {
					throw new CampoEmBrancoException();
				}				
			}				
		}
		catch (CampoEmBrancoException e) {
			error2.setText("Há campos em branco!");
			error2.setVisible(true);
		}
		catch (AssuntoNaoPertenceException e) {
			error2.setText("Assunto nao existe ou não pertence a disciplina selecionada!");
			error2.setVisible(true);
		}
		catch (DisciplinaNaoSelecionadaException e) {
			error2.setText("Disciplina nao selecionada!");
			error2.setVisible(true);
			}		
		}
	}
	
	public void desativaObjetiva() {
		objetiva.setSelected(false);
		ME.setVisible(false);
		ME.setSelected(false);
		VoF.setVisible(false);
		VoF.setSelected(false);
		gabaritoQuestao.setText("Subjetivo");
		gabaritoQuestao.setEditable(false);
	}
	
	public void desativaDiscursiva() {
		discursiva.setSelected(false);
		gabaritoQuestao.setText("");
		gabaritoQuestao.setEditable(true);
		if (objetiva.isSelected())
		{
			ME.setVisible(true);
			VoF.setVisible(true);
		}
		else {
			ME.setVisible(false);
			ME.setSelected(false);
			VoF.setVisible(false);
			VoF.setSelected(false);
		}
		
	}
	
	public void desativaME() {
		ME.setSelected(false);
	}
	
	public void desativaVOF() {
		VoF.setSelected(false);
	}
	
	public void retornar() throws Exception {
		Telas.telaQuestoes();
	}
	
}