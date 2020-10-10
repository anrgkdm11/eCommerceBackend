package tomtom.anrgkdm.ecomm.service;

import tomtom.anrgkdm.ecomm.dto.AddToCartRequest;
import tomtom.anrgkdm.ecomm.dto.AddToCartResponse;
import tomtom.anrgkdm.ecomm.model.User;

public interface CartService {


    AddToCartResponse addToCart(AddToCartRequest addToCartRequest);

    User createUser(User name);
}
