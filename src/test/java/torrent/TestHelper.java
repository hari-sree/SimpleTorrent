package torrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class TestHelper {
	File testFile = new File(this.getClass().getResource("/testfile").getFile());
	String defaultTracker = "127.0.0.1:9010";

	public static long generateChecksum(FileInputStream in, int offset,
			int length) throws IOException {
		byte[] bytes = new byte[length];
		in.skip(offset);
		in.read(bytes);
		Checksum checksum = new CRC32();
		checksum.update(bytes, 0, length);
		return checksum.getValue();
	}

	public Torrent torrentInstance(String tracker, int pieceSize)
			throws IOException {
		tracker = (tracker == null) ? defaultTracker : tracker;
		return new Torrent(new TargetFile(testFile.getPath()), tracker,
				pieceSize);
	}
}