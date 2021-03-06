package interfacedao.inventario.maestros;


import java.util.List;

import modelo.inventario.maestros.F41002;
import modelo.inventario.pk.F41002PK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF41002DAO extends JpaRepository<F41002, F41002PK> {

	@Query("Select f from F41002 f order by f.id.umitm asc")
	List<F41002> findAllOrderByUMITM();

	List<F41002> findByIdUmitm(double clave);

}
