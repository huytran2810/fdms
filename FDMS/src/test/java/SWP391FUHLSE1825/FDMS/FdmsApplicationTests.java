package SWP391FUHLSE1825.FDMS;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootConfiguration
@SpringBootTest
 class FdmsApplicationTests {

	@Test
	void hash() throws NoSuchAlgorithmException {
		String password = "123456";

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte[] digest = md.digest();
		String md5Hash = DatatypeConverter.printHexBinary(digest);

		log.info("MD5 round 1: {}", md5Hash);

		md.update(password.getBytes());
		digest = md.digest();
		md5Hash = DatatypeConverter.printHexBinary(digest);

		log.info("MD5 round 2: {}", md5Hash);
	}

}
