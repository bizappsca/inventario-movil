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
	private F4211PK clave = new F4211PK();

	@Override
	public void inicializar() throws IOException {
		clave = (F4211PK) Sessions.getCurrent().getAttribute("clave");
		F4211 f4211 = servicioF4211.buscar(clave);
		label.setValue(String.valueOf(f4211.getSdcars().longValue()));
		listaPedido.add(f4211);
		ltbPedidos.setModel(new ListModelList<F4211>(listaPedido));
		ltbPedidos.renderAll();

	}

	@Listen("onClick = #imagen")
	public void atras() {
		Executions
				.sendRedirect("/vistas/transacciones/VConfirmacionMobile.zul");
	}

}
