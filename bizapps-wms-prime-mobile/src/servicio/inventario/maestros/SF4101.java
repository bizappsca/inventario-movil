package servicio.inventario.maestros;

import interfacedao.inventario.maestros.IF4101DAO;
import interfacedao.inventario.maestros.IF41021DAO;
import interfacedao.inventario.transacciones.IF4111DAO;
import interfacedao.inventario.transacciones.IF4211DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.inventario.maestros.F4101;
import modelo.inventario.transacciones.F4111;
import modelo.inventario.transacciones.F4211;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SF4101")
public class SF4101 {

	@Autowired
	private IF4101DAO f4101DAO;
	@Autowired
	private IF4111DAO f4111DAO;
	@Autowired
	private IF41021DAO f41021DAO;
	@Autowired
	private IF4211DAO f4211DAO;

	public void guardar(F4101 f4101) {
		f4101DAO.save(f4101);
	}

	public void eliminarVarios(List<F4101> eliminar) {
		f4101DAO.delete(eliminar);
	}

	public void eliminarUno(double umitm) {
		f4101DAO.delete(umitm);
	}

	public F4101 buscar(double umitm) {
		return f4101DAO.findOne(umitm);
	}

	public List<F4101> buscarTodosOrdenados() {
		return f4101DAO.findAllAndOrderByIMDSC1();
	}

	public List<F4101> buscarTodosOrdenadosPorDoc(String string, Double claveDoc, String loc) {
		List<F4111> lista = f4111DAO.findByIldocAndIldctAndIllocn(claveDoc, string, loc);
		List<F4101> retorna = new ArrayList<F4101>();
		for (int i = 0; i < lista.size(); i++) {
			F4101 f = buscar(lista.get(i).getIlitm());
			retorna.add(f);
		}
		return retorna;
	}

	public List<F4101> buscarTodosOrdenadosPorMcu(String value) {
//		List<Double> lista = f41021DAO.buscarItemDistintos(value);
//		List<F4101> buscados = new ArrayList<F4101>();
////		for (Iterator<Double> iterator = lista.iterator(); iterator.hasNext();) {
////			Double double1 = (Double) iterator.next();
////			buscados.add(buscar(double1));
////		}
//		buscados = f4101DAO.findByImitmIn(lista);
////		for (int i = 0; i < lista.size(); i++) {
////			buscados.add(buscar(lista.get(i)));
////		}
		return f4101DAO.buscarPorMcu(value);
	}

	public List<F4101> buscarTodosOrdenadosPorSolicitud(Double value, String et) {
		List<F4211> listaF4211 = f4211DAO
				.findByIdSddocoAndIdSddctoAndSdspattnOrderBySditmAsc(value, et,"Enviada");
		List<F4101> lista = new ArrayList<F4101>();
		for (Iterator<F4211> iterator = listaF4211.iterator(); iterator
				.hasNext();) {
			F4211 f4211 = (F4211) iterator.next();
			if (f4211.getSditm() != null)
				lista.add(buscar(f4211.getSditm()));
		}
		return lista;
	}

	public void guardarVarios(List<F4101> articulos) {
		f4101DAO.save(articulos);
	}

	public F4101 buscarPorReferencia(long l) {
		return f4101DAO.findByReferencia(l);
	}
	
	public F4101 buscarPorImlitm(String idSecundario) {
		List<F4101> lista = f4101DAO.findByImlitm(idSecundario);
		if (!lista.isEmpty())
			return lista.get(0);
		return null;
	}

}
