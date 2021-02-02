package com.example.projettest.service.validator;

import antlr.StringUtils;
import com.example.projettest.model.entity.Salarie;
import com.example.projettest.repository.SalarieRepository;
import com.example.projettest.service.dto.SalarieDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Component
public class SalarieValidator {


    private static final String START = "[Start]";

    private static final String END = "[End]";
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SalarieRepository  SalarieRepo;


    public String SalarieValidation(SalarieDto salarieDto){
        LOGGER.debug(START);

        String message="";
        if(salarieDto==null){
            message+="le salarie envoye  est null" +"\n";
        }else{
            message+=attributesValidation("prenom", salarieDto.getPrenom());
            message+=attributesValidation("date", salarieDto.getDate());
            message+=attributesValidation("fonction", salarieDto.getFonction());
            message+=attributesValidation("salaire", salarieDto.getSalaire());
            message+=attributesValidation("adresse", salarieDto.getAdresse());
            message+=attributesValidation("anneexp", salarieDto.getAnneExp());
        }

        LOGGER.debug(END);
        return message;
    }

    private String attributesValidation(String attributeName, String value){
        LOGGER.debug(START);

        String message="";

        if(value.isEmpty() ){
            message="Le champs "+attributeName+" doit contenir une valeur valide"+"\n";
        }
        LOGGER.debug(END);

        return message;

    }

    private String attributesValidation(String attributeName, Long value){
        LOGGER.debug(START);

        String message="";

        if(value==null ){
            message="Le champs "+attributeName+" doit contenir une valeur valide"+"\n";
        }
        LOGGER.debug(END);

        return message;

    }

    private String codeUnicity (String code,Long id){
        LOGGER.debug(START);

        String message="";
        if(!isCodeUnique(code,id))
            message="Code existant"+"\n";
        LOGGER.debug(END);

        return message;
    }


    private Boolean isCodeUnique(String code,Long id){
        LOGGER.debug(START);

        Boolean isCodeUnique=true;
        if(code!=null){
            Salarie SalarieFound = this.SalarieRepo.findByCodeSalarie(code);
            if (SalarieFound!=null && SalarieFound.getId()!=id)
                isCodeUnique=false;}
        LOGGER.debug(END);
        return isCodeUnique;
    }




}
