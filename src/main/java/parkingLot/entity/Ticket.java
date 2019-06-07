package parkingLot.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the ticket database table.
 * 
 */
@Entity
@NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TICKET_TICKETID_GENERATOR", sequenceName = "PARKINGLOTSEQUENCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_TICKETID_GENERATOR")
	private Integer ticketid;

	// bi-directional many-to-one association to Car
	@ManyToOne
	@JoinColumn(name = "registrationnumer")
	private Car car;

	// bi-directional many-to-one association to Parkinglot
	@ManyToOne
	@JoinColumn(name = "slotnumer")
	private Parkinglot parkinglot;

	public Ticket() {
	}

	public Integer getTicketid() {
		return this.ticketid;
	}

	public void setTicketid(Integer ticketid) {
		this.ticketid = ticketid;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Parkinglot getParkinglot() {
		return this.parkinglot;
	}

	public void setParkinglot(Parkinglot parkinglot) {
		this.parkinglot = parkinglot;
	}

}