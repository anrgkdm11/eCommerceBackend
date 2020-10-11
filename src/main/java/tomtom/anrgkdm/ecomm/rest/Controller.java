package tomtom.anrgkdm.ecomm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomtom.anrgkdm.ecomm.dto.AddToCartRequest;
import tomtom.anrgkdm.ecomm.dto.AddToCartResponse;
import tomtom.anrgkdm.ecomm.dto.PlaceOrderResponse;
import tomtom.anrgkdm.ecomm.exception.ECommerceException;
import tomtom.anrgkdm.ecomm.model.Product;
import tomtom.anrgkdm.ecomm.model.User;
import tomtom.anrgkdm.ecomm.repository.ECommRepo;
import tomtom.anrgkdm.ecomm.service.CartService;
import tomtom.anrgkdm.ecomm.service.ProductService;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping(path = "/seller/product", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addProductToInventory(@RequestBody Product product){
        return new ResponseEntity(productService.addProductToInventory(product), HttpStatus.CREATED);
    }

    @PostMapping(path = "/cart/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AddToCartResponse> addProductToCart(@RequestBody AddToCartRequest addToCartRequest) throws ECommerceException {
        return new ResponseEntity(cartService.addToCart(addToCartRequest), HttpStatus.CREATED);
    }

    @PostMapping(path = "/user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createuser(@RequestBody User user){
        return new ResponseEntity(cartService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping(path = "/user/{userId}/checkout", produces = "application/json")
    public ResponseEntity<PlaceOrderResponse> placeOrder(@PathVariable Integer userId){
        return new ResponseEntity(cartService.placeOrder(userId), HttpStatus.CREATED);
    }
}
