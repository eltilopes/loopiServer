package br.com.aio.model.service;
//package br.com.aio.model.service;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.MultiValueMap;
//
//import br.com.aio.model.consumer.TicketDocumentGlpiConsumer;
//import br.com.aio.model.consumer.template.GlpiRestTemplate;
//import br.com.aio.model.entity.Document;
//import br.com.aio.model.entity.Ticket;
//
//@Repository
//public class DocumentService {
//	
//	private TicketDocumentGlpiConsumer consumer;
//	
//	private GlpiRestTemplate glpiRestTemplate;
//	
//	@Inject
//	public DocumentService(TicketDocumentGlpiConsumer consumer, GlpiRestTemplate glpiRestTemplate){
//		this.consumer = consumer;
//		this.glpiRestTemplate = glpiRestTemplate;
//	}
//
//	//TODO: ajustar aqui
//	public List<Document> salvar(List<Document> documents, Ticket ticket) throws Exception {
////		MultiValueMap<String, String> params = consumer.addTicketDocument(documents, ticket);
////		URI uri = consumer.glpiRootWebserviceUrl();
////		ResponseEntity<Ticket> response = glpiRestTemplate.postForEntity(uri, params, Ticket.class);
////		return response.getBody().getDocuments();
//		Document d = new Document("name1", "base64");
//		d.setId("1");
//		d.setFilepath("filepath");;
//		d.setThumb("thumb");
//		List<Document> lista = new ArrayList<Document>();
//		lista.add(d);
//		return lista;
//	}
//	
//	
//	public InputStream buscarDocumento(String id){
//		URI uri = consumer.getDocument(id);
////		InputStream stream = new ByteArrayInputStream(glpiRestTemplate.getForObject(uri, byte[].class));
////		return stream;
//		return new InputStream() {
//			
//			@Override
//			public int read() throws IOException {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		};
//	}
//
//
//}
