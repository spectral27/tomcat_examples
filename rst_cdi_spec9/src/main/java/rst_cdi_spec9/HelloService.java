package rst_cdi_spec9;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class HelloService {
	
	public MessageModel getMessageModel() {
		MessageModel messageModel = new MessageModel();
		messageModel.setMessage("Hello!");
		return messageModel;
	}

}
