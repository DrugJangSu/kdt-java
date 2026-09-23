package chapter02.chicken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {
    private final Map<Integer, Customer> customers = new HashMap<>();

    public void save(Customer customer) {
        customers.put(customer.getId(), customer);
    }
    public Customer findById(int id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }
}
