package com.sarvika.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarvika.dto.*;
import com.sarvika.entity.Order;
import com.sarvika.feign.ProductFeign;
import com.sarvika.feign.UserFeign;
import com.sarvika.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderServiceImpl implements  Orderservice{

    private final OrderRepository orderRepository;
    private final UserFeign userFeign;
    private final ProductFeign productFeign;
    private final ObjectMapper objectMapper;


    public OrderServiceImpl(OrderRepository orderRepository, UserFeign userFeign, ProductFeign productFeign, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.userFeign = userFeign;
        this.productFeign = productFeign;
        this.objectMapper = objectMapper;

    }

    @Override
    public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO) {
        Order orders=  orderRepository.save(objectMapper.convertValue(orderRequestDTO, Order.class));
        return objectMapper.convertValue(orders, OrderResponseDTO.class);

    }

    @Override
    public OrderResponseDTO updateOrder(int id, OrderRequestDTO orderRequestDTO) {
        Optional<Order> existingOrderOpt = orderRepository.findById(id);

        if (existingOrderOpt.isPresent()) {
            Order existingOrder = existingOrderOpt.get();
            if (orderRequestDTO.getUserId() > 0) existingOrder.setUserId(orderRequestDTO.getUserId());
            if (!orderRequestDTO.getProductIds().isEmpty()) existingOrder.setProductIds(orderRequestDTO.getProductIds());
            if (orderRequestDTO.getQuantity() > 0) existingOrder.setQuantity(orderRequestDTO.getQuantity());
            if (orderRequestDTO.getTotalprice() > 0) existingOrder.setTotalprice(orderRequestDTO.getTotalprice());
            if (orderRequestDTO.getStatus() != null) existingOrder.setStatus(orderRequestDTO.getStatus());
            if (orderRequestDTO.getOrderDate() != null) existingOrder.setOrderDate(orderRequestDTO.getOrderDate());
            existingOrder.setUpdateDate(LocalDateTime.now());
            Order updatedOrder = orderRepository.save(existingOrder);
            return objectMapper.convertValue(updatedOrder, OrderResponseDTO.class);
        }

        return null; // or throw exception
    }

    @Override
    public OrderDetailsResponseDTO fetchOrder(int id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if(orderOpt.isPresent()){
            OrderDetailsResponseDTO orderDetailsResponseDTO = new OrderDetailsResponseDTO();
            Order order = orderOpt.get();
            int userId = order.getUserId();
            UserResponseDTO userResponseDTO = fetchUserDetails(userId);
            List<String>  productIds = order.getProductIds();
            List<Product> products = fetchAllProductByIds(productIds);
            orderDetailsResponseDTO.setUserId(userId);
            orderDetailsResponseDTO.setUsername(userResponseDTO.getUsername());
            orderDetailsResponseDTO.setAddress(userResponseDTO.getAddress());
            orderDetailsResponseDTO.setStatus(order.getStatus());
            orderDetailsResponseDTO.setTotalprice(order.getTotalprice());
            orderDetailsResponseDTO.setQuantity(order.getQuantity());
            orderDetailsResponseDTO.setOrderDate(order.getOrderDate());
            orderDetailsResponseDTO.setProducts(products);
            return  orderDetailsResponseDTO;
        }
        return null;
    }

    private List<Product> fetchAllProductByIds(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for(String productId : productIds){
            Product product= objectMapper.convertValue(productFeign.fetchProductById(productId).getBody(),Product.class);
           if(Objects.nonNull(product)){
               product.setId(productId);
                 products.add(product);
           }
        }
        return  products;
    }

    private UserResponseDTO fetchUserDetails(int userId) {
        UserResponseDTO userResponseDTO =  objectMapper.convertValue(userFeign.fetchUsersById(userId).getBody(),UserResponseDTO.class);
        return  userResponseDTO;
    }

    @Override
    public List<OrderDetailsResponseDTO> fetchAllOrder() {
        List<Order> list = orderRepository.findAll();
        List<OrderDetailsResponseDTO> responseList = new ArrayList<>();
        for (Order order : list) {
            OrderDetailsResponseDTO orderDetailsResponseDTO = new OrderDetailsResponseDTO();
            int userId = order.getUserId();
            UserResponseDTO userResponseDTO = fetchUserDetails(userId);
            List<String> productIds = order.getProductIds();
            List<Product> products = fetchAllProductByIds(productIds);
            orderDetailsResponseDTO.setUserId(userId);
            orderDetailsResponseDTO.setUsername(userResponseDTO.getUsername());
            orderDetailsResponseDTO.setAddress(userResponseDTO.getAddress());
            orderDetailsResponseDTO.setStatus(order.getStatus());
            orderDetailsResponseDTO.setTotalprice(order.getTotalprice());
            orderDetailsResponseDTO.setQuantity(order.getQuantity());
            orderDetailsResponseDTO.setOrderDate(order.getOrderDate());
            orderDetailsResponseDTO.setProducts(products); // ✅ include products
            responseList.add(orderDetailsResponseDTO);
        }

        return responseList;
    }


    @Override
    public String deleteOrder(int id) {
        try{
            orderRepository.deleteById(id);
            return id+ " product deleted successfully";
        }
        catch (Exception e){
            return  e.getMessage();
        }
    }
}
