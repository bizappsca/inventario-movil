package componentes.catalogos;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F0006;
import modelo.inventario.maestros.F4100;

import org.zkoss.zk.ui.Component;

import componentes.utils.Convertidor;

import controlador.maestros.CGenerico;

public class CatalogoF4100 extends CatalogoGenerico<F4100> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogoF4100(Component cGenerico, String titulo, List<F4100> lista,
			boolean emergente, String... campos) {
		super(cGenerico, titulo, lista, emergente, campos);
	}

	@Override
	protected List<F4100> buscar(List<String> valores) {
		List<F4100> listF4100_2 = new ArrayList<F4100>();

		for (F4100 f4100 : getLista()) {
			F0006 f0006 = CGenerico.getServicioF0006().buscar(
					f4100.getId().getLmmcu());
			String mcdc = "";
			if (f0006 != null)
				if (f0006.getMcdl01() != null)
					mcdc = f0006.getMcdl01();
			String num3 = "", num4 = "", num5 = "", num6 = "", num7 = "", num8 = "", num9 = "", num10 = "";
			if (f4100.getLmla03() != null)
				num3 = f4100.getLmla03();
			if (f4100.getLmla04() != null)
				num4 = f4100.getLmla04();
			if (f4100.getLmla05() != null)
				num5 = f4100.getLmla05();
			if (f4100.getLmla06() != null)
				num6 = f4100.getLmla06();
			if (f4100.getLmla07() != null)
				num7 = f4100.getLmla07();
			if (f4100.getLmla08() != null)
				num8 = f4100.getLmla08();
			if (f4100.getLmla09() != null)
				num9 = f4100.getLmla09();
			if (f4100.getLmla10() != null)
				num10 = f4100.getLmla10();
			if (String.valueOf(f4100.getId().getLmmcu()).toLowerCase()
					.contains(valores.get(0).toLowerCase())
					&& mcdc.toLowerCase()
							.contains(valores.get(1).toLowerCase())
					&& String
							.valueOf(
									Convertidor
											.transformarJulianaAGregoria(f4100
													.getLmupmj()))
							.toLowerCase()
							.contains(valores.get(2).toLowerCase())
					&& f4100.getId().getLmlocn().toLowerCase()
							.contains(valores.get(3).toLowerCase())
					&& f4100.getLmpzon().toLowerCase()
							.contains(valores.get(4).toLowerCase())
					&& f4100.getLmkzon().toLowerCase()
							.contains(valores.get(5).toLowerCase())
					&& f4100.getLmzonr().toLowerCase()
							.contains(valores.get(6).toLowerCase())
					&& f4100.getLmlldl().toLowerCase()
							.contains(valores.get(7).toLowerCase())
					// poner campos pasillo y bin
					&& num3.toLowerCase().contains(
							valores.get(10).toLowerCase())
					&& num4.toLowerCase().contains(
							valores.get(11).toLowerCase())
					&& num5.toLowerCase().contains(
							valores.get(12).toLowerCase())
					&& num6.toLowerCase().contains(
							valores.get(13).toLowerCase())
					&& num7.toLowerCase().contains(
							valores.get(14).toLowerCase())
					&& num8.toLowerCase().contains(
							valores.get(15).toLowerCase())
					&& num9.toLowerCase().contains(
							valores.get(16).toLowerCase())
					&& num10.toLowerCase().contains(
							valores.get(17).toLowerCase())
					&& f4100.getLmmixl().toLowerCase()
							.contains(valores.get(18).toLowerCase())
					&& f4100.getLmstag().toLowerCase()
							.contains(valores.get(19).toLowerCase())) {
				listF4100_2.add(f4100);
			}
		}
		return listF4100_2;
	}

	@Override
	protected String[] crearRegistros(F4100 f4100) {
		F0006 f0006 = CGenerico.getServicioF0006().buscar(
				f4100.getId().getLmmcu());
		String[] registros = new String[20];
		registros[0] = String.valueOf(f4100.getId().getLmmcu());
		if (f0006 != null)
			registros[1] = f0006.getMcdl01();
		if (f4100.getLmupmj() != null)
			registros[2] = Convertidor.formatoFecha.format((Convertidor
					.transformarJulianaAGregoria(f4100.getLmupmj())));
		else
			registros[2] = "";
		registros[3] = String.valueOf(f4100.getId().getLmlocn());
		registros[4] = f4100.getLmpzon();
		registros[5] = f4100.getLmkzon();
		registros[6] = f4100.getLmzonr();
		registros[7] = f4100.getLmlldl();
		registros[8] = "";
		registros[9] = "";
		registros[10] = f4100.getLmla03();
		registros[11] = f4100.getLmla04();
		registros[12] = f4100.getLmla05();
		registros[13] = f4100.getLmla06();
		registros[14] = f4100.getLmla07();
		registros[15] = f4100.getLmla08();
		registros[16] = f4100.getLmla09();
		registros[17] = f4100.getLmla10();
		registros[18] = f4100.getLmmixl();
		registros[19] = f4100.getLmstag();
		return registros;
	}

}
