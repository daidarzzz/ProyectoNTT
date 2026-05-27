package com.learnhub.order.application;

import com.learnhub.course.domain.CourseRepository;
import com.learnhub.order.application.dto.EstadoOrderRequest;
import com.learnhub.order.application.dto.OrderDetailResponse;
import com.learnhub.order.application.dto.OrderRequest;
import com.learnhub.order.application.dto.OrderResponse;
import com.learnhub.order.domain.Order;
import com.learnhub.order.domain.OrderDetail;
import com.learnhub.order.domain.OrderRepository;
import com.learnhub.order.domain.OrderStatus;
import com.learnhub.shared.domain.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;

    public OrderService(OrderRepository orderRepository, CourseRepository courseRepository) {
        this.orderRepository = orderRepository;
        this.courseRepository = courseRepository;
    }

    public OrderResponse create(Long usuarioId, OrderRequest request) {
        var order = new Order(usuarioId);
        var detalles = new ArrayList<OrderDetail>();

        for (Long cursoId : request.cursoIds()) {
            var course = courseRepository.findById(cursoId)
                .orElseThrow(() -> new ResourceNotFoundException("Curso", cursoId));

            boolean yaComprado = orderRepository.findByUsuarioId(usuarioId).stream()
                .anyMatch(o -> o.getEstado() == OrderStatus.PAGADO
                    && o.getDetalles().stream().anyMatch(d -> d.getIdCurso().equals(cursoId)));

            if (!yaComprado) {
                detalles.add(new OrderDetail(cursoId, 1, course.getPrecio()));
            }
        }

        if (detalles.isEmpty()) {
            throw new IllegalStateException("Todos los cursos seleccionados ya han sido comprados");
        }

        order.setDetalles(detalles);
        order.recalcularTotal();
        return toResponse(orderRepository.save(order));
    }

    public OrderResponse updateEstado(Long id, EstadoOrderRequest request, boolean isAdmin, Long usuarioId) {
        var order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));

        if (!isAdmin && !order.getIdUsuario().equals(usuarioId)) {
            throw new IllegalStateException("No puedes modificar un pedido que no te pertenece");
        }

        OrderStatus nuevoEstado = OrderStatus.valueOf(request.estado().toUpperCase());

        if (order.getEstado() == OrderStatus.PAGADO && nuevoEstado == OrderStatus.CANCELADO) {
            throw new IllegalStateException("No puedes cancelar un pedido ya pagado");
        }

        order.setEstado(nuevoEstado);
        return toResponse(orderRepository.save(order));
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(Long id, Long usuarioId, boolean isAdmin) {
        var order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));

        if (!isAdmin && !order.getIdUsuario().equals(usuarioId)) {
            throw new IllegalStateException("No puedes ver un pedido que no te pertenece");
        }

        return toResponse(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findByUsuario(Long usuarioId) {
        return orderRepository.findByUsuarioId(usuarioId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(this::toResponse).toList();
    }

    private OrderResponse toResponse(Order order) {
        var detalles = order.getDetalles().stream().map(d -> {
            String nombreCurso = courseRepository.findById(d.getIdCurso())
                .map(c -> c.getNombre())
                .orElse("Curso");
            BigDecimal subtotal = d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad()));
            return new OrderDetailResponse(
                d.getId(), d.getIdCurso(), nombreCurso,
                d.getCantidad(), d.getPrecioUnitario(), subtotal
            );
        }).toList();

        return new OrderResponse(
            order.getId(), order.getIdUsuario(), order.getFecha(),
            order.getTotal(), order.getEstado().name(), detalles
        );
    }
}
