/*package com.kd.tv.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kd.tv.handler.OTPHandler;

@Configuration
public class TVEndPointRouter {

	@Bean
	public RouterFunction<ServerResponse> route() {
		System.out.println("********* TVEndPointRouter.route()");
		OTPHandler otpHandler = new OTPHandler();
		return RouterFunctions
				.route(RequestPredicates.GET("/otp").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				otpHandler::sendOTP);
		
		return RouterFunctions
		.route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				request -> ServerResponse.ok().body(BodyInserters.fromObject("Hello World")));
		
		return RouterFunctions.route()
				.GET("/hello", accept(MediaType.TEXT_PLAIN),
					request -> Response.ok().body(fromObject("Hello World")));
	}

}*/
