package torrent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class TorrentTest {
	File testFile = new File(this.getClass().getResource("/testfile").getFile());

	@Test
	public void canProvideTracker() throws IOException {
		Torrent torrent = new Torrent(new TargetFile(testFile.getPath()),
				"127.0.0.1:9010", 70);
		assertEquals("127.0.0.1:9010", torrent.getTracker());
	}

	@Test
	public void canGivePieces() throws IOException {
		Torrent torrent = new Torrent(new TargetFile(testFile.getPath()),
				"127.0.0.1:9010", 70);
		assertEquals(2, torrent.getPieces().size());
	}

	@Test
	public void canCreateTorrentFile() throws IOException {
		Torrent torrent = new Torrent(new TargetFile(testFile.getPath()),
				"127.0.0.1:9010", 70);

		File outputFile = new File(this.getClass().getResource("/").getPath(),
				"TestTorrent.tor");
		assertTrue(torrent.createTorrentFile(outputFile));
		assertTrue(outputFile.exists());
	}

	@Test
	public void canReadInfoFromTorrentFile() throws IOException, Exception {
		Torrent torrent = new Torrent(new TargetFile(testFile.getPath()),
				"127.0.0.1:9010", 70);

		File outputFile = new File(this.getClass().getResource("/").getPath(),
				"TestTorrent.tor");

		assertTrue(torrent.createTorrentFile(outputFile));

		TargetFile file = new TargetFile(testFile.getPath());
		FileInputStream in = new FileInputStream(file.getFile());

		Torrent newTorrent = new Torrent(outputFile);
		// Tracker and pieces
		assertEquals("127.0.0.1:9010", newTorrent.getTracker());
		assertEquals(2, newTorrent.getPieces().size());

		long checksum = TestHelper.generateChecksum(in, 0, 70);
		Piece piece;
		piece = newTorrent.getPieces().get(0);
		// Checksum of pieces
		assertEquals(checksum, piece.checksum());

	}
}
