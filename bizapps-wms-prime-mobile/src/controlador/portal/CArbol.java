package controlador.portal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;

import controlador.maestros.CGenerico;
import security.modelo.Arbol;
import security.modelo.Grupo;
import security.modelo.MArbol;
import security.modelo.Nodos;
import security.modelo.UsuarioSeguridad;

public class CArbol extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbLista;
	@Wire
	private Image imagenes;
	TreeModel<?> _model;
	URL url = getClass().getResource("/security/controlador/usuario.png");
	HashMap<String, Object> mapGeneral = new HashMap<String, Object>();

	@Override
	public void inicializar() throws IOException {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		UsuarioSeguridad u = servicioUsuario.buscarPorLogin(auth.getName());
		//
		if (u.getImagen() == null) {
			imagenes.setContent(new AImage(url));
		} else {
			try {
				BufferedImage imag;
				imag = ImageIO.read(new ByteArrayInputStream(u.getImagen()));
				imagenes.setContent(imag);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(
				auth.getAuthorities());
		ArrayList<Arbol> arbole = new ArrayList<Arbol>();
		for (int k = 0; k < authorities.size(); k++) {
			String nombre = authorities.get(k).toString();
			if (validadorNumero(nombre)) {
				Arbol arbol = servicioArbol.buscar(Long.parseLong(nombre));
				if (arbol != null)
					if (!arbol.getUrl().contains("inicio"))
						arbole.add(arbol);
			}
		}
		ltbLista.setModel(new ListModelList<Arbol>(arbole));

	}

	/*
	 * Permite seleccionar un elemento del arbol, mostrandolo en forma de
	 * pestaña y su contenido es cargado en un div
	 */
	@Listen("onSelect = #ltbLista")
	public void selectedNode() {
		if (ltbLista.getSelectedItem() != null) {
			Arbol arbol = ltbLista.getSelectedItem().getValue();
			long item = Long.valueOf(arbol.getIdArbol());
			final Arbol arbolItem = servicioArbol.buscarPorId(item);
			mapGeneral.put("titulo", arbolItem.getNombre());
			if (!arbolItem.getUrl().equals("inicio")) {
				String ruta = "/vistas/" + arbolItem.getUrl() + ".zul";
				mapGeneral.put("idArbol", arbolItem.getIdArbol());
				mapGeneral.put("titulo", arbolItem.getNombre());
				Sessions.getCurrent().setAttribute("mapaGeneral", mapGeneral);
				Sessions.getCurrent().setAttribute("codigo",
						arbolItem.getIdArbol());
				Executions.sendRedirect(ruta);
			}

		}
	}

	/* Metodo que permite abrir la ventana de editar usuario en una pestaña */
	@Listen("onClick = #lblEditarCuenta")
	public void abrirEditarCuenta() {
		String ruta = "/vistas/seguridad/VEditarUsuario.zul";
		Sessions.getCurrent().setAttribute("mapaGeneral", mapGeneral);
		Executions.sendRedirect(ruta);
	}

	public boolean validadorNumero(String numero) {
		String PATTERN = "^[0-9]*";
		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(numero);
		return matcher.matches();
	}
}