package torrent;

import java.io.Serializable;

/* Represents a piece of the TargetFile */

public class Piece implements Serializable {
	long offset;
	long size;
	long checksum;

	public Piece(long offset, long currentPieceSize, long checksum) {
		this.offset = offset;
		this.size = currentPieceSize;
		this.checksum = checksum;
	}

	public long getSize() {
		return size;
	}

	public long checksum() {
		return checksum;
	}
}
