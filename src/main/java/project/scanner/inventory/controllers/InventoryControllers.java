package project.scanner.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.scanner.inventory.database.ItemRepository;
import project.scanner.inventory.entities.Item;

import java.util.List;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class InventoryControllers {

    @Autowired
    private ItemRepository itemCollection;

    @GetMapping("/inventory/read")
    public List<Item> getAllItem() {
       List<Item> foundItem = itemCollection.findAll();
        if (foundItem != null) {
            return itemCollection.findAll();
        } else {
            return null;
        }
    }

    @GetMapping("/inventory/read/{barcode}")
    public Item getItemDetails(@PathVariable String barcode) {
       Item foundItem = itemCollection.findByBarcode(barcode);
        if (foundItem != null) {
            return itemCollection.findByBarcode(barcode);
        } else {
            return null;
        }
    }

    @PostMapping(value = "/inventory/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addNewItem(@RequestBody @Valid Item itemToAdd) {
        Item foundItem = itemCollection.findByBarcode(itemToAdd.getBarcode());
        if (foundItem != null) {
            return new ResponseEntity<>("Item with the barcode " + itemToAdd.getBarcode() + " already exists against " +
                "an item named " + "'" + foundItem.getBarcode() + "'. Please update the new item or remove it.", HttpStatus.BAD_REQUEST);
        } else {
            itemCollection.save(itemToAdd);
            return new ResponseEntity<>("Item with the barcode " + itemToAdd.getBarcode() + " added", HttpStatus.OK);
        }
    }

    @GetMapping("/inventory/delete/{barcode}")
    public ResponseEntity<String> deleteItem(@PathVariable String barcode) {
        Item foundItem = itemCollection.findByBarcode(barcode);
        if (foundItem != null) {
            itemCollection.delete(foundItem);
            return new ResponseEntity<>("Item with the barcode " + barcode + " deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find item with barcode " + barcode + " to delete" , HttpStatus.NOT_FOUND);
        }
    }

    //ToDo: preety sure this update isn't right....
//    @PutMapping(value = "/inventory/update/{barcode}", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<String> updateExistingItem(@PathVariable String barcode, @RequestBody @Valid Item itemToUpdate) {
//        Item foundItem = itemCollection.findByBarcode(barcode);
//        if (foundItem != null) {
//            foundItem = itemToUpdate;
//            itemCollection.save(foundItem);
//            return new ResponseEntity<>("Item with the barcode " + itemToUpdate.getBarcode() + " updated", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Item with the barcode " + itemToUpdate.getBarcode() + " already exists against " +
//                "an item named " + "'" + foundItem.getBarcode() + "'. Please update the new item or remove it.", HttpStatus.BAD_REQUEST);
//        }
//    }
}
