package servicio.inventario.maestros;

import interfacedao.inventario.maestros.IF4301DAO;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F4301;
import modelo.inventario.pk.F4301PK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SF4301")
public class SF4301 {

	@Autowired
	private IF4301DAO iF4301DAO;

	public void guardar(F4301 f4301) {
		iF4301DAO.save(f4301);
	}

	public List<F4301> buscarTodosOrdenados() {
		return iF4301DAO.findAllOrderByIdPhdoco();
	}
	
	public List<F4301> buscarDOCO(Double value) {
		return iF4301DAO.findByIdPhdoco(value);
	}

	public List<F4301> buscarPorMcusYEstados(List<String> mcus, String dct) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("id.phdcto");
		ordenar.add("id.phdoco");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return iF4301DAO.findByPhmcuInAndIdPhdcto(mcus,
				dct, o);
	}

	public void eliminarVarios(List<F4301> eliminarLista) {
		iF4301DAO.delete(eliminarLista);
	}

	public void eliminar(F4301PK clave) {
		iF4301DAO.delete(clave);
	}

}
