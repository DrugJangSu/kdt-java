package chapter02.chicken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderService {

    private final Map<String, DiscountPolicy> policyMap = new HashMap<>();
    private final OrderRepository orderRepository;
    private final ChickenRepository chickenRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, ChickenRepository chickenRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.chickenRepository = chickenRepository;
        this.customerRepository = customerRepository;

        policyMap.put("NORMAL", new NormalDiscountPolicy());
        policyMap.put("VIP", new VipDiscountPolicy());
        policyMap.put("NEWBIE", new NewbieDiscountPolicy());

    }

    /*
     1. 고객 조회 (없으면 CustomerNotFoundException)
     2. 치킨 조회 (없으면 ChickenNotFoundException)
     3. 고객 등급에 맞는 DiscountPolicy 선택 (매핑에 없으면 InvalidGradeException)
     4. discount() 호출 -> 최종 금액 계산
     5. Order 객체 생성 -> Repository에 저장 -> 반환
     */

    public Order order(int orderID, int customerID, int chickenID) {
        Customer customer = customerRepository.findById(customerID); // 가져온다. id를 통해서 커스터를 가져옴
        Chicken chicken = chickenRepository.findById(chickenID);

        DiscountPolicy discountPolicy = policyMap.get(customer.getGrade());

        if (discountPolicy == null) {
            throw new InvalidGradeException(customer.getGrade());
        }

        int originalPrice = chicken.getPrice();
        int finalPrice = discountPolicy.discount(originalPrice);

        Order order = new Order(orderID, customer.getId(), chicken.getId(), originalPrice, finalPrice);
        orderRepository.save(order);

        return order;

    }
        public List<Order> getAllOrders() {
        return orderRepository.findAll();
        }


}
