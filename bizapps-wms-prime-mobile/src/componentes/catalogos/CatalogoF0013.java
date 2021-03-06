package componentes.catalogos;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F0013;

import org.zkoss.zk.ui.Component;

public class CatalogoF0013 extends CatalogoGenerico<F0013> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogoF0013(Component cGenerico, String titulo, List<F0013> lista,
			boolean emergente, String... campos) {
		super(cGenerico, titulo, lista, emergente, campos);
	}

	@Override
	protected List<F0013> buscar(List<String> valores) {
		List<F0013> lista = new ArrayList<F0013>();
		for (F0013 f0013 : getLista()) {
			if (f0013.getCvcrcd().toLowerCase().contains(valores.get(0))
					&& f0013.getCvdl01().toLowerCase().contains(valores.get(1))
					&& f0013.getCvcdec().toLowerCase().contains(valores.get(2))
					&& f0013.getCvckr().toLowerCase().contains(valores.get(3))) {
				lista.add(f0013);
			}
		}
		return lista;
	}

	@Override
	protected String[] crearRegistros(F0013 f013) {
		String[] registros = new String[4];
		registros[0] = f013.getCvcrcd();
		registros[1] = f013.getCvdl01();
		registros[2] = f013.getCvcdec();
		registros[3] = f013.getCvckr();
		return registros;
	}

}
