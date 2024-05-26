package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;

@Service
public class SongServiceImpl {
	
	@Autowired
	SongRepository songrepository;

	public String addSong(Song song) {
		songrepository.save(song);
		return "song added succesfully";

	}
	
	
	
	public boolean songExists(String name) {
		Song song = songrepository.findByName(name);
		if(song==null) {
			return false;
		}
		else {
			return true;
		}
	}



	public List<Song> fetchAllSongs() {
		List<Song> songs = songrepository.findAll();
		return songs;
	}



	public void updateSong(Song s) {
		
		songrepository.save(s);
		
	}

}
