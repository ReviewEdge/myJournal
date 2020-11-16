package myJournal.DataStructures;

import java.util.ArrayList;
import java.util.HashSet;

public class Journal implements Followable {

	private long id;
	private String name;
	private ArrayList<Page> pages;
	private HashSet<Long> owners;
	private HashSet<Long> contributers;
	private JournalStatistics stats;
	private JournalOptions options;
	
	public Journal(long id, String name, ArrayList<Page> pages, HashSet<Long> owners, HashSet<Long> contributers, JournalStatistics stats, JournalOptions options) {
		this.id = id;
		this.name = name;
		this.pages = pages;
		this.owners = owners;
		this.contributers = contributers;
		this.stats = stats;
		this.options = options;
	}
	
	// Journal Data
	public long getId() {
		return id;
	}
	
	public ArrayList<Page> getPages() {
		return pages;
	}
	
	
	// TO DO (Implement Options):
	// Stats Data
	public long getLikes() {
		return stats.getLikes();
	}
	
	public HashSet<Long> getLikers() {
		return stats.getLikers();
	}
	
	public HashSet<Long> getFollowers(){
		return stats.getFollowers();
	}
	
	public long getNumFollowers() {
		return stats.getNumFollowers();
	}
	
	public JournalStatistics getStats(){
		return stats;
	}
	
	
	// Options data:
	public boolean isPrivate() {
		return options.isPrivate();
	}
	
	public boolean hasLikes() {
		return options.hasLikes();
	}
	
	public boolean hasFollowers() {
		return options.hasFollowers();
	}
	
	public HashSet<Long> getOwners(){
		return options.getOwners();
	}
	
	public HashSet<Long> getContributers(){
		return options.getContributers();
	}
	
	public JournalOptions getOptions() {
		return options;
	}
	

	// TO DO:
	public void addPage() {
		
	}
	
	//TO DO:
	public Page getLatestPage(long requestingId) {
		return null;
	}
	public ArrayList<Page> getPages(long requestingId) {return null;}
	public long getNumPages() {return 0;}

	
	
}
