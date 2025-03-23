package com.shruti.RestControllers;

import java.util.HashMap;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shruti.request.Passenger;
import com.shruti.response.Ticket;

@RestController
public class ErailRestController {
	
	HashMap<Integer, Ticket> tickets = new HashMap<>();
	
	@PostMapping(
			
			value="/ticket",
			consumes= {"application/xml","application/json"},
			produces= {"application/xml","application/json"}
			
			)
	
	public Ticket BookTicket(@RequestBody Passenger p) {
		
		// Logic to book train ticket
		
		Random r = new Random();
		int ticketId = r.nextInt();
		System.out.println(ticketId);
		Ticket t = new Ticket();
		
		t.setTid(ticketId);
		t.setFrom(p.getFrom());
		t.setTo(p.getTo());
		t.setTicketStatus("Confirmed");
		t.setTrainNum(p.getTrainNum());
		t.setTktcost("150");
		
		tickets.put(ticketId, t);
		return t;
	}
	
	@GetMapping(
			
			value="/ticket/{ticketId}",
			produces= {"application/xml","application/json"}
			
			)
	
	public Ticket getTicket(@PathVariable Integer ticketId) {
		
		if(tickets.containsKey(ticketId)) {
			return tickets.get(ticketId);
		}
		else {
			return null;
		}
	}
}
