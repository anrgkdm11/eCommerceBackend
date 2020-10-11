package tomtom.anrgkdm.ecomm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tomtom.anrgkdm.ecomm.model.Item;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PlaceOrderResponse {

    private List<Item> items;
    private Double totalAmount;

}
