package interfacedao.inventario.transacciones;

import java.math.BigDecimal;
import java.util.List;

import modelo.inventario.transacciones.F4111;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF4111DAO extends JpaRepository<F4111, Double> {

	List<F4111> findByIldctOrderByIlukidAsc(String tipo);

	List<F4111> findByIldocAndIldct(Double ildoc, String tipo);

	@Query("select coalesce((SUM(f.iltrqt)),'0') from F4111 f where f.ilitm=?1 and f.ilmcu=?2 and f.illocn=?3")
	double suma(double valor1, String valor2, String valor3);

	List<F4111> findByIlmcuOrIlmmcu(String clave, String clave2);

	List<F4111> findByIllocnOrIlasid(String lmlocn, String lmlocn2);

	List<F4111> findByIlan8(double aban8);

	List<F4111> findByIlitm(double clave);

	@Query("select f from F4111 f where f.iltrdj between ?1 and ?2 order by f.ildoc asc, f.ildct asc, f.ilitm asc")
	List<F4111> findByIltrdjBetweenOrderByIldocAsc(BigDecimal desde,
			BigDecimal hasta);

	@Query("select f from F4111 f where f.iltrdj < ?1 order by f.ilmcu asc, f.illocn asc, f.ilitm asc")
	List<F4111> findByIltrdjBeforeOrderByMcuAsc(BigDecimal hasta);

	List<F4111> findByIlan8AndIldct(Double value, String string, Sort o);

	@Query("select distinct fa.ildoc from F4111 fa where fa.ildct=?2 and fa.ilan8=?1")
	List<Double> findByIlan8AndIldctOrderByIldocAsc(Double value, String string);

	@Query("select f from F4111 f where f.ilvpej <= ?1 order by f.ilmcu asc, f.illocn asc, f.ilitm asc")
	List<F4111> findByIlvpejBeforeOrderByMcuAsc(BigDecimal hasta);

	@Query(value = "select * from F4111 f where f.ilvpej between ?1 and ?2 order by  f.ildct asc, f.ildoc asc, "
			+ "(select a.IMDSC1 from F4101 a where a.imitm = f.ilitm) asc", nativeQuery = true)
	List<F4111> findByIlvpejBetweenOrderByIldocAsc(BigDecimal desde,
			BigDecimal hasta);

	F4111 findByIldocAndIldctAndIlitm(Double claveDoc, String string,
			Double double1);

	List<F4111> findByIlvpejBetweenAndIlitmAndIlmcuLikeAndIllocnLikeAndIllotnLikeAndIldctLikeOrderByIlitmAsc(
			BigDecimal ilvpej1, BigDecimal ilvpej2, Double item, String planta,
			String ubicacion, String lote, String tipo);

	@Query(value = "select * from F4111 f where f.ilvpej between ?1 and ?2 and f.ildct=?3 "
			+ "order by f.ildct asc, f.ildoc asc, (select a.IMDSC1 from F4101 a where a.imitm = f.ilitm) asc", nativeQuery = true)
	List<F4111> findByIlvpejBetweenAndIldct(BigDecimal desde, BigDecimal hasta,
			String tipo);

	F4111 findByIldocAndIldctAndIlitmAndIllocn(Double claveDoc, String string,
			Double double1, String loc);

	List<F4111> findByIldocAndIldctAndIllocn(Double claveDoc, String string,
			String loc);

	@Query("select distinct(f.ilmcu) from F4111 f where f.ildct = ?2  and f.ildoc = ?1")
	List<String> findDisctincMcuByIldocAndIldct(Double claveDoc, String string);

	List<F4111> findByIldocAndIldctAndIlmcu(Double claveDoc, String string,
			String mcu);

	List<F4111> findByIlmcuInAndIldcto(List<String> mcus, String dct, Sort o);

	List<F4111> findByIlmcuInAndIldct(List<String> mcus, String dct, Sort o);

	@Query(value = "select * from F4111 f where f.ilitm like ?1 and f.ilmcu like ?2 "
			+ "and isnull(f.illocn,'') like ?3 and isnull(f.illotn,'') like ?4 "
			+ "and isnull(f.ilan8,'') like ?5 and isnull(f.ildoco,'') like ?7 "
			+ "and isnull(f.ildoc,'') like ?6 and f.ildct in ?8 "
			+ "and f.iltrqt >= 0 and f.ilvpej between ?9 and ?10  "
			+ "order by f.ilan8 asc, f.ilmcu asc, f.illocn asc, f.ilitm asc", nativeQuery = true)
	List<F4111> findByIlitmAndIlmcuAndIllocnAndIllotnAndIlan8AndDocoAndDocAndDctsInEntradaAndIlvrdjBetween(
			String item, String planta, String ubicacion, String lote,
			String proveedor, String ordenCliente, String doco,
			List<String> dcts, BigDecimal desde, BigDecimal hasta);

	@Query(value = "select * from F4111 f where f.ilitm like ?1 and f.ilmcu like ?2 "
			+ "and isnull(f.illocn,'') like ?3 and isnull(f.illotn,'') like ?4 "
			+ "and isnull(f.ilan8,'') like ?5 and isnull(f.ildoco,'') like ?7 "
			+ "and isnull(f.ildoc,'') like ?6 and f.ildct in ?8 "
			+ "and f.iltrqt < 0 and f.ilvpej between ?9 and ?10  "
			+ "order by f.ilmcu asc, f.illocn asc, f.ildoco asc", nativeQuery = true)
	List<F4111> findByIlitmAndIlmcuAndIllocnAndIllotnAndIlan8AndDocoAndDocAndDctsInSalidaAndIlvrdjBetween(
			String item, String planta, String ubicacion, String lote,
			String proveedor, String ordenCliente, String doco,
			List<String> dcts, BigDecimal desde, BigDecimal hasta);

	@Query(value = "select * from F4111 f where f.ilitm like ?1 and f.ilmcu like ?2 "
			+ "and isnull(f.illocn,'') like ?3 and f.illotn like ?4 and isnull(f.ilan8,'') like ?5"
			+ " and isnull(f.ildoco,'') like ?7 and isnull(f.ildoc,'') like ?6 "
			+ "and f.ilvpej between ?8 and ?9 order by f.illotn asc, f.iltrqt desc", nativeQuery = true)
	List<F4111> findByIlitmAndIlmcuAndIllocnAndIllotnAndIlan8AndDocoAndDocAndAndIlvrdjBetween(
			String item, String planta, String ubicacion, String lote,
			String proveedor, String ordenCliente, String doco,
			BigDecimal desde, BigDecimal hasta);

	@Query(value = "select * from F4111 f where f.ilitm like ?1 and f.ilmcu like ?2 "
			+ "and isnull(f.illocn,'') like ?3 and isnull(f.illotn,'') like ?4 and isnull(f.ilan8,'') like ?5"
			+ " and isnull(f.ildoco,'') like ?7 and isnull(f.ildoc,'') like ?6 "
			+ "and f.ilvpej between ?8 and ?9 order by f.ildoco asc, f.illotn asc, f.ilitm asc", nativeQuery = true)
	List<F4111> findByIlitmAndIlmcuAndIllocnAndIllotnAndIlan8AndDocoAndDocAndAndIlvrdjBetweenAlles(
			String item, String planta, String ubicacion, String lote,
			String proveedor, String ordenCliente, String doco,
			BigDecimal desde, BigDecimal hasta);

	@Query("select coalesce((SUM(f.iltrqt)),'0') from F4111 f where f.ilitm=?1 and f.ilmcu=?2 and f.illocn=?3 and f.illotn=?4")
	public Double sumByItemAndMcuAndLocnAndLotn(Double item, String mcu,
			String Locn, String lote);

	@Query("select coalesce((SUM(f.iltrqt)),'0') from F4111 f where f.ilitm=?1 and f.ilmcu=?2 "
			+ "and f.illocn=?3 and f.illotn=?4 and f.ilvpej <= ?5")
	Double sumByItemAndMcuAndLocnAndLotnAndFechaBis(Double liitm, String limcu,
			String lilocn, String lilotn, BigDecimal desde);
}
