package summarytask1.soundrecording.equipment;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * Class describes the album. The album contains the track list, the list of
 * genres, the duration of the album tracks, and the album size(kb). The tracks
 * can be added to album.
 *
 * @see Track
 * @author Nikita Datsenko
 *
 */
public class Album {

	private String name;

	private List<Track> trackList = new ArrayList<>();

	private List<String> styleList = new ArrayList<>();

	private int durationOfAllTracks;

	private int size;

	public Album(String name) {
		this.name = name;
	}

	public Album(String name, List<Track> trackList) {
		this.name = name;
		this.trackList = trackList;
		initializeTrackList(trackList);
	}

	public String getName() {
		return name;
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

	public int getSize() {
		return size;
	}

	public void addTrack(Track track) {
		trackList.add(track);
		initializeTrack(track);
	}

	private void initializeTrackList(List<Track> trackList) {
		for (Track track : trackList) {
			initializeTrack(track);
		}
	}

	private void initializeTrack(Track track) {
		styleList.add(track.getStyle());
		durationOfAllTracks += track.getDuration();
		size += track.getSize();
	}

}
