package br.com.giulianabezerra.picpaydesafiobackend.authotization;

public record Authorization (
		String  message
) {
	public boolean isAuthorized() {
		return message.equals("Autorizado");
	}

}
