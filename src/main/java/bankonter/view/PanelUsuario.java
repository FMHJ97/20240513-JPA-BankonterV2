package bankonter.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import bankonter.controller.ControladorUsuarioJPA;
import bankonter.model.Usuario;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class PanelUsuario extends JPanel implements DialogablePanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltro;
	private JButton btnFiltrar;
	private JDialog dialog;
	private DefaultListModel<Usuario> dlm;
	private List<Usuario> usuarios = (List<Usuario>) ControladorUsuarioJPA
			.getInstance().findAll();
	private List<Usuario> usuariosFiltrados = new ArrayList<Usuario>();
	
	// Puntero al PanelContrato para obtener acceso al Contrato actual
	// (current) y establecerle el tipo de contrato selccionado.
	
	private PanelContrato panelContrato;
	private Usuario selectedUser;
	
	private JPanel panelButtonGroup;
	private JLabel lblNewLabel_2;
	private JCheckBox jchbCSensitive;
	private JRadioButton jrbNombreUsuario;
	private JRadioButton jrbEmail;
	private JScrollPane scrollPane;
	private JList<Usuario> list;
	private JButton btnSelectUser;

	public void setPanelContrato(PanelContrato panelContrato) {
		this.panelContrato = panelContrato;
	}
	
	/**
	 * Create the panel.
	 * @param dialogo 
	 */
	public PanelUsuario() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Usuarios");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(20, 0, 15, 0);
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Filtro:");
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
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filterList();
			}
		});
		btnFiltrar.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 15, 5, 10);
		gbc_btnFiltrar.gridx = 2;
		gbc_btnFiltrar.gridy = 1;
		add(btnFiltrar, gbc_btnFiltrar);
		
		lblNewLabel_2 = new JLabel("Campo:");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		panelButtonGroup = new JPanel();
		GridBagConstraints gbc_panelButtonGroup = new GridBagConstraints();
		gbc_panelButtonGroup.insets = new Insets(0, 0, 5, 5);
		gbc_panelButtonGroup.fill = GridBagConstraints.BOTH;
		gbc_panelButtonGroup.gridx = 1;
		gbc_panelButtonGroup.gridy = 2;
		add(panelButtonGroup, gbc_panelButtonGroup);
		panelButtonGroup.setLayout(new BoxLayout(panelButtonGroup, BoxLayout.Y_AXIS));
		
		jrbNombreUsuario = new JRadioButton("Nombre de Usuario");
		panelButtonGroup.add(jrbNombreUsuario);
		
		jrbEmail = new JRadioButton("Email");
		panelButtonGroup.add(jrbEmail);
		
		jchbCSensitive = new JCheckBox("Case Sensitive?");
		GridBagConstraints gbc_jchbCSensitive = new GridBagConstraints();
		gbc_jchbCSensitive.anchor = GridBagConstraints.WEST;
		gbc_jchbCSensitive.insets = new Insets(0, 0, 5, 5);
		gbc_jchbCSensitive.gridx = 1;
		gbc_jchbCSensitive.gridy = 3;
		add(jchbCSensitive, gbc_jchbCSensitive);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(20, 10, 20, 10);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		
		btnSelectUser = new JButton("Seleccionar Usuario");
		btnSelectUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectUsuarioFromList();
			}
		});
		btnSelectUser.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_btnSelectUser = new GridBagConstraints();
		gbc_btnSelectUser.insets = new Insets(0, 10, 20, 10);
		gbc_btnSelectUser.gridx = 1;
		gbc_btnSelectUser.gridy = 5;
		add(btnSelectUser, gbc_btnSelectUser);
		
		/*
		 * Precarga de Datos Y Componentes
		 */
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrbNombreUsuario);
		bg.add(jrbEmail);
		jrbNombreUsuario.setSelected(true);
		
		
		this.dlm = getDefaultListModel();
		this.list = new JList<Usuario>(dlm);
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.scrollPane.setViewportView(list);
		
		// Cargamos los usuarios al comienzo.
		loadUsuariosIntoJList(usuarios);
	}
	
	/**
	 * 
	 */
	private void selectUsuarioFromList() {
		if (this.list.getSelectedValue() != null) {
			this.selectedUser = this.list.getSelectedValue();
			this.panelContrato.getCurrent()
				.setUsuario(selectedUser);
			// Cerramos el JDialog.
			this.dialog.dispose();
		} else {
			JOptionPane.showMessageDialog(null,
					"Seleccione un usuario");
		}
	}
	
	/**
	 * 
	 */
	private void filterList() {
		String str = this.jtfFiltro.getText();
		
		if (str.isEmpty()) {
			loadUsuariosIntoJList(usuarios);
			return;
		}
		
		usuariosFiltrados.clear();
		
		for (Usuario usuario : usuarios) {
			if (jchbCSensitive.isSelected()) {
				if (this.jrbNombreUsuario.isSelected()) {
					
					if (usuario.getNombreUsuario().contains(str.trim())) {
						usuariosFiltrados.add(usuario);
					}
					
				} else {
					
					if (usuario.getEmail().contains(str.trim())) {
						usuariosFiltrados.add(usuario);
					}
					
				}
			} else {
				if (this.jrbNombreUsuario.isSelected()) {
					
					if (usuario.getNombreUsuario().toUpperCase()
							.contains(str.trim().toUpperCase())) {
						usuariosFiltrados.add(usuario);
					}
					
				} else {
					
					if (usuario.getEmail().toUpperCase()
							.contains(str.trim().toUpperCase())) {
						usuariosFiltrados.add(usuario);
					}
					
				}
			}
		}
		
		loadUsuariosIntoJList(usuariosFiltrados);
		
	}
	
	/**
	 * 
	 */
	private void loadUsuariosIntoJList(List<Usuario> list) {
		
		dlm.clear();
		
		for (Usuario usuario : list) {
			this.dlm.addElement(usuario);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private DefaultListModel<Usuario> getDefaultListModel() {
		this.dlm = new DefaultListModel<Usuario>();
		return this.dlm;
	}

	@Override
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

}
