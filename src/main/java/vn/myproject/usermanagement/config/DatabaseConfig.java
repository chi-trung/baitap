package vn.myproject.usermanagement.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Cấu hình cơ sở dữ liệu
 * 
 * @author chi-trung
 * @version 1.0
 */
@Configuration
public class DatabaseConfig {

    /**
     * Cấu hình DataSource cho môi trường phát triển
     * 
     * @return DataSource H2 in-memory
     */
    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .build();
    }
    
    /**
     * Cấu hình DataSource mặc định (sử dụng H2 in-memory)
     * 
     * @return DataSource H2 in-memory
     */
    @Bean
    @Profile("default")
    public DataSource defaultDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .build();
    }
}
