package torrent;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.junit.Test;

public class FileTest {
	File testFile = new File(this.getClass().getResource("/testfile").getFile());

	@Test
	public void canReturnFileName() throws Exception {
		System.out.println(testFile.length());
		TargetFile file = new TargetFile(testFile.getPath());
		assertEquals(testFile.getName(), file.getName());
	}

	@Test
	public void canProvideCorrectNumberOfPieces() throws Exception {
		TargetFile file = new TargetFile(testFile.getPath());
		ArrayList<Piece> pieces = file.generatePieces(70);
		assertEquals(2, pieces.size());
	}

	@Test
	public void canProvideFile() {
		TargetFile file = new TargetFile(testFile.getPath());
		assertEquals(new File(testFile.getPath()), file.getFile());

	}

	@Test
	public void canProvideChecksumsForPieces() throws Exception {
		TargetFile file = new TargetFile(testFile.getPath());
		ArrayList<Piece> pieces = file.generatePieces(70);
		assertEquals(2, pieces.size());
		Piece piece = pieces.get(0);

		FileInputStream in = new FileInputStream(file.getFile());
		long checksum = TestHelper.generateChecksum(in, 0, 70);
		assertEquals(checksum, piece.checksum());
	}

	@Test
	public void canProvideCorrectChecksumForIncompleteLastPiece()
			throws Exception {
		TargetFile file = new TargetFile(testFile.getPath());
		ArrayList<Piece> pieces = file.generatePieces(70);
		assertEquals(2, pieces.size());
		Piece piece = pieces.get(1);

		FileInputStream in = new FileInputStream(file.getFile());
		long checksum = TestHelper.generateChecksum(in, 70, (133 - 70));
		assertEquals(checksum, piece.checksum());
	}

}
