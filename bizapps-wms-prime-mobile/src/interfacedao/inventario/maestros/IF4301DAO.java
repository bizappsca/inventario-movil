package interfacedao.inventario.maestros;

import java.util.List;

import modelo.inventario.maestros.F4301;
import modelo.inventario.pk.F4301PK;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF4301DAO extends JpaRepository<F4301, F4301PK> {

	@Query("Select f from F4301 f order by f.id.phdoco asc")
	List<F4301> findAllOrderByIdPhdoco();
	
	List<F4301> findByIdPhdoco(Double value);

	List<F4301> findByPhmcuInAndIdPhdcto(List<String> mcus, String dct, Sort o);

}
