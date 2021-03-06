package servicio.inventario.transacciones;

import interfacedao.inventario.transacciones.IF4111DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import modelo.inventario.transacciones.F4111;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SF4111")
public class SF4111 {

	@Autowired
	private IF4111DAO iF4111DAO;

	public List<F4111> buscarTodosOrdenadosPorTipo(String tipo) {
		return iF4111DAO.findByIldctOrderByIlukidAsc(tipo);
	}

	public void guardar(F4111 f4111) {
		iF4111DAO.saveAndFlush(f4111);
	}

	public List<F4111> buscarPorDoc(Double ildoc, String tipo) {
		return iF4111DAO.findByIldocAndIldct(ildoc, tipo);
	}

	public Double sumar(Double value, String value2, String value3) {
		return iF4111DAO.suma(value, value2, value3);
	}

	public List<F4111> buscarPorMcuOMccu(String clave) {
		return iF4111DAO.findByIlmcuOrIlmmcu(clave, clave);
	}

	public List<F4111> buscarPorUbicaciones(String lmlocn) {
		return iF4111DAO.findByIllocnOrIlasid(lmlocn, lmlocn);
	}

	public List<F4111> buscarPorAn(double aban8) {
		return iF4111DAO.findByIlan8(aban8);
	}

	public List<F4111> buscarPorItm(double clave) {
		return iF4111DAO.findByIlitm(clave);
	}

	public List<F4111> buscarEntreFechas(BigDecimal desde, BigDecimal hasta) {
		// TODO Auto-generated method stub
		return iF4111DAO.findByIltrdjBetweenOrderByIldocAsc(desde, hasta);
	}

	public List<F4111> buscarHastaFecha(BigDecimal hasta) {
		return iF4111DAO.findByIltrdjBeforeOrderByMcuAsc(hasta);
	}

	public List<F4111> buscarTodosOrdenadosPorProveedor(String string,
			Double value) {
		// List<String> ordenar = new ArrayList<String>();
		// ordenar.add("ildoc");
		// ordenar.add("ilitm");
		// Sort o = new Sort(Sort.Direction.ASC, ordenar);
		List<F4111> lista = new ArrayList<F4111>();
		List<Double> lista2 = iF4111DAO.findByIlan8AndIldctOrderByIldocAsc(
				value, string);
		for (int i = 0; i < lista2.size(); i++) {
			lista.add(iF4111DAO.findByIldocAndIldct(lista2.get(i), string).get(
					0));
		}
		return lista;
	}

	public F4111 buscar(Double clave41) {
		return iF4111DAO.findOne(clave41);
	}

	public void guardarVarios(List<F4111> cardexs) {
		iF4111DAO.save(cardexs);
	}

	public List<F4111> buscarHastaFecha2(BigDecimal hasta) {
		return iF4111DAO.findByIlvpejBeforeOrderByMcuAsc(hasta);
	}

	public List<F4111> buscarEntreFechas2(BigDecimal desde, BigDecimal hasta) {
		// TODO Auto-generated method stub
		return iF4111DAO.findByIlvpejBetweenOrderByIldocAsc(desde, hasta);
	}

	public F4111 buscarTodosOrdenadosPorDoc(String string, Double claveDoc,
			Double double1, String loc) {
		return iF4111DAO.findByIldocAndIldctAndIlitmAndIllocn(claveDoc, string,
				double1, loc);
	}

	public List<F4111> buscarEntreFechasItemMcuLocnDct(BigDecimal ilvpej1,
			BigDecimal ilvpej2, Double item, String planta, String ubicacion,
			String lote, String tipo) {
		return iF4111DAO
				.findByIlvpejBetweenAndIlitmAndIlmcuLikeAndIllocnLikeAndIllotnLikeAndIldctLikeOrderByIlitmAsc(
						ilvpej1, ilvpej2, item, planta, ubicacion, lote, tipo);
	}

	public List<F4111> buscarEntreFechas2YTipo(BigDecimal desde,
			BigDecimal hasta, String tipo) {
		return iF4111DAO.findByIlvpejBetweenAndIldct(desde, hasta, tipo);
	}

	public List<F4111> buscarPorMcusYEstados(List<String> mcus, String dct) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("ildcto");
		ordenar.add("iltrdj");
		ordenar.add("ilitm");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return iF4111DAO.findByIlmcuInAndIldct(mcus, dct, o);
	}

	public List<F4111> buscarEntreFechasYParametrosEntrada(BigDecimal desde,
			BigDecimal hasta, String item, String planta, String ubicacion,
			String proveedor, String ordenCliente, String doco, String lote,
			List<String> dcts) {
		return iF4111DAO
				.findByIlitmAndIlmcuAndIllocnAndIllotnAndIlan8AndDocoAndDocAndDctsInEntradaAndIlvrdjBetween(
						item, planta, ubicacion, lote, proveedor, ordenCliente,
						doco, dcts, desde, hasta);
	}

	public List<F4111> buscarEntreFechasYParametrosSalida(BigDecimal desde,
			BigDecimal hasta, String item, String planta, String ubicacion,
			String proveedor, String ordenCliente, String doco, String lote,
			List<String> dcts) {
		return iF4111DAO
				.findByIlitmAndIlmcuAndIllocnAndIllotnAndIlan8AndDocoAndDocAndDctsInSalidaAndIlvrdjBetween(
						item, planta, ubicacion, lote, proveedor, ordenCliente,
						doco, dcts, desde, hasta);
	}

	public List<F4111> buscarEntreFechasYAgrupacionLote(BigDecimal desde,
			BigDecimal hasta, String item, String planta, String ubicacion,
			String lote, String proveedor, String ordenCliente, String doco) {
		return iF4111DAO
				.findByIlitmAndIlmcuAndIllocnAndIllotnAndIlan8AndDocoAndDocAndAndIlvrdjBetween(
						item, planta, ubicacion, lote, proveedor, ordenCliente,
						doco, desde, hasta);
	}

	public List<F4111> buscarTodoEntreFechasYParametros(BigDecimal desde,
			BigDecimal hasta, String item, String planta, String ubicacion,
			String lote, String proveedor, String ordenCliente, String doco) {
		return iF4111DAO
				.findByIlitmAndIlmcuAndIllocnAndIllotnAndIlan8AndDocoAndDocAndAndIlvrdjBetweenAlles(
						item, planta, ubicacion, lote, proveedor, ordenCliente,
						doco, desde, hasta);
	}

	public Double sumarPorItemPlantaLocnYLotn(Double liitm, String limcu,
			String lilocn, String lilotn) {
		return iF4111DAO.sumByItemAndMcuAndLocnAndLotn(liitm, limcu, lilocn,
				lilotn);
	}

	public Double sumarPorItemPlantaLocnYLotnHastaFecha(Double liitm,
			String limcu, String lilocn, String lilotn, BigDecimal desde) {
		return iF4111DAO.sumByItemAndMcuAndLocnAndLotnAndFechaBis(liitm, limcu, lilocn,
				lilotn, desde);
	}
}
