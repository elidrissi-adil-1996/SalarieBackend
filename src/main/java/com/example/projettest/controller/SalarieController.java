package com.example.projettest.controller;

import com.example.projettest.controller.response.Response;
import com.example.projettest.model.entity.Salarie;
import com.example.projettest.service.SalarieService;
import com.example.projettest.service.dto.SalarieDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class SalarieController {

    @Autowired
    SalarieService salarieService;



    @RequestMapping(value = "/addsalarie", method = RequestMethod.POST)
    public Response addSalarie(@RequestBody SalarieDto salarieDto) throws Exception{

        Response response =salarieService.addSalarie(salarieDto);

        return response;

    }


    @RequestMapping(value = "/edit/{idSalarie}", produces = "application/json;charset=UTF-8", method = RequestMethod.PUT)
    public Response editSalarie(@RequestBody SalarieDto salarieDto,@PathVariable Long idSalarie) throws Exception{

        Response response = salarieService.editSalarie(salarieDto,idSalarie);

        return response;
    }
    @RequestMapping(value = "/delete/{idSalarie}", produces = "application/json;charset=UTF-8", method = RequestMethod.DELETE)
    public Response deletSalarie(@PathVariable Long idSalarie) throws Exception{

        salarieService.deleteSalarie(idSalarie);
        Response res=new Response();
        SalarieDto salarie=this.salarieService.getSalarieById(idSalarie);

        res.setMessage("salarier"+salarie.getPrenom()+"supprimer");
        return res;
    }


    @RequestMapping(value = "", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public List<SalarieDto> getSalarie(@RequestParam Map<String,String> allRequestParams) {


        List<SalarieDto> salariesDto=salarieService.getsalaries(allRequestParams);

        return salariesDto;
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public SalarieDto getSalarieById(@PathVariable Long id) {


        SalarieDto SalarieDto=salarieService.getSalarieById(id);


        return SalarieDto;

    }


    @RequestMapping(value = "/salarieSize",method = RequestMethod.GET)
    public Integer getSalarieSize() {


        Integer size=salarieService.salarieSize();


        return size;

    }



}