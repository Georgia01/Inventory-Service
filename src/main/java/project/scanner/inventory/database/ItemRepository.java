package project.scanner.inventory.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import project.scanner.inventory.entities.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

    Item findByBarcode(@Param("barcode") String barcode);

}
