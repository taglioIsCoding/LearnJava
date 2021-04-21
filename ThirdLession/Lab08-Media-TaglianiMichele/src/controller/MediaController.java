package controller;

import media.Media;
import media.collection.MediaCollection;
import media.filters.Filter;

public class MediaController {
	
	private MediaCollection allMedias = null;
	
	public MediaController() {
		allMedias = new  MediaCollection();
	}
	
	public boolean add(Media m) {
		int index = this.allMedias.indexOf(m);
		if(index == -1) {
			allMedias.add(m);
			return true;
		}
		return false;
	}
	
	public MediaCollection getAll() {
		return this.allMedias;
	}
	
	public boolean remove(Media m) {
		int index = allMedias.indexOf(m);
		if(index != -1) {
			allMedias.remove(index);
			return true;
		}
		return false;
	}
	
	public MediaCollection find(Filter f) {
		MediaCollection ret = new MediaCollection();
		for(int i=0; i<allMedias.size(); i++) {
			if(f.filter(allMedias.get(i))){
				ret.add(allMedias.get(i));
			}
		}
		return ret;
	}
}
