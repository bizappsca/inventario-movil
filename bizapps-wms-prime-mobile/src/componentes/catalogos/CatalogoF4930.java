package componentes.catalogos;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F4930;

import org.zkoss.zk.ui.Component;

public class CatalogoF4930 extends CatalogoGenerico<F4930> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogoF4930(Component cGenerico, String titulo, List<F4930> lista,
			boolean emergente, String... campos) {
		super(cGenerico, titulo, lista, emergente, campos);
	}

	@Override
	protected List<F4930> buscar(List<String> valores) {
		List<F4930> tvehiculo = new ArrayList<F4930>();
		for (F4930 tvehiculos : getLista()) {
			if (tvehiculos.getVmvehi().toLowerCase()
					.contains(valores.get(0).toLowerCase())
					&& tvehiculos.getVmvtyp().toLowerCase()
							.contains(valores.get(1).toLowerCase())
					&& tvehiculos.getVmdl01().toLowerCase()
							.contains(valores.get(2).toLowerCase())
					&& tvehiculos.getVmmcu().toLowerCase()
							.contains(valores.get(3).toLowerCase())
					&& String.valueOf(tvehiculos.getVmvown()).toLowerCase()
							.contains(valores.get(4).toLowerCase())
					&& tvehiculos.getVmvehs().toLowerCase()
							.contains(valores.get(5).toLowerCase())
					&& String.valueOf(tvehiculos.getVmwtca()).toLowerCase()
							.contains(valores.get(6).toLowerCase())
					&& tvehiculos.getVmwtum().toLowerCase()
							.contains(valores.get(7).toLowerCase())
					&& String.valueOf(tvehiculos.getVmcvol()).toLowerCase()
							.contains(valores.get(8).toLowerCase())
					&& tvehiculos.getVmcvum().toLowerCase()
							.contains(valores.get(9).toLowerCase())) {
				tvehiculo.add(tvehiculos);
			}
		}
		return tvehiculo;
	}

	@Override
	protected String[] crearRegistros(F4930 objeto) {
		String[] registros = new String[10];
		registros[0] = objeto.getVmvehi();
		registros[1] = objeto.getVmvtyp();
		registros[2] = objeto.getVmdl01();
		registros[3] = objeto.getVmmcu();
		registros[4] = String.valueOf(objeto.getVmvown());
		registros[5] = objeto.getVmvehs();
		registros[6] = String.valueOf(objeto.getVmwtca());
		registros[7] = objeto.getVmwtum();
		registros[8] = String.valueOf(objeto.getVmcvol());
		registros[9] = objeto.getVmcvum();
		return registros;
	}

}
