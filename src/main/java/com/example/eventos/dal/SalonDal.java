package com.example.eventos.dal;

import com.example.eventos.entity.SalonEntity;
import com.example.eventos.repository.ISalonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class SalonDal {

    @Autowired
    private ISalonRepository salonRepository;

    /*public SalonDal(ISalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }*/

    public SalonEntity findById(long id) {
        return this.salonRepository.findById(id).get();
    }

    public List<SalonEntity> findAll() {
        return this.salonRepository.findAll();
    }

    public SalonEntity save(SalonEntity salon) {
        return this.salonRepository.save(salon);
    }

    public SalonEntity update(SalonEntity salon) throws Exception {

        if(!this.salonRepository.existsById(salon.getId())) {
            throw new Exception("No se encuentra el salon");
        }

        return this.salonRepository.findById(salon.getId())
                .map(x -> {
                    x.setNombre(salon.getNombre());
                    x.setCapacidad(salon.getCapacidad());
                    x.setDireccion(salon.getDireccion());
                    return this.salonRepository.save(x);
                }).get();
    }

    public boolean delete(long id) throws Exception {

        if(!this.salonRepository.existsById(id)) {
            throw new Exception("No se encuentra el salon con este id");
        }
        this.salonRepository.deleteById(id);
        return true;
    }
}
