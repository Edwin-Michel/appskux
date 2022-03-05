package com.edwin.api.service;

import com.edwin.api.entity.Thermometer;
import com.edwin.api.exception.DataNotFoundException;
import com.edwin.api.exception.GeneralServiceException;
import com.edwin.api.repository.ThermometerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ThermometerService {
    private final ThermometerRepository repository;

    @Autowired
    public ThermometerService(ThermometerRepository repository) {
        this.repository = repository;
    }

    public Thermometer findById(Long uuid){
        try {
            return repository.findById(uuid)
                    .orElseThrow(() -> new DataNotFoundException("Recurso no encontrado"));
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    public List<Thermometer> findByName(String name){
        return repository.findByName(name);
    }

    public Thermometer findLastValue(String name){
        return repository.findByIdLast(name)
                .orElseThrow(() -> new DataNotFoundException("Recurso no encontrado"));
    }

    public List<Thermometer> findAll(){
        try {
            return repository.findAll();
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    @Transactional
    public Thermometer save(Thermometer th){
        if(th == null) return null;
        try {
            return repository.save(th);
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    @Transactional
    public Thermometer update(Thermometer th){
        try {
            Thermometer response = repository.findById(th.getId())
                    .orElseThrow(() -> new DataNotFoundException("Recurso no encontrado"));
            response.setName(th.getName());
            response.setDate(LocalDateTime.now().toString());
            response.setValue(th.getValue());
            return repository.save(response);
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    @Transactional
    public String deleteById(Long id){
        try {
            Thermometer response = repository.findById(id)
                    .orElse(null);
            if(response == null) throw new DataNotFoundException("Recurso no encontrado");
            repository.deleteById(response.getId());
            return "Delete OK";
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    @Transactional
    public String deleteByName(String name){
        try {
            List<Thermometer> response = repository.findByName(name);
            if(response == null) throw new DataNotFoundException("Recurso no encontrado");
            repository.deleteByName(response.get(0).getName());
            return "Delete OK";
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }
}
