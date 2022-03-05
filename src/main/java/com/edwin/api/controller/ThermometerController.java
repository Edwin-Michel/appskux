package com.edwin.api.controller;

import com.edwin.api.config.CustomResponse;
import com.edwin.api.converter.ThermometerConverter;
import com.edwin.api.entity.Thermometer;
import com.edwin.api.service.ThermometerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"*"}, methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
})
@RestController
@RequestMapping(value = "/th")
public class ThermometerController {
    private final ThermometerService service;
    private final ThermometerConverter converter = new ThermometerConverter();

    @Autowired
    public ThermometerController(ThermometerService service) {
        this.service = service;
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<CustomResponse<Thermometer>> findById(@PathVariable("id") Long id){
        CustomResponse<Thermometer> customResponse = new CustomResponse<>(service.findById(id));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("byName/{name}")
    public ResponseEntity<CustomResponse<List<Thermometer>>> findByName(@PathVariable("name") String name){
        CustomResponse<List<Thermometer>> customResponse = new CustomResponse<>(service.findByName(name));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("lastValue/{name}")
    public ResponseEntity<CustomResponse<Thermometer>> findLastValue(@PathVariable("name") String name){
        CustomResponse<Thermometer> customResponse = new CustomResponse<>(service.findLastValue(name));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Thermometer>>> findAll(){
        CustomResponse<List<Thermometer>> customResponse = new CustomResponse<>(service.findAll());
        return ResponseEntity.ok(customResponse);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Thermometer>> save(@RequestBody Thermometer th){
        CustomResponse<Thermometer> customResponse = new CustomResponse<>(service.save(th));
        return ResponseEntity.ok(customResponse);
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Thermometer>> update(@RequestBody Thermometer th){
        CustomResponse<Thermometer> customResponse = new CustomResponse<>(service.update(th));
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping("byId/{id}")
    public ResponseEntity<CustomResponse<String>> deleteById(@PathVariable("id") Long id){
        CustomResponse<String> customResponse = new CustomResponse<>(service.deleteById(id));
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping("byName/{name}")
    public ResponseEntity<CustomResponse<String>> deleteByName(@PathVariable("name") String name){
        CustomResponse<String> customResponse = new CustomResponse<>(service.deleteByName(name));
        return ResponseEntity.ok(customResponse);
    }
}