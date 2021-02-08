package co.com.organization.proyectoapi.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PERSONA")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TIPO_COD")
    private String tipoDoc;

    @Column(name = "NUM_DOC")
    private String numDoc;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tarjeta> tarjetas;


}
