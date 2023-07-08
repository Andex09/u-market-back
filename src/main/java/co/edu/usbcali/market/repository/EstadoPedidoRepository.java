package co.edu.usbcali.market.repository;

import co.edu.usbcali.market.domain.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer> {
}
