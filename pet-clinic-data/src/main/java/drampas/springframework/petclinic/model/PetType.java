package drampas.springframework.petclinic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "types")
public class PetType extends BaseEntity {
    @Column(name = "name")
    private String name;

}
