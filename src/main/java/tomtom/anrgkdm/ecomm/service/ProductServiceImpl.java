package tomtom.anrgkdm.ecomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tomtom.anrgkdm.ecomm.model.Product;
import tomtom.anrgkdm.ecomm.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProductToInventory(Product product) {
        if(product.getPrice()> 5000){
            throw new RuntimeException("Price is too high");
        }
        return productRepository.save(product);
    }


}
