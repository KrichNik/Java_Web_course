package summarytask1.soundrecording.equipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class describes the disk. Disc has a track list, a list of genres, the
 * duration of all the tracks, capacity and size of containing tracks. On disc,
 * you can record an album or a single song. Disc can be found for a given
 * duration of track and all tracks sorted by genre. The disc has id.
 *
 * @see Track
 * @see Album
 * @author Nikita Datsenko
 *
 */
public class Disk {

	public static final int CAPACITY = 650;

	private static int counter;

	private final int id;

	private List<Track> trackList = new ArrayList<>();

	private List<String> styleList = new ArrayList<>();

	private int durationOfAllTracks;

	private int sizeOfTracks;

	{
		id = idCounter();
	}

	public Disk() {

	}

	public Disk(List<Track> trackList) {
		this.trackList = trackList;
		initializeTrackList(trackList);
	}

	public int getId() {
		return id;
	}

	public int getDurationOfAllTracks() {
		isEmptyCheck();
		return durationOfAllTracks;
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

	public void addTrack(Track track) {
		if (sizeOfTracks + track.getSize() > CAPACITY) {
			throw new IllegalArgumentException("Not enough capacity");
		}
		trackList.add(track);
		styleList.add(track.getStyle());
		durationOfAllTracks += track.getDuration();
		sizeOfTracks += track.getSize();
	}

	public void printAllTracks() {
		isEmptyCheck();
		for (Track track : trackList) {
			System.out.println(track);
		}
	}

	public Track findTrackByDuration(int from, int until) {
		isEmptyCheck();
		if (from < 0 || from > until) {
			throw new IllegalArgumentException("Invalid data!");
		}
		int currentDuration = 0;
		for (Track track : trackList) {
			currentDuration = track.getDuration();
			if (currentDuration >= from && currentDuration <= until) {
				return track;
			}
		}
		System.out.println("No such tracks!");
		return null;
	}

	public void sortTracksByStyle() {
		isEmptyCheck();
		Map<String, List<Track>> tracksOnStyle = spreadTracksByStyle();
		trackList.clear();
		for (List<Track> tracks : tracksOnStyle.values()) {
			trackList.addAll(tracks);
		}
	}

	private Map<String, List<Track>> spreadTracksByStyle() {
		Map<String, List<Track>> tracksOnStyle = new HashMap<>();
		for (String style : styleList) {
			tracksOnStyle.put(style, new ArrayList<Track>());
			for (Track track : trackList) {
				if (style.equals(track.getStyle())) {
					tracksOnStyle.get(style).add(track);
				}
			}
		}
		return tracksOnStyle;
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

	private void isEmptyCheck() {
		if (trackList.size() == 0) {
			throw new IllegalArgumentException("Empty disk!");
		}
	}

	private static int idCounter() {
		return ++counter;
	}
}
