import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

public class VentanaPpal extends JFrame {

	private Vista vista;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem menuAnadirClave;
	private JMenuItem menuModificarclave;
	private JMenuItem menuEliminarClave;
	private JMenuItem menuBloquear;
	private JMenuItem menuSalir;
	private JScrollPane scrollPane;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JSeparator separator_1;
	private JMenuItem menuCambiarContrasena;
	private JToolBar toolBar;
	private JButton botonAgregarEntrada;
	private JButton botonModificarEntrada;
	private JButton botonEliminarEntrada;
	private JButton botonAbrir;
	private JMenu mnClaves_1;
	private JMenuItem menuAbrirUrl;
	private JSeparator separator;
	private String claveSecreta;
	private JMenuItem menuImportar;
	private JMenuItem menuExportar;
	private JSeparator separator_2;
	private JFileChooser selector;
	private FileNameExtensionFilter filtro;
	private JButton botonCopiarClave;
	private JButton botonPegar;
	private Clipboard cb;

	public VentanaPpal(Vista vista) {
		setMinimumSize(new Dimension(600, 300));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPpal.class.getResource("/img/logo.png")));
		setTitle("KeyRing");
		this.vista = vista;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);

		this.cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		selector = new JFileChooser();
		filtro = new FileNameExtensionFilter("Archivos XML", "xml");
		selector.addChoosableFileFilter(filtro);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);

		int ancho = 23, alto = 19;
		menuBloquear = new JMenuItem("Bloquear");
		URL urlBloquear = VentanaPpal.class.getResource("/img/bloquear.png");
		ImageIcon iconoOriginalBloquear = new ImageIcon(urlBloquear);
		menuBloquear.setIcon(new ImageIcon(iconoOriginalBloquear.getImage().getScaledInstance(ancho - 5, alto, java.awt.Image.SCALE_DEFAULT)));
		menuBloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonBloquearPulsado();
			}
		});

		menuImportar = new JMenuItem("Importar");
		menuImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonImportarPulsado();
			}
		});
		URL url = VentanaPpal.class.getResource("/img/importar.png");
		ImageIcon iconoOriginal = new ImageIcon(url);
		menuImportar.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(ancho - 5, alto, java.awt.Image.SCALE_DEFAULT)));
		mnNewMenu.add(menuImportar);

		menuExportar = new JMenuItem("Exportar");
		menuExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonExportarPulsado();
			}
		});
		url = VentanaPpal.class.getResource("/img/exportar.png");
		iconoOriginal = new ImageIcon(url);
		menuExportar.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(ancho - 5, alto, java.awt.Image.SCALE_DEFAULT)));
		mnNewMenu.add(menuExportar);

		separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);
		mnNewMenu.add(menuBloquear);

		menuSalir = new JMenuItem("Salir");
		URL urlSalir = VentanaPpal.class.getResource("/img/salir.png");
		ImageIcon iconoOriginalSalir = new ImageIcon(urlSalir);
		menuSalir.setIcon(new ImageIcon(iconoOriginalSalir.getImage().getScaledInstance(ancho - 5, alto, java.awt.Image.SCALE_DEFAULT)));
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonSalirPulsado();
			}
		});

		menuCambiarContrasena = new JMenuItem("Cambiar contrase\u00F1a");
		menuCambiarContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonCambiarClavePulsado();
			}
		});
		mnNewMenu.add(menuCambiarContrasena);

		separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		mnNewMenu.add(menuSalir);

		mnClaves_1 = new JMenu("Claves");
		menuBar.add(mnClaves_1);

		menuAnadirClave = new JMenuItem("A\u00F1adir clave");
		URL urlAgregar = VentanaPpal.class.getResource("/img/agregar_clave.png");
		ImageIcon iconoOriginalAgregar = new ImageIcon(urlAgregar);
		menuAnadirClave.setIcon(new ImageIcon(iconoOriginalAgregar.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT)));
		mnClaves_1.add(menuAnadirClave);

		menuModificarclave = new JMenuItem("Modificar clave");
		mnClaves_1.add(menuModificarclave);

		menuEliminarClave = new JMenuItem("Eliminar clave");
		mnClaves_1.add(menuEliminarClave);

		menuAbrirUrl = new JMenuItem("Abrir URL");
		menuAbrirUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonAbrirURLPulsado();
			}
		});

		separator = new JSeparator();
		mnClaves_1.add(separator);
		mnClaves_1.add(menuAbrirUrl);
		menuEliminarClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonEliminarPulsado();
			}
		});
		menuModificarclave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonModificarClavePulsado();
			}
		});
		menuAnadirClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonAnadirClavePulsado();
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tabla = new JTable();
		modeloTabla = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"T\u00EDtulo", "Usuario", "Contraseña", "URL", "Notas", "", ""
				}
				)
		{
			boolean[] columnEditables = new boolean[] {
					true, true, false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tabla.setModel(modeloTabla);
		modeloTabla.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				celdaEditada(e);
			}
		});
		tabla.getColumnModel().getColumn(5).setMaxWidth(0);
		tabla.getColumnModel().getColumn(5).setMinWidth(0);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(0);
		tabla.getColumnModel().getColumn(5).setResizable(false);
		tabla.getColumnModel().getColumn(6).setMaxWidth(0);
		tabla.getColumnModel().getColumn(6).setMinWidth(0);
		tabla.getColumnModel().getColumn(6).setPreferredWidth(0);
		tabla.getColumnModel().getColumn(6).setResizable(false);
		tabla.getColumnModel().getColumn(4).setResizable(false);

		scrollPane.setViewportView(tabla);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		botonAgregarEntrada = new JButton("");
		botonAgregarEntrada.setIcon(new ImageIcon(iconoOriginalAgregar.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT)));
		botonAgregarEntrada.setSize(new Dimension(63, 23));
		botonAgregarEntrada.setMnemonic(KeyEvent.VK_A);
		botonAgregarEntrada.setToolTipText("A\u00F1adir entrada (Alt + A)");
		botonAgregarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonAnadirClavePulsado();
			}
		});
		toolBar.add(botonAgregarEntrada);

		botonModificarEntrada = new JButton("");
		botonModificarEntrada.setMnemonic('m');
		URL urlModificar = VentanaPpal.class.getResource("/img/modificar_clave.png");
		ImageIcon iconoOriginalModificar = new ImageIcon(urlModificar);
		botonModificarEntrada.setIcon(new ImageIcon(iconoOriginalModificar.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT)));
		botonModificarEntrada.setToolTipText("Modificar/ver entrada");
		botonModificarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonModificarClavePulsado();
			}
		});
		toolBar.add(botonModificarEntrada);

		botonEliminarEntrada = new JButton("");
		botonModificarEntrada.setMnemonic('e');
		URL urlEliminar = VentanaPpal.class.getResource("/img/eliminar_clave.png");
		ImageIcon iconoOriginalEliminar = new ImageIcon(urlEliminar);
		botonEliminarEntrada.setIcon(new ImageIcon(iconoOriginalEliminar.getImage().getScaledInstance(ancho, alto, java.awt.Image.SCALE_DEFAULT)));
		botonEliminarEntrada.setToolTipText("Eliminar entrada");
		botonEliminarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonEliminarPulsado();
			}
		});
		toolBar.add(botonEliminarEntrada);

		botonAbrir = new JButton("");
		URL urlAbrir = VentanaPpal.class.getResource("/img/abrir_navegador.png");
		ImageIcon iconoOriginalAbrir = new ImageIcon(urlAbrir);
		botonAbrir.setIcon(new ImageIcon(iconoOriginalAbrir.getImage().getScaledInstance(ancho - 2, alto, java.awt.Image.SCALE_DEFAULT)));
		botonAbrir.setToolTipText("Abrir URL");
		botonAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonAbrirURLPulsado();
			}
		});
		toolBar.add(botonAbrir);

		botonCopiarClave = new JButton("");
		url = VentanaPpal.class.getResource("/img/copiar.png");
		iconoOriginal = new ImageIcon(url);
		botonCopiarClave.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(ancho - 2, alto, java.awt.Image.SCALE_DEFAULT)));
		toolBar.add(botonCopiarClave);

		botonPegar = new JButton("");
		botonPegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonPegarPulsado();
			}
		});
		url = VentanaPpal.class.getResource("/img/pegar.png");
		iconoOriginal = new ImageIcon(url);
		botonPegar.setIcon(new ImageIcon(iconoOriginal.getImage().getScaledInstance(ancho - 2, alto, java.awt.Image.SCALE_DEFAULT)));
		toolBar.add(botonPegar);
	}
	
	private void copiar(String texto) {
		StringSelection ss = new StringSelection(texto);
		cb.setContents(ss, ss);
	}
	
	private void pegar() {
		Transferable t = cb.getContents(this);

		// Construimos el DataFlavor correspondiente al String java
		DataFlavor dataFlavorStringJava;
		try {
			dataFlavorStringJava = new DataFlavor("application/x-java-serialized-object; class=java.lang.String");
			// Y si el dato se puede conseguir como String java, lo sacamos por pantalla
			if (t.isDataFlavorSupported(dataFlavorStringJava)) {
				String texto = (String) t.getTransferData(dataFlavorStringJava);
				System.out.println(texto);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setVisible(true);
	}

	protected void botonPegarPulsado() {
		// TODO Auto-generated method stub
		int index = this.tabla.getSelectedRow();

		if (index > 0) {
			this.setVisible(false);
			String usuario = this.modeloTabla.getValueAt(index, 1).toString();
			String pass = this.modeloTabla.getValueAt(index, 5).toString();
			copiar(usuario + "\t" + pass);
			pegar();

		}
		else {
			this.vista.error(this, "Debe seleccionar una entrada");
		}
	}

	protected void botonImportarPulsado() {
		// TODO Auto-generated method stub
		this.selector.setFileFilter(this.filtro);
		int opcion = this.selector.showOpenDialog(this);

		if (opcion == JFileChooser.APPROVE_OPTION) {
			String ruta = this.selector.getSelectedFile().getAbsolutePath();
			File file = new File(ruta);

			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);

				// estos métodos podemos usarlos combinados para normalizar el archivo XML
				doc.getDocumentElement().normalize();

				// almacenamos los nodos para luego mostrar la
				// cantidad de ellos con el método getLength()
				NodeList nList = doc.getElementsByTagName("key");
				System.out.println("Número de coches: " + nList.getLength());

				for(int i = 0; i < nList.getLength(); i++) {
					org.w3c.dom.Node nNode = nList.item(i);

					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;

						String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
						String usuario = eElement.getElementsByTagName("usuario").item(0).getTextContent();
						String pass = eElement.getElementsByTagName("password").item(0).getTextContent();
						String url = eElement.getElementsByTagName("url").item(0).getTextContent();
						String notas = eElement.getElementsByTagName("notas").item(0).getTextContent();
						//						String id = eElement.getAttribute("id");

						this.vista.agregarClave(titulo, usuario, pass, url, notas);
						getEntradas();
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void CrearElemento(Element key, Document doc, String titulo, String usuario, String pass, String url, String notas, String id) {
		// atributo para el nodo coche
		Attr attr = doc.createAttribute("id");
		attr.setValue(id);
		key.setAttributeNode(attr);

		// definimos cada uno de los elementos y le asignamos un valor
		Element eTitulo = doc.createElement("titulo");
		eTitulo.appendChild(doc.createTextNode(titulo));
		key.appendChild(eTitulo);

		Element eUsuario = doc.createElement("usuario");
		eUsuario.appendChild(doc.createTextNode(usuario));
		key.appendChild(eUsuario);

		Element ePass = doc.createElement("password");
		ePass.appendChild(doc.createTextNode(pass));
		key.appendChild(ePass);

		Element eURL = doc.createElement("url");
		eURL.appendChild(doc.createTextNode(url));
		key.appendChild(eURL);

		Element eNotas = doc.createElement("notas");
		eNotas.appendChild(doc.createTextNode(notas));
		key.appendChild(eNotas);
	}

	private void botonExportarPulsado() {
		// TODO Auto-generated method stub
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// definimos el elemento raíz del documento
			Element raiz = doc.createElement("keyring");
			doc.appendChild(raiz);

			int[] index = this.tabla.getSelectedRows();

			if (index.length == 0) {
				int n = this.tabla.getRowCount();
				index = new int[n];
				for (int i = 0; i < n; i++) {
					index[i] = i;
				}
			}

			for (int i : index) {
				// definimos el nodo que contendrá los elementos
				Element key = doc.createElement("key");
				raiz.appendChild(key);

				// elementos
				String titulo = this.modeloTabla.getValueAt(i, 0).toString();
				String usuario = this.modeloTabla.getValueAt(i, 1).toString();
				String url = this.modeloTabla.getValueAt(i, 3).toString();
				String notas = this.modeloTabla.getValueAt(i, 4).toString();
				String pass = this.modeloTabla.getValueAt(i, 5).toString();
				pass = Vista.encrypt(pass, "ADO");
				String id = this.modeloTabla.getValueAt(i, 6).toString();

				CrearElemento(key, doc, titulo, usuario, pass, url, notas, id);
			}

			// clases necesarias finalizar la creación del archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			this.selector.setFileFilter(this.filtro);
			boolean guardar = false;

			do {
				int opcion = this.selector.showSaveDialog(this);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					String ruta = this.selector.getSelectedFile().getAbsolutePath();
					String extension;
					extension = ruta.substring(ruta.length() - 4);

					if (!extension.equals(".xml")) {
						ruta += ".xml";
					}

					File file = new File(ruta);

					if (file.exists()) {
						int res = this.vista.pregunta(this, "El archivo ya existe, desea sobrescribirlo?");

						if (res == JOptionPane.YES_OPTION) {
							StreamResult result = new StreamResult(file);
							transformer.transform(source, result);
						}
					}
					else {
						StreamResult result = new StreamResult(file);
						transformer.transform(source, result);
					}
				} 
			} while (guardar);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void celdaEditada(TableModelEvent e) {
		// TODO Auto-generated method stub
		if (e.getType() == TableModelEvent.UPDATE) {
			TableModel modelo = ((TableModel) (e.getSource()));
			int index = e.getFirstRow();
			int columna = e.getColumn();

			String titulo = this.modeloTabla.getValueAt(index, 0).toString();
			String usuario = this.modeloTabla.getValueAt(index, 1).toString();
			String url = this.modeloTabla.getValueAt(index, 3).toString();
			String notas = this.modeloTabla.getValueAt(index, 4).toString();
			String pass = this.modeloTabla.getValueAt(index, 5).toString();
			pass = Vista.encrypt(pass, "ADO");
			String id = this.modeloTabla.getValueAt(index, 6).toString();

			if (this.vista.agregarClave(titulo, usuario, pass, url, notas, id)) {
				this.vista.msg(this, "Entrada actualizada con éxito");
			}
		}
	}

	protected void botonAbrirURLPulsado() {
		// TODO Auto-generated method stub
		int index = this.tabla.getSelectedRow();

		if (index > -1) {
			String url = this.modeloTabla.getValueAt(index, 3).toString();
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.vista.error(this, "Error");
			}
		}
		else {
			this.vista.error(this, "Debe seleccionar una entrada");
		}
	}

	protected void botonCambiarClavePulsado() {
		// TODO Auto-generated method stub
		this.vista.mostrarDialogoCambiarClave();
	}

	protected void botonSalirPulsado() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	protected void botonBloquearPulsado() {
		// TODO Auto-generated method stub
		this.vista.bloquear();
	}

	protected void botonEliminarPulsado() {
		// TODO Auto-generated method stub
		int index = this.tabla.getSelectedRow();

		if (index > -1) {
			String titulo = this.modeloTabla.getValueAt(index, 0).toString();
			if (this.vista.pregunta(this, "¿Seguro que quiere eliminar la entrada " + titulo + "?") == 0) {
				String id = this.modeloTabla.getValueAt(index, 6).toString();

				if (this.vista.eliminarClave(id)) {
					this.vista.msg(this, "Entrada eliminada con éxito");
					this.modeloTabla.removeRow(index);
				}
				else {
					this.vista.error(this, "Error al eliminar entrada");
				}
			}
		}
		else {
			this.vista.error(this, "Debe seleccionar una fila");
		}
	}

	protected void botonModificarClavePulsado() {
		// TODO Auto-generated method stub
		int index = this.tabla.getSelectedRow();

		if (index > -1) {
			String titulo = this.modeloTabla.getValueAt(index, 0).toString();
			String usuario = this.modeloTabla.getValueAt(index, 1).toString();
			String url = this.modeloTabla.getValueAt(index, 3).toString();
			String notas = this.modeloTabla.getValueAt(index, 4).toString();
			String pass = this.modeloTabla.getValueAt(index, 5).toString();
			pass = Vista.encrypt(pass, "ADO");
			String id = this.modeloTabla.getValueAt(index, 6).toString();
			this.vista.mostrarDialogoModificarClave(titulo, usuario, pass, url, notas, id);
		}
		else {
			this.vista.error(this, "Debe seleccionar una fila");
		}
	}

	protected void botonAnadirClavePulsado() {
		// TODO Auto-generated method stub
		this.vista.mostrarDialogoAgregarClave();
	}

	public void mostar() {
		// TODO Auto-generated method stub
		getEntradas();
		this.setVisible(true);
	}

	private void getEntradas() {
		// TODO Auto-generated method stub
		limpiarTabla();
		ResultSet resultado = this.vista.getEntradas();
		claveSecreta = "ADO!";

		try {
			while (resultado.next()) {
				String titulo = resultado.getString("Titulo");
				String usuario = resultado.getString("Usuario");
				String pass = resultado.getString("Password");
				pass = Vista.decrypt(pass, "ADO");
				String url = resultado.getString("URL");
				String nota = resultado.getString("Nota");
				String id = resultado.getString("Id");

				String datos[] = {titulo, usuario, "••••", url, nota, pass, id};
				this.modeloTabla.addRow(datos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void limpiarTabla() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.tabla.getRowCount(); i++) {
			this.modeloTabla.removeRow(i);
			i -= 1;
		}
	}
}
