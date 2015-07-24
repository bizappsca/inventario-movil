package interfacedao.inventario.seguridad;

import java.util.List;

import modelo.inventario.pk.F4201APK;
import modelo.inventario.seguridad.F4201A;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IF4201ADAO extends JpaRepository<F4201A, F4201APK> {

	List<F4201A> findByIdVerprg(Long idArbol);

}
