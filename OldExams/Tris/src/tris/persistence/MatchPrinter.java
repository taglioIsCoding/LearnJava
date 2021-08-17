package tris.persistence;


import java.io.PrintWriter;
import tris.model.Match;

public interface MatchPrinter {
	void print(PrintWriter os, Match match) throws SadPrinterException;
}
