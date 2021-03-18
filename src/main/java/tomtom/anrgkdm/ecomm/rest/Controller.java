package tomtom.anrgkdm.ecomm.rest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tomtom.anrgkdm.ecomm.dto.AddToCartRequest;
import tomtom.anrgkdm.ecomm.dto.AddToCartResponse;
import tomtom.anrgkdm.ecomm.dto.PlaceOrderResponse;
import tomtom.anrgkdm.ecomm.exception.ECommerceException;
import tomtom.anrgkdm.ecomm.model.Product;
import tomtom.anrgkdm.ecomm.model.User;
import tomtom.anrgkdm.ecomm.service.CartService;
import tomtom.anrgkdm.ecomm.service.ProductService;

import java.util.List;


@RestController
public class Controller {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    private RestTemplate restTemplate;

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping(path = "/seller/product", consumes = "application/json", produces = "application/json")
    @CircuitBreaker(name = "defaultCircuitBreaker",fallbackMethod = "addProductToInventoryFallback")
    @ResponseBody
    public String addProductToInventory(@RequestBody Product product){
        System.out.println("In Actual Method");
        //ResponseEntity<Boolean> response =  restTemplate.exchange(new RequestEntity<>(product, HttpMethod.POST, URI.create("http://localhost:9090/product"), Boolean.class));
        return productService.addProductToInventory(product).getName();
    }

    public String addProductToInventoryFallback(Product product, Exception th){
        System.out.println(th);
        System.out.println("In Fallback Method");
        return "Fallback";

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

    /*
    static {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindow(10, 4, CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                .enableAutomaticTransitionFromOpenToHalfOpen()
                .failureRateThreshold(40)
                .permittedNumberOfCallsInHalfOpenState(2)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.custom().withCircuitBreakerConfig(config).build();

        CircuitBreaker circuitBreaker = registry.circuitBreaker("productInventoryBreaker");

        Supplier<String> supplier = CircuitBreaker.decorateSupplier(circuitBreaker, );

        Try.ofSupplier(supplier).recover(this.addProductToInventoryFallback());


    }*/
}
