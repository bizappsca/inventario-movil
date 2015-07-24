package interfacedao.inventario.maestros;

import java.util.List;

import modelo.inventario.maestros.F0006;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF0006DAO extends JpaRepository<F0006, String> {

	@Query("Select f from F0006 f order by f.mcmcu asc")
	List<F0006> findAllOrderByMcmcu();

	List<F0006> findByMcmcu(String value);

	List<F0006> findByMcco(String ccco);

	List<F0006> findByMcan8(double mcan8);

	@Query("select c from F0006 c where c.mcmcu in (select distinct(f.id.limcu) from F41021 f where f.id.liitm = ?1)")
	List<F0006> findByItmIn(Double idItem);
}
