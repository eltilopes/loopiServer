package br.com.aio.model.service;
//package br.com.aio.model.service;
//
//import java.net.URI;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.MultiValueMap;
//
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectReader;
//
//import br.com.aio.model.consumer.TicketGlpiConsumer;
//import br.com.aio.model.consumer.template.GlpiRestTemplate;
//import br.com.aio.model.entity.Ticket;
//import br.com.aio.model.entity.wrapper.TicketUserWrapper;
//import br.com.aio.model.entity.wrapper.TicketUserWrapperBuilder;
//
//@Repository
//public class TicketService {
//	
//	private TicketGlpiConsumer consumer;
//	private GlpiRestTemplate restTemplate;
//	
//	@Inject
//	public TicketService(TicketGlpiConsumer consumer, GlpiRestTemplate restTemplate) {
//		this.consumer = consumer;
//		this.restTemplate = restTemplate;
//	}
//	
//	public Ticket registrar(Ticket ticket) throws Exception{
//		MultiValueMap<String, Object> params = consumer.createTicket(ticket);
//		URI uri = consumer.glpiRootWebserviceUrl();
//		HttpEntity<Ticket> response = restTemplate.postForEntity(uri, params, Ticket.class);
//		return response.getBody();
//	}
//
//	public TicketUserWrapper listar(Integer idUsuarioGlpi) throws Exception {
//		URI uri = consumer.listTickets(idUsuarioGlpi);
//		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//
//		ObjectMapper mapper = new ObjectMapper();
//		JavaType list = mapper.getTypeFactory().constructCollectionType(List.class, Ticket.class);
//		ObjectReader reader = mapper.readerFor(list);
//		List<Ticket> tickets = reader.readValue(response.getBody());
//				
//		tickets.stream().forEach(item -> Collections.sort(item.getAccompaniment()));
//		
//		return new TicketUserWrapperBuilder().addTickets(tickets).addUser(idUsuarioGlpi).build();
//	}
//
//	public Ticket buscar(String id){
//		URI uri = consumer.getTicketWithThumbnails(id);
//		HttpEntity<Ticket> response = restTemplate.getForEntity(uri, Ticket.class);
//		return response.getBody();
//	}
//	
//	public static void main(String[] args) {
//		
//		List<Integer> numbers = Arrays.asList(3,5, 4, 2);
//	
//		numbers.stream().map(n -> new Item(n))
//		.sorted( Comparator.comparing(Item :: getValue).reversed() )
//		.forEach(System.out :: println);
//	}
//}
//
//class Item {
//	private Integer value;
//
//	public Item(Integer value) {
//		super();
//		this.value = value;
//	}
//
//	public Integer getValue() {
//		return value;
//	}
//
//	public void setValue(Integer value) {
//		this.value = value;
//	}
//
//	@Override
//	public String toString() {
//		return "Item [value=" + value + "]";
//	}
//}
