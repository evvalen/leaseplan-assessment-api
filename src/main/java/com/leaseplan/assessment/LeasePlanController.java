package com.leaseplan.assessment;

import com.leaseplan.assessment.pojo.order.Order;
import com.leaseplan.assessment.pojo.pets.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
// FIXME this controller is only used for the ATTD to work
public class LeasePlanController {

    // FIXME this map is only used for the ATTD to work
    private Map<String, Pet> pets = new HashMap<>();

    @PostMapping("/pet")
    public Pet create(@RequestBody Pet pet) {
        pets.put(pet.getId(), pet);

        return pet;
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<Pet> reach(@PathVariable("id") String id) {
        HttpStatus httpStatus = pets.containsKey(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        ResponseEntity responseEntity = new ResponseEntity(pets.get(id), httpStatus);

        return responseEntity;
    }

    @GetMapping("/pet/findByStatus")
    public List<Pet> find(@RequestParam("status") String status) {

        return new ArrayList<>(pets.values());
    }

    @DeleteMapping("/pet/{id}")
    public ResponseEntity<Pet> delete(@PathVariable("id") String id) {
        HttpStatus httpStatus = pets.containsKey(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        ResponseEntity responseEntity = new ResponseEntity(pets.get(id), httpStatus);

        pets.remove(id);

        return responseEntity;
    }

    @PostMapping("/store/order")
    public void storeOrder(@RequestBody Order order) {
        // FiXME implementation faked for ATDD
    }
}
