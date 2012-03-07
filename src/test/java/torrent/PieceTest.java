package torrent;

import static org.junit.Assert.*;

import org.junit.Test;

public class PieceTest {

	@Test
	public void canProvideByteOffset() {
		Piece piece = new Piece(0, 1024,1234);
		assertEquals(1024, piece.getSize());
	}

}
