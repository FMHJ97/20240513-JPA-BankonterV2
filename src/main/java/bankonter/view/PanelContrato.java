package bankonter.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bankonter.controller.ControladorContratoJPA;
import bankonter.model.Contrato;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JSpinner;
import javax.swing.JSlider;

public class PanelContrato extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfDescripcion;
	private JTextField jtfTipoContrato;
	private JTextField jtfUsuario;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JFormattedTextField jftfFecha;
	private JSpinner spinner;
	private JSlider slider;
	private JLabel lbCantidadSaldo;
	private JButton btnTipoContrato;
	private JButton btnUsuario;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private JDialog dialogo;
	
	// Objeto que almacenará el Contrato actual mostrado.
	// Lo usaremos para acceder al 'id' del Objeto.
	private Contrato current = null;

	/**
	 * Create the panel.
	 */
	public PanelContrato() {
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("Dialog", Font.BOLD, 15));
		add(toolBar, BorderLayout.NORTH);
		
		btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showFirst();
			}
		});
		btnFirst.setIcon(new ImageIcon(PanelContrato.class.getResource("/bankonter/res/gotostart.png")));
		toolBar.add(btnFirst);
		
		btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				showPrevious();
			}
		});
		btnPrevious.setIcon(new ImageIcon(PanelContrato.class.getResource("/bankonter/res/previous.png")));
		toolBar.add(btnPrevious);
		
		btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showNext();
			}
		});
		btnNext.setIcon(new ImageIcon(PanelContrato.class.getResource("/bankonter/res/next.png")));
		toolBar.add(btnNext);
		
		btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				showLast();
			}
		});
		btnLast.setIcon(new ImageIcon(PanelContrato.class.getResource("/bankonter/res/gotoend.png")));
		toolBar.add(btnLast);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newEntry();
			}
		});
		btnNuevo.setIcon(new ImageIcon(PanelContrato.class.getResource("/bankonter/res/nuevo.png")));
		toolBar.add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(PanelContrato.class.getResource("/bankonter/res/guardar.png")));
		toolBar.add(btnGuardar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelContrato.class.getResource("/bankonter/res/eliminar.png")));
		toolBar.add(btnEliminar);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Gestión de Contratos");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(20, 0, 20, 0);
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(10, 0, 10, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfDescripcion = new JTextField();
		jtfDescripcion.setFont(new Font("Dialog", Font.PLAIN, 15));
		GridBagConstraints gbc_jtfDescripcion = new GridBagConstraints();
		gbc_jtfDescripcion.gridwidth = 2;
		gbc_jtfDescripcion.insets = new Insets(10, 0, 10, 5);
		gbc_jtfDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfDescripcion.gridx = 1;
		gbc_jtfDescripcion.gridy = 1;
		panel.add(jtfDescripcion, gbc_jtfDescripcion);
		jtfDescripcion.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha:");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(10, 0, 10, 5);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 2;
		panel.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		jftfFecha = new JFormattedTextField(new AbstractFormatter() {
			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null && value instanceof Date) {
					return sdf.format(((Date) value));
				}
				return "";
			}
			
			@Override
			public Object stringToValue(String text) throws ParseException {
				try {
					jftfFecha.setBackground(Color.WHITE);
					return sdf.parse(text);
				} catch (Exception e) {
					jftfFecha.setBackground(Color.RED);
					JOptionPane.showMessageDialog(null,
							"Error. La fecha debe tener el siguiente formato: dd/MM/yyyy");
					return null;
				}
			}
		});
		jftfFecha.setFont(new Font("Dialog", Font.PLAIN, 15));
		GridBagConstraints gbc_jftfFecha = new GridBagConstraints();
		gbc_jftfFecha.gridwidth = 2;
		gbc_jftfFecha.insets = new Insets(10, 0, 10, 5);
		gbc_jftfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jftfFecha.gridx = 1;
		gbc_jftfFecha.gridy = 2;
		panel.add(jftfFecha, gbc_jftfFecha);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Límite:");
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1_1.insets = new Insets(10, 0, 10, 5);
		gbc_lblNewLabel_1_1_1.gridx = 0;
		gbc_lblNewLabel_1_1_1.gridy = 3;
		panel.add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
		
		spinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000000, 1));
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				slider.setMaximum(
						// Si el objeto del Spinner es distinto de Null.
						// En caso contrario, establecemos un cero.
						/*
						 * Hacemos un casteo a Number, dado que podemos obtener 
						 * objetos Float o Integer.
						 */
						(spinner.getValue() != null && spinner.getValue() instanceof Number)? 
								((Number) spinner.getValue()).intValue() : 0 
				);
			}
		});
		spinner.setFont(new Font("Dialog", Font.PLAIN, 15));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.gridwidth = 2;
		gbc_spinner.insets = new Insets(10, 0, 10, 5);
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 3;
		panel.add(spinner, gbc_spinner);
		
		JLabel lblNewLabel_2 = new JLabel("Euros (€)");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(10, 5, 10, 0);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Saldo:");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_2.insets = new Insets(10, 0, 10, 5);
		gbc_lblNewLabel_1_2.gridx = 0;
		gbc_lblNewLabel_1_2.gridy = 4;
		panel.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		slider = new JSlider(0, 1000000);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				lbCantidadSaldo.setText(
						slider.getValue() + " Euros (€)");
			}
		});
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 2;
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.insets = new Insets(10, 0, 10, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 4;
		panel.add(slider, gbc_slider);
		
		lbCantidadSaldo = new JLabel("0 Euros (€)");
		lbCantidadSaldo.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lbCantidadSaldo = new GridBagConstraints();
		gbc_lbCantidadSaldo.anchor = GridBagConstraints.WEST;
		gbc_lbCantidadSaldo.insets = new Insets(10, 5, 10, 10);
		gbc_lbCantidadSaldo.gridx = 3;
		gbc_lbCantidadSaldo.gridy = 4;
		panel.add(lbCantidadSaldo, gbc_lbCantidadSaldo);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Tipo Contrato:");
		lblNewLabel_1_1_2.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1_2.insets = new Insets(10, 10, 10, 5);
		gbc_lblNewLabel_1_1_2.gridx = 0;
		gbc_lblNewLabel_1_1_2.gridy = 5;
		panel.add(lblNewLabel_1_1_2, gbc_lblNewLabel_1_1_2);
		
		jtfTipoContrato = new JTextField();
		jtfTipoContrato.setEnabled(false);
		jtfTipoContrato.setFont(new Font("Dialog", Font.PLAIN, 15));
		jtfTipoContrato.setColumns(10);
		GridBagConstraints gbc_jtfTipoContrato = new GridBagConstraints();
		gbc_jtfTipoContrato.insets = new Insets(10, 0, 10, 5);
		gbc_jtfTipoContrato.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfTipoContrato.gridx = 1;
		gbc_jtfTipoContrato.gridy = 5;
		panel.add(jtfTipoContrato, gbc_jtfTipoContrato);
		
		btnTipoContrato = new JButton("Seleccionar Tipo Contrato");
		btnTipoContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showJDialog(new PanelTipoContrato());
			}
		});
		btnTipoContrato.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_btnTipoContrato = new GridBagConstraints();
		gbc_btnTipoContrato.gridwidth = 2;
		gbc_btnTipoContrato.insets = new Insets(10, 5, 10, 10);
		gbc_btnTipoContrato.gridx = 2;
		gbc_btnTipoContrato.gridy = 5;
		panel.add(btnTipoContrato, gbc_btnTipoContrato);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Usuario:");
		lblNewLabel_1_1_2_1.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_2_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1_2_1.insets = new Insets(10, 0, 10, 5);
		gbc_lblNewLabel_1_1_2_1.gridx = 0;
		gbc_lblNewLabel_1_1_2_1.gridy = 6;
		panel.add(lblNewLabel_1_1_2_1, gbc_lblNewLabel_1_1_2_1);
		
		jtfUsuario = new JTextField();
		jtfUsuario.setFont(new Font("Dialog", Font.PLAIN, 15));
		jtfUsuario.setEnabled(false);
		jtfUsuario.setColumns(10);
		GridBagConstraints gbc_jtfUsuario = new GridBagConstraints();
		gbc_jtfUsuario.insets = new Insets(10, 0, 10, 5);
		gbc_jtfUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfUsuario.gridx = 1;
		gbc_jtfUsuario.gridy = 6;
		panel.add(jtfUsuario, gbc_jtfUsuario);
		
		btnUsuario = new JButton("Seleccionar Usuario");
		btnUsuario.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_btnUsuario = new GridBagConstraints();
		gbc_btnUsuario.gridwidth = 2;
		gbc_btnUsuario.insets = new Insets(10, 0, 10, 5);
		gbc_btnUsuario.gridx = 2;
		gbc_btnUsuario.gridy = 6;
		panel.add(btnUsuario, gbc_btnUsuario);

		showFirst();
	}
	
	/**
	 * 
	 */
	private void newEntry() {
		this.jtfDescripcion.setText("");
		this.spinner.setValue(0);
		this.slider.setValue(0);
		this.jtfTipoContrato.setText("");
		this.jtfUsuario.setText("");
		this.jftfFecha.setText("");
	}
 	
	/**
	 * 
	 */
	private void showPrevious() {
		Contrato c = (Contrato) ControladorContratoJPA
				.getInstance().findPrevious(current.getId());
		
		if (c != null) {
			current = c;
		}
		showEntry(current);
	}
	
	/**
	 * 
	 */
	private void showNext() {
		Contrato c = (Contrato) ControladorContratoJPA
				.getInstance().findNext(current.getId());
		
		if (c != null) {
			current = c;
		}
		showEntry(current);
	}
	
	/**
	 * 
	 */
	private void showLast() {
		current = (Contrato) ControladorContratoJPA
				.getInstance().findLast();
		showEntry(current);
	}
	
	/**
	 * 
	 */
	private void showFirst() {
		current = (Contrato) ControladorContratoJPA
				.getInstance().findFirst();
		showEntry(current);
	}

	/**
	 * Muestra en pantalla la información del Contrato actual.
	 * @param c Objeto Contrato
	 */
	private void showEntry(Contrato c) {
		if (c != null) {
			this.jtfDescripcion.setText(c.getDescripcion());
			this.spinner.setValue(c.getLimite());
			this.slider.setValue((int) c.getSaldo());
			
			this.jtfTipoContrato.setText(
					c.getTipocontrato().getId() + " - "
					+ c.getTipocontrato().getDescripcion());
			
			this.jtfUsuario.setText(c.getUsuario().getNombreUsuario());
			this.jftfFecha.setValue(c.getFechaFirma());
		}
	}
	
	/**
	 * 
	 * @param panel
	 */
	private void showJDialog(DialogablePanel panel) {
		// Inicializamos un JDialog.
		JDialog dialogo = new JDialog();
		
		panel.setDialog(dialogo);
		// El usuario no puede redimensionar el diálogo
		dialogo.setResizable(true);
		// título del díalogo
		dialogo.setTitle("");
		// Agregamos el panel al JDialog.
		dialogo.setContentPane((JPanel) panel);
		// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que deben y el lugar adecuado
		dialogo.pack();
		// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
		dialogo.setModal(true);
		// Centro el di�logo en pantalla
		dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
		// Muestro el di�logo en pantalla
		dialogo.setVisible(true);
	}
	
}
