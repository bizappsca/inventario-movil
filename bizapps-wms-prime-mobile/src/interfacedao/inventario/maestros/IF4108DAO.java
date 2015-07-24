package interfacedao.inventario.maestros;

import java.math.BigDecimal;
import java.util.List;

import modelo.inventario.maestros.F4108;
import modelo.inventario.pk.F4108PK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF4108DAO extends JpaRepository<F4108, F4108PK> {

	List<F4108> findByIdIomcuAndIdIoitm(String mcu, Double idItem);

	@Query("select c from F4108 c where c.id.iolotn in (select distinct(f.id.lilotn) from F41021 f where f.id.liitm = ?2) "
			+ "and c.id.iomcu = ?1 and c.id.ioitm = ?2")
	List<F4108> findByIdIomcuAndIdIoitmExistencia(String mcu, Double idItem);

	@Query(value = "select * from F4108 f where f.iomcu like ?2 and f.ioitm like ?1 and f.iolotn like ?3 and COALESCE(f.iorlot,'') like ?4 order by f.ioitm asc, "
			+ "f.iomcu asc, f.iolotn asc", nativeQuery = true)
	List<F4108> findByIdItmLikeAndIdMcuLikeAndIDLotnLike(String item,
			String planta, String lotn, String loteProveedor);

	@Query(value = "select * from F4108 f where f.ioitm like ?1 and f.iomcu like ?2 and "
			+ "f.iommej >= ?3 order by f.iomcu asc, iommej asc, f.ioitm asc ", nativeQuery = true)
	List<F4108> findByIoitemLikeAndIoMcuLikeAndIoIommejBetween(String item,
			String planta, BigDecimal desde);

}
