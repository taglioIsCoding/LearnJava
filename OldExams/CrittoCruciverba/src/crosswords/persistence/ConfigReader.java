package crosswords.persistence;

import crosswords.model.Scheme;

public interface ConfigReader {
	int getSize();
	Scheme getScheme();
}
