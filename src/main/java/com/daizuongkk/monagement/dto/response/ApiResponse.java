package com.daizuongkk.monagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
	int code;
	Status status;
	String message;
	T data;

	public enum Status {
		SUCCESS, ERROR
	}
}
