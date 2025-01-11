package lk.ijse.customerjsp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
   private int id;
   private String name;
   private String address;
   private String email;
}
