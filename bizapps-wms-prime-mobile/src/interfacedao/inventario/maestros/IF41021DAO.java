package interfacedao.inventario.maestros;

import java.math.BigDecimal;
import java.util.List;

import modelo.inventario.maestros.F41021;
import modelo.inventario.pk.F41021PK;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF41021DAO extends JpaRepository<F41021, F41021PK> {

	@Query("select distinct f.id.liitm from F41021 f where f.id.limcu = ?1")
	List<Double> buscarItemDistintos(String value);

	// @Query("select f from F41021 f where f.liupmj <= ?1 order by f.id.limcu asc, f.id.lilocn asc, (select a.imdsc1 from F4101 a where a.imitm = f.id.liitm) asc")

	@Query(value = "select * from F41021 f where f.liupmj <= ?1 order by f.limcu asc, f.lilocn asc, "
			+ "(select a.IMDSC1 from F4101 a where a.imitm = f.liitm) asc", nativeQuery = true)
	List<F41021> findByLiupmjBeforeNow(BigDecimal desde);

	@Query(value = "select * from F41021 f where f.liupmj <= ?1 and f.lipqoh <> 0 order by f.limcu asc, f.lilocn asc, "
			+ "(select a.IMDSC1 from F4101 a where a.imitm = f.liitm) asc", nativeQuery = true)
	List<F41021> findByLiupmjBeforeNowExistencia(BigDecimal hasta);

	List<F41021> findByIdLimcuLikeAndIdLiitm(String planta, Double value, Sort o);

	List<F41021> findByIdLimcuLikeAndIdLiitmAndLipqohNot(String planta,
			Double value, Double valor, Sort o);

	@Query("select v from F41021 v where v.id.limcu like ?1 and v.lipqoh > "
			+ "(select f.ibanpl from F4102 f where f.id.ibmcu = v.id.limcu and f.id.ibitm = v.id.liitm) "
			+ "order by v.id.limcu asc, v.id.lilocn asc")
	List<F41021> findByMaxExistence(String company);

	@Query("select v from F41021 v where v.id.limcu like ?1 and v.lipqoh < "
			+ "(select f.ibavrt from F4102 f where f.id.ibmcu = v.id.limcu and f.id.ibitm = v.id.liitm) "
			+ "order by v.id.limcu asc, v.id.lilocn asc")
	List<F41021> findByMinExistence(String company);

	@Query("select v from F41021 v where v.id.limcu like ?1 and v.lipqoh < "
			+ "(select f.ibbqty from F4102 f where f.id.ibmcu = v.id.limcu and f.id.ibitm = v.id.liitm) "
			+ "order by v.id.limcu asc, v.id.lilocn asc")
	List<F41021> findByMinOrder(String company);

	@Query("select v from F41021 v where v.id.limcu like ?1 and v.lipqoh < "
			+ "(select f.ibcarp from F4102 f where f.id.ibmcu = v.id.limcu and f.id.ibitm = v.id.liitm) "
			+ "order by v.id.limcu asc, v.id.lilocn asc")
	List<F41021> findByMinSecurityStock(String company);

	@Query("select f from F41021 f, F4108 l "
			+ "where f.id.liitm = ?1 and l.id.ioitm = ?1 and f.id.lilotn = l.id.iolotn and f.id.limcu = l.id.iomcu and "
			+ "f.id.limcu in ?3 and f.lilots not in ?2 "
			+ "and (f.lipqoh - f.lihcom -f.lipcom -f.liqtin)>0 order by l.ioohdj asc")
	List<F41021> findByMcusAndItemAndLotsNotInAndAvailableFifo(Double imitm,
			List<String> lots, List<String> mcus);

	@Query("select f from F41021 f, F4108 l "
			+ "where f.id.liitm = ?1 and l.id.ioitm = ?1 and f.id.lilotn = l.id.iolotn and f.id.limcu = l.id.iomcu and "
			+ "f.id.limcu in ?3 and f.lilots not in ?2 "
			+ "and (f.lipqoh - f.lihcom -f.lipcom -f.liqtin)>0 order by l.ioohdj desc")
	List<F41021> findByMcusAndItemAndLotsNotInAndAvailableLifo(Double imitm,
			List<String> lots, List<String> mcus);

	@Query("select f from F41021 f, F4108 l "
			+ "where f.id.liitm = ?1 and l.id.ioitm = ?1 and f.id.lilotn = l.id.iolotn and f.id.limcu = l.id.iomcu and "
			+ "f.id.limcu in ?3 and f.lilots not in ?2 "
			+ "and (f.lipqoh - f.lihcom -f.lipcom -f.liqtin)>0 order by l.iommej asc")
	List<F41021> findByMcusAndItemAndLotsNotInAndAvailableFefo(Double imitm,
			List<String> lots, List<String> mcus);

	@Query("select f from F41021 f where f.id.liitm = ?2  and f.id.limcu in ?1 and f.lilots not in ?3 "
			+ "and (f.lipqoh - f.lihcom -f.lipcom -f.liqtin)>0")
	List<F41021> findByIdLimcuInAndIdLiitmAndLilotsNotIn(List<String> mcus,
			Double imitm, List<String> lots, Sort o);

	@Query(value = "select * from F41021 f where f.liitm like ?1 and f.lilots like ?2 and f.limcu like ?3 "
			+ "and f.lilocn like ?4 order by f.limcu asc, f.lilocn asc, f.liitm asc", nativeQuery = true)
	List<F41021> findByIdliitmLikeAndLotsLikeAndIdmcuLikeAndIdLocn(String item,
			String estado, String planta, String ubicacion);

	@Query(value = "select * from F41021 f where f.liitm like ?1 and f.limcu like ?2 and "
			+ "f.lilocn like ?3 and f.lilotn like ?4 and f.lilots like ?5 and  f.liupmj <= ?6 "
			+ "order by f.limcu asc, f.lilocn asc, f.lipqoh asc, f.liitm asc", nativeQuery = true)
	List<F41021> findByLiitmAndLimcuAndLilocnAndLilotnAndLiumpjBefore(
			String item, String planta, String ubicacion, String lote,
			String estado, BigDecimal hasta);

	@Query(value = "select * from F41021 f where (select sum(c.ILTRQT) as cardex from F4111 c "
			+ "where c.ilitm = f.liitm) <> (select sum(s.lipqoh) from F41021 s where s.liitm = f.liitm) "
			+ "and f.limcu like ?2 and f.lilocn like ?3 and f.liitm like ?1 and f.lilotn like ?4 "
			+ "and f.lilots like ?5", nativeQuery = true)
	List<F41021> findByDiferenceBetweenF4111AndF41021(String item,
			String planta, String ubicacion, String lote, String estado);

	@Query(value = "select * from F41021 f where f.liitm like ?1 and f.limcu like ?2 and f.lilocn like ?3 "
			+ "and f.lilotn like ?4 and f.lilots like ?5 and  f.liupmj between ?6 and  ?7 "
			+ "order by f.liitm asc", nativeQuery = true)
	List<F41021> findByIdliitmAndIdlimcuAndIdlocnAndIdlotnAndlilotsAndDateBetween(
			String item, String planta, String ubicacion, String lote,
			String estado, BigDecimal desde, BigDecimal hasta);

}
