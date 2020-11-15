package myJournal.DataStructures;

public class PageOptions extends Options{
	private boolean hasLikes;
	private boolean hasViews;
	
	public PageOptions(Page p) {
		
	}
	
	public PageOptions(Page p, boolean hasLikes, boolean hasViews) {
		this.hasLikes = hasLikes;
		this.hasViews = hasViews;
	}
	
	public boolean hasLikes() {
		return this.hasLikes;
	}
	
	public boolean hasViews() {
		return this.hasViews;
	}

	public void setHasLikes(boolean hasLikes) {
		this.hasLikes = hasLikes;
	}
	
	public void setHasViews(boolean hasViews) {
		this.hasViews = hasViews;
	}
}
