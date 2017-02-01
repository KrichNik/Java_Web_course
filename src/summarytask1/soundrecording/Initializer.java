package summarytask1.soundrecording;

import summarytask1.soundrecording.equipment.Album;
import summarytask1.soundrecording.equipment.Disk;
import summarytask1.soundrecording.equipment.Track;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Class to initialize the application using the file.
 *
 * @see SoundRecording
 * @author Nikita Datsenko
 *
 */
public class Initializer {
	private static List<Album> albumList = new ArrayList<>();

	private static List<Disk> diskList = new ArrayList<>();

	static {
		try {
			readFile("init.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Album> getAlbums() {
		return albumList;
	}

	public static List<Disk> getDisks() {
		return diskList;
	}

	public static void readFile(final String fileName) throws FileNotFoundException {
		String[] content = null;
		Album album = null;
		Disk disk = null;
		Scanner scanner = new Scanner(new File(fileName), "Cp1251");
		while (scanner.hasNextLine()) {
			content = scanner.nextLine().split(", ");
			if (content.length == 1) {
				album = new Album(content[0]);
				disk = new Disk();
				diskList.add(disk);
				albumList.add(album);
			} else {
				Track track = new Track(content[0], content[1], Integer.parseInt(content[2]));
				album.addTrack(track);
				disk.addTrack(track);
			}

		}
		scanner.close();
	}
}
