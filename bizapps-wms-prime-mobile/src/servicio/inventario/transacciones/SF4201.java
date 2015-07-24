package servicio.inventario.transacciones;

import interfacedao.inventario.transacciones.IF4201DAO;

import java.util.Iterator;
import java.util.List;

import modelo.inventario.pk.F4201PK;
import modelo.inventario.transacciones.F4201;
import modelo.inventario.transacciones.F4211;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SF4201")
public class SF4201 {

	@Autowired
	private IF4201DAO f4201DAO;

	public void guardar(F4201 cabecera) {
		f4201DAO.save(cabecera);
	}

	public F4201 buscar(F4201PK claveCabecera) {
		return f4201DAO.findOne(claveCabecera);
	}

	public void eliminar(F4201PK claveCabecera) {
		f4201DAO.delete(claveCabecera);
	}

	public void eliminarSegunDetalle(List<F4211> eliminarLista) {
		for (Iterator<F4211> iterator = eliminarLista.iterator(); iterator
				.hasNext();) {
			F4211 f4211 = (F4211) iterator.next();
			F4201PK clave = new F4201PK();
			clave.setShdcto(f4211.getId().getSddcto());
			clave.setShdoco(f4211.getId().getSddoco());
			clave.setShkcoo(f4211.getId().getSdkcoo());
			if (f4201DAO.exists(clave))
				eliminar(clave);
		}
	}
}
