package Demo.song.maneger;

import java.util.Iterator;
import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import org.apache.commons.collections.Factory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import Demo.song.entity.Song;

/**
 * Hello world!
 *
 */
public class SongManagerment {
	private static SessionFactory factory;

	public static void main(String[] args) {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println("Failed to create Session factory");
			ex.printStackTrace();
		}
		SongManagerment sm = new SongManagerment();
		//add few records to database

//		Integer songid2 =sm.addSong(1, "Lost Star"	, "Adam Levine", "EngLish Song-Pop Music");
//		Integer songid3 =sm.addSong(2, "Fake Love"	, "BTS", "Korean Pop Song");
//		Integer songid4 =sm.addSong(6, "Sorry"	, "Justin ", "EngLish Song");
		//shot list song
		sm.listSong();
//		sm.UpdateSongDes(songid4, "Sorry is a justin beiber song");
        // Delete an employee from the database
//		sm.deleteSong(1);
     

	}

	// insert song to database
	public Integer addSong(int id, String name, String artist, String des) {
		Session session = factory.openSession();
		Transaction trx = null;
		Integer songid = null;
		try {
			trx = session.beginTransaction();
			Song song = new Song(id, name, artist, des);
			songid = (Integer) session.save(song);
			trx.commit();
		} catch (HibernateException e) {
			if (trx != null)
				trx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return songid;
	}

	// method read all song
	public void listSong() {
		Session session = factory.openSession();
		Transaction trx = null;
		try {
			trx = session.beginTransaction();
			List songs = session.createQuery("from Song").list();
			for (Iterator iterator = songs.iterator(); iterator.hasNext();) {
				Song song = (Song) iterator.next();
				System.out.println("ID:" + song.getId());
				System.out.println("Name:" + song.getName());
				System.out.println("Artist:" + song.getArtist());
				System.out.println("Description:" + song.getDescription());
			}
			trx.commit();
		} catch (HibernateException e) {
			if (trx != null)
				trx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// update song description
	public void UpdateSongDes(Integer songid, String des) {
		Session session = factory.openSession();
		Transaction trx = null;
		try {
			trx = session.beginTransaction();
			Song s = (Song) session.get(Song.class, songid);
			s.setDescription(des);
			session.update(s);
			trx.commit();
		} catch (HibernateException e) {
			if (trx != null)
				trx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	// delete a song
	public void deleteSong(Integer songid) {
		Session session = factory.openSession();
		Transaction trx = null;
		try {
			trx = session.beginTransaction();
			Song s = (Song) session.get(Song.class, songid);
			session.delete(s);
			trx.commit();
		} catch (HibernateException e) {
			if (trx != null)
				trx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
