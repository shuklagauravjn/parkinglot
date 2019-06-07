package parkingLot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ParkingLotClientt {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		// Checking parking lot REST application is up and running
		// Send request with GET method and default Headers.
		System.out.println(restTemplate.getForObject("http://localhost:9090", String.class));
		FileInputStream fstream = new FileInputStream(args[0]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		final Logger logger = LoggerFactory.getLogger(ParkingLotClientt.class);
		String strLine;

		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			// Print the content on the console
			System.out.println(strLine);
			if (strLine.startsWith("create_parking_lot")) {
				// create_parking_lot 6
				String[] words = strLine.split(" ");
				logger.info(restTemplate
						.getForObject("http://localhost:9090/setParkinglotSize?parkingSize=" + words[1], String.class));
			} else if (strLine.startsWith("park")) {
				String[] words = strLine.split(" ");
				// park KA-01-HH-1234 White
				logger.info(restTemplate.getForObject(
						"http://localhost:9090/issueTicket?registrationNumber=" + words[1] + "&colour=" + words[2],
						String.class));
			} else if (strLine.startsWith("leave")) {
				// leave 4
				String[] words = strLine.split(" ");
				logger.info(restTemplate
						.getForObject("http://localhost:9090/returnTicket?slotNumber=" + words[1], String.class));
			} else if (strLine.startsWith("status")) {
				// status
				logger.info(restTemplate.getForObject("http://localhost:9090/status", String.class));
			} else if (strLine.startsWith("registration_numbers_for_cars_with_colour")) {
				// registration_numbers_for_cars_with_colour White
				String[] words = strLine.split(" ");
				logger.info(restTemplate.getForObject(
						"http://localhost:9090/findRegistrationNumberByColour?colour=" + words[1], String.class));
			} else if (strLine.startsWith("slot_numbers_for_cars_with_colour")) {
				// slot_numbers_for_cars_with_colour White
				String[] words = strLine.split(" ");
				logger.info(restTemplate.getForObject(
						"http://localhost:9090/findSlotNumberByCarColour?colour=" + words[1], String.class));
			} else if (strLine.startsWith("slot_number_for_registration_number")) {
				// slot_number_for_registration_number KA-01-HH-3141
				String[] words = strLine.split(" ");
				logger.info(restTemplate.getForObject(
						"http://localhost:9090/findSlotNumberByRegistrationNumber?registrationNumber=" + words[1],
						String.class));
			}

		}

		// Close the input stream
		fstream.close();
	}

}
