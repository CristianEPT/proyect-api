package co.com.organization.proyectoapi.persistence.repositories;

import co.com.organization.proyectoapi.persistence.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFuncionarioRepository extends JpaRepository<Funcionario, String> {

    @Query("select f from Funcionario f where f.user = :user and f.pass = :pass")
    Optional<Funcionario> validateCredentials(@Param("user") String user, @Param("pass") String pass);
}
