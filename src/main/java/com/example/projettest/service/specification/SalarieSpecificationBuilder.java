package com.example.projettest.service.specification;

import com.example.projettest.model.entity.Salarie;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SalarieSpecificationBuilder {
    private final List<SearchCriteria> params;


    public SalarieSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public SalarieSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }



    public List<SearchCriteria> getParams() {
        return params;
    }



    public Specification<Salarie> build() {
        if (params!=null && params.isEmpty()) {
            return null;
        }

        List<Specification<Salarie>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new SalarieSpecification(param));
        }

        Specification<Salarie> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }



}
