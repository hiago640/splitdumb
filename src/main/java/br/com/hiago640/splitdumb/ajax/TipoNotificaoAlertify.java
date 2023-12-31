package br.com.hiago640.splitdumb.ajax;

public enum TipoNotificaoAlertify {
	
	SUCESSO("success"),
	ERRO("error"),
	WARNING("warning"),
	MEMSAGEM("message");
	
	private String tipo;
	
	private TipoNotificaoAlertify(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}	
}