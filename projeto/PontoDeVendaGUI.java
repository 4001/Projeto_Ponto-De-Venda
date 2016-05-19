import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PontoDeVendaGUI extends JFrame implements ActionListener {
	private JButton bNovaCompra;
	private JButton bCancelaCompra;
	private JButton bFechaCompra;
	private JButton bAdicionaMercadoria;
	private JButton bCancelaMercadoria;
	private JButton bExibeMercadorias;
	private JButton bExibeMaisVendidos;
	private JButton bFechaPDV;

	private JLabel lTotalCompra;
	private JLabel lTotalCompraValor;
	private JLabel lCodigoMercadoria;
	private JTextField tCodigoMercadoria;
	private JLabel lQuantidadeMercadoria;
	private JTextField tQuantidadeMercadoria;

	private JTextArea taMonitor;
	JScrollPane pMonitor;
	private PontoDeVenda pdv;
	private boolean exibeMercadorias = true;
	MercadoriaGUI lm;
	Container principal;

	public PontoDeVendaGUI() {
		super("Mercado USJT - Ponto de Venda (PDV)");
		principal = getContentPane();
		principal.setLayout(new BorderLayout());

		JPanel pGridEsquerdo = new JPanel(new GridLayout(6, 1));
		bNovaCompra = new JButton("Nova Compra");
		bCancelaCompra = new JButton("Cancelar Compra");
		bFechaCompra = new JButton("Fechar Compra");
		bFechaPDV = new JButton("Fechar PDV");
		lTotalCompra = new JLabel(" Total Compra:");
		lTotalCompraValor = new JLabel("  R$ 0,00");
		bNovaCompra.addActionListener(this);
		bCancelaCompra.addActionListener(this);
		bFechaCompra.addActionListener(this);
		bFechaPDV.addActionListener(this);
		pGridEsquerdo.add(bNovaCompra);
		pGridEsquerdo.add(bCancelaCompra);
		pGridEsquerdo.add(bFechaCompra);
		pGridEsquerdo.add(lTotalCompra);
		pGridEsquerdo.add(lTotalCompraValor);
		pGridEsquerdo.add(bFechaPDV);
		principal.add(pGridEsquerdo, BorderLayout.WEST);

		// Adiciona GRID Direito
		JPanel pGridDireito = new JPanel(new GridLayout(8, 1));

		// Cria os Botoões
		bAdicionaMercadoria = new JButton("Adiciona Mercadoria");
		bCancelaMercadoria = new JButton("Cancela Mercadoria");
		bExibeMercadorias = new JButton("  Exibir Mercadorias   ");
		bExibeMaisVendidos = new JButton("Exibir TOP 10");
		// Adiciona o ActionListener
		bAdicionaMercadoria.addActionListener(this);
		bCancelaMercadoria.addActionListener(this);
		bExibeMercadorias.addActionListener(this);
		bExibeMaisVendidos.addActionListener(this);
		
		// Crias os Labels e TextFields
		lCodigoMercadoria = new JLabel(" Código Mercadoria");
		tCodigoMercadoria = new JTextField(10);
		lQuantidadeMercadoria = new JLabel(" Quantidade Mercadoria");
		tQuantidadeMercadoria = new JTextField(10);

		// Adiciona ao Grid Direito
		pGridDireito.add(lCodigoMercadoria);
		pGridDireito.add(tCodigoMercadoria);
		pGridDireito.add(lQuantidadeMercadoria);
		pGridDireito.add(tQuantidadeMercadoria);
		pGridDireito.add(bAdicionaMercadoria);
		pGridDireito.add(bCancelaMercadoria);
		pGridDireito.add(bExibeMercadorias);
		pGridDireito.add(bExibeMaisVendidos);
		
		// adiciona Grid Direito ao Painel Principal
		principal.add(pGridDireito, BorderLayout.EAST);

		// Cria TextArea Central
		taMonitor = new JTextArea();
		pMonitor = new JScrollPane(taMonitor);

		// adiciona TextArea ao Painel Principal
		principal.add(pMonitor, BorderLayout.CENTER);

		// cria o ponto de venda (baixo acoplamento)
		pdv = new PontoDeVenda();

		// ajustes da janela - JFrame
		setSize(800, 450);
		setLocation(50, 200);
		setVisible(true);

		inicio();

	} // fim do construtor

	private void inicio() {

		taMonitor.setText(pdv.inicializaPDV());
		taMonitor.setEditable(false);;
		tQuantidadeMercadoria.setText("1");
		tQuantidadeMercadoria.setEnabled(false);
		tCodigoMercadoria.setEnabled(false);
		bAdicionaMercadoria.setEnabled(false);
		bCancelaMercadoria.setEnabled(false);
		bNovaCompra.grabFocus();

	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == bFechaPDV) {
			if(pdv.isCompraEmAndamento()){
				int dialogResult = JOptionPane.showConfirmDialog (null, "A compra atual não está fechada! Deseja mesmo sair do PDV?","Atenção",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					
					if(!exibeMercadorias){
						lm.setVisible(false);
						lm.dispose();
					}
					
					setVisible(false);
					dispose();
				}
			}else{
				if(!exibeMercadorias){
					lm.setVisible(false);
					lm.dispose();
				}
				setVisible(false);
				dispose();
			}
		}
		if (evento.getSource() == bNovaCompra) {
			taMonitor.setText("");
			add(pdv.novaCompra());
			tQuantidadeMercadoria.setEnabled(true);
			tCodigoMercadoria.setEnabled(true);
			bAdicionaMercadoria.setEnabled(true);
			bCancelaMercadoria.setEnabled(true);
			tCodigoMercadoria.grabFocus();
		}
		if (evento.getSource() == bAdicionaMercadoria) {

			int codigo = 0;
			int quantidade = 0;
			String retorno = "";
			try {
				codigo = Integer.parseInt(tCodigoMercadoria.getText());
				quantidade = Integer.parseInt(tQuantidadeMercadoria.getText());

				retorno = pdv.adicionarItemNaCompra(codigo, quantidade);
				if (retorno.indexOf("Atenção:") > -1) {
					JOptionPane.showMessageDialog(this, retorno);
				} else {
					add(retorno);
					lTotalCompraValor.setText("  R$ " + pdv.getTotalCompra());
					tQuantidadeMercadoria.setText("1");
					tCodigoMercadoria.grabFocus();
				}

			} catch (NumberFormatException e) {

				JOptionPane
						.showMessageDialog(this,
								"Código e quantidade da mercadoria devem ser numéricos inteiros !");
			}

		}
		if (evento.getSource() == bCancelaCompra) {
			if(pdv.isCompraEmAndamento()){
				int dialogResult = JOptionPane.showConfirmDialog (null, "Todos os dados da compra seram perdidos! Deseja mesmo cancelar toda a compra?","Atenção",JOptionPane.YES_NO_OPTION);
				String resultado ="";
				if(dialogResult == JOptionPane.YES_OPTION){
					resultado = pdv.cancelarCompra();
				}
				add(resultado);
				reInicio();
			}
		}
		
		if (evento.getSource() == bCancelaMercadoria) {
			
			int codigo = 0;
			try{
				codigo = Integer.parseInt(tCodigoMercadoria.getText());
				int dialogResult = JOptionPane.showConfirmDialog (null, "A última compra da mercadoria "+tCodigoMercadoria.getText()+" será retirada da compra! Confirma ?","Atenção",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					String resultado = pdv.cancelarItemCompra(codigo);
					if(resultado.indexOf("Atenção:")>-1){
						JOptionPane
						.showMessageDialog(this,
								"Mercadoria não localizada na compra corrente !");
					}else{
						add(resultado);
						lTotalCompraValor.setText("  R$ " + pdv.getTotalCompra());
					}
				}
				
			}catch (NumberFormatException e) {

				JOptionPane
						.showMessageDialog(this,
								"Código da mercadoria deve ser numérico e inteiro !");
			}
			
		}
		if (evento.getSource() == bFechaCompra) {
			if(pdv.isCompraEmAndamento()){
				int dialogResult = JOptionPane.showConfirmDialog (null, "Confirma?","Atenção",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					add(pdv.fecharCompra());
					
					Object[] options = {"Cartão",
		                    "Dinheiro",};
					int opcao = JOptionPane.showOptionDialog(principal, "Selecione o meio de pagamento:", "Pagamento", JOptionPane.YES_NO_CANCEL_OPTION,
						    JOptionPane.QUESTION_MESSAGE, null,
						    options,
						    options[0]);
					String resultado="";
					if(opcao==0){
						String numeroCartao = JOptionPane.showInputDialog("Entre número do cartão:");
						String validade = JOptionPane.showInputDialog("Entre validade do cartão (MM/AA):");
						resultado = pdv.pagarComCartao(numeroCartao, validade);
					}
					if(opcao==1){
						double valorRecebido = Double.parseDouble(JOptionPane.showInputDialog("Valor recebido (R$):"));
						resultado = pdv.pagarEmDinheiro(valorRecebido);
					}
					add(resultado);
					reInicio();
				}
			}
		}
		if (evento.getSource() == bExibeMercadorias) {
			if(exibeMercadorias){
				bExibeMercadorias.setText("Esconder Mercadorias");
				exibeMercadorias = false;
			lm = new MercadoriaGUI();
			lm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}else{
				bExibeMercadorias.setText("  Exibir Mercadorias   ");
				exibeMercadorias = true;
				lm.setVisible(false);
				lm.dispose();
			}
		}
		if (evento.getSource() == bExibeMaisVendidos) {

			JOptionPane.showMessageDialog(principal,pdv.selecionaMaisVendidos());

		}

	}

	private void add(String str) {
		taMonitor.append(str);
		taMonitor.update(taMonitor.getGraphics());
	}
	
	private void reInicio(){
		tQuantidadeMercadoria.setText("1");
		tQuantidadeMercadoria.setEnabled(false);
		tCodigoMercadoria.setEnabled(false);
		bAdicionaMercadoria.setEnabled(false);
		bCancelaMercadoria.setEnabled(false);
		bNovaCompra.grabFocus();
	}
}
