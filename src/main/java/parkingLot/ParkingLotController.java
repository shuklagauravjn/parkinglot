package parkingLot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import parkingLot.entity.Car;

import java.util.List;

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
    //done
    @RequestMapping("/setParkinglotSize")
    @GetMapping(value = "/{parkingSize}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with setting parking size",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = String.class) })
    public ResponseEntity<String> setParkinglotSize(@RequestParam(value = "parkingSize") String parkingSize) {
    	String message = parkingProcessor.setParkinglotSize(Integer.parseInt(parkingSize));
    	return new ResponseEntity<String>(message,HttpStatus.OK);

    }
    //done
    @RequestMapping("/findSlotNumberByCarColour")
    @GetMapping(value = "/{colour}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with getting Slot number by colour",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = List.class) })
    public ResponseEntity<String> findSlotNumberByCarColour(@RequestParam(value = "colour") String colour) {
    	String message =null;
    	String regNumbers = parkingProcessor.findSlotNumberByCarColour(colour);
    	return new ResponseEntity<String>(regNumbers,HttpStatus.OK);
    }
    //done
    @RequestMapping("/findRegistrationNumberByColour")
    @GetMapping(value = "/{colour}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with getting registration number by colour",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = List.class) })
    public ResponseEntity<String> findRegistrationNumberByColour(@RequestParam(value = "colour") String colour) {
    	String message =null;
    	String regNumbers = parkingProcessor.findRegistrationNumberByColour(colour);
    	return new ResponseEntity<String>(regNumbers,HttpStatus.OK);
    }
    //done
    @RequestMapping("/returnTicket")
    @GetMapping(value = "/{slotNumber}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with return ticket function",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = String.class) })

    public ResponseEntity<String>  returnTicket(@RequestParam(value = "slotNumber") String slotNumber) {
    	int slotNumberReturned = parkingProcessor.returnTicketBySlotNumber(Integer.parseInt(slotNumber));
    	//String message = ticketId+ " accepted. Now slot number "+slotNumber+" is available";
    	String message = "Slot number "+slotNumberReturned+" is free";
        return new ResponseEntity<String>(message,HttpStatus.OK);
    } 
    //done
    @RequestMapping("/issueTicket")
    @GetMapping(value = "/{registrationNumber}/{colour}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with ticket issuance",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = String.class) })

    public ResponseEntity<String>  issueTicket(@RequestParam(value = "registrationNumber") String registrationNumber,@RequestParam(value = "colour") String colour) {
    	String message =parkingProcessor.issueTicket(registrationNumber, colour);
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }
    //done
    @RequestMapping("/findSlotNumberByRegistrationNumber")
    @GetMapping(value = "/{registrationNumber}")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with getting slot number by registration number",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = List.class) })
    public ResponseEntity<String> findSlotNumberByRegistrationNumber(@RequestParam(value = "registrationNumber") String registrationNumber) {
    	int slotNumber = parkingProcessor.findSlotNumberByRegistrationNumber(registrationNumber);
    	if(slotNumber !=0) {
    		return new ResponseEntity<String>(""+slotNumber,HttpStatus.OK);
    	}else {
    		return new ResponseEntity<String>("Not found",HttpStatus.OK);
    	}
    	
    }
  //done
    @RequestMapping("/status")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Issue with getting the status",response = String.class),
            @ApiResponse(code = 200, message = "Success", response = List.class) })
    public ResponseEntity<String> status() {
    	String message = parkingProcessor.status();
    	return new ResponseEntity<String>(message,HttpStatus.OK);
    }
}
