package myJournal.DataStructures.TestingCode;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import myJournal.DataStructures.*;
import myJournal.Intermediaries.*;

public class JournalTester {

	// Quick overview, needs more detail in the future
	
	Journal jnl;
	ArrayList<PageId> pIds;
	JournalStatistics sts;
	JournalOptions opt;
	
	public void createJournal() {
		pIds = new ArrayList<>();
		
		HashSet<Long> likers = new HashSet<>();
		HashSet<Long> followers = new HashSet<>();
		HashSet<Long> viewed = new HashSet<>();
		
		HashSet<Long> owners = new HashSet<>();
		HashSet<Long> contributers = new HashSet<>();
		HashSet<Long> viewers = new HashSet<>();
		
		
		for (int i = 0; i < 4; i++) {
			pIds.add(new PageId(i));
			
			likers.add(Long.valueOf(i));
			followers.add(Long.valueOf(i));
			viewed.add(Long.valueOf(i));
			
			owners.add(Long.valueOf(i));
			contributers.add(Long.valueOf(i));
			viewers.add(Long.valueOf(i));
		}
		
		
		sts = new JournalStatistics(likers, followers, viewed);
		opt = new JournalOptions(true, true, true, owners, contributers, viewers);
		
		
		jnl = new Journal(1,"jnl1", pIds, sts, opt);
	}
	
	@Test
	public void constructorAndGetMethodsAreWorking() {
		createJournal();
		assert(Long.valueOf(1).equals(jnl.getId()));
		assert("jnl1".equals(jnl.getName()));
		assert(jnl.getPageIds().containsAll(pIds));
		
		assert(jnl.isPrivate());
		assert(jnl.hasFollowers());
		assert(jnl.hasLikes());
		assert(jnl.getLikers().containsAll(sts.getLikers()));
		assert(jnl.getLikes() == 4);
		assert(jnl.getFollowers().containsAll(sts.getLikers()));
		assert(jnl.getNumFollowers() == 4);
		assert(jnl.getViewers().containsAll(sts.getLikers()));
		assert(jnl.getViews() == 4);
		assert(jnl.getOwners().containsAll(opt.getOwners()));
		assert(jnl.getContributers().containsAll(opt.getContributers()));
		jnl.setPrivacy(true , 0);
		assert(jnl.getViewers().containsAll(opt.getViewers()));
	}
	
	@Test
	public void verifyOptions() {
		createJournal();
		assert(opt.equals(jnl.getOptions()));
	}
	
	@Test
	public void verifyStats() {
		createJournal();
		assert(sts.equals(jnl.getStats()));
	}
	
	@Test
	public void addLikerTest() {
		createJournal();
		long ln = 4;
		jnl.addLiker(ln);
		sts.addLiker(ln);
		assert(sts.equals(jnl.getStats()));	
	}
	
	@Test
	public void removeLikerTest() {
		createJournal();
		long ln = 3;
		jnl.removeLiker(ln);
		sts.removeLiker(ln);
		assert(sts.equals(jnl.getStats()));	
	}
	
	@Test
	public void addFollowerTest() {
		createJournal();
		long ln = 4;
		jnl.addFollower(ln);
		sts.addFollower(ln);
		assert(sts.equals(jnl.getStats()));	
	}
	
	@Test
	public void removeFollowerTest() {
		createJournal();
		long ln = 3;
		jnl.removeFollower(ln);
		sts.removeFollower(ln);
		assert(sts.equals(jnl.getStats()));	
	}
	
	@Test
	public void addViewedTest() {
		createJournal();
		long ln = 4;
		jnl.addViewed(ln);
		sts.addViewed(ln);
		assert(sts.equals(jnl.getStats()));	
	}
	
	@Test
	public void setPrivateTest() {
		createJournal();
		jnl.setPrivacy(false, 1);
		assert(!jnl.isPrivate());
	}
	
	@Test
	public void hasLikesTest() {
		createJournal();
		jnl.setHasLikes(false, 1);
		assert(!jnl.hasLikes());
	}
	
	@Test
	public void hasFollowersTest() {
		createJournal();
		jnl.setHasFollowers(false, 1);
		assert(!jnl.hasFollowers());
	}
	
	@Test
	public void addOwnerTest() {
		createJournal();
		jnl.addOwner(4, 1);
		opt.addOwner(4);
		assert(opt.equals(jnl.getOptions()));	
	}
	
	@Test
	public void removeOwnerTest() {
		createJournal();
		jnl.removeOwner(3, 1);
		opt.removeOwner(3);
		assert(opt.equals(jnl.getOptions()));	
	}
	
	@Test
	public void addContributerTest() {
		createJournal();
		jnl.addContributer(4, 1);
		opt.addContributer(4);
		assert(opt.equals(jnl.getOptions()));	
	}
	

	@Test
	public void removeContributerTest() {
		createJournal();
		jnl.removeContributer(3, 1);
		opt.removeContributer(3);
		assert(opt.equals(jnl.getOptions()));	
	}
	
	@Test
	public void addViewerTest() {
		createJournal();
		jnl.addViewer(4, 1);
		opt.addViewer(4);
		assert(opt.equals(jnl.getOptions()));	
	}
	

	@Test
	public void removeViewerTest() {
		createJournal();
		jnl.removeViewer(3, 1);
		opt.removeViewer(3);
		assert(opt.equals(jnl.getOptions()));	
	}

	@Test
	public void latestPageTest() {
		//Not Written
	}
	
	@Test
	public void copyWithIdTest() {
		//Not Written
	}
	
	@Test
	public void equalsTest() {
		createJournal();
		Journal j2 = jnl;
		createJournal();
		assert(jnl.equals(j2));
		j2.setName("HI", 1);
		assert(!(jnl.equals(j2)));
	}

	@Test
	public void HashCodeTest() {
		createJournal();
		Journal j2 = jnl;
		createJournal();
		assert(jnl.hashCode() == j2.hashCode());
		j2.setName("HI", 1);
		assert(!(jnl.hashCode() == j2.hashCode()));
	}
	
	@Test
	public void JSONTest() {
		createJournal();
		String str = "{ \"pages\":[ 0,1,2,3],"
					 + "\"stats\":{ \"viewers\":[ 0,1,2,3],"
					 			 + "\"followers\":[ 0,1,2,3],"
					 			 + "\"likers\":[ 0,1,2,3]},"
		 			 + "\"name\":\"jnl1\","
		 			 + "\"options\":{ \"hasFollowers\":\"true\","
					 			 + "\"viewers\":[ 0,1,2,3],"
					 			 + "\"hasLikes\":\"true\","
					 			 + "\"owners\":[ 0,1,2,3],"
					 			 + "\"isPrivate\":\"true\","
					 			 + "\"contributers\":[ 0,1,2,3]},"
		 			 + "\"id\":1}";
		assert(jnl.asJson().equals(str));
	}
	
}
