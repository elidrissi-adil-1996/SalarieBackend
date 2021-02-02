package com.example.projettest.service;

import com.example.projettest.controller.response.Response;
import com.example.projettest.model.entity.Salarie;
import com.example.projettest.repository.SalarieRepository;
import com.example.projettest.service.dto.SalarieDto;
import com.example.projettest.service.mapper.SalarieMapper;
import com.example.projettest.service.specification.SalarieSpecificationBuilder;
import com.example.projettest.service.validator.SalarieValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.PageRequest;

import javax.persistence.criteria.Order;
import java.util.*;

@Service
@Transactional
public class SalarieService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final String CODESALARIE = "codeSALARIE";

    private static final String PRENOM = "prenom";

    private static final String FONCTION = "fonction";
    private static final String ADRESSE = "adresse";

    private static final String ANNEEXP = "anneexp";

    private static final String  SALAIRE = "salaire";

    private static final String DATE = "date";
    private static final String PAGE = "page";
    private static final String PAGESIZE = "pageSize";
    @Autowired
    private final SalarieRepository salarieRepository ;
    private  SalarieDto salarieDto;
    @Autowired
    SalarieValidator salarieValidator;
    @Autowired
    private CommonService commonService;
    @Autowired
    public SalarieService(final SalarieRepository salarieRepository){
        this.salarieRepository = salarieRepository;
    };
    public Response addSalarie(SalarieDto salarieDto) throws Exception{
        LOGGER.info("Input :: SalarieDto :: {}",salarieDto);
        if(!StringUtils.isBlank(validateInput(salarieDto)))
            return commonService.responseApiObjectSalarie(new SalarieDto(), validateInput(salarieDto), false);
        SalarieDto createdSalarie= saveSalarie(salarieDto);
        Response response	= verifyCreatedSalarie(createdSalarie);
        LOGGER.info("reponse :: {} ", response.getMessage());
        LOGGER.info("output :: salarie :: {} ", (SalarieDto)response.getData().get("salarie"));
        return response;
    }
    public Response editSalarie(SalarieDto salarieDto,Long id)throws Exception{
        LOGGER.info("Input :: SalarieDto :: {} , id :: {}",salarieDto,id);
        if(!StringUtils.isBlank(validateInput(salarieDto,id))){
            return commonService.responseApiObjectSalarie(new SalarieDto(), validateInput(salarieDto,id), false);
        }

        Salarie salarie=salarieRepository.findById(id).get();
        setAttributes(salarie,salarieDto);
        SalarieDto createdSalarie=saveSalarie(SalarieMapper.mapEntityIntoDto(salarie));
        Response response = verifyCreatedSalarie(createdSalarie);
        LOGGER.info("reponse :: {} ", response.getMessage());
        LOGGER.info("output :: salarie :: {} ", (SalarieDto)response.getData().get("Salarie"));
        return response;
    }
    public void deleteSalarie(Long id)throws Exception{
        LOGGER.info("Input :: SalarieDto delete :: {} , id :: {}",salarieDto,id);
        Salarie salarie=salarieRepository.findById(id).get();
        salarieRepository.delete(salarie);
    }
    private String validateInput(SalarieDto salarieDto,Long id){
        LOGGER.debug("Input :: salarieDto :: {} , id :: {}",salarieDto,id);
        String message="";
        if(id==null){
            message= "L'id salarie envoyé est null";
        }else if(!StringUtils.isBlank(findSalarietById(id))){
            message= findSalarietById(id);
        } else if(!StringUtils.isBlank(salarieValidator.SalarieValidation(salarieDto))){
            message=salarieValidator.SalarieValidation(salarieDto);
        }
        LOGGER.debug("output :: message :: {} ", message);
        return message;
    }
    private Salarie setAttributes(Salarie salarie,SalarieDto salarieDto){
        LOGGER.debug("Input :: SalarieDto :: {} , Salarie :: {}",salarieDto,salarie);
        if(salarieDto!=null && salarie!=null){
            salarie.setPrenom(salarieDto.getPrenom());
            salarie.setFonction(salarieDto.getFonction());
            salarie.setAdresse(salarieDto.getAdresse());
            salarie.setSalaire(salarieDto.getSalaire());
            salarie.setAnneExp(salarieDto.getAnneExp());
            salarie.setDate(salarieDto.getDate());
        }
        LOGGER.debug("output :: salarie :: {} ", salarie);
        return salarie;
    }
    private String findSalarietById(Long id){
        LOGGER.debug("Input :: id :: {}",id);
        String message ="";
        Salarie salarie= salarieRepository.findById(id).get();
        if (salarie==null){
            message+= "Aucun salarie ne corresponds a cet id :"+ id ;
        }
        LOGGER.debug("output :: message :: {} ", message);
        return message;
    }
    private String validateInput(SalarieDto salarieDto){
        LOGGER.info("Input :: salarie :: {}",salarieDto);
        String msgToDisplay="";
        if(salarieDto!=null){
            msgToDisplay+=salarieValidator.SalarieValidation(salarieDto);

        }else {
            msgToDisplay+="le salarie envoye est null";
        }
        LOGGER.debug("output :: message :: {} ", msgToDisplay);
        return msgToDisplay;
    }

    private SalarieDto saveSalarie(SalarieDto salarieDto) {
        LOGGER.debug("Input :: salarie :: {}",salarieDto);
        Salarie salarie= SalarieMapper.mapDtoIntoEntity(salarieDto);
        Salarie createdSalarie = salarieRepository.save(salarie);
        SalarieDto createdSalarieDto = SalarieMapper.mapEntityIntoDto(createdSalarie);
        LOGGER.debug("output :: createdSalarie :: {} ", createdSalarieDto);
        return createdSalarieDto;

    }

    private Response verifyCreatedSalarie(SalarieDto salarieDto){
        LOGGER.debug("Input :: salarie :: {}",salarieDto);
        Response response=new Response();
        String ajoutOK="Le salarie a ete sauvegarde avec succe";
        String ajoutNOK="Le sauvegarde du salarie a echoue";
        if(salarieDto==null)
            response=commonService.responseApiObjectSalarie(salarieDto, ajoutNOK, true);
        if(salarieDto!=null)
            response=commonService.responseApiObjectSalarie(salarieDto,ajoutOK, true);
        LOGGER.debug("reponse :: {} ", response.getMessage());
        LOGGER.debug("output :: salarie :: {} ", (SalarieDto)response.getData().get("salarie"));
        return response;
    }
    public SalarieDto getSalarieById(Long id){
        LOGGER.info("Input :: id :: {}",id);
        SalarieDto SalarietDto = null;
        Salarie salarie= salarieRepository.findById(id).get();
        if(salarie!=null){
            salarieDto=SalarieMapper.mapEntityIntoDto(salarie);
        }
        LOGGER.info("output :: salarier :: {} ", salarieDto);
        return salarieDto;
    }


    public List<SalarieDto> getsalaries(Map<String,String> allRequestParams) {
        for (String key: allRequestParams.keySet()) {
            LOGGER.debug("Input :: {} " , key, " :: {} ", allRequestParams.get(key));
        }
        SalarieSpecificationBuilder salarieSpecificationBuilder = new SalarieSpecificationBuilder();
        processParam(salarieSpecificationBuilder, allRequestParams);
        List<Salarie> salaries=findAllByPageSize(salarieSpecificationBuilder,allRequestParams);
        List<SalarieDto> salarieDto=SalarieMapper.mapEntitiesIntoDTOs(salaries);
        LOGGER.info("output :: salarie :: {} ", salarieDto);
        return salarieDto;
    }

    private List<Salarie> findAllByPageSize(SalarieSpecificationBuilder salarieSpecificationBuilder,Map<String,String> allRequestParams){
        for (String key: allRequestParams.keySet()) {
            LOGGER.debug("Input :: {} " , key, " :: {} ", allRequestParams.get(key));
        }
        LOGGER.debug("Input :: salarieSpecificationsBuilder :: {} ", salarieSpecificationBuilder);
        List<Salarie> salaries;
         if (allRequestParams.get(PAGESIZE) == null) {
            salaries=salarieRepository.findAll(salarieSpecificationBuilder.build());
            Collections.reverse(salaries);
        }else{
            salaries=salarieRepository.findAll(salarieSpecificationBuilder.build(),getPaginationSalarie(allRequestParams)).getContent();
        }
        LOGGER.info("output :: Salaries :: {} ", salaries);
        return salaries;
    }

    private void processParam(SalarieSpecificationBuilder salarieSpecificationBuilder,Map<String,String> allRequestParams){
        for (String key: allRequestParams.keySet()) {
            LOGGER.debug("Input :: {} " , key, " :: {} ", allRequestParams.get(key));
        }
        LOGGER.debug("Input :: salarieSpecificationBuilder :: {} ", salarieSpecificationBuilder);
        if(!StringUtils.isBlank(allRequestParams.get(CODESALARIE))){
            salarieSpecificationBuilder.with(CODESALARIE, "= " , String.valueOf(allRequestParams.get(CODESALARIE)));
        }
        if(!StringUtils.isBlank(allRequestParams.get(PRENOM))){
            salarieSpecificationBuilder.with(PRENOM, "= " , String.valueOf(allRequestParams.get(PRENOM)));
        }
        if(!StringUtils.isBlank(allRequestParams.get(FONCTION))){
            salarieSpecificationBuilder.with(FONCTION, "= " , String.valueOf(allRequestParams.get(FONCTION)));
        }
        if(allRequestParams.get(DATE)!=null){
            salarieSpecificationBuilder.with(DATE, "= " , Long.valueOf(allRequestParams.get(DATE)));
        }
        if(allRequestParams.get(ANNEEXP)!=null){
            salarieSpecificationBuilder.with(ANNEEXP , "= " , Long.valueOf(allRequestParams.get(ANNEEXP)));
        }
        if(allRequestParams.get(SALAIRE)!=null){
            salarieSpecificationBuilder.with(SALAIRE , "= " , Long.valueOf(allRequestParams.get(SALAIRE)));
        }
    }



    private Pageable getPaginationSalarie(Map<String, String> allRequestParams) {
        for (String key: allRequestParams.keySet()) {
            LOGGER.debug("Input :: {} " , key, " :: {} ", allRequestParams.get(key));
        }
        Integer page = 0;
        Integer pageSize = 10;

        if (allRequestParams.get(PAGE) != null) {
            page = new Integer(allRequestParams.get(PAGE));
        }
        if (allRequestParams.get(PAGESIZE) != null) {
            pageSize = new Integer(allRequestParams.get(PAGESIZE));
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        LOGGER.debug("output :: pageable :: {} ", pageable);
        return pageable;
    }

    public Integer salarieSize(){
        Integer size=0;
        size=salarieRepository.findAll().size();
        LOGGER.debug("output :: size :: {} ", size);
        return size;
    }

}
