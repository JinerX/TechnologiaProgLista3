package eu.jpereira.trainings.designpatterns.structural.facade.model;

import eu.jpereira.trainings.designpatterns.structural.facade.BookstoreFacade;
import eu.jpereira.trainings.designpatterns.structural.facade.service.*;

public class Bookstore implements BookstoreFacade {
  private CustomerDBService customerDBService;
  private BookDBService bookDBService;
  private OrderingService orderingService;
  private WharehouseService warehouseService;
  public CustomerNotificationService customerNotificationService;

  public void setCustomerDBService(CustomerDBService customerDBService) {
    this.customerDBService = customerDBService;
  }

  public void setBookDBService(BookDBService bookDBService) {
    this.bookDBService = bookDBService;
  }

  public void setOrderingService(OrderingService orderingService) {
    this.orderingService = orderingService;
  }

  public void setWarehouseService(WharehouseService warehouseService) {
    this.warehouseService = warehouseService;
  }

  public void setCustomerNotificationService(CustomerNotificationService customerNotificationService) {
    this.customerNotificationService = customerNotificationService;
  }

  @Override
  public void placeOrder(String customerId, String isbn) {
    Customer customer = customerDBService.findCustomerById(customerId);
    Book book = bookDBService.findBookByISBN(isbn);

    Order order = orderingService.createOrder(customer,book);

    DispatchReceipt receipt = warehouseService.dispatch(order);

    customerNotificationService.notifyClient(receipt);
  }
}
