package com.Project.Project.DTO;

public class CustomResponseDTO {

	private Integer status_Code;
	private String message;
	public Integer getStatus_Code() {
		return status_Code;
	}
	public void setStatus_Code(Integer status_Code) {
		this.status_Code = status_Code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CustomResponseDTO [status_Code=" + status_Code + ", message=" + message + "]";
	}
	
}
