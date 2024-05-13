package bankonter.controller;

import bankonter.model.Tipocontrato;

public class ControladorTipoContratoJPA extends SuperControladorJPA {
	
	public ControladorTipoContratoJPA() {
		super("tipocontrato", Tipocontrato.class);
	}

	private static ControladorTipoContratoJPA instance = null;
	
	/**
	 * Singleton.
	 * @return
	 */
	public static ControladorTipoContratoJPA getInstance() {
		if (instance == null) {
			instance = new ControladorTipoContratoJPA();
		}
		return instance;
	}
	
}
