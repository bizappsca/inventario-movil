package interfacedao.inventario.maestros;

import java.util.List;

import modelo.inventario.maestros.F4930;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface  IF4930DAO extends JpaRepository<F4930, String> {

	F4930 findByVmvehi(String clave);

	List<F4930> findByVmmcu(String vmmcu);

	List<F4930> findByVmvown(double vmvown);

	List<F4930> findByVmvtyp(String vmvtyp);

	@Query("select f from F4930 f where f.vmvehi in (select y.vsvehi from F49041 y where y.id.vsstfn = ?1)")
	List<F4930> findByFahrer(double fahrer);



}
