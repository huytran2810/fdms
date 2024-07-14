package org.SWP391_FUHL_SE1825.FDMS.security;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Cloudinary cloudinaryConfig() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dk9j6rq01",
                "api_key", "481378768279841",
                "api_secret", "cdp41PCTXyl4nnpxFps200wolLk"));
    }

}
