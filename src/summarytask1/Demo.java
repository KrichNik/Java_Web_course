//JTM-114
package summarytask1;

import summarytask1.soundrecording.SoundRecording;

public class Demo {

	public static void main(String[] args) {
		SoundRecording soundRecording = new SoundRecording();
		soundRecording.showTracksInAlbums();
		System.out.println("------------------------------------------------------------------------");
		soundRecording.showTracksOnDisks();
		System.out.println("------------------------------------------------------------------------");
		soundRecording.sortTracksByStyle();
		System.out.println("------------------------------------------------------------------------");
		soundRecording.findTrackByDurationMenu();
	}

}
