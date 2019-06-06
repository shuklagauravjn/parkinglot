package parkingLot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ParkingLotController {
    private static final GsonBuilder builder = new GsonBuilder().setDateFormat("dd/mm/yyyy");
    private static final Gson gson = builder.create();
    private static final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);

    @Autowired
    private parkingLot.services.implementation.ParkingProcessor parkingProcessor;
    
    @RequestMapping("/setParkinglotSize")
    @GetMapping(value = "/{parkingSize}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with retrieving details",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = String.class) })
    public ResponseEntity<String> setParkinglotSize(@RequestParam(value = "parkingSize") String parkingSize) {
    	parkingProcessor.setParkinglotSize(Integer.parseInt(parkingSize));
    	return new ResponseEntity<String>("The Parking Size is set to "+parkingSize,HttpStatus.OK);

    }
    @RequestMapping("/findSlotNumberByCarColour")
    public String findSlotNumberByCarColour() {
        return "Code project for Backend Engineer, GO-Life/Commerce";
    }
    @RequestMapping("/findSlotNumberByRegistrationNumber")
    public String findSlotNumberByRegistrationNumber() {
        return "Code project for Backend Engineer, GO-Life/Commerce";
    }
    @RequestMapping("/findRegistrationNumberByColour")
    public String findRegistrationNumberByColour() {
        return "Code project for Backend Engineer, GO-Life/Commerce";
    }
    @RequestMapping("/returnTicket")
    @GetMapping(value = "/{ticketId}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with return ticket function",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = String.class) })

    public ResponseEntity<String>  returnTicket(@RequestParam(value = "ticketId") String ticketId) {
    	int slotNumber = parkingProcessor.returnTicket(Integer.parseInt(ticketId));
    	String message = ticketId+ " accepted. Now slot number "+slotNumber+" is available";
        return new ResponseEntity<String>(message,HttpStatus.OK);
    } 
    @RequestMapping("/issueTicket")
    @GetMapping(value = "/{registrationNumber}/{colour}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with ticket issuance",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = String.class) })

    public ResponseEntity<String>  issueTicket(@RequestParam(value = "registrationNumber") String registrationNumber,@RequestParam(value = "colour") String colour) {
    	int ticketNumber = parkingProcessor.issueTicket(registrationNumber, colour);
    	String message =null;
    	if(ticketNumber!=0) {
    		message = "The ticket with ticket number "+ticketNumber+" has been issued to "+registrationNumber+" with "+colour+" colour";
    	}else {
    		message = "All the parking slots are full";
    	}
        return new ResponseEntity<String>(message,HttpStatus.OK);
    } 
}
