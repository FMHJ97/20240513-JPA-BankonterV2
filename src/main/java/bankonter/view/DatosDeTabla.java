package bankonter.view;

import java.util.List;

import bankonter.controller.ControladorTipoContratoJPA;
import bankonter.model.Tipocontrato;

public class DatosDeTabla {

	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Id", "Descripción"};
	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla(List<Tipocontrato> lista) {
		
		List<Tipocontrato> tiposContrato = lista;
		
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[tiposContrato.size()][2];
		
		for (int i = 0; i < tiposContrato.size(); i++) {
			Tipocontrato tc = tiposContrato.get(i);
			
			datos[i][0] = tc.getId();
			datos[i][1] = tc.getDescripcion();
		}
		
		return datos;
	}
	
	
}
