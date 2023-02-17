package com.example.eventos.controller;

import com.example.eventos.bl.SalonBl;
import com.example.eventos.dto.SalonDto;
import com.example.eventos.globaldto.ResultadoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salones")
public class SalonController {

    @Autowired
    private SalonBl salonBl;

    @GetMapping("/{id}")
    public ResultadoDto<SalonDto> findById(@PathVariable long id) {
        return this.salonBl.findById(id);
    }

    @GetMapping
    public ResultadoDto<List<SalonDto>> findAll() {
        return this.salonBl.findAll();
    }

    @PostMapping
    public ResultadoDto<SalonDto> save(@RequestBody SalonDto salonDto) {
        return this.salonBl.save(salonDto);
    }

    @PutMapping
    public ResultadoDto<SalonDto> update(@RequestBody SalonDto salonDto) {
        return this.salonBl.update(salonDto);
    }
}
