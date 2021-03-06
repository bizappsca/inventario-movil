package servicio.inventario.maestros;

import interfacedao.inventario.maestros.IF4931DAO;

import java.util.List;

import modelo.inventario.maestros.F4931;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SF4931")
public class SF4931 {

	@Autowired
	private IF4931DAO iF4931DAO;

	public void guardar(F4931 f4931) {
		iF4931DAO.save(f4931);
	}

	public List<F4931> buscarTodosOrdenados() {
		return iF4931DAO.findAllOrderByIdVgvtyp();
	}
	
	public F4931 buscar(String clave){
		return iF4931DAO.findByVgvtyp(clave);

	}
	
	public void eliminarVarios(List<F4931> eliminar) {
		iF4931DAO.delete(eliminar);
	}

	public void eliminarUno(String clave) {
		iF4931DAO.delete(clave);
	}
	

}
