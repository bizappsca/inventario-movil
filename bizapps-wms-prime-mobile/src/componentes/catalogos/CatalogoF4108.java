package componentes.catalogos;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F4108;

import org.zkoss.zk.ui.Component;

import componentes.utils.Convertidor;

public class CatalogoF4108 extends CatalogoGenerico<F4108> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogoF4108(Component cGenerico, String titulo, List<F4108> lista,
			boolean emergente, String... campos) {
		super(cGenerico, titulo, lista, emergente, campos);
	}

	@Override
	protected List<F4108> buscar(List<String> valores) {
		List<F4108> objects = new ArrayList<F4108>();
		for (F4108 objeto : getLista()) {
			String lots = "";
			if (objeto.getIolots() != null)
				lots = objeto.getIolots();
			String desc = "";
			if (objeto.getIoldsc() != null)
				desc = objeto.getIoldsc();
			String descItem = "";
			if (objeto.getIolitm() != null)
				descItem = objeto.getIolitm();
			if (String.valueOf(objeto.getId().getIoitm().longValue())
					.toLowerCase().contains(valores.get(0).toLowerCase())
					&& descItem.toLowerCase().contains(
							valores.get(1).toLowerCase())
					&& Convertidor.formatoFecha
							.format(Convertidor
									.transformarJulianaAGregoria(objeto
											.getIommej())).toLowerCase()
							.contains(valores.get(3).toLowerCase())
					&& lots.toLowerCase()
							.contains(valores.get(2).toLowerCase())
					&& objeto.getId().getIomcu().toLowerCase()
							.contains(valores.get(4).toLowerCase())
					&& objeto.getId().getIolotn().toLowerCase()
							.contains(valores.get(5).toLowerCase())
					&& desc.toLowerCase()
							.contains(valores.get(6).toLowerCase())) {
				objects.add(objeto);
			}
		}
		return objects;
	}

	@Override
	protected String[] crearRegistros(F4108 objeto) {
		String[] registros = new String[7];
		registros[0] = String.valueOf(objeto.getId().getIoitm().longValue());
		registros[1] = objeto.getIolitm();
		registros[2] = objeto.getIolots();
		registros[3] = Convertidor.formatoFecha.format(Convertidor
				.transformarJulianaAGregoria(objeto.getIommej()));
		registros[4] = objeto.getId().getIomcu();
		registros[5] = objeto.getId().getIolotn();
		registros[6] = objeto.getIoldsc();
		return registros;
	}

}
