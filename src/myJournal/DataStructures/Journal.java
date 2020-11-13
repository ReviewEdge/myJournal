package myJournal.DataStructures;

import java.util.ArrayList;

public class Journal implements Followable {

	private long id;
	private String name;
	private ArrayList<Page> pages;
	private ArrayList<Long> owners;
	private ArrayList<Long> contributers;
	private ArrayList<Long> viewers;
	private JournalStatistics stats;
	private JournalOptions options;
	
	public Journal(long id, String name, ArrayList<Page> pages, ArrayList<Long> owners, ArrayList<Long> contributers, ArrayList<Long> viewers, JournalOptions options) {
		this.id = id;
		this.name = name;
		this.pages = pages;
		this.owners = owners;
		this.contributers = contributers;
		this.viewers = viewers;
	}
	
	public long getId() {
		return id;
	}
	
	public ArrayList<Page> getPages() {
		return pages;
	}
	
	public ArrayList<Long> getOwners() {
		return owners;
	}
	
	public ArrayList<Long> getContributers() {
		return contributers;
	}
	public ArrayList<Long> getViewers() {
		return viewers;
	}
	
	public void addPage() {
		
	}
	
	
	
}
