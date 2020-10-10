package tomtom.anrgkdm.ecomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomtom.anrgkdm.ecomm.dto.AddToCartRequest;
import tomtom.anrgkdm.ecomm.dto.AddToCartResponse;
import tomtom.anrgkdm.ecomm.model.Cart;
import tomtom.anrgkdm.ecomm.model.Item;
import tomtom.anrgkdm.ecomm.model.Product;
import tomtom.anrgkdm.ecomm.model.User;
import tomtom.anrgkdm.ecomm.repository.CartRepo;
import tomtom.anrgkdm.ecomm.repository.ProductRepository;
import tomtom.anrgkdm.ecomm.repository.UserRepo;

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
    private ProductRepository productRepository;


    @Override
    public AddToCartResponse addToCart(AddToCartRequest addToCartRequest){

        Optional<User> user = userRepo.findById(addToCartRequest.getUserId());

        Cart cart = cartRepo.findById(user.get().getCart().getCartId()).get();

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

}
