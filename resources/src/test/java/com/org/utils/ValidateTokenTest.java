package com.org.utils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.service.business.errorhandling.exception.BadRequestException;
import com.org.service.interfaces.IMessages;

@RunWith(SpringRunner.class)
public class ValidateTokenTest {

	@InjectMocks
	private ValidateToken validateToken;
	
	@Mock
	private IMessages messages;
	
	@Mock
	HttpServletRequest request;
	
	
	@Test
	public void testForBasicAuth(){
		when(request.getHeader(Mockito.anyString())).thenReturn("c2VjdXJpdHlBZG1pbg==");
		assertTrue(validateToken.judgeToken(request));
	}
	
	@Test(expected=BadRequestException.class)
	public void testForAuthorization(){
		validateToken.judgeToken(request);
	}
}
