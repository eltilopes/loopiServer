package br.com.aio.model.validation;

import org.springframework.http.HttpStatus;

public enum RestErrorCode {
	
	GET,
	USER_EXISTS(10, "Usuário Existente"),
	COULD_NOT_AUTHENTICATE(11, "Usuário Existente"),
	CPF_USED(12, "CPF já usado"),
	FIELD_ERROR(20, "Um ou mais campos são inválidos"),
	
	NOT_AUTHORIZED(41, "Usuário não autenticado"),
	LOGIN_FAILURE(42, "Credenciais Inválidas"),
	ACCESS_DENIED(43, "Acesso negado"),
	NOT_FOUND(44, "Recurso não encontrado"),
	WRONG_TYPE(45, "Campo ou parâmetro com formato inválido"),
    METHOD_NOT_ALLOWED(46, "Método http não suportado"),
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
