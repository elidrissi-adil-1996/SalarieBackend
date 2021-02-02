package com.example.projettest.repository;

import com.example.projettest.model.entity.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalarieRepository extends JpaRepository<Salarie, Long>, JpaSpecificationExecutor<Salarie> {

    Salarie findByCodeSalarie(String salaire);

    @Override
    Optional<Salarie> findById(Long aLong);
}
