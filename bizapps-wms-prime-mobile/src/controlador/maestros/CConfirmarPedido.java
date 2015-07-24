package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.inventario.pk.F4211PK;
import modelo.inventario.transacciones.F4211;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tab;

import security.modelo.Arbol;
import componentes.utils.Convertidor;

public class CConfirmarPedido extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbLista;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	private List<F4211> listaGeneral = new ArrayList<F4211>();

	@Override
	public void inicializar() throws IOException {

		long map2 = (long) Sessions.getCurrent().getAttribute("codigo");
		idArbol = map2;
		Map<String, Object> mapaVersiones = new HashMap<String, Object>();
		mapaVersiones = obtenerVersiones(idArbol);
		if ((List<String>) mapaVersiones.get("lots") != null)
			lots = (List<String>) mapaVersiones.get("lots");
		if ((List<String>) mapaVersiones.get("mcus") != null)
			mcus = (List<String>) mapaVersiones.get("mcus");
		if ((List<String>) mapaVersiones.get("dcto") != null) {
			dcts = (List<String>) mapaVersiones.get("dcto");
			dct = dcts.get(0);
		} else
			dct = "S0";
		if ((List<String>) mapaVersiones.get("nxtr") != null)
			next = (List<String>) mapaVersiones.get("nxtr");
		if ((List<String>) mapaVersiones.get("lttr") != null)
			last = (List<String>) mapaVersiones.get("lttr");
		mapaVersiones.clear();
		if (!mcus.isEmpty())
			listaGeneral = servicioF4211.buscarPorMcusYEstados(mcus, dct, next,
					last);
		ltbLista.setModel(new ListModelList<F4211>(listaGeneral));
	}

	@Listen("onChange = #dtbDesde, #dtbHasta")
	public void buscar() {
		System.out.println("metodo");
		System.out.println(Convertidor.transformarGregorianoAJulia(dtbDesde
				.getValue()));
		System.out.println(Convertidor.transformarGregorianoAJulia(dtbHasta
				.getValue()));
		listaGeneral = servicioF4211.buscarPorMcusYEstadosYFechas(mcus, dct,
				next, last,
				Convertidor.transformarGregorianoAJulia(dtbDesde.getValue()),
				Convertidor.transformarGregorianoAJulia(dtbHasta.getValue()));
		ltbLista.setModel(new ListModelList<F4211>(listaGeneral));
	}

	@Listen("onSelect = #ltbLista")
	public void selectedNode() {
		if (ltbLista.getSelectedItem() != null) {
			HashMap<String, Object> mapaGeneral = new HashMap<String, Object>();
			F4211 arbol = ltbLista.getSelectedItem().getValue();
			F4211PK clave = arbol.getId();
			String ruta = "/vistas/transacciones/VConfirmacionDetalle.zul";
			mapaGeneral.put("clave", clave);
			Sessions.getCurrent().setAttribute("clave", arbol.getId());
			Executions.sendRedirect(ruta);
		}
	}

	@Listen("onClick = #imagen")
	public void atras() {
		Executions.sendRedirect("/vistas/inicio.zul");
	}

}
