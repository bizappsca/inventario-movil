package servicio.inventario.maestros;

import interfacedao.inventario.maestros.IF40203DAO;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F40203;
import modelo.inventario.pk.F40203PK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SF40203")
public class SF40203 {

	@Autowired
	private IF40203DAO f40203DAO;

	public List<F40203> buscarTodosOrdenados() {
		return f40203DAO.findAllOrderByIdfsdcto();
	}

	public F40203 buscar(String value, String value2, String value3) {
		F40203PK clave = new F40203PK();
		clave.setFsdcto(value);
		clave.setFstrty(value2);
		clave.setFslnty(value3);
		return f40203DAO.findOne(clave);
	}

	public void guardar(F40203 f40203) {
		f40203DAO.save(f40203);
	}

	public void eliminarVarios(List<F40203> eliminar) {
		f40203DAO.delete(eliminar);
	}

	public void eliminarUno(F40203PK clave) {
		f40203DAO.delete(clave);
	}

	public List<F40203> buscarSY(String value) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("id.fstrty");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return f40203DAO.findByIdFsdcto(value, o);
	}

	public List<F40203> buscarSYYEstado(String dct, String next) {
		return f40203DAO.findByIdFsdctoAndIdFstrty(dct, next);
	}

	public List<F40203> buscarNextYEstado(String dct, String next) {
		return f40203DAO.findByIdFsdctoAndFsnxtr(dct, next);
	}
}
