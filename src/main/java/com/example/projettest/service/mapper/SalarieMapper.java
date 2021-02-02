package com.example.projettest.service.mapper;

import com.example.projettest.model.entity.Salarie;
import com.example.projettest.repository.SalarieRepository;
import com.example.projettest.service.dto.SalarieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class SalarieMapper {

    private  SalarieRepository salarieRepositor;

    @Autowired
    public SalarieMapper(SalarieRepository salarieRepository) {
        this.salarieRepositor=salarieRepository;
    }



    public static List<SalarieDto> mapEntitiesIntoDTOs(List<Salarie> entities) {
        return entities.stream().map(SalarieMapper::mapEntityIntoDto).collect(toList());
    }

    public static List<Salarie> mapDtosIntoEntities(List<SalarieDto> dtos) {
        return dtos.stream().map(SalarieMapper::mapDtoIntoEntity).collect(toList());
    }
    public static Salarie mapDtoIntoEntity(SalarieDto dto) {
        Salarie entity = new Salarie();
        if(dto==null)
            return null;
        entity.setId(dto.getId());
        entity.setCodeSalarie(dto.getCodeSalarie());
        entity.setPrenom(dto.getPrenom());
        entity.setAdresse(dto.getAdresse());
        entity.setFonction(dto.getFonction());
        entity.setAnneExp(dto.getAnneExp());
        entity.setDate(dto.getDate());
        entity.setSalaire(dto.getSalaire());
        return entity;

    }


    public static SalarieDto mapEntityIntoDto(Salarie entity) {
        SalarieDto dto = new SalarieDto();
        if(entity==null)
            return null;
        dto.setId(entity.getId());
        dto.setCodeSalarie(entity.getCodeSalarie());
        dto.setAdresse(entity.getAdresse());
        dto.setFonction(entity.getFonction());
        dto.setPrenom(entity.getPrenom());
        dto.setSalaire(entity.getSalaire());
        dto.setDate(entity.getDate());
        dto.setAnneExp(entity.getAnneExp());

        return dto;

    }
}
