package bankonter.controller;

import bankonter.model.Contrato;

public class ControladorContratoJPA extends SuperControladorJPA {
	
	public ControladorContratoJPA() {
		super("contrato", Contrato.class);
	}

	private static ControladorContratoJPA instance = null;
	
	/**
	 * Singleton.
	 * @return
	 */
	public static ControladorContratoJPA getInstance() {
		if (instance == null) {
			instance = new ControladorContratoJPA();
		}
		return instance;
	}
	
}
