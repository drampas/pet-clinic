package drampas.springframework.petclinic.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visits")
public class Visit extends BaseEntity{
    @Column(name = "visit_date")
    private LocalDate visitDate;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @Column(name = "description")
    private String description;

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
