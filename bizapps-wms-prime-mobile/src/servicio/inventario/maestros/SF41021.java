package servicio.inventario.maestros;

import interfacedao.inventario.maestros.IF41021DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F4101;
import modelo.inventario.maestros.F41021;
import modelo.inventario.pk.F41021PK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SF41021")
public class SF41021 {

	@Autowired
	private IF41021DAO iF41021DAO;

	public F41021 buscar(F41021PK claveSaldo) {
		return iF41021DAO.findOne(claveSaldo);
	}

	public void guardar(F41021 f41021) {
		iF41021DAO.saveAndFlush(f41021);
	}

	public List<F41021> buscarHastaFecha2(BigDecimal desde) {
		return iF41021DAO.findByLiupmjBeforeNow(desde);
	}

	public List<F41021> buscarHastaFechaExistencia2(BigDecimal hasta) {
		return iF41021DAO.findByLiupmjBeforeNowExistencia(hasta);
	}

	public List<F41021> buscarPorItemMcuSinCeros(String planta, Double value) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("idLimcu");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		Double valor = (double) 0;
		return iF41021DAO.findByIdLimcuLikeAndIdLiitmAndLipqohNot(planta,
				value, valor, o);
	}

	public List<F41021> buscarPorItemMcu(String planta, Double value) {

		List<String> ordenar = new ArrayList<String>();
		ordenar.add("idLimcu");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return iF41021DAO.findByIdLimcuLikeAndIdLiitm(planta, value, o);
	}

	public List<F41021> buscarExistenciaMaxima(String company) {
		return iF41021DAO.findByMaxExistence(company);
	}

	public List<F41021> buscarExistenciaMinima(String company) {
		return iF41021DAO.findByMinExistence(company);
	}

	public List<F41021> buscarOrdenMinimo(String company) {
		return iF41021DAO.findByMinOrder(company);
	}

	public List<F41021> buscarSeguridadMinimo(String company) {
		return iF41021DAO.findByMinSecurityStock(company);
	}

	public List<F41021> buscarSaldosFifoSinEstados(F4101 item,
			List<String> lots, List<String> mcus) {
		return iF41021DAO.findByMcusAndItemAndLotsNotInAndAvailableFifo(
				item.getImitm(), lots, mcus);
	}

	public List<F41021> buscarSaldosLifoSinEstados(F4101 item,
			List<String> lots, List<String> mcus) {
		return iF41021DAO.findByMcusAndItemAndLotsNotInAndAvailableLifo(
				item.getImitm(), lots, mcus);
	}

	public List<F41021> buscarSaldosFefoSinEstados(F4101 item,
			List<String> lots, List<String> mcus) {
		return iF41021DAO.findByMcusAndItemAndLotsNotInAndAvailableFefo(
				item.getImitm(), lots, mcus);
	}

	public List<F41021> buscarSaldosSinEstados(F4101 item, List<String> lots,
			List<String> mcus) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("id.limcu");
		ordenar.add("id.lilocn");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return iF41021DAO.findByIdLimcuInAndIdLiitmAndLilotsNotIn(mcus,
				item.getImitm(), lots, o);
	}

	public void guardarVarios(List<F41021> listaSaldos) {
		iF41021DAO.save(listaSaldos);
	}

	public List<F41021> buscarPorItemEstadoPlantaYUbicacion(String item,
			String estado, String planta, String ubicacion) {
		return iF41021DAO.findByIdliitmLikeAndLotsLikeAndIdmcuLikeAndIdLocn(
				item, estado, planta, ubicacion);
	}

	public List<F41021> buscarHastaFechaYParametros(BigDecimal hasta,
			String item, String planta, String ubicacion, String lote,
			String estado) {
		return iF41021DAO.findByLiitmAndLimcuAndLilocnAndLilotnAndLiumpjBefore(
				item, planta, ubicacion, lote, estado, hasta);
	}

	public List<F41021> buscarDiferenciasCardexYSaldos(String item,
			String planta, String ubicacion, String lote, String estado) {
		return iF41021DAO.findByDiferenceBetweenF4111AndF41021(item, planta,
				ubicacion, lote, estado);
	}

	public List<F41021> buscarEntreFechasYParametros(BigDecimal desde,
			BigDecimal hasta, String item, String planta, String ubicacion,
			String lote, String estado) {
		return iF41021DAO
				.findByIdliitmAndIdlimcuAndIdlocnAndIdlotnAndlilotsAndDateBetween(
						item, planta, ubicacion, lote, estado, desde, hasta);
	}
}
