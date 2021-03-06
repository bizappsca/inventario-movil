package interfacedao.inventario.maestros;

import java.util.List;

import modelo.inventario.maestros.F0015;
import modelo.inventario.pk.F0015PK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF0015DAO extends JpaRepository<F0015, F0015PK> {

	@Query("Select f from F0015 f order by f.id.cxcrcd asc")
	List<F0015> findAllOrderByIdCxcrcd();
	
	List<F0015> findByIdCxcrcd(String value);

	//public List<F0015> findByIdCxan8(double cxan8);


}