package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.inventario.maestros.F41021;
import modelo.inventario.pk.F41021PK;
import modelo.inventario.pk.F4211PK;
import modelo.inventario.transacciones.F4211;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;

import componentes.Mensaje;

public class CSeleccionarLote extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Listbox ltbPedidos;
	@Wire
	private Label label;
	@Wire
	private Label lblCantidad;
	private List<F4211> listaPedido = new ArrayList<F4211>();
	private F4211PK clave = new F4211PK();
	private List<F4211> listaLotes = new ArrayList<F4211>();

	@Override
	public void inicializar() throws IOException {
		clave = (F4211PK) Sessions.getCurrent().getAttribute("clave");
		F4211 f4211 = servicioF4211.buscar(clave);
		label.setValue("Item:" + f4211.getSditm() + ". " + f4211.getSdlitm());
		lblCantidad.setValue(String.valueOf(f4211.getSdpqor()));
		listaPedido.add(f4211);
		if (Sessions.getCurrent().getAttribute("listaLotes") != null) {
			listaLotes = (List<F4211>) Sessions.getCurrent().getAttribute(
					"listaLotes");
			listaPedido.addAll(listaLotes);
		}
		ltbPedidos.setModel(new ListModelList<F4211>(listaPedido));
		ltbPedidos.renderAll();
	}

	@Listen("onClick = #imagen")
	public void atras() {
		Executions
				.sendRedirect("/vistas/transacciones/VConfirmacionDetalle.zul");
	}

	@Listen("onClick = #btnAgregar")
	public void selectedNode() {
		// validacion de la cantidad
		String ruta = "/vistas/transacciones/VLote.zul";
		Sessions.getCurrent().setAttribute("listaLotes", listaLotes);
		Executions.sendRedirect(ruta);
	}

	@Listen("onSelect = #ltbPedidos")
	public void removeNode() {
		if (ltbPedidos.getSelectedItem() != null) {
			Messagebox.show("¿Desea remover el registro?", "Alerta",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener<Event>() {
						public void onEvent(Event evt)
								throws InterruptedException {
							if (evt.getName().equals("onOK")) {

								F4211 arbol = ltbPedidos.getSelectedItem()
										.getValue();
								listaPedido.remove(arbol);
								listaLotes.remove(arbol);
								ltbPedidos.removeItemAt(ltbPedidos
										.getSelectedItem().getIndex());
							}
						}
					});
		}
	}

	private double cantidadComprometidaActual(F41021PK claveSaldo) {
		F4211 objet = servicioF4211.buscar(clave);
		if (claveSaldo.getLilocn().equals(objet.getSdlocn())
				&& claveSaldo.getLimcu().equals(objet.getSdmcu())
				&& claveSaldo.getLilotn().equals(objet.getSdlotn()))
			return Double.valueOf(lblCantidad.getValue());
		else
			return 0;
	}

	@Listen("onClick = #btnGuardar")
	public void guardar() {
		if (validar()) {
			boolean error = false;
			List<F41021> listaSaldos = new ArrayList<F41021>();
			F4211 objet = servicioF4211.buscar(clave);
			for (int i = 0; i < listaPedido.size(); i++) {
				F4211 f42 = listaPedido.get(i);
				F41021PK claveSaldo = new F41021PK();
				claveSaldo.setLiitm(f42.getSditm());
				claveSaldo.setLilocn(f42.getSdlocn());
				claveSaldo.setLimcu(f42.getSdmcu());
				claveSaldo.setLilotn(f42.getSdlotn());
				F41021 f = servicioF41021.buscar(claveSaldo);
				double suma = 0;
				if (f != null) {
					suma = f.getLipqoh() - f.getLihcom() - f.getLipcom()
							- f.getLiqtin()
							+ cantidadComprometidaActual(claveSaldo);
					if (suma - f42.getSduorg() < 0) {
						Mensaje.mensajeError(Mensaje.noPoseeExistencia
								+ ". Para el Nº de Linea"
								+ f42.getId().getSdlnid()
								+ ". Por favor, verifique su seleccion. Solo Posee disponibilidad para esta seleccion de: "
								+ suma);
						error = true;
						i = listaPedido.size();
					} else {
						f.setLihcom(f.getLihcom() + f42.getSduorg());
						if (claveSaldo.getLilocn().equals(objet.getSdlocn())
								&& claveSaldo.getLimcu().equals(
										objet.getSdmcu())
								&& claveSaldo.getLilotn().equals(
										objet.getSdlotn())) {
							f.setLihcom(f.getLihcom()
									- Double.valueOf(lblCantidad.getValue()));
						}
						listaSaldos.add(f);
					}
				} else {
					error = true;
					Mensaje.mensajeError("No se ha encontrado la Disponibilidad de este item");
				}
			}
			if (!error) {
				Mensaje.mensajeInformacion("Guardado de mentira, paso validaciones");
				// servicioF41021.guardarVarios(listaSaldos);
				// servicioF4211.guardarVarios(listaPedido);
				// Mensaje.mensajeInformacion("Lineas de Pedido modificadas correctamente");
				// ltbPedidos.getItems().clear();
				// listaPedido.clear();
			}
		}
	}

	protected boolean validar() {
		if (listaPedido.isEmpty()) {
			Mensaje.mensajeAlerta("Debe agregar al menos un Item al Pedido");
			return false;
		} else {
			if (!Double.valueOf(lblCantidad.getValue())
					.equals(cantidadActual().doubleValue())) {
				Mensaje.mensajeAlerta("La Cantidad agregada de Items no coincide con la Cantidad anterior, que fue de: "
						+ Double.valueOf(lblCantidad.getValue())
						+ ". Por favor verifique que las cantidades sean las mismas");
				return false;
			} else
				return true;
		}
	}

	private Integer cantidadActual() {
		Integer suma = 0;
		for (int i = 0; i < ltbPedidos.getItemCount(); i++) {
			Listitem listItem = ltbPedidos
					.getItemAtIndex(i);
			Integer cantidad = ((Spinner) ((listItem
					.getChildren().get(0).getChildren().get(0).getChildren().get(1).getChildren().get(1))))
					.getValue();
			suma = suma + cantidad;
		}
		return suma;
	}

}
