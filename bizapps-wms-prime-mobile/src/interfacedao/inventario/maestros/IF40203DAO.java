package interfacedao.inventario.maestros;

import java.util.List;

import modelo.inventario.maestros.F40203;
import modelo.inventario.pk.F40203PK;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF40203DAO extends JpaRepository<F40203, F40203PK> {
	

		@Query("Select f from F40203 f order by f.id.fsdcto asc, f.id.fstrty asc, f.id.fslnty asc")
		List<F40203> findAllOrderByIdfsdcto();

		List<F40203> findByIdFsdcto(String value);

		List<F40203> findByIdFsdcto(String value, Sort o);

		List<F40203> findByIdFsdctoAndIdFstrty(String dct, String next);

		List<F40203> findByIdFsdctoAndFsnxtr(String dct, String next);

}
