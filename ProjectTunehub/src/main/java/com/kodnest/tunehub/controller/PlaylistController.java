package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.serviceimpl.PlaylistServiceImpl;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;

@Controller
public class PlaylistController {
	
	@Autowired
	SongServiceImpl songservice;
	
	@Autowired
	PlaylistServiceImpl playlistservice;
	
	@GetMapping("/createplaylists")
	public String createPlaylists(Model model) {//model to store list of songs from db
		List<Song> songList = songservice.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createplaylists";
	}
	
	
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) {
		//updating the playlist table
		playlistservice.addplaylist(playlist);
	
		//updating the song table
		List<Song> songList = playlist.getSongs();
		for (Song s : songList) {
			s.getPlaylists().add(playlist);
			songservice.updateSong(s);
			
		}
		return "adminhome";
	}
	
	
	@GetMapping("/viewplaylists")
	public String viewplaylists(Model model) {
		List<Playlist> allplaylist = playlistservice.fetchAllPlaylists();
		
		model.addAttribute("allplaylist", allplaylist);
		return "displayplaylist";
	}
	
	

}
