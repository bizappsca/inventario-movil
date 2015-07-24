package componentes.catalogos;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F0010;

import org.zkoss.zk.ui.Component;

import componentes.utils.Convertidor;

public class CatalogoF0010 extends CatalogoGenerico<F0010> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogoF0010(Component cGenerico, String titulo, List<F0010> lista,
			boolean emergente, String... campos) {
		super(cGenerico, titulo, lista, emergente, campos);
	}

	@Override
	protected List<F0010> buscar(List<String> valores) {
		List<F0010> lista2 = new ArrayList<F0010>();
		for (F0010 f0010 : getLista()) {

			String fecha1 = "";
			if (f0010.getCcarfj() != null)
				fecha1 = Convertidor.formatoFecha.format(Convertidor
						.transformarJulianaAGregoria(f0010.getCcdfyj()));
			String fecha2 = "";
			if (f0010.getCcapfj() != null)
				fecha2 = Convertidor.formatoFecha.format(Convertidor
						.transformarJulianaAGregoria(f0010.getCcapfj()));
			String fecha3 = "";
			if (f0010.getCcdfyj() != null)
				fecha3 = Convertidor.formatoFecha.format(Convertidor
						.transformarJulianaAGregoria(f0010.getCcarfj()));
			String ccdot = "";
			if (f0010.getCcdot1() != null)
				ccdot = f0010.getCcdot1();
			if (f0010.getCcco().toLowerCase()
					.contains(valores.get(0).toLowerCase())
					&& f0010.getCcname().toLowerCase()
							.contains(valores.get(1).toLowerCase())
					&& String.valueOf(f0010.getCcpnc()).toLowerCase()
							.contains(valores.get(2).toLowerCase())
					&& ccdot.toLowerCase().contains(
							valores.get(3).toLowerCase())
					&& fecha1.toString().toLowerCase()
							.contains(valores.get(4).toLowerCase())
					&& String.valueOf(f0010.getCctxbm()).toLowerCase()
							.contains(valores.get(5).toLowerCase())
					&& fecha2.toString().toLowerCase()
							.contains(valores.get(6).toLowerCase())
					&& String.valueOf(f0010.getCctxbo()).toLowerCase()
							.contains(valores.get(7).toLowerCase())
					&& fecha3.toString().toLowerCase()
							.contains(valores.get(8).toLowerCase())
					&& String.valueOf(f0010.getCcarpn()).toLowerCase()
							.contains(valores.get(9).toLowerCase())) {
				lista2.add(f0010);
			}
		}
		return lista2;
	}

	@Override
	protected String[] crearRegistros(F0010 f0010) {
		String fecha1 = "";
		if (f0010.getCcarfj() != null)
			fecha1 = Convertidor.formatoFecha.format(Convertidor
					.transformarJulianaAGregoria(f0010.getCcarfj()));
		String fecha2 = "";
		if (f0010.getCcapfj() != null)
			fecha2 = Convertidor.formatoFecha.format(Convertidor
					.transformarJulianaAGregoria(f0010.getCcapfj()));
		String fecha3 = "";
		if (f0010.getCcdfyj() != null)
			fecha3 = Convertidor.formatoFecha.format(Convertidor
					.transformarJulianaAGregoria(f0010.getCcdfyj()));
		String[] registros = new String[11];
		registros[0] = f0010.getCcco();
		registros[1] = f0010.getCcname();
		registros[2] = String.valueOf(f0010.getCcpnc());
		registros[3] = f0010.getCcdot1();
		registros[4] = fecha1.toString();
		registros[5] = String.valueOf(f0010.getCctxbm());
		registros[6] = fecha2.toString();
		registros[7] = String.valueOf(f0010.getCctxbo());
		registros[8] = fecha3.toString();
		registros[9] = String.valueOf(f0010.getCcarpn());
		return registros;
	}

}
