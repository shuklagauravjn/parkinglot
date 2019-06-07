package parkingLot.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the parkinglot database table.
 * 
 */
@Entity
@NamedQuery(name = "Parkinglot.findAll", query = "SELECT p FROM Parkinglot p")
public class Parkinglot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PARKINGLOT_SLOTNUMBER_GENERATOR", sequenceName = "PARKINGLOTSEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARKINGLOT_SLOTNUMBER_GENERATOR")
	private Integer slotnumber;

	private String isavailable;

	// bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy = "parkinglot")
	private List<Ticket> tickets;

	public Parkinglot() {
	}

	public Integer getSlotnumber() {
		return this.slotnumber;
	}

	public void setSlotnumber(Integer slotnumber) {
		this.slotnumber = slotnumber;
	}

	public String getIsavailable() {
		return this.isavailable;
	}

	public void setIsavailable(String isavailable) {
		this.isavailable = isavailable;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setParkinglot(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setParkinglot(null);

		return ticket;
	}

}