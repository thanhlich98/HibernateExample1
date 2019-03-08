package Demo.song.entity;

public class Song {

	private int id;
	private String name;
	private String artist;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Song(int id, String name, String artist, String description) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.description = description;
	}

	public Song() {
	}
}
