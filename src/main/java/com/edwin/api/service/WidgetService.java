package com.edwin.api.service;

import com.edwin.api.entity.Widget;
import com.edwin.api.exception.DataExistsException;
import com.edwin.api.exception.DataNotFoundException;
import com.edwin.api.exception.GeneralServiceException;
import com.edwin.api.repository.WidgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class WidgetService {
    private final WidgetRepository repository;

    @Autowired
    public WidgetService(WidgetRepository repository) {
        this.repository = repository;
    }

    public Widget findById(Long uuid){
        try {
            return repository.findById(uuid)
                    .orElseThrow(() -> new DataNotFoundException("El widget no existe"));
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    public Widget findByName(String name){
        try {
            return repository.findByName(name)
                    .orElseThrow(() -> new DataNotFoundException("El widget no existe"));
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    public List<Widget> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Widget create(Widget widget){
        if(widget == null) return null;
        try {
            Widget returnWidget = repository.findByName(widget.getName())
                    .orElse(null);
            if(returnWidget != null) throw new DataExistsException("El widget ya existe");
            return repository.save(widget);
        }catch (DataNotFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new GeneralServiceException(e.getMessage());
        }
    }

    @Transactional
    public Widget update(Widget widget){
        if(widget == null) return null;
        try {
            Widget response = repository.findByName(widget.getName())
                    .orElseThrow(() -> new DataNotFoundException("El widget no existe"));
            response.setName(widget.getName());
            response.setColor(widget.getColor());
            response.setMaxValue(widget.getMaxValue());
            response.setMinValue(widget.getMinValue());
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
            Widget response = repository.findById(uuid)
                    .orElse(null);
            if(response == null) throw new DataNotFoundException("El widget no existe");
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
            Widget response = repository.findByName(name)
                    .orElse(null);
            if(response == null) throw new DataNotFoundException("El widget no existe");
            repository.deleteByName(response.getName());
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