package interfacedao.inventario.maestros;

import java.util.List;

import modelo.inventario.maestros.F4100;
import modelo.inventario.pk.F4100PK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF4100DAO extends JpaRepository<F4100, F4100PK> {

	@Query("Select f from F4100 f order by f.id.lmmcu asc")
	List<F4100> findAllOrderByMcu();

	List<F4100> findByIdLmmcu(String valor);

	List<F4100> findByIdLmmcuOrderByIdLmmcuAsc(String mcu);

	F4100 findByIdLmmcuAndIdLmlocn(String almacenRef, String ubicacionRef);

	List<F4100> findByIdLmmcuIn(List<String> mcus);

	@Query("select c from F4100 c where c.id.lmmcu = ?1 and c.id.lmlocn in (select distinct(f.id.lilocn)"
			+ " from F41021 f where f.id.liitm = ?2)")
	List<F4100> findByMcuAndItmExist(String mcu, Double idItem);
}
