package servicio.inventario.maestros;

import interfacedao.inventario.maestros.IF4108DAO;

import java.math.BigDecimal;
import java.util.List;

import modelo.inventario.maestros.F4108;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SF4108")
public class SF4108 {

	@Autowired
	private IF4108DAO iF4108DAO;

	public List<F4108> buscarPorMcuExistenciaItm(String mcu, Double idItem) {
		return iF4108DAO.findByIdIomcuAndIdIoitmExistencia(mcu, idItem);
	}

	public List<F4108> buscarTodos() {
		return iF4108DAO.findAll();
	}

	public void guardar(F4108 object) {
		iF4108DAO.save(object);
	}

	public List<F4108> buscarPorMcuEItemYLoteLike(String planta, String item,
			String lotn, String loteProveedor) {
		return iF4108DAO.findByIdItmLikeAndIdMcuLikeAndIDLotnLike(item, planta, lotn, loteProveedor);
	}

	public List<F4108> buscarEntreFechasYParametros(BigDecimal hasta,
			String item, String planta) {
		return iF4108DAO.findByIoitemLikeAndIoMcuLikeAndIoIommejBetween(item, planta, hasta);
	}
}
