package bankonter;

import javax.swing.JFrame;

import bankonter.utils.Apariencia;
import bankonter.view.PanelContrato;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Principal instance = null;
	
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}
	
	/**
	 * Constructor.
	 */
	public Principal() {
		super("Bankonter - Version 2");
		
		this.setBounds(100, 100, 725, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		PanelContrato pc = new PanelContrato();
		
		this.getContentPane().add(pc);
	}
	
	/**
	 * Singleton.
	 * @return
	 */
	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}

	/**
	 * Método Principal.
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

}
