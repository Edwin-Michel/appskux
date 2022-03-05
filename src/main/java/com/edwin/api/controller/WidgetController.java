package com.edwin.api.controller;

import com.edwin.api.config.CustomResponse;
import com.edwin.api.entity.Widget;
import com.edwin.api.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"*"}, methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
})
@RequestMapping("/widgets")
@RestController
public class WidgetController {
    private final WidgetService service;

    @Autowired
    public WidgetController(WidgetService service) {
        this.service = service;
    }

    @GetMapping(value = "byId/{id}")
    public ResponseEntity<CustomResponse<Widget>> findById(@PathVariable(value = "id") Long uuid){
        CustomResponse<Widget> customResponse = new CustomResponse<>(service.findById(uuid));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping(value = "byName/{name}")
    public ResponseEntity<CustomResponse<Widget>> findByName(@PathVariable(value = "name") String name){
        CustomResponse<Widget> customResponse = new CustomResponse<>(service.findByName(name));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping(value = "/")
    public ResponseEntity<CustomResponse<List<Widget>>> findAll(){
        CustomResponse<List<Widget>> customResponse = new CustomResponse<>(service.findAll());
        return ResponseEntity.ok(customResponse);
    }

    @PostMapping(value = "/")
    public ResponseEntity<CustomResponse<Widget>> save(@RequestBody Widget widget){
        CustomResponse<Widget> customResponse = new CustomResponse<>(service.create(widget));
        return ResponseEntity.ok(customResponse);
    }

    @PutMapping(value = "/")
    public ResponseEntity<CustomResponse<Widget>> update(@RequestBody Widget widget){
        CustomResponse<Widget> customResponse = new CustomResponse<>(service.update(widget));
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping(value = "byId/{id}")
    public ResponseEntity<CustomResponse<String>> deleteById(@PathVariable("id") Long id){
        CustomResponse<String> customResponse = new CustomResponse<>(service.deleteById(id));
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping(value = "byName/{name}")
    public ResponseEntity<CustomResponse<String>> deleteByName(@PathVariable("name") String name){
        CustomResponse<String> customResponse = new CustomResponse<>(service.deleteByName(name));
        return ResponseEntity.ok(customResponse);
    }
}