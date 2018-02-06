
public class Customer implements Comparable<Object> {

	private Double latitude;
	private int user_id;
	private String name;
	private Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int userId) {
		this.user_id = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		int compareId =((Customer)o).getUserId();
		return this.user_id - compareId;
	}
}