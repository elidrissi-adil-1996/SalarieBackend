package com.example.projettest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import com.example.projettest.controller.response.Response;
import com.example.projettest.model.entity.Salarie;
import com.example.projettest.repository.SalarieRepository;
import com.example.projettest.service.SalarieService;
import com.example.projettest.service.dto.SalarieDto;
import com.example.projettest.service.mapper.SalarieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

public class SalarieTest extends TestNG {

        @Autowired
        SalarieRepository  salarieRepository;



        @Autowired
        SalarieService salarieService;

        @Test
        public void addSalarieOKTest() throws Exception {
            salarieRepository.save(new Salarie("code1", "prenom", "fonction", 2L, "paris", 1800L,14447878L));
            int sizeBeforAddSalarie=salarieRepository.findAll().size();
            Response returnedResponse= salarieService.addSalarie(new SalarieDto(1L,"code1", "prenom", "fonction", 2L, "paris", 1800L,14447878L));
            SalarieDto createdsalarie=(SalarieDto) returnedResponse.getData().get("salarie");
            Salarie sl= SalarieMapper.mapDtoIntoEntity(createdsalarie);
            System.out.println("--Salarie "+sl);
            Assert.assertEquals(sl, salarieRepository.findById(1L));
            Assert.assertEquals(salarieRepository.findAll().size(), sizeBeforAddSalarie);
            Assert.assertEquals(returnedResponse.getMessage(), "salarie été sauvegardée avec succès");
        }


        @Test
        public void editSalarieTest() throws Exception {
            addSalarieOKTest();

            Response returnedResponse= salarieService.editSalarie(new SalarieDto(1L,null, "prenom2", "fonction2", 2L, "paris", 1800L,14447878L),1L) ;
            SalarieDto updatedSalarieDto=(SalarieDto) returnedResponse.getData().get("salarie");
            Assert.assertEquals(SalarieMapper.mapDtoIntoEntity(updatedSalarieDto),salarieRepository.findById(updatedSalarieDto.getId()));
            Assert.assertEquals(returnedResponse.getMessage(),"Le Salarie  a été sauvegardée avec succès");
        }


        @Test
        public void getAssembleeGeneralesTest() throws Exception {
            addSalarieOKTest();
            Map<String,String> allRequestParams=new HashMap<>();
            allRequestParams.put("page", "0");
            allRequestParams.put("pageSize", "10");
            int sizeExpected = 1;
            Assert.assertEquals(salarieService.getsalaries(allRequestParams).size(),sizeExpected);
            Assert.assertEquals(salarieService.getsalaries(new
                    HashMap<>()).size(), salarieRepository.findAll().size());

        }

        @Test
        public void getSalarieByIdTest() throws Exception {
            addSalarieOKTest();
            Long expectedId = 1L;
            SalarieDto salarieFound=salarieService.getSalarieById(expectedId);
            Assert.assertEquals(salarieFound.getId(), expectedId);
            Assert.assertEquals(SalarieMapper.mapDtoIntoEntity(salarieFound),salarieRepository.findById(expectedId));
        }


    }


