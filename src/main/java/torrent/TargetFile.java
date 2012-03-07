package torrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.CRC32;

/* Represents a File to be shared in the network */

public class TargetFile {
	File file;
	private long size;

	public TargetFile(String string) {
		file = new File(string);
		size = file.length();
	}

	public TargetFile(String string, long size) {
		this.file = new File(string);
		this.size = size;
	}

	public String getName() {
		return file.getName();
	}

	public ArrayList<Piece> generatePieces(long pieceSize) throws IOException {
		ArrayList<Piece> pieces = new ArrayList<Piece>();

		long offset = 0;
		long bytesProcessed = 0;
		CRC32 currentChecksum = new CRC32();
		FileInputStream in = new FileInputStream(file);
		try {
			/* TODO array size */
			byte[] pieceBytes = new byte[(int) pieceSize];
			while (bytesProcessed < size) {
				long currentPieceSize = (bytesRemaining(bytesProcessed) >= pieceSize) ? pieceSize
						: bytesRemaining(bytesProcessed);

				currentChecksum.reset();

				/* TODO cast to int size */
				in.read(pieceBytes, 0, (int) currentPieceSize);
				currentChecksum.update(pieceBytes, 0, (int) currentPieceSize);
				pieces.add(new Piece(offset, currentPieceSize, currentChecksum
						.getValue()));
				bytesProcessed += currentPieceSize;
				offset += currentPieceSize;

			}
			return pieces;
		} finally {
			in.close();
		}
	}

	private long bytesRemaining(long bytesProcessed) {
		return size - bytesProcessed;
	}

	public File getFile() {
		return file;
	}

	public long getSize() {
		return size;
	}
}
