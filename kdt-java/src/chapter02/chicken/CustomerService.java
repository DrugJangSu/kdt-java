package chapter02.chicken;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(int id, String name, String grade) {

        boolean valid = grade.equals("NORMAL") || grade.equals("VIP") || grade.equals("NEWBIE");
        if (!valid) {
            throw new InvalidGradeException(grade);
        }

        customerRepository.save(new Customer(id, name, grade));
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(int id) {
        return customerRepository.findById(id);
    }

}
