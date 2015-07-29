package controlador.maestros;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import modelo.inventario.maestros.F00021;
import modelo.inventario.maestros.F0006;
import modelo.inventario.maestros.F4101;
import modelo.inventario.maestros.F4108;
import modelo.inventario.pk.F00021PK;
import modelo.inventario.pk.F4108PK;
import modelo.inventario.seguridad.F4201A;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.impl.XulElement;

import security.modelo.Arbol;
import security.servicio.SArbol;
import security.servicio.SGrupo;
import security.servicio.SUsuarioSeguridad;
import servicio.inventario.historico.SF42119;
import servicio.inventario.maestros.SF00021;
import servicio.inventario.maestros.SF0004;
import servicio.inventario.maestros.SF0005;
import servicio.inventario.maestros.SF0006;
import servicio.inventario.maestros.SF0008;
import servicio.inventario.maestros.SF0010;
import servicio.inventario.maestros.SF0013;
import servicio.inventario.maestros.SF0015;
import servicio.inventario.maestros.SF0101;
import servicio.inventario.maestros.SF0111;
import servicio.inventario.maestros.SF0115;
import servicio.inventario.maestros.SF01151;
import servicio.inventario.maestros.SF0116;
import servicio.inventario.maestros.SF4008;
import servicio.inventario.maestros.SF40203;
import servicio.inventario.maestros.SF40205;
import servicio.inventario.maestros.SF4100;
import servicio.inventario.maestros.SF41002;
import servicio.inventario.maestros.SF4101;
import servicio.inventario.maestros.SF4102;
import servicio.inventario.maestros.SF41021;
import servicio.inventario.maestros.SF4105;
import servicio.inventario.maestros.SF4108;
import servicio.inventario.maestros.SF4301;
import servicio.inventario.maestros.SF4311;
import servicio.inventario.maestros.SF49041;
import servicio.inventario.maestros.SF4930;
import servicio.inventario.maestros.SF4931;
import servicio.inventario.seguridad.SF4201A;
import servicio.inventario.transacciones.SF4111;
import servicio.inventario.transacciones.SF4201;
import servicio.inventario.transacciones.SF4211;

import componentes.Mensaje;
import componentes.buscadores.BuscadorUDC;
import componentes.catalogos.CatalogoGenerico;
import componentes.utils.Convertidor;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class CGenerico extends SelectorComposer<Component> {

	private static final long serialVersionUID = -3701148488846104476L;

	@WireVariable("SArbol")
	protected SArbol servicioArbol;
	@WireVariable("SF0004")
	protected SF0004 servicioF0004;
	@WireVariable("SF0005")
	protected SF0005 servicioF0005;
	@WireVariable("SF0006")
	protected SF0006 servicioF0006;
	@WireVariable("SF0008")
	protected SF0008 servicioF0008;
	@WireVariable("SF0010")
	protected SF0010 servicioF0010;
	@WireVariable("SF0013")
	protected SF0013 servicioF0013;
	@WireVariable("SF0015")
	protected SF0015 servicioF0015;
	@WireVariable("SF0101")
	protected SF0101 servicioF0101;
	@WireVariable("SF0111")
	protected SF0111 servicioF0111;
	@WireVariable("SF0115")
	protected SF0115 servicioF0115;
	@WireVariable("SF4201A")
	protected SF4201A servicioF4201A;
	@WireVariable("SF4201")
	protected SF4201 servicioF4201;
	@WireVariable("SF01151")
	protected SF01151 servicioF01151;
	@WireVariable("SF0116")
	protected SF0116 servicioF0116;
	@WireVariable("SF4008")
	protected SF4008 servicioF4008;
	@WireVariable("SF40205")
	protected SF40205 servicioF40205;
	@WireVariable("SF4100")
	protected SF4100 servicioF4100;
	@WireVariable("SF00021")
	protected SF00021 servicioF00021;
	@WireVariable("SF40203")
	protected SF40203 servicioF40203;
	@WireVariable("SF4211")
	protected SF4211 servicioF4211;
	@WireVariable("SF42119")
	protected SF42119 servicioF42119;
	@WireVariable("SF4101")
	protected SF4101 servicioF4101;
	@WireVariable("SF4102")
	protected SF4102 servicioF4102;
	@WireVariable("SF41002")
	protected SF41002 servicioF41002;
	@WireVariable("SF4111")
	protected SF4111 servicioF4111;
	@WireVariable("SF41021")
	protected SF41021 servicioF41021;
	@WireVariable("SF4105")
	protected SF4105 servicioF4105;
	@WireVariable("SF4108")
	protected SF4108 servicioF4108;
	@WireVariable("SF4301")
	protected SF4301 servicioF4301;
	@WireVariable("SF4311")
	protected SF4311 servicioF4311;
	@WireVariable("SF49041")
	protected SF49041 servicioF49041;
	@WireVariable("SF4930")
	protected SF4930 servicioF4930;
	@WireVariable("SF4931")
	protected SF4931 servicioF4931;
	@WireVariable("SGrupo")
	protected SGrupo servicioGrupo;
	@WireVariable("SUsuarioSeguridad")
	protected SUsuarioSeguridad servicioUsuario;
	protected static SimpleDateFormat formatoFecha = new SimpleDateFormat(
			"dd-MM-yyyy");
	public List<Tab> tabs = new ArrayList<Tab>();
	protected DateFormat df = new SimpleDateFormat("HH:mm:ss");
	protected DateFormat formatoXml = new SimpleDateFormat("yyyyMMdd");
	protected DateFormat formatoHoraXml = new SimpleDateFormat("HHmmss");
	protected DateFormat formatoLote = new SimpleDateFormat("yyyyMMddHHmmss");
	File fXmlFile = null;
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	public Calendar calendario = Calendar.getInstance();
	public static double id = 0;
	public String titulo = "";
	public Long idArbol;
	public List<String> lots = new ArrayList<String>();
	public List<String> mcus = new ArrayList<String>();
	public String dct = null;
	public List<String> dcts = new ArrayList<String>();
	public List<String> last = new ArrayList<String>();
	public List<String> next = new ArrayList<String>();
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/META-INF/ConfiguracionAplicacion.xml");

	// Cambio en la hora borrados los :
	//
	// public static SF4101 getServicioF4101() {
	// return applicationContext.getBean(SF4101.class);
	// }
	//
	// public static SF4100 getServicioF4100() {
	// return applicationContext.getBean(SF4100.class);
	// }
	//
	// public static SF40203 getServicioFF40203() {
	// return applicationContext.getBean(SF40203.class);
	// }
	//
	// public static SF4111 getServicioF4111() {
	// return applicationContext.getBean(SF4111.class);
	// }
	//
	// public static SF4211 getServicioF4211() {
	// return applicationContext.getBean(SF4211.class);
	// }
	//
	// public static SF4201 getServicioF4201() {
	// return applicationContext.getBean(SF4201.class);
	// }
	//
	// public static SF42119 getServicioF42119() {
	// return applicationContext.getBean(SF42119.class);
	// }
	//
	// public static SF40203 getServicioF40203() {
	// return applicationContext.getBean(SF40203.class);
	// }
	//
	// public static SF4301 getServicioF4301() {
	// return applicationContext.getBean(SF4301.class);
	// }
	//
	// public static SF4311 getServicioF4311() {
	// return applicationContext.getBean(SF4311.class);
	// }
	//
	// public static SF41021 getServicioF41021() {
	// return applicationContext.getBean(SF41021.class);
	// }
	//
	// public static SF00021 getServicioF00021() {
	// return applicationContext.getBean(SF00021.class);
	// }
	//
	public static SF0005 getServicioF0005() {
		return applicationContext.getBean(SF0005.class);
	}

	public static SF0004 getServicioF0004() {
		return applicationContext.getBean(SF0004.class);
	}

	public static SF0101 getServicioF0101() {
		return applicationContext.getBean(SF0101.class);
	}

	//
	// public static SF0115 getServicioF0115() {
	// return applicationContext.getBean(SF0115.class);
	// }
	//
	// public static SF4108 getServicioF4108() {
	// return applicationContext.getBean(SF4108.class);
	// }
	//
	// public static SF4102 getServicioF4102() {
	// return applicationContext.getBean(SF4102.class);
	// }
	//
	// public static SF4105 getServicioF4105() {
	// return applicationContext.getBean(SF4105.class);
	// }
	//
	public static SF0006 getServicioF0006() {
		return applicationContext.getBean(SF0006.class);
	}

	//
	public static SF4201A getServicioSF4201A() {
		return applicationContext.getBean(SF4201A.class);
	}

	//
	public static SArbol getServicioArbol() {
		return applicationContext.getBean(SArbol.class);
	}

	//
	// public static SOrden getServicioOrden() {
	// return applicationContext.getBean(SOrden.class);
	// }
	//
	// public static SOrdenF4101 getServicioOrdenF4101() {
	// return applicationContext.getBean(SOrdenF4101.class);
	// }
	//
	public String horaAuditoria = String.valueOf(calendario
			.get(Calendar.HOUR_OF_DAY))
			+ String.valueOf(calendario.get(Calendar.MINUTE))
			+ String.valueOf(calendario.get(Calendar.SECOND));
	public java.util.Date fecha = new Date();
	public Timestamp fechaHora = new Timestamp(fecha.getTime());
	public Mensaje msj = new Mensaje();
	public static boolean nextNumber = true;
	public static boolean registro4111 = true;
	public static boolean accesoF41021 = true;
	public Div divMuestra;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		inicializar();
	}

	public void settearTituo(Div div, long idNuevo) {
		XulElement componente = (XulElement) div.getChildren().get(0);
		if (componente instanceof Groupbox) {
			Groupbox comp = (Groupbox) componente;
			String nombre = comp.getTitle();
			comp.setTitle(nombre + " ID Vista: " + idNuevo);
		}
	}

	// hacer metodo abstracto y meterlo en el inicializar, que retorne el mapa y
	// todos los valores
	// ademas meter lo del boolean para mostrar todo

	// public BigDecimal transformarGregorianoAJulia(Date fecha) {
	// String valor = "";
	//
	// calendario = new GregorianCalendar();
	// calendario.setTime(fecha);
	// String dia = "";
	// if (calendario.get(Calendar.DAY_OF_YEAR) < 10)
	// dia = "00";
	// else {
	// if (calendario.get(Calendar.DAY_OF_YEAR) >= 10
	// && calendario.get(Calendar.DAY_OF_YEAR) < 100)
	// dia = "0";
	// }
	// if ((fecha.getYear() + 1900) < 2000)
	// valor = "";
	// else
	// valor = "1";
	// long al = Long.valueOf(valor
	// + String.valueOf(calendario.get(Calendar.YEAR)).substring(2)
	// + dia + String.valueOf(calendario.get(Calendar.DAY_OF_YEAR)));
	// BigDecimal a = BigDecimal.valueOf(al);
	// return a;
	// }
	public static BigDecimal transformarGregorianoAJulia(Date fecha) {
		String valor = "";

		Calendar calendario = new GregorianCalendar();
		calendario.setTime(fecha);
		String dia = "";
		if (calendario.get(Calendar.DAY_OF_YEAR) < 10)
			dia = "00";
		else {
			if (calendario.get(Calendar.DAY_OF_YEAR) >= 10
					&& calendario.get(Calendar.DAY_OF_YEAR) < 100)
				dia = "0";
		}
		if ((calendario.get(Calendar.YEAR) + 1900) < 2000)
			valor = "";
		else
			valor = "1";
		long al = Long.valueOf(valor
				+ String.valueOf(calendario.get(Calendar.YEAR)).substring(2)
				+ dia + String.valueOf(calendario.get(Calendar.DAY_OF_YEAR)));
		BigDecimal a = BigDecimal.valueOf(al);
		return a;
	}

	public Date transformarJulianaAGregoria(BigDecimal valor) {
		return Convertidor.transformarJulianaAGregoria(valor);
	}

	public Date transformarJulianaAGregoriadeLong(Long valor) {
		return Convertidor.transformarJulianaAGregoriadeLong(valor);
	}

	public abstract void inicializar() throws IOException;

	public void cerrarVentana(Div div, String id, List<Tab> tabs2) {
		div.setVisible(false);
		tabs = tabs2;
		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i).getLabel().equals(id)) {
				if (i == (tabs.size() - 1) && tabs.size() > 1) {
					tabs.get(i - 1).setSelected(true);
				}
				tabs.get(i).onClose();
				tabs.remove(i);
			}
		}
	}

	public String nombreUsuarioSesion() {
		Authentication sesion = SecurityContextHolder.getContext()
				.getAuthentication();
		return sesion.getName();
	}

	public boolean enviarEmailNotificacion(String correo, String mensajes) {
		try {

			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props);
			String asunto = "Notificacion de Inventario Serloveca";
			// FALTA USUARIO y PASSWORD
			String remitente = "siteg.ucla@gmail.com";
			String contrasena = "Equipo.2";
			String destino = correo;
			String mensaje = mensajes;
			String destinos[] = destino.split(",");
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remitente));
			Address[] receptores = new Address[destinos.length];
			int j = 0;
			while (j < destinos.length) {
				receptores[j] = new InternetAddress(destinos[j]);
				j++;
			}
			message.addRecipients(Message.RecipientType.TO, receptores);
			message.setSubject(asunto);
			message.setText(mensaje);
			Transport t = session.getTransport("smtp");
			t.connect(remitente, contrasena);
			t.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));
			t.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("cdusa", "cartucho");
		}
	}

	protected double nextNumber(String empresa, String doc, String descripcion) {
		synchronized (this) {
			while (!nextNumber) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			nextNumber = false;
			try {
				double numeroNext = servicioF00021.Numero(empresa, doc);
				if (numeroNext != 0) {
					id = numeroNext + 1;
					F00021 f021 = servicioF00021.buscar(empresa, doc);
					f021.setNln001(id);
					servicioF00021.guardar(f021);
				} else {
					id = 1;
					F00021 f021 = new F00021();
					F00021PK clave21 = new F00021PK();
					// clave21.setNldct(verificarUDC("00", "DT", doc,
					// descripcion,
					// 6));
					clave21.setNldct(doc);
					// clave21.setNlkco(servicioF0010.verificar(empresa));
					clave21.setNlkco(empresa);
					clave21.setNlctry((double) 0);
					clave21.setNlfy((double) 0);
					f021.setId(clave21);
					f021.setNln001(id);
					f021.setNlck01(descripcion);
					servicioF00021.guardar(f021);
				}
			} catch (Exception a) {
				nextNumber = true;
				a.printStackTrace();
				return 0;
			}
		}
		synchronized (this) {
			nextNumber = true;
			notify();
			return id;
		}
	}

	public String damePath() {
		return Executions.getCurrent().getContextPath() + "/";
	}

	protected BuscadorUDC crearCampoUDC(Div div, String titulo,
			boolean requerido, String valor1, String valor2, String ancho1,
			String ancho2, String ancho3, String ancho4) {
		BuscadorUDC buscador = new BuscadorUDC(titulo, 10, requerido, valor1,
				valor2, servicioF0005, ancho1, ancho2, ancho3, ancho4);
		div.appendChild(buscador);
		return buscador;
	}

	protected BuscadorUDC crearCampoUDC(Div div, String titulo,
			boolean requerido, String valor1, String valor2) {
		BuscadorUDC buscador = new BuscadorUDC(titulo, 10, requerido, valor1,
				valor2, servicioF0005);
		div.appendChild(buscador);
		return buscador;
	}

	public Map<String, Object> obtenerVersiones(Long idArbol) {
		Arbol arbol = getServicioArbol().buscar(idArbol);
		List<F4201A> versiones = getServicioSF4201A().buscarPorPrg(idArbol);
		String kcco = null;
		String valoresMcu = null;
		List<String> mcus = null;
		String valoresLots = null;
		List<String> lots = null;
		String valoresNext = null;
		List<String> next = null;
		String valoresLast = null;
		List<String> last = null;
		String valoresDcto = null;
		List<String> dctos = null;
		if (arbol.getManejo() != null) {
			if (arbol.getManejo() == 1) {
				mcus = new ArrayList<String>();
				lots = new ArrayList<String>();
				next = new ArrayList<String>();
				last = new ArrayList<String>();
				dctos = new ArrayList<String>();
				for (Iterator<F4201A> iterator = versiones.iterator(); iterator
						.hasNext();) {
					F4201A f4201a = (F4201A) iterator.next();
					if (f4201a.getId().getVerfield().equalsIgnoreCase("mcu"))
						valoresMcu = f4201a.getVervals();
					if (f4201a.getId().getVerfield().equalsIgnoreCase("dcto"))
						valoresDcto = f4201a.getVervals();
					if (f4201a.getId().getVerfield().equalsIgnoreCase("nxtr"))
						valoresNext = f4201a.getVervals();
					if (f4201a.getId().getVerfield().equalsIgnoreCase("lttr"))
						valoresLast = f4201a.getVervals();
					if (f4201a.getId().getVerfield().equalsIgnoreCase("lots"))
						valoresLots = f4201a.getVervals();
				}
				if (valoresLots != null) {
					String valores[] = valoresLots.split(",");
					int j = 0;
					while (j < valores.length) {
						lots.add(valores[j].trim());
						j++;
					}
				} else
					lots = new ArrayList<>(Arrays.asList("Cuarentena",
							"Bloqueado"));

				if (valoresDcto != null) {
					String valores[] = valoresDcto.split(",");
					int j = 0;
					while (j < valores.length) {
						dctos.add(valores[j].trim());
						j++;
					}
				} else
					dctos = null;

				if (valoresLast != null) {
					String valores[] = valoresLast.split(",");
					int j = 0;
					while (j < valores.length) {
						last.add(valores[j].trim());
						j++;
					}
				} else
					last = getServicioF0005().buscarEstados("AT", "40");

				if (valoresNext != null) {
					String valores[] = valoresNext.split(",");
					int j = 0;
					while (j < valores.length) {
						next.add(valores[j].trim());
						j++;
					}
				} else
					next = getServicioF0005().buscarEstados("AT", "40");

				if (valoresMcu != null) {
					String valores[] = valoresMcu.split(",");
					int j = 0;
					while (j < valores.length) {
						mcus.add(valores[j].trim());
						j++;
					}
				} else
					mcus = getServicioF0006().buscarTodosDistinct();

				F0006 object = null;
				for (int i = 0; i < mcus.size(); i++) {
					object = getServicioF0006().buscar(mcus.get(i));
					if (object != null)
						i = mcus.size();
				}
				if (object != null) {
					kcco = object.getMcco();
				} else
					Mensaje.mensajeError("Los valores asignados a las mcu de este programa no existen en la tabla F0006");
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mcus", mcus);
		map.put("dcto", dctos);
		map.put("nxtr", next);
		map.put("lttr", last);
		map.put("kcco", kcco);
		map.put("lots", lots);
		return map;
	}

	public boolean validarSeleccion(CatalogoGenerico<?> catalogo) {
		List<?> seleccionados = catalogo.obtenerSeleccionados();
		if (seleccionados == null) {
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			return false;
		} else {
			if (seleccionados.isEmpty()) {
				Mensaje.mensajeAlerta(Mensaje.noSeleccionoItem);
				return false;
			} else {
				return true;
			}
		}
	}

	public String registrarLote(F4101 articulo, String mcu) {
		String lote = "";
		if (articulo.getImsrnr() != null) {
			if (articulo.getImsrnr().equals("1")) {
				F0006 planta = servicioF0006.buscar(mcu);
				String sucursal = "00";
				if (planta.getMcldm() != null) {
					sucursal = planta.getMcldm();
				}
				lote = sucursal + formatoLote.format(new Date())
						+ String.valueOf(randomInt(10, 99));
				F4108 objeto = new F4108();
				F4108PK key = new F4108PK();
				key.setIoitm(articulo.getImitm());
				key.setIolotn(lote);
				key.setIomcu(mcu);
				objeto.setId(key);
				objeto.setIolitm(articulo.getImdsc1());
				objeto.setIoohdj(transformarGregorianoAJulia(new Date()));
				objeto.setIommej(transformarGregorianoAJulia(new Date()));
				servicioF4108.guardar(objeto);
			}
		}
		return lote;
	}

	public int randomInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public String obtenerDatoXml(String valor) {
		try {
			fXmlFile = new File(URLDecoder.decode(
					getClass().getResource("/META-INF/ConfiguracionDorado.xml")
							.getFile(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getDocumentElement().getChildNodes();
		List<NodeList> nodos = new ArrayList<NodeList>();
		nodos.add(nList);
		for (int i = 0; i < nodos.size(); i++) {
			NodeList listaNodos = nodos.get(i);
			for (int j = 0; j < listaNodos.getLength(); j++) {
				if (listaNodos.item(j) instanceof Element) {
					Element e = (Element) listaNodos.item(j);
					NodeList lista2 = e.getChildNodes();
					if (e.getTagName().equalsIgnoreCase(valor)) {
						return e.getTextContent();
					}
					nodos.add(lista2);
				}
			}
		}
		return null;
	}

	public Date agregarDia(Date fecha) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		calendario.add(Calendar.DAY_OF_YEAR, +1);
		return fecha = calendario.getTime();
	}

	public Date restarUnDia(Date fecha) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		calendario.add(Calendar.DAY_OF_YEAR, -1);
		return fecha = calendario.getTime();
	}

	public BigDecimal convertirFecha(String part2, boolean b) {
		Date fecha1 = null;
		try {
			fecha1 = Convertidor.formatoFecha.parse(part2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (b)
			fecha1 = agregarDia(fecha1);
		BigDecimal desde = transformarGregorianoAJulia(fecha1);
		return desde;
	}

	public byte[] generarReporteGenerico(Map<String, Object> p, List<?> lista,
			String nombreReporte, String tipo) {
		byte[] fichero = null;
		JasperReport repor = null;
		try {
			repor = (JasperReport) JRLoader.loadObject(getClass().getResource(
					nombreReporte));
		} catch (JRException e1) {
			e1.printStackTrace();
		}

		if (tipo.equals("EXCEL")) {
			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(repor, p,
						new JRBeanCollectionDataSource(lista));
			} catch (JRException e) {
				e.printStackTrace();
			}
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				e.printStackTrace();
			}
			return xlsReport.toByteArray();
		} else {
			try {
				fichero = JasperRunManager.runReportToPdf(repor, p,
						new JRBeanCollectionDataSource(lista));
			} catch (JRException e) {
				e.printStackTrace();
				Mensaje.mensajeError(e.getMessage());
			}
			return fichero;
		}
	}

	public static String restarFechas(Date fin, Date inicio) {
		Calendar desde = new GregorianCalendar();
		Calendar hasta = new GregorianCalendar();
		Calendar finAnno = new GregorianCalendar();
		desde.setTime(inicio);
		hasta.setTime(fin);
		try {
			Date ultimoDia = Convertidor.formatoFecha.parse("31-12-"
					+ desde.get(Calendar.YEAR));
			finAnno.setTime(ultimoDia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int factor = 0;
		if (hasta.get(Calendar.YEAR) > desde.get(Calendar.YEAR)) {
			if (hasta.get(Calendar.YEAR) - desde.get(Calendar.YEAR) == 1) {
				if (hasta.get(Calendar.DAY_OF_YEAR) <= desde
						.get(Calendar.DAY_OF_YEAR))
					return String.valueOf(hasta.get(Calendar.DAY_OF_YEAR)
							+ (finAnno.get(Calendar.DAY_OF_YEAR) - desde
									.get(Calendar.DAY_OF_YEAR)))
							+ "Dias";

			}
			factor = hasta.get(Calendar.YEAR) - desde.get(Calendar.YEAR);
			return "+" + String.valueOf(factor) + "Años";
		}
		return String.valueOf(hasta.get(Calendar.DAY_OF_YEAR)
				- desde.get(Calendar.DAY_OF_YEAR))
				+ "Dias";
	}
	//
	// private String verificarUDC(String sy, String rt, String ky,
	// String descripcion, int max) {
	// String codigo = ky;
	// if (codigo.length() > max)
	// codigo = codigo.substring(0, max);
	// F0004 tipoUdc = servicioF0004.buscar(sy, rt);
	// if (tipoUdc == null) {
	// F0004PK clave = new F0004PK();
	// clave.setDtsy(sy);
	// clave.setDtrt(rt);
	// tipoUdc = new F0004();
	// tipoUdc.setId(clave);
	// tipoUdc.setDtdl01(descripcion);
	// tipoUdc.setDtln2("");
	// tipoUdc.setDtcnum("");
	// servicioF0004.guardar(tipoUdc);
	// } else {
	// F0005 ob = servicioF0005.buscar(sy, rt, codigo);
	// if (ob == null) {
	// ob = new F0005();
	// F0005PK claveF0005 = new F0005PK();
	// claveF0005.setDrsy(sy);
	// claveF0005.setDrrt(rt);
	// claveF0005.setDrky(codigo);
	// ob.setId(claveF0005);
	// ob.setDrdl01(ky);
	// servicioF0005.guardar(ob);
	// }
	// }
	// return codigo;
	// }
}