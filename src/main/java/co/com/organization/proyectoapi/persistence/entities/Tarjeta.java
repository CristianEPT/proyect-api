package co.com.organization.proyectoapi.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TARJETA")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NUM_TARJETA")
    private String numTarjeta;

    @Column(name = "ACTIVO")
    private Boolean activo;

    @Column(name = "CAPACIDAD")
    private Long capacidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona", nullable = false, referencedColumnName = "ID")
    private Persona persona;

}
