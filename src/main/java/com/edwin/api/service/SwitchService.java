package com.edwin.api.service;

import com.edwin.api.entity.Switch;
import com.edwin.api.exception.DataExistsException;
import com.edwin.api.exception.DataNotFoundException;
import com.edwin.api.exception.GeneralServiceException;
import com.edwin.api.repository.SwitchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class SwitchService {
    private final SwitchRepository repository;

    @Autowired
    public SwitchService(SwitchRepository repository) {
        this.repository = repository;
    }

    public Switch findById(Long uuid){
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

    public Switch findByName(String name){
        try {
            return repository.findByName(name)
                    .orElseThrow(() -> new DataNotFoundException("Recurso no encontrado"));
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    public List<Switch> findAll(){
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
    public Switch save(Switch sw){
        try {
            if(sw == null) return null;
            Switch response = repository.findByName(sw.getName())
                    .orElse(null);
            if(response != null) throw new DataExistsException("El widget ya existe");
            return repository.save(sw);
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    @Transactional
    public Switch update(Switch sw){
        if(sw == null) return null;
        try {
            Switch response = repository.findByName(sw.getName())
                    .orElseThrow(() -> new DataNotFoundException("El widget no existe"));
            response.setValue(sw.getValue());
            response.setName(sw.getName());
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
    public String deleteById(Long uuid){
        try {
            Switch response = repository.findById(uuid)
                            .orElse(null);
            if (response == null) throw new DataNotFoundException("El widget no existe");
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
    public String deleteByNane(String name){
        try {
            Switch response = repository.findByName(name)
                .orElse(null);
            if (response == null) throw new DataNotFoundException("El widget no existe");
            repository.deleteByName(name);
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