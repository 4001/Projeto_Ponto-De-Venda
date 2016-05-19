import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MercadoriaGUI extends JFrame {


	private JTextArea taLista;
	private JScrollPane pLista;

	public MercadoriaGUI() {		
		super("Mercado USJT - v.1.0");
		Container tela = getContentPane();
		tela.setLayout(new BorderLayout());

		taLista = new JTextArea();
		pLista = new JScrollPane(taLista);

		tela.add(pLista, BorderLayout.CENTER);

		Mercadoria mercadoria = new Mercadoria();
		taLista.setText(mercadoria.getAllMercadoria());
		taLista.setEditable(false);
		
		
		setSize(400, 500);
		setLocation(500, 400);
		setVisible(true);
	} 

}
