package com.example.imbee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FcmMessageReq implements Serializable{
	String identifier;
	String type;
	String deviceId;
	String text;
}
