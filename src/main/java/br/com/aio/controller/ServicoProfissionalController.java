package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.model.entity.vo.FiltroVo;
import br.com.aio.model.entity.vo.ServicoCardVo;
import br.com.aio.model.entity.vo.SolicitarPedidoVo;
import br.com.aio.model.service.ProfissionalService;

@RestController
@RequestMapping("/servico")
public class ServicoProfissionalController {

	@Inject
	private ProfissionalService profissionalService;
	
//	@RequestMapping(value = "/novo", method = POST)
//	public ResponseEntity<List<ServicoCardVo>> salvar(@Valid @RequestBody ServicoProfissional servicoProfissional){
//		try {
//			servicoProfissionalService.salvar(servicoProfissional);
//			List<ServicoCardVo> servicos = servicoProfissionalService.getServicos(servicoProfissional.getProfissional());
//			return new ResponseEntity<List<ServicoCardVo>>(servicos, HttpStatus.CREATED);
//		} catch (BusinessException e) {
//			throw new BusinessException(e.getMessage());
//		} 
//	}

	@RequestMapping(value = "/listar", method = POST)
	public ResponseEntity<List<ServicoCardVo>> getServicos(@Valid @RequestBody FiltroVo filtroVo){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(!authentication.getPrincipal().toString().equals(login)){
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		} 
		List<ServicoCardVo> servicos = profissionalService.getProfissionais(filtroVo);
		if(servicos.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ServicoCardVo>>(servicos, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/solicitar", method = POST)
	public ResponseEntity<SolicitarPedidoVo> solicitarServico(@Valid @RequestBody SolicitarPedidoVo solicitarPedidoVo){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(!authentication.getPrincipal().toString().equals(login)){
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		} 
		
		
		
		try {
			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=AIzaSyBcU9bk64c7cOig4us1WMlO_o_k7Ococ6o");

			conn.setDoOutput(true);

			String input = "{\"to\":\"APA91bHiokXS2nQXHJxGrrpg5-gSNakHndTXWcWCR7MBsgCCICdeIFMou6fcOwj-6Elah9M2VBsOXqa-8wXCIPdQWO3FlafqMGvqRVe8ZCtCVXBDMQHTaLc\",\"notification\":{\"title\":\"Servico Solicitado\",\"body\":\"Aceitar Servico?\",\"click_action\":\"br.com.aio.activity.AceitarServicoFirebaseActivity\"},\"data\":{\"titulo\":\"Servico Solicitado\",\"descricao\":\"Aceitar Servico?\",\"click_action\":\"br.com.aio.activity.AceitarServicoFirebaseActivity\"}}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			os.close();

			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + input);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
			    response.append(inputLine);
			}
			in.close();
			solicitarPedidoVo.setSolicitado(responseCode==200);
			// print result
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		
		return new ResponseEntity<SolicitarPedidoVo>(solicitarPedidoVo, HttpStatus.OK);
	}
}
