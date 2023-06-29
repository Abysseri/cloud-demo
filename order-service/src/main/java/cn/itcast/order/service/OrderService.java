package cn.itcast.order.service;


import cn.itcast.feign.clients.UserClients;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private UserClients userClients;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.利用restTemplate发起Http请求，查询用户
//        // 2.1url路径
//        String url = "http://userservice/user/" +order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
        User user = userClients.findById(order.getUserId());
        // 3.封装user到order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
