package tomtom.anrgkdm.ecomm.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer userId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId", nullable = false)
    private Cart cart;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Orders> orders;

}
