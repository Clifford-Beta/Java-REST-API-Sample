import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Rester {
	
	//URL to the REST API *Note that the result is in JSON*
	private static final String API_URL = "https://protected-falls-20828.herokuapp.com/locations/county";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Call to the API
		String counties = callApi();
		
		try {
			JSONArray countyList = new JSONArray(counties);
			
			for (int i = 0; i < countyList.length(); i++) {
                JSONObject c = countyList.getJSONObject(i);

                // Storing each json item in variable
                String county_name = c.getString("name");

              System.out.println(county_name);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Print out the output in the console
		
	}
	//Method to call the County API
	public static String callApi(){
		
		 try {
			 //Initialize the URL to call
             URL url = new URL(API_URL);
             
             //Create connection to the URL to get data from
             HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
             
             try {
            	 //Put the input into a buffer and specify the input stream to be read from
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                 StringBuilder stringBuilder = new StringBuilder();
                 String line;
                 
                 //Iterate through all the lines returned and place the result in a string
                 while ((line = bufferedReader.readLine()) != null) {
                     stringBuilder.append(line).append("\n");
                 }


                 bufferedReader.close();
                 return stringBuilder.toString();
             } finally {
            	 //Close the connection initially made
                 urlConnection.disconnect();
             }
         } catch (Exception e) {
            System.out.println("Sorry, there was an error reading from the API");
             return null;
         }
	}

}
