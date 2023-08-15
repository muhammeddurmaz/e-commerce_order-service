package com.durmaz.orderservice.service;

import com.durmaz.orderservice.DataGenerator;
import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.repository.OrderRepository;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;
import com.durmaz.orderservice.service.exception.OrderNotFoundException;
import com.durmaz.orderservice.service.impl.OrderItemServiceImpl;
import com.durmaz.orderservice.service.impl.OrderServiceImpl;
import com.durmaz.orderservice.service.mapper.OrderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest {

    private Order order;
    private ViewOrderDetailDTO viewOrderDetailDTO;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItemServiceImpl orderItemService;
    @Mock
    private OrderMapper orderMapper;
    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = DataGenerator.createOrderEntity();
        viewOrderDetailDTO = DataGenerator.createViewOrderDetailDtoEntity();
    }

    @Test
    void testGetOrderDetailById_OrderExists(){

        Mockito.when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        Mockito.when(orderItemService.getOrderItemsDetailByOrderId(order.getId())).thenReturn(viewOrderDetailDTO.getOrderItemDetails());
        Mockito.when(orderMapper.toDto(order)).thenReturn(viewOrderDetailDTO);

        ViewOrderDetailDTO result = orderService.getOrderDetailById(order.getId());

        assertEquals(viewOrderDetailDTO,result);

        Mockito.verify(orderRepository).findById(order.getId());
        Mockito.verify(orderItemService).getOrderItemsDetailByOrderId(order.getId());
        Mockito.verify(orderMapper).toDto(order);
    }

    @Test
    public void testGetOrderDetailById_OrderNotFound() {
        Long orderId = 2L;

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderDetailById(orderId));

        Mockito.verify(orderRepository).findById(orderId);
        Mockito.verifyNoMoreInteractions(orderItemService);
        Mockito.verifyNoMoreInteractions(orderMapper);
    }
}
