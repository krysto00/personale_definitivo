package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class PersonalTrainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascita;

    @NotBlank
    private String specializzazione; // es: "Bodybuilding", "Pilates", "CrossFit"

    @OneToOne(cascade = CascadeType.ALL)
    private ImageEntity foto;

    @ManyToMany(mappedBy = "personalTrainers", fetch = FetchType.LAZY)
    private List<Palestra> palestre;

    public PersonalTrainer() {}

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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getSpecializzazione() {
        return specializzazione;
    }

    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione;
    }

    public ImageEntity getFoto() {
        return foto;
    }

    public void setFoto(ImageEntity foto) {
        this.foto = foto;
    }

    public List<Palestra> getPalestre() {
        return palestre;
    }

    public void setPalestre(List<Palestra> palestre) {
        this.palestre = palestre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cognome, dataNascita, nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PersonalTrainer other = (PersonalTrainer) obj;
        return Objects.equals(cognome, other.cognome)
                && Objects.equals(dataNascita, other.dataNascita)
                && Objects.equals(nome, other.nome);
    }
}
