package servicio.inventario.maestros;

import interfacedao.inventario.maestros.IF4311DAO;

import java.util.List;

import modelo.inventario.maestros.F4311;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SF4311")
public class SF4311 {

	@Autowired
	private IF4311DAO iF4311DAO;

	public void guardar(F4311 f4311) {
		iF4311DAO.save(f4311);
	}

	public List<F4311> buscarTodosOrdenados() {
		return iF4311DAO.findAllOrderByIdPddoco();
	}

	public List<F4311> buscarPorDocoDctoKcooYSfxo(double phdoco, String phdcto,
			String phkcoo, String phsfxo) {
		return iF4311DAO
				.findByIdPddocoAndIdPddctoAndIdPdkcooAndIdPdsfxoOrderByIdPddocoAsc(
						phdoco, phdcto, phkcoo, phsfxo);
	}

	public void eliminarVarios(List<F4311> eliminarDetalles) {
		iF4311DAO.delete(eliminarDetalles);
	}

	public List<F4311> buscarPorDocoYDcto(Double ildoco, String ildct) {
		return iF4311DAO.findByIdPddocoAndIdPddctoOrderByIdPddocoAsc(ildoco,
				ildct);
	}
}
