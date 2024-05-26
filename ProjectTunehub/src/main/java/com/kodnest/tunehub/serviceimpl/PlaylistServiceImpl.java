package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.repository.PlaylistRepository;

@Service
public class PlaylistServiceImpl {
	
	@Autowired
	PlaylistRepository playlistrepository;
	

	public void addplaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		playlistrepository.save(playlist);
		
	}


	public List<Playlist> fetchAllPlaylists() {
		List<Playlist> allplaylist=playlistrepository.findAll();
		return allplaylist;
	}

}
