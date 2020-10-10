package tomtom.anrgkdm.ecomm.service;

import org.springframework.stereotype.Service;
import tomtom.anrgkdm.ecomm.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product addProductToInventory(Product product);
}
