
package parkingLot;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext

public class ParkingLotTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHomePage() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    @Test
    public void testSetParkinglotSize() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/setParkinglotSize?parkingSize=5", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    @Test
    public void testissueTicketForCar1() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/issueTicket?registrationNumber=KA53MA9184&colour=BLACK", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    @Test
    public void testReturnTicket() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/issueTicket?registrationNumber=KA53MA9188&colour=RED", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        String id = entity.getBody();
        entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/returnTicket?slotNumber=1", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    @Test
    public void testFindRegistrationNumberByColour() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/findRegistrationNumberByColour?colour=BLACK", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
    @Test
    public void testStatus() throws Exception {
        ResponseEntity<String> entity = restTemplate
                .getForEntity("http://localhost:" + this.port + "/status", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}
