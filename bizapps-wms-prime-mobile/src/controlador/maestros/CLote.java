package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.inventario.maestros.F0004;
import modelo.inventario.maestros.F4101;
import modelo.inventario.maestros.F41021;
import modelo.inventario.pk.F4211PK;
import modelo.inventario.transacciones.F4211;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class CLote extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbPedidos;
	private List<String> sucursales = new ArrayList<String>();
	private List<String> estados = new ArrayList<String>();
	Double item;
	List<F41021> saldo = new ArrayList<F41021>();
	private List<F4211> listaLotes = new ArrayList<F4211>();
	@Wire
	private Textbox txtSearch;

	@Override
	public void inicializar() throws IOException {
		if (Sessions.getCurrent().getAttribute("item") != null) {
			listaLotes = (List<F4211>) Sessions.getCurrent().getAttribute(
					"listaLotes");
			sucursales = (List<String>) Sessions.getCurrent().getAttribute(
					"sucursales");
			estados = (List<String>) Sessions.getCurrent().getAttribute(
					"estados");
			item = (Double) Sessions.getCurrent().getAttribute("item");
			F4101 articulo = servicioF4101.buscar(item);
			saldo = servicioF41021.buscarSaldosSinEstados(articulo, estados,
					sucursales);
			ltbPedidos.setModel(new ListModelList<F41021>(saldo));
			ltbPedidos.renderAll();
			txtSearch.setFocus(true);
		} else
			redireccionar();
	}

	@Listen("onClick = #imagen")
	public void atras() {
		Executions.sendRedirect("/vistas/transacciones/VSeleccionarLote.zul");
	}

	@Listen("onChange = #txtSearch")
	public void buscar() {
		ltbPedidos.clearSelection();
		ltbPedidos.getItems().clear();
		ltbPedidos.renderAll();
		final List<F41021> lista = new ArrayList<F41021>();
		for (F41021 f0004 : saldo) {
			if (f0004.getId().getLilotn().toLowerCase()
					.contains(txtSearch.getValue().toLowerCase()))
				lista.add(f0004);
		}
		ltbPedidos.setModel(new ListModelList<F41021>(lista));
		ltbPedidos.renderAll();
	}

	@Listen("onBlur = #txtSearch")
	public void blur() {
		txtSearch.setFocus(true);
	}

	@Listen("onSelect = #ltbPedidos")
	public void selectedNode() {
		if (ltbPedidos.getSelectedItem() != null) {
			F41021 arbol = ltbPedidos.getSelectedItem().getValue();
			F4211 object = new F4211();
			object.setSdlotn(arbol.getId().getLilotn());
			object.setSduorg(arbol.getLipqoh());
			listaLotes.add(object);
			String ruta = "/vistas/transacciones/VSeleccionarLote.zul";
			Sessions.getCurrent().setAttribute("listaLotes", listaLotes);
			Executions.sendRedirect(ruta);
		}
	}

}
