package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modelo.inventario.maestros.F40203;
import modelo.inventario.maestros.F41021;
import modelo.inventario.pk.F41021PK;
import modelo.inventario.pk.F4211PK;
import modelo.inventario.transacciones.F4111;
import modelo.inventario.transacciones.F4211;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;

import security.modelo.Arbol;
import componentes.Mensaje;
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
	@Wire
	private Button btnSeleccionar;
	@Wire
	private Button btnProcesar;
	@Wire
	private Button btnCancelar;
	private List<F4211> listaGeneral = new ArrayList<F4211>();

	@Override
	public void inicializar() throws IOException {

		if (Sessions.getCurrent().getAttribute("codigo") != null) {
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

		} else
			redireccionar();
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
							lotes = "";
							documento = listaAuxiliar.get(i).getSdcars();
							item = listaAuxiliar.get(i).getSditm();
							objeto = listaAuxiliar.get(i);
							i--;
						}
					} else {
						objeto.setSdapum(lotes);
						listaGeneral.add(objeto);
						lotes = "";
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
		boolean multiple = false;
		if (ltbLista.isMultiple())
			multiple = true;
		ltbLista.setModel(new ListModelList<F4211>(cargarLista(
				dtbDesde.getValue(), dtbHasta.getValue())));
		if (multiple)
			listasMultiples(true);
	}

	@Listen("onSelect = #ltbLista")
	public void selectedNode() {
		if (ltbLista.getSelectedItem() != null) {
			if (!ltbLista.isMultiple()) {
				F4211 arbol = ltbLista.getSelectedItem().getValue();
				String ruta = "/vistas/transacciones/VConfirmacionDetalle.zul";
				Sessions.getCurrent().setAttribute("item", arbol.getSditm());
				Sessions.getCurrent().setAttribute("carga", arbol.getSdcars());
				Sessions.getCurrent().setAttribute("last", last);
				Sessions.getCurrent().setAttribute("sucursales", mcus);
				Sessions.getCurrent().setAttribute("estados", lots);
				Executions.sendRedirect(ruta);
			}
		} else
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
	}

	@Listen("onClick = #imagen")
	public void atras() {
		Executions.sendRedirect("/vistas/inicio.zul");
	}

	@Listen("onClick = #btnSeleccionar")
	public void seleccionar() {
		mostrarBotones(true);
		listasMultiples(true);
	}

	@Listen("onClick = #btnProcesar")
	public void confirmar() {
		confirmarCarga();
	}

	@Listen("onClick = #btnCancelar")
	public void cancelar() {
		mostrarBotones(false);
		listasMultiples(false);
	}

	public void mostrarBotones(boolean visible) {
		btnSeleccionar.setVisible(!visible);
		btnCancelar.setVisible(visible);
		btnProcesar.setVisible(visible);
	}

	public void listasMultiples(boolean multiples) {
		ltbLista.setMultiple(!multiples);
		ltbLista.setCheckmark(!multiples);
		ltbLista.setMultiple(multiples);
		ltbLista.setCheckmark(multiples);
	}

	private void confirmarCarga() {

		final List<F4211> lista = obtenerSeleccion();
		if (validarSeleccion(lista)) {
			Messagebox.show("¿Desea Confirmar los " + lista.size()
					+ " Pedidos?", "Alerta", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION,
					new org.zkoss.zk.ui.event.EventListener<Event>() {
						public void onEvent(Event evt)
								throws InterruptedException {
							if (evt.getName().equals("onOK")) {
								List<F4211> guardados = new ArrayList<F4211>();
								for (int i = 0; i < lista.size(); i++) {
									guardados.addAll(servicioF4211
											.buscarPorCargaEItem(lista.get(i)
													.getSdcars(), lista.get(i)
													.getSditm(), Arrays
													.asList(lista.get(i)
															.getSdlttr())));
								}
								List<F40203> estados = servicioF40203
										.buscarSYYEstado(dct, lista.get(0)
												.getSdnxtr());
								F40203 estado = new F40203();
								if (!estados.isEmpty())
									estado = estados.get(0);
								for (Iterator<F4211> iterator = guardados
										.iterator(); iterator.hasNext();) {
									F4211 object = (F4211) iterator.next();
									// DATOS DE LINEA DE PEDIDO
									Double idCardex = nextNumber("01",
											"CARDEX", "Movimientos Cardex");
									object.setSdlttr(estado.getId().getFstrty());
									object.setSdnxtr(estado.getFsnxtr());
									object.setSdukid(idCardex);
									// DATOS DE CARDEX
									F4111 f4111 = new F4111();
									f4111.setIluser(nombreUsuarioSesion());
									f4111.setIlvpej(transformarGregorianoAJulia(fecha));
									f4111.setIlicu(Double
											.valueOf(horaAuditoria));
									f4111.setIlukid(idCardex);
									f4111.setIldoc(object.getSddoc());
									f4111.setIldoco(object.getId().getSddoco());
									f4111.setIldgl(object.getSddrqj());
									f4111.setIltrdj(object.getSddrqj());
									f4111.setIlcrdj(object.getSddrqj());
									f4111.setIldct(object.getId().getSddcto());
									f4111.setIltrex("Confirmacion de Pedido");
									f4111.setIllotn(object.getSdlotn());
									f4111.setIlmcu(object.getSdmcu());
									f4111.setIllocn(object.getSdlocn());
									f4111.setIlitm(object.getSditm());
									f4111.setIllitm(object.getSdlitm());
									f4111.setIluom2(object.getSduom1());
									f4111.setIltrum(object.getSduom());
									f4111.setIlan8(object.getSdan8());
									f4111.setIltrqt(object.getSduorg() * -1);
									f4111.setIluncs(object.getSduncs());
									f4111.setIlpaid(object.getSdecst() * -1);
									f4111.setIllpnu(object.getSdprp4());
									f4111.setIlobj(object.getSdprp5());
									// DATOS DE SALDO
									F41021PK claveSaldo = new F41021PK();
									claveSaldo.setLiitm(object.getSditm());
									claveSaldo.setLilocn(object.getSdlocn());
									claveSaldo.setLimcu(object.getSdmcu());
									claveSaldo.setLilotn(object.getSdlotn());
									F41021 f = servicioF41021
											.buscar(claveSaldo);
									f.setLihcom(f.getLihcom()
											- object.getSduorg());
									f.setLipqoh(f.getLipqoh()
											- object.getSduorg());
									// GUARDADO DE TODO
									servicioF41021.guardar(f);
									servicioF4111.guardar(f4111);
									servicioF4211.guardar(object);
								}
								mostrarBotones(false);
								ltbLista.setModel(new ListModelList<F4211>(
										cargarLista(restarUnDia(fecha), fecha)));
								listasMultiples(false);
								Mensaje.mensajeInformacion("Pedidos Confirmados con Exito");
							} else
								ltbLista.clearSelection();
						}
					});
		}

	}

	public boolean validarSeleccion(List<F4211> list) {
		if (list == null) {
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			return false;
		} else {
			if (list.isEmpty()) {
				Mensaje.mensajeAlerta(Mensaje.noSeleccionoItem);
				return false;
			} else {
				return true;
			}
		}
	}

	private List<F4211> obtenerSeleccion() {
		List<F4211> valores = new ArrayList<F4211>();
		boolean entro = false;
		if (ltbLista.getItemCount() != 0) {
			final List<Listitem> list1 = ltbLista.getItems();
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).isSelected()) {
					F4211 clase = list1.get(i).getValue();
					entro = true;
					valores.add(clase);
				}
			}
			if (!entro) {
				valores.clear();
				return valores;
			}
			return valores;
		} else
			return null;
	}

}
