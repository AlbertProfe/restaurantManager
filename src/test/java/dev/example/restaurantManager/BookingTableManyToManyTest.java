package dev.example.restaurantManager;

import dev.example.restaurantManager.model.Booking;
import dev.example.restaurantManager.model.Customer;
import dev.example.restaurantManager.model.TableRestaurant;
import dev.example.restaurantManager.repository.BookingRepository;
import dev.example.restaurantManager.repository.CustomerRepository;
import dev.example.restaurantManager.repository.TableRestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class BookingTableManyToManyTest {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TableRestaurantRepository tableRestaurantRepository;

    @Test
    void createBooking() {

          // Create and save a customer
          // creating the arraylist for bookings
          // because it is a many-to-many relationship,
          // and we need to save it
          Customer c1 = new Customer();
          c1.setId("C1");
          c1.setName("John");
          c1.setEmail("john@email.com");
          c1.setPhoneNumber("123-456-7890");
          c1.setAge(30);
          c1.setVipCustomer(false);
          c1.setDeleted(false);
          c1.setBookings(new ArrayList<Booking>());
          // save customer c1
          customerRepository.save(c1);

          assertThat(customerRepository.findById("C1")).isPresent();
          assertThat(customerRepository.findById("C1").get().getName()).isEqualTo("John");

          // create and save a table restaurant
          TableRestaurant tr1 = new TableRestaurant();
          tr1.setId("TR01");
          tr1.setName("Table 01");
          tr1.setDescription("Table 01 for 4 people outdoors");
          tr1.setQty(4);
          tr1.setBusy(false);
          tr1.setBookings(new ArrayList<Booking>());

          // save table restaurant tr1
          tableRestaurantRepository.save(tr1);

          assertThat(tableRestaurantRepository.findById("TR01")).isPresent();
          assertThat(tableRestaurantRepository.findById("TR01").get().getQty()).isEqualTo(4);

          // create and save a booking
          Booking b1 = new Booking();
          b1.setId("BO01");
          b1.setBookingDate(new Date());
          b1.setCustomerMapped(c1);
          b1.setTableRestaurantMapped(tr1);
          b1.setShift("M");
          b1.setPeopleQty(8);
          b1.setDate(new Date());
          bookingRepository.save(b1);
          // create and save a booking
          Booking b2 = new Booking();
          b2.setId("BO02");
          b2.setBookingDate(new Date());
          b2.setCustomerMapped(c1);
          b2.setTableRestaurantMapped(tr1);
          b2.setShift("N");
          b2.setPeopleQty(8);
          b2.setDate(new Date());
          bookingRepository.save(b2);

          assertThat(bookingRepository.findById("BO01")).isPresent();
          assertThat(bookingRepository.findById("BO02")).isPresent();
          assertThat(bookingRepository.findById("BO03")).isNotPresent();

          // print all bookings, BOO3 does not exist
          System.out.println("--------------------");
          System.out.println("Bookings B001, B002, B003: " );
          System.out.println("--------------------");
          bookingRepository.findById("BO01").ifPresent(System.out::println);
          bookingRepository.findById("BO02").ifPresent(System.out::println);
          bookingRepository.findById("BO03").ifPresent(System.out::println);

          // add booking to table
          tr1.addBooking(b1);
          tr1.addBooking(b2);
          tableRestaurantRepository.save(tr1);
          Optional<TableRestaurant> tableFound = tableRestaurantRepository.findById("TR01");
          System.out.println('\n' + "Table: " + tableFound.get());
          // bookings field is now NOT null
          System.out.println("--------------------");
          System.out.println("Bookings Table: " );
          System.out.println("--------------------");
          System.out.println(tableFound.get().getBookings());

          assertThat(tableFound).isPresent();
          assertThat(tableFound.get().getBookings()).hasSize(2);


          // add booking to customer
          c1.addBooking(b1);
          c1.addBooking(b2);
          customerRepository.save(c1);
          Optional<Customer> customerFound = customerRepository.findById("C1");
          System.out.println( '\n' + "Customer: " + customerFound.get());
          System.out.println("--------------------");
          System.out.println("Bookings Customer: " );
          System.out.println("--------------------");
          System.out.println(customerFound.get().getBookings());

          assertThat(customerFound).isPresent();
          assertThat(customerFound.get().getBookings()).hasSize(2);



    }
}
