package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.controller.validator.ArtistValidator;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.FileStorageService;
import it.uniroma3.siw.service.ImageService;
import jakarta.validation.Valid;

@Controller
public class ArtistController {
	
	@Autowired 
	private ArtistService artistService;
	
	@Autowired
	private ArtistValidator artistValidator;

	@Autowired
	private FileStorageService storageService;

	@Autowired
	private ImageService imageService;
	
	@GetMapping(value="/admin/formNewArtist")
	public String formNewArtist(Model model) {
		model.addAttribute("artist", new Artist());
		return "admin/formNewArtist.html";
	}
	
	@GetMapping(value="/admin/indexArtist")
	public String indexArtist() {
		return "admin/indexArtist.html";
	}
	
	@PostMapping("/admin/artist")
	public String newArtist(@Valid @ModelAttribute("artist") Artist artist,
			BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
		this.artistValidator.validate(artist, bindingResult);
		if (!bindingResult.hasErrors()) {
			if(file.getSize() != 0) {
				Image image = this.storageService.createImage(file);
				artist.setPicture(image);
				this.imageService.save(image);
			}
			this.artistService.save(artist);
			model.addAttribute("artist", artist);
			return "artist.html";
		} else {
			return "admin/formNewArtist.html"; 
		}
	}

	@GetMapping("/artist/{id}")
	public String getArtist(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artist", this.artistService.findById(id));
		return "artist.html";
	}

	@GetMapping("/artist")
	public String getArtists(Model model) {
		model.addAttribute("artists", this.artistService.findAll());
		return "artists.html";
	}
	
	@GetMapping(value="/admin/manageArtists")
	public String manageArtists(Model model) {
		model.addAttribute("artists", this.artistService.findAll());
		return "admin/manageArtists.html";
	}
	
	@GetMapping("/admin/updateArtist/{id}")
	public String formUpdateActor(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artist", this.artistService.findById(id));
		return "admin/formUpdateActor.html";
	}
	
	@PostMapping("/admin/artistUpdated/{id}")
	public String updateArtist(@ModelAttribute("artist") Artist updated,
			@PathVariable("id") Long id, @RequestParam("file") MultipartFile file, Model model) {
		Artist oldArtist = this.artistService.findById(id);
		if(this.artistService.isModified(oldArtist, updated)) {
			oldArtist.setDateOfBirth(updated.getDateOfBirth());
			oldArtist.setName(updated.getName());
			oldArtist.setSurname(updated.getSurname());
			oldArtist.setDateOfDeath(updated.getDateOfDeath());
		}
		this.artistService.updatePicture(oldArtist, file);
		this.artistService.save(oldArtist);
		model.addAttribute("artist", oldArtist);
		return "artist.html";
	}
	
	@GetMapping("/admin/deleteArtist/{id}")
	public String deleteArtist(@PathVariable("id") Long id, Model model) {
		this.artistService.deleteArtist(id);
		model.addAttribute("artists", this.artistService.findAll());
		return "admin/manageArtists.html";
	}
}
