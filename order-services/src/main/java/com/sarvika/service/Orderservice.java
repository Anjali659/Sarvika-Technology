package com.sarvika.service;

import com.sarvika.dto.OrderRequestDTO;
import com.sarvika.dto.OrderDetailsResponseDTO;
import com.sarvika.dto.OrderResponseDTO;

import java.util.List;

public interface Orderservice {
    OrderResponseDTO saveOrder(OrderRequestDTO user);

    OrderResponseDTO updateOrder(int id, OrderRequestDTO users);

    OrderDetailsResponseDTO fetchOrder(int id);

    List<OrderDetailsResponseDTO> fetchAllOrder();

    String deleteOrder(int id);
}
