package com.example.projettest.service;

import com.example.projettest.controller.response.Response;
import com.example.projettest.service.dto.SalarieDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommonService {
    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
    public Response responseApiObjectSalarie(SalarieDto salarieRetour, String msg, boolean status) {
        Response response = new Response();
        response.setMessage(msg);
        response.setStatus(status);
        response.setData(new HashMap<>());
        response.getData().put("salarie", salarieRetour);

        return response;
    }

    public Response responseApiObjectSalarie(List<SalarieDto> salaries, String msg, boolean status) {
        Response response = new Response();
        response.setMessage(msg);
        response.setStatus(status);
        response.setData(new HashMap<>());
        response.getData().put("salaries", salaries);

        return response;
    }


}
