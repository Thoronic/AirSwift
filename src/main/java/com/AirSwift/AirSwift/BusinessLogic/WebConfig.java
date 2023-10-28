@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:51515") // Replace with your frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
