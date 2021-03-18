package tomtom.anrgkdm.ecomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tomtom.anrgkdm.ecomm.dto.AddToCartRequest;
import tomtom.anrgkdm.ecomm.dto.AddToCartResponse;
import tomtom.anrgkdm.ecomm.dto.PlaceOrderResponse;
import tomtom.anrgkdm.ecomm.exception.ECommerceException;
import tomtom.anrgkdm.ecomm.model.*;
import tomtom.anrgkdm.ecomm.repository.CartRepo;
import tomtom.anrgkdm.ecomm.repository.OrderRepo;
import tomtom.anrgkdm.ecomm.repository.ProductRepository;
import tomtom.anrgkdm.ecomm.repository.UserRepo;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public AddToCartResponse addToCart(AddToCartRequest addToCartRequest) throws ECommerceException {

        User user = userRepo.findById(addToCartRequest.getUserId()).orElseThrow(() -> new ECommerceException("No Such User Found In Database...!", HttpStatus.BAD_REQUEST));

        Cart cart = cartRepo.findById(user.getCart().getCartId()).get();

        List<Item> items = cart.getItems();

        Optional<Product> product = productRepository.findById(addToCartRequest.getProductId());

        Item itemexists = items.stream().filter( itm -> itm.getProduct().getProductId().equals(addToCartRequest.getProductId())).findFirst().orElse(null);

        if(null != itemexists ){
            itemexists.setQuantity(itemexists.getQuantity() + addToCartRequest.getQuantity());
            itemexists.setAmount(itemexists.getQuantity() * itemexists.getProduct().getPrice());
        } else{
            items.add(Item.builder()
                    .product(product.get())
                    .quantity(addToCartRequest.getQuantity())
                    .amount(product.get().getPrice() * addToCartRequest.getQuantity())
                    .build());
        }

        cart.setItems(items);
        cartRepo.save(cart);
        AddToCartResponse addToCartResponse = new AddToCartResponse();
        addToCartResponse.setItems(new ArrayList<>(items));
        return addToCartResponse;
    }

    @Override
    public User createUser(User user) {
        Cart cart = new Cart();
        user.setCart(cart);
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public PlaceOrderResponse placeOrder(Integer userId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new ECommerceException("No Such User Found In Database!", HttpStatus.BAD_REQUEST));

        Cart cart = cartRepo.findById(user.getCart().getCartId()).get();
        if(cart.getItems().isEmpty()){
            throw new ECommerceException("No Items In Cart To Be Ordered...!", HttpStatus.BAD_REQUEST);
        }

        // Check for Payment

        Orders orders = new Orders();
        orders.setAmount(cart.getItems().stream().mapToDouble(item -> item.getAmount()).sum());
        orders.setItems(new ArrayList<>(cart.getItems()));
        orders.setDateCreated(LocalDateTime.now());
        orders.setStatus("OrderPlaced");
        orders.setUser(user);

        orders = orderRepo.save(orders);

        return PlaceOrderResponse.builder()
                .items(orders.getItems())
                .totalAmount(orders.getAmount())
                .build();
    }

}
