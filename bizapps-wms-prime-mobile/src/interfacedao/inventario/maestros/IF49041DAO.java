package interfacedao.inventario.maestros;

import java.util.List;

import modelo.inventario.maestros.F49041;
import modelo.inventario.pk.F49041PK;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  IF49041DAO extends JpaRepository<F49041, F49041PK> {

	List<F49041> findByVsmcu(String vsmcu);

	List<F49041> findByIdVsstfn(double vsstfn);

	List<F49041> findByVsvehi(String vsvehi);

	List<F49041> findByIdVsstfnAndVsvehi(Double idChofer, String placaChuto);



}
