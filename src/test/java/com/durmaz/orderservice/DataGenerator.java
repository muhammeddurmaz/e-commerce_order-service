package com.durmaz.orderservice;

import com.durmaz.orderservice.domain.Order;
import com.durmaz.orderservice.domain.enums.OrderItemStatus;
import com.durmaz.orderservice.domain.enums.OrderStatus;
import com.durmaz.orderservice.service.dto.ProductDTO;
import com.durmaz.orderservice.service.dto.ViewOrderDetailDTO;
import com.durmaz.orderservice.service.dto.ViewOrderItemDTO;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class DataGenerator {
    private static final Long ORDER_ID = 1L;
    private static final String ORDER_CUSTOMER_NAME = "Sample Name";
    private static final Integer ORDER_TOTAL_QUANTITY = 5;
    private static final Double ORDER_TOTAL_PRICE = 15.00;
    private static final OrderStatus ORDER_STATUS = OrderStatus.PENDING;
    private static final LocalDateTime ORDER_PLACED_TIME = LocalDateTime.of(2023, 8, 5, 12, 0);
    private static final String ORDER_ADDRESS = "Istanbul";



    private static final Long VIEW_ORDER_ITEM_DTO_ID = 1L;
    private static final Integer VIEW_ORDER_ITEM_DTO_QUANTITY = 8;
    private static final Double VIEW_ORDER_ITEM_DTO_TOTAL_PRICE = 12.00;
    private static final OrderItemStatus VIEW_ORDER_ITEM_DTO_STATUS = OrderItemStatus.PENDING;
    private static final ProductDTO VIEW_ORDER_ITEM_DTO_PRODUCT_DTO = createProductDtoEntity();
    private static final List<ViewOrderItemDTO> viewOrderItemDTOList = Collections.singletonList(createViewOrderItemDtoEntity());




    private static final Long PRODUCT_DTO_ID = 101L;
    private static final String PRODUCT_DTO_NAME = "Sample Product";
    private static final String PRODUCT_DTO_DESCRIPTION = "This is a sample product.";
    private static final double PRODUCT_DTO_PRICE = 49.99;
    private static final Long PRODUCT_DTO_CATEGORY_ID = 1L;
    private static final int PRODUCT_DTO_AVAILABLE_STOCK = 100;


    public static Order createOrderEntity(){
        return new Order(
                ORDER_ID,
                ORDER_CUSTOMER_NAME,
                ORDER_TOTAL_QUANTITY,
                ORDER_TOTAL_PRICE,
                ORDER_STATUS,
                ORDER_PLACED_TIME,
                ORDER_ADDRESS
        );
    }


    public static ProductDTO createProductDtoEntity(){
        return new ProductDTO(
                PRODUCT_DTO_ID,
                PRODUCT_DTO_NAME,
                PRODUCT_DTO_DESCRIPTION,
                PRODUCT_DTO_PRICE,
                1L,
                PRODUCT_DTO_AVAILABLE_STOCK
        );
    }

    public static ViewOrderItemDTO createViewOrderItemDtoEntity(){
        return new ViewOrderItemDTO(
                VIEW_ORDER_ITEM_DTO_ID,
                VIEW_ORDER_ITEM_DTO_QUANTITY,
                VIEW_ORDER_ITEM_DTO_TOTAL_PRICE,
                VIEW_ORDER_ITEM_DTO_STATUS,
                VIEW_ORDER_ITEM_DTO_PRODUCT_DTO
        );
    }

    public static ViewOrderDetailDTO createViewOrderDetailDtoEntity(){
        return new ViewOrderDetailDTO(
                ORDER_ID,
                ORDER_CUSTOMER_NAME,
                ORDER_TOTAL_QUANTITY,
                ORDER_TOTAL_PRICE,
                ORDER_STATUS,
                ORDER_PLACED_TIME,
                ORDER_ADDRESS,
                viewOrderItemDTOList
        );
    }
}
