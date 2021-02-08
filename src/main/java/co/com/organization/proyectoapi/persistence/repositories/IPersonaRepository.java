package co.com.organization.proyectoapi.persistence.repositories;

import co.com.organization.proyectoapi.persistence.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, String> {

    @Query("select p from Persona p where p.id = :id")
    Optional<Persona> findPersonaById(@Param("id") Long id);

}
