package tomtom.anrgkdm.ecomm.service;

import tomtom.anrgkdm.ecomm.dto.AddToCartRequest;
import tomtom.anrgkdm.ecomm.dto.AddToCartResponse;
import tomtom.anrgkdm.ecomm.dto.PlaceOrderResponse;
import tomtom.anrgkdm.ecomm.exception.ECommerceException;
import tomtom.anrgkdm.ecomm.model.User;

public interface CartService {


    AddToCartResponse addToCart(AddToCartRequest addToCartRequest) throws ECommerceException;

    User createUser(User name);

    PlaceOrderResponse placeOrder(Integer userId);
}
