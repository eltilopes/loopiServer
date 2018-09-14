package br.com.aio.model.validation;

import org.springframework.http.HttpStatus;

public enum RestErrorCode {
	
	GET,
	USER_EXISTS(10, "Usu�rio Existente"),
	COULD_NOT_AUTHENTICATE(11, "Usu�rio Existente"),
	CPF_USED(12, "CPF j� usado"),
	FIELD_ERROR(20, "Um ou mais campos s�o inv�lidos"),
	
	NOT_AUTHORIZED(41, "Usu�rio nao autenticado"),
	LOGIN_FAILURE(42, "Credenciais Inv�lidas"),
	ACCESS_DENIED(43, "Acesso negado"),
	NOT_FOUND(44, "Recurso n�o encontrado"),
	WRONG_TYPE(45, "Campo ou par�metro com formato inv�lido"),
    METHOD_NOT_ALLOWED(46, "M�todo http n�o suportado"),
    CLIENT_REGISTRATION(47, "Cliente inexistente"),
	
	INTERNAL_ERROR(50, "Ocorreu um erro interno no servidor"),
	
	BUSSINESS_FAILURE(60, "");
	
	
	private final int value;
	private final String errorMessage;
	
	private RestErrorCode(int value, String errorMessage){
		this.value = value;
		this.errorMessage = errorMessage;
	}
	
	private RestErrorCode(){
		value = this.getValue();
		errorMessage= this.getErrorMessage();
	}

	public int getValue() {
		return value;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	public RestErrorCode getByHttpStatus(HttpStatus status){
		if(status.equals(HttpStatus.UNAUTHORIZED)){
			return RestErrorCode.NOT_AUTHORIZED;
		}
		if(status.equals(HttpStatus.FORBIDDEN)){
			return RestErrorCode.ACCESS_DENIED;
		}
		if(status.equals(HttpStatus.NOT_FOUND)){
			return RestErrorCode.NOT_FOUND;
		}
		if(status.equals(HttpStatus.NOT_FOUND)){
			return RestErrorCode.NOT_FOUND;
		}
		return RestErrorCode.INTERNAL_ERROR;
	}

}
