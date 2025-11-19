package com.project.common.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
	SUCCESS(200, "성공했습니다.", HttpStatus.OK),

	UNAUTHORIZED(401, "유효하지 않은 권한입니다.", HttpStatus.UNAUTHORIZED),
	AUTH_ERROR(403, "인가된 사용자가 아닙니다.", HttpStatus.FORBIDDEN),
	NOT_FOUND(404, "존재하지 않는 정보입니다.", HttpStatus.NOT_FOUND),

	EXPIRED_ACCESS_TOKEN(405, "토큰 유효 기간이 만료 되었습니다.", HttpStatus.UNAUTHORIZED),
	EXPIRED_REFRESH_TOKEN(406, "토큰 유효 기간이 만료 되었습니다..", HttpStatus.UNAUTHORIZED),
	UNSUPPORTED_JWT(407, "올바르지 않는 토큰 입니다.", HttpStatus.UNAUTHORIZED),
	EMPTY_JWT(408, "토큰정보가 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
	DUPLICATE_USER(409, "이미 존재하는 사용자입니다.", HttpStatus.UNAUTHORIZED),
	TOKEN_EXPIRED(401, "만료된 토큰입니다.", HttpStatus.UNAUTHORIZED),
	TOKEN_ERROR(401, "잘못된 토큰입니다.", HttpStatus.UNAUTHORIZED),

	INTERNAL_SERVER_ERROR(500, "내부 오류가 발생했습니다. 확인 후 다시 시도해주세요.", HttpStatus.INTERNAL_SERVER_ERROR),
	INVALID_REQUEST(501, "유효하지 않은 요청입니다.", HttpStatus.BAD_REQUEST),
	FORBIDDEN_REQUEST(502, "허용되지 않은 요청입니다.", HttpStatus.FORBIDDEN),
	DUPLICATED_REQUEST(503, "중복된 요청입니다.", HttpStatus.BAD_REQUEST),
	HTTP_INTERFACE_API_ERROR(504, "외부 API 호출 오류 입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
	SAVE_ERROR(505, "저장시 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
	DELETE_ERROR(506, "삭제 중 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

	// Exception Handler 에러 코드
	INPUT_CHECK_ERROR(600, "입력값 무결성 오류 입니다.", HttpStatus.BAD_REQUEST),
	METHOD_ARGUMENT_NOT_VALID(601, "파라미터가 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
	MISSING_SERVLET_REQUEST_PARAMETER(602, "필수 파라미터가 누락되었습니다.", HttpStatus.BAD_REQUEST),
	CONSTRAINT_VIOLATION(603, "파라미터 유효성 검사에 실패했습니다.", HttpStatus.BAD_REQUEST),
	METHOD_ARGUMENT_TYPE_MISMATCH(604, "파라미터 타입이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
	NO_HANDLER_FOUND(605, "요청한 URL을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
	HTTP_REQUEST_METHOD_NOT_SUPPORTED(606, "지원하지 않는 메서드입니다.", HttpStatus.BAD_REQUEST),
	HTTP_MEDIA_TYPE_NOT_SUPPORTED(607, "지원되지 않는 미디어 타입입니다.", HttpStatus.BAD_REQUEST),
	HTTP_MESSAGE_NOT_READABLE_EXCEPTION(608, "읽을 수 있는 요청 정보가 없습니다.", HttpStatus.BAD_REQUEST);

	private final int code;
	private String message;
	private final HttpStatus status;
}
