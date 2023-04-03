package drampas.springframework.petclinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "visits")
public class Visit extends BaseEntity{
    @Column(name = "visit_date")
    private LocalDate visitDate;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @Column(name = "description")
    private String description;

}
