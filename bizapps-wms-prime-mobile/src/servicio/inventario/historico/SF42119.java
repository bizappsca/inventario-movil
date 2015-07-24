package servicio.inventario.historico;

import interfacedao.inventario.historico.IF42119DAO;

import java.util.List;

import modelo.inventario.historico.F42119;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SF42119")
public class SF42119 {

	@Autowired
	private IF42119DAO f42119DAO;

	public void guardarVarios(List<F42119> historicos) {
		f42119DAO.save(historicos);
	}
}
