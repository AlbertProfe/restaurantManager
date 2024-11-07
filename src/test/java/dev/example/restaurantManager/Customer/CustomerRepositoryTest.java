package dev.example.restaurantManager;

import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void whenFindByEmail_thenReturnCustomer() {
        // given
        Customer alex = new Customer("1", "Alex", "alex@example.com", "1234567890", 30, false, false);
        entityManager.persist(alex);
        entityManager.flush();
        //customerRepository.save(alex);

        // when
        Optional<Customer> found = customerRepository.findByEmail(alex.getEmail());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(alex.getName());
    }

    @Test
    public void whenFindByPhoneNumber_thenReturnCustomer() {
        // given
        Customer bob = new Customer("2", "Bob", "bob@example.com", "0987654321", 25, false, false);
        entityManager.persist(bob);
        entityManager.flush();

        // when
        Customer found = customerRepository.findByPhoneNumber(bob.getPhoneNumber());

        // then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(bob.getName());
    }

    @Test
    public void whenFindByNameAndEmail_thenReturnCustomer() {
        // given
        Customer charlie = new Customer("3", "Charlie", "charlie@example.com", "1122334455", 35, true, false);
        entityManager.persist(charlie);
        entityManager.flush();

        // when
        Customer found = customerRepository.findByNameAndEmail(charlie.getName(), charlie.getEmail());

        // then
        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(charlie.getId());
    }

    @Test
    public void whenFindByNameContaining_thenReturnCustomer() {
        // given
        Customer david = new Customer("4", "David", "david@example.com", "5566778899", 40, false, false);
        entityManager.persist(david);
        entityManager.flush();

        // when
        Customer found = customerRepository.findByNameContaining("avi");

        // then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(david.getName());
    }

    @Test
    public void whenFindByAgeGreaterThan_thenReturnCustomerList() {
        // given
        Customer eve = new Customer("5", "Eve", "eve@example.com", "9988776655", 45, true, false);
        Customer frank = new Customer("6", "Frank", "frank@example.com", "1122334455", 50, false, false);
        entityManager.persist(eve);
        entityManager.persist(frank);
        entityManager.flush();

        // when
        List<Customer> foundCustomers = customerRepository.findByAgeGreaterThan(40);

        // then
        assertThat(foundCustomers).hasSize(2);
        assertThat(foundCustomers).extracting(Customer::getName).containsExactlyInAnyOrder("Eve", "Frank");
    }
}