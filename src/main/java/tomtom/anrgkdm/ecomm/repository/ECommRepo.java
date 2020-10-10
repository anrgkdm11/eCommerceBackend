package tomtom.anrgkdm.ecomm.repository;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import tomtom.anrgkdm.ecomm.model.Product;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ECommRepo {

    /*static List<Product> inventry = new ArrayList<>();

    @PostConstruct
    private void initRepo(){
        for(int i = 1 ; i <= 10 ; i++){
         inventry.add(Product.builder()
                 .id(i)
                 .name("Product_"+i)
                 .price(new Random().nextInt(9000))
                 .quantity(new Random().nextInt(200))
                 .build());
        }
    }

    public List<Product> getAllProductsFromInventory(){
        return inventry.stream().map( p -> {return Product.builder()
                .name(p.getName()).price(p.getPrice()).build();}).collect(Collectors.toList());
    }

    public String addProductToInventory(Product product) {
        product.setId(inventry.size()+1);
        inventry.add(product);
        return product.getName();
    }*/
}
