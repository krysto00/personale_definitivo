package it.uniroma3.siw.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Palestra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String citta;

    @NotBlank
    private String specializzazione; // es. "CrossFit", "Fitness", "Bodybuilding"

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ImageEntity> images;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<PersonalTrainer> personalTrainers;

    @OneToMany(mappedBy = "palestra", cascade = CascadeType.ALL)
    private List<Esperienza> esperienze; // se vuoi mantenere "esperienze" come corsi/attività

    public Palestra() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getSpecializzazione() {
        return specializzazione;
    }

    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    public Set<ImageEntity> getImages() {
        return images;
    }

    public void setImages(Set<ImageEntity> images) {
        this.images = images;
    }

    public List<PersonalTrainer> getPersonalTrainers() {
        return personalTrainers;
    }

    public void setPersonalTrainers(List<PersonalTrainer> personalTrainers) {
        this.personalTrainers = personalTrainers;
    }

    public List<Esperienza> getEsperienze() {
        return esperienze;
    }

    public void setEsperienze(List<Esperienza> esperienze) {
        this.esperienze = esperienze;
    }

    @Override
    public int hashCode() {
        return Objects.hash(citta, nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Palestra other = (Palestra) obj;
        return Objects.equals(citta, other.citta) && Objects.equals(nome, other.nome);
    }
}
