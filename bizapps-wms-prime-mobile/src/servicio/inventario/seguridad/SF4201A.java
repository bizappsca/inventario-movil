package servicio.inventario.seguridad;

import interfacedao.inventario.seguridad.IF4201ADAO;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.pk.F4201APK;
import modelo.inventario.seguridad.F4201A;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SF4201A")
public class SF4201A {

	@Autowired
	private IF4201ADAO f4201ADAO;

	public List<F4201A> buscarTodosOrdenados() {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("idVerprg");
		ordenar.add("idVerfield");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return f4201ADAO.findAll(o);
	}

	public void guardar(F4201A object) {
		f4201ADAO.save(object);
	}

	public F4201A buscar(F4201APK id) {
		return f4201ADAO.findOne(id);
	}

	public List<F4201A> buscarPorPrg(Long idArbol) {
		return f4201ADAO.findByIdVerprg(idArbol);
	}
}
