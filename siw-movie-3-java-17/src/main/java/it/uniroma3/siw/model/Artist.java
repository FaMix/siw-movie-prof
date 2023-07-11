package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
	private String name;
    @NotBlank
	private String surname;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfDeath;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Image picture;
	
	@ManyToMany(mappedBy="actors")
	private Set<Movie> starredMovies;
	
	@OneToMany(mappedBy="director")
	private Set<Movie> directedMovies;
	
	public Artist(){
		this.starredMovies = new HashSet<>();
		this.directedMovies = new HashSet<>();
	}
	
	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public Set<Movie> getStarredMovies() {
		return starredMovies;
	}

	public void setStarredMovies(Set<Movie> starredMovies) {
		this.starredMovies = starredMovies;
	}

	public Set<Movie> getDirectedMovies() {
		return directedMovies;
	}

	public void setDirectedMovies(Set<Movie> directedMovies) {
		this.directedMovies = directedMovies;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}

	public Set<Movie> getActorOf() {
		return starredMovies;
	}

	public void setActorOf(Set<Movie> starredMovies) {
		this.starredMovies = starredMovies;
	}

	public Set<Movie> getDirectorOf() {
		return directedMovies;
	}

	public void setDirectorOf(Set<Movie> directedMovies) {
		this.directedMovies = directedMovies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}
}