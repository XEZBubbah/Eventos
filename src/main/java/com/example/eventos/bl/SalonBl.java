package com.example.eventos.bl;

import com.example.eventos.dal.SalonDal;
import com.example.eventos.dto.SalonDto;
import com.example.eventos.entity.SalonEntity;
import com.example.eventos.globaldto.ResultadoDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonBl {

    @Autowired
    private SalonDal salonDal;

    @Autowired
    private ModelMapper modelMapper;

    public ResultadoDto<SalonDto> findById(long id) {
        try {
            var salonEncontrado = this.salonDal.findById(id);
            var salonEncontradoDto = modelMapper.map(salonEncontrado, SalonDto.class);
            return ResultadoDto.<SalonDto>ok(salonEncontradoDto);
        } catch (Exception e) {
            return ResultadoDto.fail(e);
        }
    }

    public ResultadoDto<List<SalonDto>> findAll() {
        try {
            var salonEncontrado = this.salonDal.findAll();
            List<SalonDto> salonEncontradoDto = modelMapper.map(salonEncontrado, new TypeToken<List<SalonDto>>(){}.getType());
            return ResultadoDto.<List<SalonDto>>ok(salonEncontradoDto);
        } catch (Exception e) {
            return ResultadoDto.fail(e);
        }
    }

    public ResultadoDto<SalonDto> save(SalonDto salonDto) {
        try {
            var salonEntity = modelMapper.map(salonDto, SalonEntity.class);
            salonEntity.setId(0L);
            var salonGuardado = this.salonDal.save(salonEntity);
            return ResultadoDto.<SalonDto>ok(modelMapper.map(salonGuardado, SalonDto.class));
        } catch (Exception e) {
            return ResultadoDto.fail(e);
        }
    }

    public ResultadoDto<SalonDto> update(SalonDto salonDto) {
        try {
            var salonEntity = modelMapper.map(salonDto, SalonEntity.class);
            var salonGuardado = this.salonDal.update(salonEntity);
            return ResultadoDto.<SalonDto>ok(modelMapper.map(salonGuardado, SalonDto.class));
        } catch (Exception e) {
            return ResultadoDto.fail(e);
        }
    }

    public ResultadoDto<Boolean> delete(long id) {
        try {
            var confirmacion = this.salonDal.delete(id);
            return ResultadoDto.<Boolean>ok(confirmacion);
        } catch (Exception e) {
            return ResultadoDto.fail(e);
        }
    }
}
