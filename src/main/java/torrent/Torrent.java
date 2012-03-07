package torrent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Torrent {
	String tracker;
	ArrayList<Piece> pieces;
	TargetFile target;

	public Torrent(TargetFile targetFile, String tracker, long pieceSize)
			throws IOException {
		this.tracker = tracker;
		this.target = targetFile;
		pieces = targetFile.generatePieces(pieceSize);
	}

	public Torrent(File torrentFile) throws IOException, ClassNotFoundException {
		DataInputStream in = new DataInputStream(new FileInputStream(
				torrentFile));
		ObjectInputStream objIn = new ObjectInputStream(in);
		try {
			String fileName = in.readUTF();
			this.tracker = in.readUTF();
			long fileSize = in.readLong();
			target = new TargetFile(fileName, fileSize);

			int numPieces = in.readInt();
			pieces = new ArrayList<Piece>();
			for (int i = 0; i < numPieces; i++) {
				pieces.add((Piece) objIn.readObject());
			}
		} finally {
			if (null != in)
				in.close();
			if (null != objIn)
				objIn.close();
		}
	}

	public String getTracker() {
		return tracker;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	/*-
	 * Format : 
	 * Filename
	 * tracker address
	 * length
	 * No.of pieces (n)
	 * n lines of piece information (Serialized pieces)
	 */
	public boolean createTorrentFile(File outputFile) throws IOException {
		DataOutputStream out = null;
		ObjectOutputStream objOut = null;
		try {
			out = new DataOutputStream(new FileOutputStream(outputFile));
			objOut = new ObjectOutputStream(out);
			out.writeUTF(outputFile.getName());
			out.writeUTF(tracker);
			out.writeLong(target.getSize());
			out.writeInt(pieces.size());
			for (Piece piece : pieces) {
				objOut.writeObject(piece);
			}
			return true;
		} catch (IOException e) {
			return false;
		} finally {
			if (null != out)
				out.close();
			if (null != objOut)
				objOut.close();
		}
	}
}
