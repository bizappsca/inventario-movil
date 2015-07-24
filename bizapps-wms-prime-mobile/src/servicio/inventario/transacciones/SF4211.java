package servicio.inventario.transacciones;

import interfacedao.inventario.transacciones.IF4211DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import modelo.inventario.pk.F4211PK;
import modelo.inventario.transacciones.F4211;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SF4211")
public class SF4211 {

	@Autowired
	private IF4211DAO f4211DAO;

	public List<F4211> buscarTodosOrdenados() {
		return f4211DAO.findAllById();
	}

	public void eliminarVarios(List<F4211> eliminarLista) {
		f4211DAO.delete(eliminarLista);
	}

	public void eliminarUno(F4211PK clave) {
		f4211DAO.delete(clave);
	}

	public List<F4211> buscarPorDocoYDcto(Double sddoco, String sddcto) {
		return f4211DAO.findByIdSddocoAndIdSddctoAndSdspattnOrderBySditmAsc(
				sddoco, sddcto, "Enviada");
	}

	public List<F4211> buscarPorCabecera(Double sddoco, String sddcto,
			String sdkcoo) {
		return f4211DAO.findByIdSddocoAndIdSddctoAndIdSdkcoo(sddoco, sddcto,
				sdkcoo);
	}

	public void guardar(F4211 f4211) {
		f4211DAO.save(f4211);
	}

	public void guardarVarios(List<F4211> guardados) {
		f4211DAO.save(guardados);
	}

	public List<F4211> buscarTodosOrdenadosUnicos(String et) {
		List<Double> listaBuscada = f4211DAO.findDocoDistinct(et);
		List<F4211> lista = new ArrayList<F4211>();
		for (int i = 0; i < listaBuscada.size(); i++) {
			lista.add(buscarPorDocoYDcto(listaBuscada.get(i), et).get(0));
		}
		return lista;
	}

	public F4211 buscarPorDocoEItem(Double value, Double imitm) {
		return f4211DAO.findByIdSddocoAndSditm(value, imitm);
	}

	public List<F4211> buscarEntreFechasYEstado(BigDecimal desde,
			BigDecimal hasta, String string) {
		return f4211DAO.findBySddrqjBetweenAndSdspattn(desde, hasta, string);
	}

	public List<F4211> buscarEntreFechasYEstadoYTipo(BigDecimal desde,
			BigDecimal hasta, String string, String tipo) {
		return f4211DAO.findBySddrqjBetweenAndSdspattnAndIdSddcto(desde, hasta,
				string, tipo);
	}

	public List<F4211> buscarPorMcus(List<String> mcus, String dct) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("id.sddcto");
		ordenar.add("id.sddoco");
		ordenar.add("sditm");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return f4211DAO.findBySdmcuInAndIdSddcto(mcus, dct, o);
	}

	public List<F4211> buscarPorMcusYEstados(List<String> mcus, String dct,
			List<String> next, List<String> last) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("id.sddcto");
		ordenar.add("id.sddoco");
		ordenar.add("id.sdlnid");
		ordenar.add("sditm");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return f4211DAO.findBySdmcuInAndIdSddctoAndSdnxtrInOrSdlttrIn(mcus,
				dct, next, last, o);
	}

	public List<F4211> buscarPorMcusYLikeDctoOrdenOrdenCItemMcu(
			List<String> mcus, String dct, String orden, String ordenCliente,
			String item, String planta) {
		return f4211DAO
				.findBySdmcuInAndIdSddctoLikeAndIdSddocoLikeAndSddocLikeAndSditmLikeAndSdmcuLike(
						mcus, dct, orden, ordenCliente, item, planta);
	}

	public Double buscarUltimaLinea(F4211PK id) {
		return f4211DAO.findMaxSdlnid(id.getSddcto(), id.getSddoco(),
				id.getSdkcoo());
	}

	public List<F4211> buscarEnviosPorMcusYEstados(List<String> mcus,
			String dct, List<String> next, List<String> last) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("sdshpn");
		ordenar.add("sditm");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		List<F4211> lista = f4211DAO
				.findBySdmcuInAndIdSddctoAndSdnxtrInOrSdlttrIn(mcus, dct, next,
						last, o);
		List<F4211> retorna = new ArrayList<F4211>();
		if (!lista.isEmpty()) {
			Double shpn = lista.get(0).getSdshpn();
			Double itm = lista.get(0).getSditm();
			Double cantidad = (double) 0;
			F4211 objeto = lista.get(0);
			for (int i = 0; i < lista.size(); i++) {
				if (shpn.equals(lista.get(i).getSdshpn())) {
					if (itm.equals(lista.get(i).getSditm())) {
						cantidad = cantidad + lista.get(i).getSduorg();
					} else {
						objeto.setSdcars(cantidad);
						retorna.add(objeto);
						cantidad = (double) 0;
						itm = lista.get(i).getSditm();
						objeto = new F4211();
						objeto = lista.get(i);
						i--;
					}
				} else {
					objeto.setSdcars(cantidad);
					retorna.add(objeto);
					cantidad = (double) 0;
					itm = lista.get(i).getSditm();
					shpn = lista.get(i).getSdshpn();
					objeto = new F4211();
					objeto = lista.get(i);
					i--;
				}
			}
			objeto.setSdcars(cantidad);
			retorna.add(objeto);
		}
		return retorna;
	}

	public List<F4211> buscarPorDocoDctoItemEnvio(F4211 f4211) {
		return f4211DAO
				.findByIdSddocoAndIdSddctoAndIdSdkcooAndSditmAndSdshpnAndSdlttr(
						f4211.getId().getSddoco(), f4211.getId().getSddcto(),
						f4211.getId().getSdkcoo(), f4211.getSditm(),
						f4211.getSdshpn(), f4211.getSdlttr());
	}

	public List<F4211> buscarPorNumeroCarga(Long part6, String part2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("sditm");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return f4211DAO.findBySdcarsAndSdlttr(part6.doubleValue(), part2, o);
	}

	public List<F4211> buscarPorNumeroDespacho(Long part6, String part2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("sditm");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return f4211DAO.findBySddmcsAndSdlttr(part6.doubleValue(), part2, o);
	}

	public F4211 buscar(F4211PK clave) {
		return f4211DAO.findOne(clave);
	}

	public List<F4211> buscarCargasPorMcusYEstados(List<String> mcus,
			String dct, List<String> next, List<String> last) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("sdcars");
		ordenar.add("sditm");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		List<F4211> lista = f4211DAO
				.findBySdmcuInAndIdSddctoAndSdnxtrInOrSdlttrIn(mcus, dct, next,
						last, o);
		List<F4211> retorna = new ArrayList<F4211>();
		if (!lista.isEmpty()) {
			Double cars = lista.get(0).getSdcars();
			Double itm = lista.get(0).getSditm();
			Double cantidad = (double) 0;
			F4211 objeto = lista.get(0);
			String itms = String.valueOf(objeto.getSditm().longValue());
			for (int i = 0; i < lista.size(); i++) {
				if (cars != null) {
					if (cars.equals(lista.get(i).getSdcars())) {
						if (itm.equals(lista.get(i).getSditm())) {
							cantidad = cantidad + lista.get(i).getSduorg();
						} else {
							itms += ","
									+ String.valueOf(lista.get(i).getSditm()
											.longValue());
							itm = lista.get(i).getSditm();
							i--;
						}
					} else {
						objeto.setSdaaid(cantidad);
						objeto.setSdacom(itms);
						retorna.add(objeto);
						cantidad = (double) 0;
						itms = String.valueOf(lista.get(i).getSditm()
								.longValue());
						itm = lista.get(i).getSditm();
						cars = lista.get(i).getSdcars();
						objeto = new F4211();
						objeto = lista.get(i);
						i--;
					}
				}
			}
			if (cars != null) {
				objeto.setSdaaid(cantidad);
				objeto.setSdacom(itms);
				retorna.add(objeto);
			}
		}
		return retorna;
	}

	public List<F4211> buscarPorMcusYLikeDctoOrdenOrdenCItemMcuCarga(
			List<String> mcus, String dct, String orden, String ordenCliente,
			String item, String planta, String carga, String last) {
		List<F4211> lista = f4211DAO
				.findBySdmcuInAndIdSddctoLikeAndIdSddocoLikeAndSddocLikeAndSditmLikeAndSdmcuLikeCargaLikeLttrLike(
						mcus, dct, orden, ordenCliente, item, planta, carga,
						last);
		List<F4211> retorna = new ArrayList<F4211>();
		if (!lista.isEmpty()) {
			Double cars = lista.get(0).getSdcars();
			Double itm = lista.get(0).getSditm();
			Double cantidad = (double) 0;
			F4211 objeto = lista.get(0);
			String itms = String.valueOf(objeto.getSditm().longValue());
			for (int i = 0; i < lista.size(); i++) {
				if (cars.equals(lista.get(i).getSdcars())) {
					if (itm.equals(lista.get(i).getSditm())) {
						cantidad = cantidad + lista.get(i).getSduorg();
					} else {
						itms += ","
								+ String.valueOf(lista.get(i).getSditm()
										.longValue());
						itm = lista.get(i).getSditm();
						i--;
					}
				} else {
					objeto.setSdaaid(cantidad);
					objeto.setSdacom(itms);
					retorna.add(objeto);
					cantidad = (double) 0;
					itms = String.valueOf(lista.get(i).getSditm().longValue());
					itm = lista.get(i).getSditm();
					cars = lista.get(i).getSdcars();
					objeto = new F4211();
					objeto = lista.get(i);
					i--;
				}
			}
			objeto.setSdaaid(cantidad);
			objeto.setSdacom(itms);
			retorna.add(objeto);
		}
		return retorna;
	}

	public List<F4211> buscarPorDocoDctoItemCarga(F4211 f4211) {
		return f4211DAO.findBySdcarsAndSdlttr(f4211.getSdcars(),
				f4211.getSdlttr());
	}

	public F4211 buscarPorIdEItemYNxtr(F4211 f4211a) {
		return f4211DAO.findByIdSddocoAndIdSddctoAndIdSdkcooAndSditmAndSdnxtr(
				f4211a.getId().getSddoco(), f4211a.getId().getSddcto(), f4211a
						.getId().getSdkcoo(), f4211a.getSditm(), f4211a
						.getSdlttr());
	}

	public List<F4211> buscarPorDocYControl(Double docGrande, Double valueOf) {
		return f4211DAO.findBySddocAndSdxlln(docGrande, valueOf);
	}

	public List<F4211> buscarPorMcusYEstadosYFechas(List<String> mcus,
			String dct, List<String> next, List<String> last,
			BigDecimal fecha1, BigDecimal fecha2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("id.sddcto");
		ordenar.add("id.sddoco");
		ordenar.add("id.sdlnid");
		ordenar.add("sditm");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return f4211DAO
				.findBySdmcuInAndIdSddctoAndSdnxtrInOrSdlttrInAndSddrqjBetween(
						mcus, dct, next, last, fecha1, fecha2, o);
	}
}
