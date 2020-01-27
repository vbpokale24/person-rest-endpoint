package com.org.resources.controller.security.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.org.resources.controller.security.interfaces.IGetJWTToken;
import com.org.service.model.JWTTokenModel;
import com.org.utils.JWTUtils;

@RestController
@RequestMapping(value = "/securitymanagement/v1")
public class GetJWTToken implements IGetJWTToken{

	@Value("${jwt.id}")
	private String jwtId;
	
	@Value("${jwt.issuer}")
	private String jwtIssuer;
	
	@Value("${jwt.subject}")
	private String jwtSubject;
	
	@Value("${jwt.ttl}")
	private String ttl;
	
	@Override 
	@RequestMapping( value = "/getJwtToken", method = RequestMethod.GET)
    public ResponseEntity<JWTTokenModel> getToken() {
		JWTTokenModel jwtTokenModel =  new JWTTokenModel();
		jwtTokenModel.setSubjectToken(JWTUtils.createJWT(jwtId, jwtIssuer, jwtSubject, Long.valueOf(ttl)));
		return new ResponseEntity<JWTTokenModel>(jwtTokenModel,HttpStatus.CREATED);
	}
}
