package com.edwin.api.controller;

import com.edwin.api.config.CustomResponse;
import com.edwin.api.converter.SwitchConverter;
import com.edwin.api.dto.SwitchDto;
import com.edwin.api.service.SwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = {"*"}, methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE
})
@RestController
@RequestMapping(value = "/switch")
public class SwitchController {
    private final SwitchService service;
    private final SwitchConverter converter = new SwitchConverter();

    @Autowired
    public SwitchController(SwitchService service) {
        this.service = service;
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<CustomResponse<SwitchDto>> findById(@PathVariable("id") Long uuid){
        CustomResponse<SwitchDto> customResponse = new CustomResponse<>(converter.toDto(service.findById(uuid)));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("byName/{name}")
    public ResponseEntity<CustomResponse<SwitchDto>> findByName(@PathVariable("name") String name){
        CustomResponse<SwitchDto> customResponse = new CustomResponse<>(converter.toDto(service.findByName(name)));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<SwitchDto>>> findAll(){
        CustomResponse<List<SwitchDto>> customResponse = new CustomResponse<>(converter.toDtoList(service.findAll()));
        return ResponseEntity.ok(customResponse);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<SwitchDto>> save(@RequestBody SwitchDto sw){
        CustomResponse<SwitchDto> customResponse = new CustomResponse<>(converter.toDto(service.save(converter.toEntity(sw))));
        return ResponseEntity.ok(customResponse);
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<SwitchDto>> update(@RequestBody SwitchDto sw){
        CustomResponse<SwitchDto> customResponse = new CustomResponse<>(converter.toDto(service.update(converter.toEntity(sw))));
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping("byId/{id}")
    public ResponseEntity<CustomResponse<String>> deteleById(@PathVariable("id") Long uuid){
        CustomResponse<String> customResponse = new CustomResponse<>(service.deleteById(uuid));
        return ResponseEntity.ok(customResponse);
    }

    @DeleteMapping("byName/{name}")
    public ResponseEntity<CustomResponse<String>> deteleByName(@PathVariable("name") String name){
        CustomResponse<String> customResponse = new CustomResponse<>(service.deleteByNane(name));
        return ResponseEntity.ok(customResponse);
    }
}