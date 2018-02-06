import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class HelperFunctions {
	
	// Office co ordinates
	public static final Double OFFICE_LONG = -6.257664;
	public static final Double OFFICE_LAT =  53.339428;
	private static HttpURLConnection con;

	

	public static ArrayList<Customer> getCloseCustomers(ArrayList<Customer> customers) {
		ArrayList<Customer> toReturn = new ArrayList<>();
		for(Customer cust: customers) {
			Double distanceAway = distance(OFFICE_LAT,OFFICE_LONG,cust.getLatitude(),cust.getLongitude(),'K');
			if(distanceAway < 100) {
				toReturn.add(cust);
			}
		}
		return toReturn;
	}
	
	public static String getData(String url) {
		String toReturn = "";
        try {
            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line +",");
                    
                    content.append(System.lineSeparator());
                }
                toReturn = "["+content.substring(0,content.length()-3)+"]";
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {    
            con.disconnect();
        }
        return toReturn;
	}
	
	
	
	// Function to get distance in Km
	private static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      if (unit == 'K') {
	        dist = dist * 1.609344;
	      } else if (unit == 'N') {
	        dist = dist * 0.8684;
	        }
	      return (dist);
	    }
	
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
      return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
      return (rad * 180.0 / Math.PI);
    }


}
