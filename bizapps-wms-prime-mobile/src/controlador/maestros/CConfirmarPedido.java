package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
		ltbLista.setModel(new ListModelList<F4211>(cargarLista(
				restarUnDia(fecha), fecha)));
	}

	private Collection<? extends F4211> cargarLista(Date fecha1, Date fecha2) {
		if (!mcus.isEmpty()) {
			listaGeneral.clear();
			List<F4211> listaAuxiliar = servicioF4211
					.buscarPorMcusYEstadosYFechasAgrupados(mcus, dct, next,
							last, transformarGregorianoAJulia(fecha1),
							transformarGregorianoAJulia(fecha2));
			if (!listaAuxiliar.isEmpty()) {
				Double documento = listaAuxiliar.get(0).getSdcars();
				Double item = listaAuxiliar.get(0).getSditm();
				String lotes = "";
				F4211 objeto = listaAuxiliar.get(0);
				for (int i = 0; i < listaAuxiliar.size(); i++) {
					if (documento.equals(listaAuxiliar.get(i).getSdcars())) {
						if (item.equals(listaAuxiliar.get(i).getSditm())) {
							lotes += listaAuxiliar.get(i).getSdlotn() + ",";
						} else {
							objeto.setSdapum(lotes);
							listaGeneral.add(objeto);
							lotes ="";
							documento = listaAuxiliar.get(i).getSdcars();
							item = listaAuxiliar.get(i).getSditm();
							objeto = listaAuxiliar.get(i);
							i--;
						}
					} else {
						objeto.setSdapum(lotes);
						listaGeneral.add(objeto);
						lotes ="";
						documento = listaAuxiliar.get(i).getSdcars();
						item = listaAuxiliar.get(i).getSditm();
						objeto = listaAuxiliar.get(i);
						i--;
					}
				}
				objeto.setSdapum(lotes);
				listaGeneral.add(objeto);
			}
		}
		return listaGeneral;
	}

	@Listen("onChange = #dtbDesde, #dtbHasta")
	public void buscar() {
		ltbLista.setModel(new ListModelList<F4211>(cargarLista(
				dtbDesde.getValue(), dtbHasta.getValue())));
	}

	@Listen("onSelect = #ltbLista")
	public void selectedNode() {
		if (ltbLista.getSelectedItem() != null) {
			F4211 arbol = ltbLista.getSelectedItem().getValue();
			String ruta = "/vistas/transacciones/VConfirmacionDetalle.zul";
			Sessions.getCurrent().setAttribute("item", arbol.getSditm());
			Sessions.getCurrent().setAttribute("carga", arbol.getSdcars());
			Sessions.getCurrent().setAttribute("last", last);
			Sessions.getCurrent().setAttribute("sucursales", mcus);
			Sessions.getCurrent().setAttribute("estados", lots);
			Executions.sendRedirect(ruta);
		}
	}

	@Listen("onClick = #imagen")
	public void atras() {
		Executions.sendRedirect("/vistas/inicio.zul");
	}

}
