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
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

public class CConfirmarDetalle extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbPedidos;
	@Wire
	private Label label;
	private List<F4211> listaPedido = new ArrayList<F4211>();
	private Double carga, item;
	private List<String> lttr = new ArrayList<String>();

	@Override
	public void inicializar() throws IOException {
		if (Sessions.getCurrent().getAttribute("carga") != null) {
			carga = (Double) Sessions.getCurrent().getAttribute("carga");
			item = (Double) Sessions.getCurrent().getAttribute("item");
			lttr = (List<String>) Sessions.getCurrent().getAttribute("last");
			List<F4211> f4211 = servicioF4211.buscarPorCargaEItem(carga, item,
					lttr);
			label.setValue("ORDEN: " + String.valueOf(f4211.get(0).getSdcars()));
			listaPedido.addAll(f4211);
			ltbPedidos.setModel(new ListModelList<F4211>(listaPedido));
			ltbPedidos.renderAll();
		} else
			redireccionar();

	}

	@Listen("onClick = #imagen")
	public void atras() {
		Executions
				.sendRedirect("/vistas/transacciones/VConfirmacionMobile.zul");
	}

	@Listen("onSelect = #ltbPedidos")
	public void selectedNode() {
		if (ltbPedidos.getSelectedItem() != null) {
			HashMap<String, Object> mapaGeneral = new HashMap<String, Object>();
			F4211 arbol = ltbPedidos.getSelectedItem().getValue();
			F4211PK clave = arbol.getId();
			String ruta = "/vistas/transacciones/VSeleccionarLote.zul";
			mapaGeneral.put("clave", clave);
			Sessions.getCurrent().setAttribute("clave", arbol.getId());
			Executions.sendRedirect(ruta);
		}
	}

}
