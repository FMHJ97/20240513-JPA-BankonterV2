package bankonter.controller;

import bankonter.model.Usuario;

public class ControladorUsuarioJPA extends SuperControladorJPA {
	
	public ControladorUsuarioJPA() {
		super("usuario", Usuario.class);
	}

	private static ControladorUsuarioJPA instance = null;
	
	/**
	 * Singleton.
	 * @return
	 */
	public static ControladorUsuarioJPA getInstance() {
		if (instance == null) {
			instance = new ControladorUsuarioJPA();
		}
		return instance;
	}
	
}
