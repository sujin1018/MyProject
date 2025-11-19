package com.project.book.common.response;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ResponseData<T> {
	private final int status;
	private final String message;
	private final ResponseCode code;
	private T data;

	// response data가 없을때
	public static ResponseEntity<ResponseData> toResponseEntity(ResponseCode responseCode) {
		return ResponseEntity
			.status(responseCode.getStatus())
			.body(ResponseData.builder()
				.message(responseCode.getMessage())
				.code(responseCode)
				.build()
			);
	}

	// response data가 있을때
	public static <T> ResponseEntity<ResponseData<T>> toResponseEntity(ResponseCode responseCode, T data) {
		return ResponseEntity
			.status(responseCode.getStatus())
			.body(ResponseData.<T>builder()
				.message(responseCode.getMessage())
				.code(responseCode)
				.data(data)
				.build()
			);
	}

	// response data와 header가 있을때
	public static <T> ResponseEntity<ResponseData<T>> toResponseEntity(ResponseCode responseCode, MultiValueMap<String, String> header, T data) {
		return ResponseEntity
			.status(responseCode.getStatus())
			.header(String.valueOf(header))
			.body(ResponseData.<T>builder()
				.message(responseCode.getMessage())
				.code(responseCode)
				.data(data)
				.build()
			);
	}
}
