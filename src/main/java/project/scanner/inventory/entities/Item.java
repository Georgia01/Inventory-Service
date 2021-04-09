package project.scanner.inventory.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

@Getter @Setter
public class Item {

    @Id private String id;

    @NotBlank(message = "Barcode is mandatory")
    private String barcode;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private int quantity;

    private double buyPrice;
    private double sellPrice;

}
