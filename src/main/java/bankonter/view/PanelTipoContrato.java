package bankonter.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bankonter.controller.ControladorTipoContratoJPA;
import bankonter.model.Tipocontrato;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelTipoContrato extends JPanel implements DialogablePanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltro;
	private JButton btnNewButton;
	private JTable table;
	private DefaultTableModel dtm;
	private Tipocontrato selectedTC;
	private JDialog dialog;
	private JScrollPane scrollPane;
	private List<Tipocontrato> tiposContrato = 
			(List<Tipocontrato>) ControladorTipoContratoJPA.getInstance().findAll();

	/**
	 * Create the panel.
	 * @param dialogo 
	 */
	public PanelTipoContrato() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Tipos de Contrato");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(20, 0, 15, 0);
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtro (Descripción):");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfFiltro = new JTextField();
		jtfFiltro.setFont(new Font("Dialog", Font.PLAIN, 15));
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltro.gridx = 1;
		gbc_jtfFiltro.gridy = 1;
		add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		
		btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterTipoContrato();
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 15, 5, 10);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(20, 10, 20, 10);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		/*
		 * Precarga de Datos Y Componentes
		 */
		
		// Inicializamos el DefaultTableModel.
		this.dtm = getDefaultTableModel();
		// Creo la tabla utilizando el DefaultTableModel.
		this.table = new JTable(dtm);
		// Limitamos el modo selección de filas a una única selección.
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// Agregamos la tabla al ScrollPane.
		scrollPane.setViewportView(table);
		
		// Agregamos el Listener que usaremos para escoger un
		// tipoContrato tras realizar doble clic.
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				// Se realiza doble clic.
				if (e.getClickCount() == 2) {
//					selectedTC = getTipoContratoFromTable();
					dialog.dispose();
				}
			}
		});
	}
	
	/**
	 * 
	 * @return
	 */
	private Tipocontrato getTipoContratoFromTable() {
		int indexRow = this.table.getSelectedRow();
		if (indexRow != -1) {
			Integer idTC = (Integer) dtm.getValueAt(indexRow, 0);
			return (Tipocontrato) ControladorTipoContratoJPA
					.getInstance().findById(idTC);
		}
		return null;
	}
	
	/**
	 * Obtenemos el modelo de la tabla.
	 * @return
	 */
	private DefaultTableModel getDefaultTableModel() {
		// Inicializamos el modelo de la tabla.
		DefaultTableModel dtm = 
				new DefaultTableModel(DatosDeTabla.getDatosDeTabla(tiposContrato),
						DatosDeTabla.getTitulosColumnas()) {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		return dtm;
	}

	@Override
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	private boolean isNameValid(Tipocontrato tc) {
		String str = this.jtfFiltro.getText();
		
		// Si el campo está vacío, se cargará el fichero actual
		// en la tabla.
		if (str.trim().isEmpty()) {
			return true;
		}
		
		// Si el nombre del fichero contiene la cadena de texto
		// del filtrado, se cargará en la tabla.
		if (tc.getDescripcion().toUpperCase()
				.contains(str.trim().toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 */
	private void filterTipoContrato() {
		
		List<Tipocontrato> filteredTiposContrato = new ArrayList<Tipocontrato>();
		
		for (Tipocontrato tc : tiposContrato) {
			if (isNameValid(tc)) {
				filteredTiposContrato.add(tc);
			}
		}
		
		// Actualizamos los datos de la tabla. De esta manera, mantenemos
		// el mouseListener de la tabla.
		this.dtm.setDataVector(DatosDeTabla.getDatosDeTabla(filteredTiposContrato),
				DatosDeTabla.getTitulosColumnas());
		// Se notifican los posibles cambios de las celdas de la tabla.
		this.dtm.fireTableDataChanged();
	}

}
