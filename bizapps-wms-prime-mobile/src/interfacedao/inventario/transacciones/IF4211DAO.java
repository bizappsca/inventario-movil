package interfacedao.inventario.transacciones;

import java.math.BigDecimal;
import java.util.List;

import modelo.inventario.pk.F4211PK;
import modelo.inventario.transacciones.F4211;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF4211DAO extends JpaRepository<F4211, F4211PK> {

	@Query("Select f from F4211 f where f.sdspattn='Enviada' order by f.id.sddoco asc, f.id.sddcto asc, f.id.sdkcoo asc")
	List<F4211> findAllById();

	@Query("select distinct(f.id.sddoco) from F4211 f where f.sdspattn='Enviada' and f.id.sddcto = ?1")
	List<Double> findDocoDistinct(String et);

	F4211 findByIdSddocoAndSditm(Double value, Double imitm);

	List<F4211> findByIdSddocoAndIdSddctoAndSdspattnOrderBySditmAsc(
			Double sddoco, String sddcto, String string);

	@Query(value = "select * from F4211 f where f.sddrqj between ?1 and ?2 and f.sdspattn=?3 order by f.sddcto asc, "
			+ "f.sddoc asc, (select a.IMDSC1 from F4101 a where a.imitm = f.sditm) asc", nativeQuery = true)
	List<F4211> findBySddrqjBetweenAndSdspattn(BigDecimal desde,
			BigDecimal hasta, String string);

	@Query(value = "select * from F4211 f where f.sddrqj between ?1 and ?2 and f.sdspattn=?3 and f.sddcto = ?4 order by f.sddcto asc, "
			+ "f.sddoc asc, (select a.IMDSC1 from F4101 a where a.imitm = f.sditm) asc", nativeQuery = true)
	List<F4211> findBySddrqjBetweenAndSdspattnAndIdSddcto(BigDecimal desde,
			BigDecimal hasta, String string, String tipo);

	List<F4211> findBySdmcuInAndIdSddcto(List<String> mcus, String dct, Sort o);

	List<F4211> findByIdSddocoAndIdSddctoAndIdSdkcoo(Double sddoco,
			String sddcto, String sdkcoo);

	List<F4211> findBySdmcuInAndIdSddctoAndSdnxtrLikeAndSdlttrLike(
			List<String> mcus, String dct, String next, String last, Sort o);

	@Query(value = "select * from F4211 f where f.sdmcu in ?1 and f.sddcto like ?2 and f.sddoco like ?3 "
			+ "and f.sddoc like ?4 and f.sditm like ?5 and f.sdmcu like ?6 order by f.sddcto asc, "
			+ "f.sddoc asc, f.sditm asc", nativeQuery = true)
	List<F4211> findBySdmcuInAndIdSddctoLikeAndIdSddocoLikeAndSddocLikeAndSditmLikeAndSdmcuLike(
			List<String> mcus, String dct, String orden, String ordenCliente,
			String item, String planta);

	@Query("select coalesce(max(f.id.sdlnid), '0') from F4211 f where f.id.sddcto = ?1 and f.id.sddoco = ?2 and f.id.sdkcoo = ?3")
	Double findMaxSdlnid(String sddcto, Double sddoco, String sdkcoo);

	List<F4211> findByIdSddocoAndIdSddctoAndIdSdkcooAndSditmAndSdshpn(
			Double sddoco, String sddcto, String sdkcoo, Double sditm,
			Double sdshpn);

	List<F4211> findBySdcars(double doubleValue, Sort o);

	@Query(value = "select * from F4211 f where f.sdmcu in ?1 and f.sddcto like ?2 and f.sddoco like ?3 "
			+ "and f.sddoc like ?4 and f.sditm like ?5 and f.sdmcu like ?6 and f.sdcars like ?7 and f.sdlttr = ?8 order by f.sdcars asc, "
			+ "f.sditm asc", nativeQuery = true)
	List<F4211> findBySdmcuInAndIdSddctoLikeAndIdSddocoLikeAndSddocLikeAndSditmLikeAndSdmcuLikeCargaLikeLttrLike(
			List<String> mcus, String dct, String orden, String ordenCliente,
			String item, String planta, String carga, String last);

	List<F4211> findByIdSddocoAndIdSddctoAndIdSdkcooAndSditmAndSdcars(
			Double sddoco, String sddcto, String sdkcoo, Double sditm,
			Double sdcars);

	List<F4211> findBySdcarsAndSdlttr(double doubleValue, String part2, Sort o);

	List<F4211> findByIdSddocoAndIdSddctoAndIdSdkcooAndSditmAndSdcarsAndSdlttr(
			Double sddoco, String sddcto, String sdkcoo, Double sditm,
			Double sdcars, String sdlttr);

	List<F4211> findBySddmcsAndSdlttr(double doubleValue, String part2, Sort o);

	List<F4211> findByIdSddocoAndIdSddctoAndIdSdkcooAndSdcarsAndSdlttr(
			Double sddoco, String sddcto, String sdkcoo, Double sdcars,
			String sdlttr);

	List<F4211> findBySdcarsAndSdlttr(Double sdcars, String sdlttr);

	List<F4211> findBySdmcuInAndIdSddctoAndSdnxtrInOrSdlttrIn(
			List<String> mcus, String dct, List<String> next,
			List<String> last, Sort o);

	List<F4211> findByIdSddocoAndIdSddctoAndIdSdkcooAndSditmAndSdshpnAndSdlttr(
			Double sddoco, String sddcto, String sdkcoo, Double sditm,
			Double sdshpn, String sdlttr);

	F4211 findByIdSddocoAndIdSddctoAndIdSdkcooAndSditmAndSdnxtr(Double sddoco,
			String sddcto, String sdkcoo, Double sditm, String sdlttr);

	List<F4211> findBySddocAndSdxlln(Double docGrande, Double valueOf);

	List<F4211> findBySdmcuInAndIdSddctoAndSdnxtrInOrSdlttrInAndSddrqjBetween(
			List<String> mcus, String dct, List<String> next,
			List<String> last, BigDecimal fecha1, BigDecimal fecha2, Sort o);

}
