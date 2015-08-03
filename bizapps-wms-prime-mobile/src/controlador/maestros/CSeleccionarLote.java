package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.inventario.maestros.F41021;
import modelo.inventario.maestros.F4105;
import modelo.inventario.pk.F41021PK;
import modelo.inventario.pk.F4105PK;
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
		if (Sessions.getCurrent().getAttribute("clave") != null) {
			clave = (F4211PK) Sessions.getCurrent().getAttribute("clave");
			F4211 f4211 = servicioF4211.buscar(clave);
			label.setValue("Item:" + f4211.getSditm() + ". "
					+ f4211.getSdlitm());
			lblCantidad.setValue(String.valueOf(f4211.getSdpqor()));
			listaPedido.add(f4211);
			if (Sessions.getCurrent().getAttribute("listaLotes") != null) {
				listaLotes = (List<F4211>) Sessions.getCurrent().getAttribute(
						"listaLotes");
				listaPedido.addAll(listaLotes);
			}
			ltbPedidos.setModel(new ListModelList<F4211>(listaPedido));
			ltbPedidos.renderAll();
		} else
			redireccionar();
	}

	@Listen("onClick = #imagen")
	public void atras() {

		Sessions.getCurrent().removeAttribute("listaLotes");
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
							} else
								ltbPedidos.clearSelection();
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
			List<F4211> listaGuardar = new ArrayList<F4211>();
			F4211 anterior = servicioF4211.buscar(clave);
			ltbPedidos.renderAll();
			for (int i = 0; i < ltbPedidos.getItemCount(); i++) {
				Listitem listItem = ltbPedidos.getItemAtIndex(i);
				String lote = ((Label) ((listItem.getChildren().get(0)
						.getChildren().get(0).getChildren().get(0)
						.getChildren().get(1)))).getValue();
				Integer cantidad = ((Spinner) ((listItem.getChildren().get(0)
						.getChildren().get(0).getChildren().get(1)
						.getChildren().get(1)))).getValue();
				listaGuardar.add(crearNuevo(lote, cantidad, anterior,
						anterior.getSditm(), anterior.getSdmcu(),
						anterior.getSdlocn(), ltbPedidos.getItemCount() + i));
				F41021PK claveSaldo = new F41021PK();
				claveSaldo.setLiitm(anterior.getSditm());
				claveSaldo.setLilocn(anterior.getSdlocn());
				claveSaldo.setLimcu(anterior.getSdmcu());
				claveSaldo.setLilotn(lote);
				F41021 f = servicioF41021.buscar(claveSaldo);
				double suma = 0;
				if (f != null) {
					suma = f.getLipqoh() - f.getLihcom() - f.getLipcom()
							- f.getLiqtin()
							+ cantidadComprometidaActual(claveSaldo);
					if (suma - cantidad < 0) {
						Mensaje.mensajeError(Mensaje.noPoseeExistencia
								+ ". Para el Lote"
								+ lote
								+ ". Por favor, verifique su seleccion. Solo Posee disponibilidad para esta seleccion de: "
								+ suma);
						error = true;
						i = ltbPedidos.getItemCount();
					} else {
						f.setLihcom(f.getLihcom() + cantidad);
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
					i = ltbPedidos.getItemCount();
					Mensaje.mensajeError("No se ha encontrado la Disponibilidad de este item");
				}
			}
			if (!error) {
				Mensaje.mensajeInformacion("Pedido guardado correctamente");
				servicioF41021.guardarVarios(listaSaldos);
				servicioF4211.guardarVarios(listaGuardar);
				Mensaje.mensajeInformacion("Lineas de Pedido modificadas correctamente");
				atras();
			}
		}
	}

	private F4211 crearNuevo(String lote, Integer cantidad, F4211 f4211,
			Double item, String mcu, String loc, int valor) {
		F4211PK claveNueva = new F4211PK();
		claveNueva.setSddcto(clave.getSddcto());
		claveNueva.setSddoco(clave.getSddoco());
		claveNueva.setSdkcoo(clave.getSdkcoo());
		String lote2 = f4211.getSdlotn();
		if (!lote.equals(lote2)) {
			Double ultimoValorLinea = servicioF4211.buscarUltimaLinea(clave);
			// int numero = 1;
			// if (ltbPedidos.getItemCount() != 0)
			// numero = ltbPedidos.getItemCount();
			Double nuevo = ultimoValorLinea + valor;
			claveNueva.setSdlnid(nuevo);
		} else
			claveNueva.setSdlnid(f4211.getId().getSdlnid());
		F4211 nuevo = new F4211();
		nuevo.setId(claveNueva);
		nuevo.setSdxlln(f4211.getSdxlln());
		nuevo.setSdprp4(f4211.getSdprp4());
		nuevo.setSdprp5(f4211.getSdprp5());
		nuevo.setSdshpn(f4211.getSdshpn());
		nuevo.setSdcars(f4211.getSdcars());
		nuevo.setSdcrcd(f4211.getSdcrcd());
		nuevo.setSdcrmd(f4211.getSdcrmd());
		nuevo.setSddoc(f4211.getSddoc());
		nuevo.setSdan8(f4211.getSdan8());
		nuevo.setSdir01(f4211.getSdir01());
		nuevo.setSdshan(f4211.getSdshan());
		nuevo.setSdlttr(f4211.getSdlttr());
		nuevo.setSdnxtr(f4211.getSdnxtr());
		nuevo.setSduom(f4211.getSduom());
		nuevo.setSduom1(f4211.getSduom1());
		nuevo.setSditm(f4211.getSditm());
		nuevo.setSdlitm(f4211.getSdlitm());
		nuevo.setSddrqj(f4211.getSddrqj());
		nuevo.setSdcadc(f4211.getSdcadc());
		nuevo.setSdvr01(f4211.getSdvr01());
		nuevo.setSdvr02(f4211.getSdvr02());
		nuevo.setSdaopn(f4211.getSdaopn());
		nuevo.setSdir02(f4211.getSdir02());
		nuevo.setSdir03(f4211.getSdir03());
		nuevo.setSdir04(f4211.getSdir04());
		nuevo.setSdir05(f4211.getSdir05());
		F4105 f4105 = new F4105();
		F4105PK clave = new F4105PK();
		clave.setCoitm(item);
		clave.setColedg("02");
		clave.setColocn("");
		clave.setColotn("");
		clave.setComcu(mcu);
		f4105 = servicioF4105.buscar(clave);
		double costo = 0;
		if (f4105 != null) {
			costo = f4105.getCouncs();
		}
		double total = costo * cantidad;
		nuevo.setSduncs(costo);
		nuevo.setSdecst(total);
		nuevo.setSduorg((double) cantidad);
		nuevo.setSdpqor((double) cantidad);
		nuevo.setSdlnty(f4211.getSdlnty());
		nuevo.setSdmcu(mcu);
		nuevo.setSdlocn(loc);
		nuevo.setSdlotn(lote);
		return nuevo;
	}

	protected boolean validar() {
		if (listaPedido.isEmpty()) {
			Mensaje.mensajeAlerta("Debe agregar al menos un Item al Pedido");
			return false;
		} else {
			if (!Double.valueOf(lblCantidad.getValue()).equals(
					cantidadActual().doubleValue())) {
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
			Listitem listItem = ltbPedidos.getItemAtIndex(i);
			Integer cantidad = ((Spinner) ((listItem.getChildren().get(0)
					.getChildren().get(0).getChildren().get(1).getChildren()
					.get(1)))).getValue();
			suma = suma + cantidad;
		}
		return suma;
	}

}
