package org.antoniotrentin.epidogsitting.entities.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	private String message;

	public Message(String m) {
		this.message = m;
	}
}
