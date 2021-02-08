package co.com.organization.proyectoapi.persistence.repositories;

import co.com.organization.proyectoapi.persistence.entities.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITarjetaRepository extends JpaRepository<Tarjeta, String> {

    @Query("select t from Tarjeta t where  t.id = :id")
    Optional<Tarjeta> findTarjetaById(@Param("id") Long id);

}
