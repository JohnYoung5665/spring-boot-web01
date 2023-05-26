package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagementApplicationTests {

	/**
	 * 生成JWT
	 */
	@Test
	public void testGenJwt() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id",1);
		claims.put("name","Tom");
		String jwt = Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, "matrix")//签名算法
				.setClaims(claims)    //自定义内容（载荷）
				.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))    //设置有效期为1h
				.compact();
		System.out.println(jwt);
	}

	/**
	 * 解析Jwt
	 */
	@Test
	public void testParseJwt(){
		Claims claims = Jwts.parser()
				.setSigningKey("matrix")
				.parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTY4NDkzNTY5Mn0.m4gd_H8iDRoxkosmU5KwLg22-i9Z_bn2co2ZpNY3Tzo")
				.getBody();
		System.out.println(claims);
	}

}
