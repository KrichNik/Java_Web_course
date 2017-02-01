package summarytask1.soundrecording;

import summarytask1.soundrecording.equipment.Album;
import summarytask1.soundrecording.equipment.Disk;
import summarytask1.soundrecording.equipment.Track;

import java.util.ArrayList;
import java.util.List;


/**
 * The class contains list of albums with tracks and list of disks. Class has a
 * main menu, where you can create an album, or print information about all
 * albums in the console, or use discs.
 *
 * @see Track
 * @see Album
 * @see Disk
 * @see Initializer
 * @author Nikita Datsenko
 *
 */
public class SoundRecording {
	private List<Album> albumList = new ArrayList<>();

	private List<Disk> diskList = new ArrayList<>();

	{
		albumList = Initializer.getAlbums();
		diskList = Initializer.getDisks();
	}

	public void showTracksInAlbums() {
		System.out.println("Albums in Sound Recording:" + System.lineSeparator());
		for (Album album : albumList) {
			System.out.println("Tracks in album \"" + album.getName() + "\":");
			for (Track track : album.getTrackList()) {
				System.out.println(track);
			}
			System.out.println();
		}
	}

	public void showTracksOnDisks() {
		System.out.println("Tracks on disks:" + System.lineSeparator());
		int counter = 0;
		for (Disk disk : diskList) {
			System.out.println(++counter + ". " + "disk #" + disk.getId() + ":");
			disk.printAllTracks();
			System.out.println("Duration of all tracks: " + disk.getDurationOfAllTracks() + " min.");
			System.out.println();
		}
	}

	public void sortTracksByStyle() {
		System.out.println("Sorting tracks on disks by style:" + System.lineSeparator());
		int counter = 0;
		for (Disk disk : diskList) {
			System.out.println(++counter + ". " + "disk #" + disk.getId() + ":");
			disk.sortTracksByStyle();
			disk.printAllTracks();
			System.out.println();
		}
	}

	public void findTrackByDurationMenu() {
		System.out.println("Finding track by duration from 3 to 6:");
		int from = 3;
		int until = 6;
		Disk disk = diskList.get(0);
		Track track = disk.findTrackByDuration(from, until);
		System.out.println("Desired track: " + track);
	}
}
