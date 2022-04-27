package com.vms.orderapi.facades;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderFacade {

	String outChannel="order-out";
	@Output(value = outChannel)
	MessageChannel outputChannel();
}
