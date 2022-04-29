package com.waazon.backend.services.Impls;

import com.waazon.backend.services.BuyerService;
import com.waazon.backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.waazon.backend.domains.*;
import com.waazon.backend.dtos.LineItemDTO;
import com.waazon.backend.dtos.OrderDTO;
import com.waazon.backend.repositories.*;
import com.waazon.backend.services.ProductsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BuyerServiceImpl implements BuyerService {
    SellerRepo sellerRepo;
    BuyerRepo buyerRepo;
    OrderRepo orderRepo;
    OrderService orderService;
    ProductsService productService;
    AddressRepo addressRepo;
    CreditCardRepo creditCardRepo;

    @Autowired
    public BuyerServiceImpl(BuyerRepo buyerRepo, SellerRepo sellerRepo, OrderRepo orderRepo, OrderService orderService, ProductsService productService, AddressRepo addressRepo, CreditCardRepo creditCardRepo) {
        this.buyerRepo = buyerRepo;
        this.sellerRepo = sellerRepo;
        this.orderRepo = orderRepo;
        this.orderService = orderService;
        this.productService = productService;
        this.addressRepo = addressRepo;
        this.creditCardRepo = creditCardRepo;
    }

    private final JavaMailSender mailSender = new JavaMailSenderImpl();


    @Override
    public List<Order> getAllOrdersForBuyer(long id) {
        return buyerRepo.getAllOrdersByBuyerId(id);
    }

    @Override
    public Buyer getBuyerByUsername(String userName) {
        return buyerRepo.findBuyerByUsername(userName);
    }

    @Override
    public void followSeller(long buyerId, long sellerId) {
        Buyer buyer = buyerRepo.findBuyerByBId(buyerId);
        Seller seller = sellerRepo.findSellerById(sellerId);
        List<Seller> sellersFollowed = buyerRepo.getSellerFollowedByBuyerId(buyerId);
        sellersFollowed.add(seller);
        buyer.setSellersFollowed(sellersFollowed);
        buyerRepo.save(buyer);
    }

    @Override
    public void unFollowSeller(long buyerId, long sellerId) {
        Buyer buyer = buyerRepo.findBuyerByBId(buyerId);
        Seller seller = sellerRepo.findSellerById(sellerId);
        List<Seller> sellersFollowed = buyerRepo.getSellerFollowedByBuyerId(buyerId);
        sellersFollowed.remove(seller);

        buyer.setSellersFollowed(sellersFollowed);
        buyerRepo.save(buyer);
    }

    @Override
    public Buyer getBuyerBybId(long id) {
        return buyerRepo.findBuyerByBId(id);
    }

    @Override
    public List<Buyer> getAllBuyers() {
        return buyerRepo.findAllBuyers();
    }

    @Override
    public Buyer getBuyerByEmail(String email) {
        return buyerRepo.findBuyerByEmail(email);
    }

    @Override
    public List<Order> getAllOrderByBuyerId(long id) {
        return buyerRepo.getAllOrdersByBuyerId(id);
    }

    @Override
    public void addBuyer(Buyer buyer) {
        buyerRepo.save(buyer);
    }

    @Override
    public List<Buyer> getAllBuyerWithPagingAndSorting(Pageable pageable) {
        return buyerRepo.findAllBuyers();
    }

    @Override
    public void addOrder(OrderDTO orderDTO, String userName) {
        Buyer buyer = buyerRepo.findBuyerByUsername(userName);
        Order order = new Order();
        order.setBuyer(buyer);

        order.setLineItems(new ArrayList<>());

        for (int i = 0; i < orderDTO.getLineItemsDTO().size(); i++) {
            LineItem li = new LineItem();
            LineItemDTO lineItemDTO;
            lineItemDTO = orderDTO.getLineItemsDTO().get(i);
            Product product = productService.getProductById(lineItemDTO.getProductId()).orElse(null);
            assert product != null;
            li.setPrice(product.getPrice());
            li.setQuantity(lineItemDTO.getQuantity());
            li.setProduct(product);
            order.getLineItems().add(li);
        }

        order.setPrice(order.getLineItems().stream().map(l -> l.getPrice() * l.getQuantity()).reduce((double) 0, Double::sum));
        orderRepo.save(order);

        List<Order> orders = orderRepo.findAllOrdersByBuyerId(buyer.getBId());
        orders.add(order);

        buyer.setOrders(orders);
        buyerRepo.save(buyer);

        String orderText = order.toString();

        sendEmail(buyer.getUser().getEmail(), orderText);
    }

    private void sendEmail(String emailUser, String text) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(emailUser);
            email.setSubject("waazon : Order details....");
            email.setText(text);
            mailSender.send(email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean deleteOrder(String userName, long id) {
        Order order = orderService.getOrderById(id);
        if (!Objects.equals(order.getOrderStatus(), OrderStatus.SHIPPED.getOrderStatus()) || !Objects.equals(order.getOrderStatus(), OrderStatus.DELIVERED.getOrderStatus())) {
            Buyer buyer = buyerRepo.findBuyerByUsername(userName);
            buyer.getOrders().remove(order);

            buyerRepo.save(buyer);
            orderRepo.delete(order);

            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LineItem> listOrderItems(String userName, long id) {
        Buyer buyer = buyerRepo.findBuyerByUsername(userName);
        return (List<LineItem>) buyer.getOrders().stream().map(o -> {
                    if (o.getId() == id)
                        return o.getLineItems();
                    else return null;
                }
        );
    }

    @Override
    public void editBuyer(String userName, Buyer buyer) {
        try {
            buyerRepo.save(buyer);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address addBillingAddress(String userName, Address address) {
        Buyer old = buyerRepo.findBuyerByUsername(userName);
        old.setBillingAddress(address);
        buyerRepo.save(old);
        return address;
    }

    @Override
    public Address editBillingAddress(long address_id, Address address) {
        Address oldAddress = addressRepo.findById(address_id).orElse(null);
        if (oldAddress != null) {
            return getAddress(address, oldAddress);
        } else return null;
    }

    private Address getAddress(Address address, Address oldAddress) {
        if (address.getCountry() != null)
            oldAddress.setCountry(address.getCountry());
        if (address.getCity() != null)
            oldAddress.setCity(address.getCity());
        if (address.getState() != null)
            oldAddress.setState(address.getState());
        if (address.getAddressLine() != null)
            oldAddress.setAddressLine(address.getAddressLine());
        if (address.getZipCode() != 0)
            oldAddress.setZipCode(address.getZipCode());
        return addressRepo.save(oldAddress);
    }

    @Override
    public Address addShippingAddress(String userName, Address address) {
        Buyer old = buyerRepo.findBuyerByUsername(userName);
        old.setShippingAddress(address);
        buyerRepo.save(old);
        return address;
    }

    @Override
    public Address editShippingAddress(long address_id, Address address) {
        Address oldAddress = addressRepo.findById(address_id).orElse(null);
        if (oldAddress != null) {
            return getAddress(address, oldAddress);
        }
        return null;

    }

    @Override
    public CreditCard addCreditCard(String userName, CreditCard creditCard) {
        Buyer old = buyerRepo.findBuyerByUsername(userName);
        old.setCreditCard(creditCard);
        buyerRepo.save(old);
        return creditCard;
    }

    @Override
    public CreditCard editCreditCard(long creditCard_id, CreditCard creditCard) {
        CreditCard oldCC = creditCardRepo.findById(creditCard_id).orElse(null);
        if (oldCC != null) {
            if (creditCard.getCardNumber() != null)
                oldCC.setCardNumber(creditCard.getCardNumber());
            if (creditCard.getCvv() != null)
                oldCC.setCvv(creditCard.getCvv());
            if (creditCard.getExpDate() != null)
                oldCC.setExpDate(creditCard.getExpDate());
            creditCardRepo.save(oldCC);
        }
        return null;
    }

    @Override
    public Order getOrderByBuyerUserNameOrderId(long id, String userName) {
        Buyer buyer = buyerRepo.findBuyerByUsername(userName);
        return orderRepo.findById(buyerRepo.getOrderByBuyerUserNameAndOrderId(id, buyer.getBId())).orElse(null);
    }

    @Override
    public void save(Buyer buyer) {
        buyerRepo.save(buyer);
    }

    @Override
    public Address getShippingAddressBysId(long id) {
        return buyerRepo.findShippingAddress(id);

    }

    @Override
    public Order returnedOrder(String userName, long oId) {
        Buyer buyer = buyerRepo.findBuyerByUsername(userName);
        List<Long> lOID = buyer.getOrders().stream().map(Order::getId).collect(Collectors.toList());
        Order order = orderRepo.findOrderById(oId);
        if (lOID.contains(oId) && !Objects.equals(order.getOrderStatus(), OrderStatus.RETURNED.getOrderStatus())) {

            order.setOrderStatus(OrderStatus.RETURNED.getOrderStatus());
            buyer.setPoints((int) (buyer.getPoints() - order.getPrice()));
            orderRepo.save(order);
            buyerRepo.save(buyer);
            return order;
        }
        return null;
    }
}
