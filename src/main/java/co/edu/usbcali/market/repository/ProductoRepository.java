package co.edu.usbcali.market.repository;

import co.edu.usbcali.market.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    boolean existsByReferenciaIgnoreCase(String referencia);
    boolean existsByReferenciaIgnoreCaseAndIdNot(String referencia, Integer id);
}
