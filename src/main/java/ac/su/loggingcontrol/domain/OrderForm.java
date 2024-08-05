package ac.su.loggingcontrol.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderForm {
    private int id;
    private int productId;
    private int cartId;
    private int userId;
    private int quantity;
}
