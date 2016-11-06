package com.SummaryTask1.src.ua.nure.datsenko.SummaryTask1.soundrecording.equipment;

import java.util.ArrayList;
import java.util.List;

public class Disk {

	public static final int CAPACITY = 650;

	private static int idCounter;

	private final int id;

	private String label;

	private List<Track> trackList = new ArrayList<>();

	private List<String> styleList = new ArrayList<>();

	private int durationOfAllTracks;

	private int sizeOfTracks;

	{
		id = idCounter++;
	}

	public Disk() {
	}

	public Disk(List<Track> trackList) {
		this.trackList = trackList;
		initializeTrackList(trackList);
	}

	public Disk(List<Track> trackList, List<String> styleList, int durationOfAllTracks, int sizeOfTracks) {
		if (sizeOfTracks > CAPACITY) {
			throw new IllegalArgumentException("Not enough capacity");
		}
		this.trackList = trackList;
		this.styleList = styleList;
		this.durationOfAllTracks = durationOfAllTracks;
		this.sizeOfTracks = sizeOfTracks;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public List<Track> getTrackList() {
		return trackList;
	}

	public List<String> getStyleList() {
		return styleList;
	}

	public int getDurationOfAllTracks() {
		return durationOfAllTracks;
	}

	public int getSizeOfTracks() {
		return sizeOfTracks;
	}

	public void addAlbum(Album album) {
		if (sizeOfTracks + album.getSize() > CAPACITY) {
			throw new IllegalArgumentException("Not enough capacity");
		}
		trackList.addAll(album.getTrackList());
		styleList.addAll(album.getStyleList());
		durationOfAllTracks += album.getDurationOfAllTracks();
		sizeOfTracks += album.getSize();
	}

	private void initializeTrackList(List<Track> trackList) {
		for (Track track : trackList) {
			initializeTrack(track);
		}
	}

	private void initializeTrack(Track track) {
		if (sizeOfTracks + track.getSize() > CAPACITY) {
			throw new IllegalArgumentException("Not enough capacity");
		}
		styleList.add(track.getStyle());
		durationOfAllTracks += track.getDuration();
		sizeOfTracks += track.getSize();
	}
}
