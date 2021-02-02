package com.example.projettest.service.specification;

import com.example.projettest.model.entity.Salarie;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class SalarieSpecification implements Specification<Salarie> {
    private SearchCriteria criteria;
    private String ID="id";

    private String PRENOM="prenom";

    private String FONCTION="fonction";

    private String ANNEEXP="anneExp" ;

    private String ADRESSE="adresse";

    private String SALAIRE="salaire";

    private String DATE="date";




    @Override
    public Predicate toPredicate(Root<Salarie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if(criteria.getKey().equals(ID)) {

            return builder.equal(root.<String> get("id"), criteria.getValue().toString().trim());

        } else if (criteria.getKey().equals(PRENOM)) {

            return builder.equal(root.<String>get("prenom"), criteria.getValue().toString().trim() );

        } else if (criteria.getKey().equals(FONCTION)) {

            return builder.equal(root.<String> get("fonction"), criteria.getValue().toString().trim());

        } else if (criteria.getKey().equals(ADRESSE)) {

            return builder.equal(root.<String> get("adresse"), criteria.getValue());

        } else if (criteria.getKey().equals(SALAIRE)) {

            return builder.equal(root.<Long> get("salaire"), criteria.getValue().toString().trim());

        }  else if (criteria.getKey().equals(ANNEEXP)) {

            return builder.equal(root.<Long> get("anneexp"), criteria.getValue().toString().trim());

        }else if (criteria.getKey().equals(DATE)) {

            return builder.equal(root.<Long>get(DATE), criteria.getValue());

        }

        return null;
    }
    public SearchCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public SalarieSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
}
