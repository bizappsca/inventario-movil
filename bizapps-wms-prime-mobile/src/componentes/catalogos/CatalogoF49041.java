package componentes.catalogos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F49041;

import org.zkoss.zk.ui.Component;

import componentes.utils.Convertidor;

public class CatalogoF49041 extends CatalogoGenerico<F49041> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogoF49041(Component cGenerico, String titulo,
			List<F49041> lista, boolean emergente, String... campos) {
		super(cGenerico, titulo, lista, emergente, campos);
	}

	@Override
	protected List<F49041> buscar(List<String> valores) {
		List<F49041> chofer = new ArrayList<F49041>();
		for (F49041 choferesv : getLista()) {
			if (String.valueOf(choferesv.getId().getVsstfn()).toLowerCase()
					.contains(valores.get(0).toLowerCase())
					&& String.valueOf(choferesv.getId().getVsstfn())
							.toLowerCase()
							.contains(valores.get(1).toLowerCase())
					&& choferesv.getVsmcu().toLowerCase()
							.contains(valores.get(2).toLowerCase())
					&& choferesv.getVsvehi().toLowerCase()
							.contains(valores.get(3).toLowerCase())
					&& choferesv.getId().getVsshft().toLowerCase()
							.contains(valores.get(4).toLowerCase())
					&& Convertidor.formatoFecha
							.format(Convertidor
									.transformarJulianaAGregoria(BigDecimal
											.valueOf(choferesv.getId()
													.getVseftj())))
							.toLowerCase()
							.contains(valores.get(5).toLowerCase())
					&& Convertidor.formatoFecha
							.format(Convertidor
									.transformarJulianaAGregoria(choferesv
											.getVsexdj())).toLowerCase()
							.contains(valores.get(6).toLowerCase())) {
				chofer.add(choferesv);
			}
		}
		return chofer;
	}

	@Override
	protected String[] crearRegistros(F49041 f49041) {
		String[] registros = new String[7];
		registros[0] = String.valueOf(f49041.getId().getVsstfn());
		registros[1] = String.valueOf(f49041.getId().getVsstfn());
		registros[2] = f49041.getVsmcu();
		registros[3] = f49041.getVsvehi();
		registros[4] = f49041.getId().getVsshft();
		if (f49041.getId().getVseftj() != 0)
			registros[5] = Convertidor.formatoFecha.format(Convertidor
					.transformarJulianaAGregoria(BigDecimal.valueOf(f49041
							.getId().getVseftj())));
		else
			registros[5] = "";
		if (f49041.getVsexdj() != null)
			registros[6] = Convertidor.formatoFecha.format(Convertidor
					.transformarJulianaAGregoria(f49041.getVsexdj()));
		else
			registros[6] = "";

		return registros;
	}

}
