package ip.swagger.petstore.config;

import io.swagger.oas.inflector.models.RequestContext;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.*;

public class TestRequestContext extends RequestContext {
    private final MultivaluedMap<String, String> headers = new MultivaluedHashMap<>();
    private final List<MediaType> acceptableMediaTypes = new ArrayList<>();
    private String path;


    public TestRequestContext(String method, String acceptHeader,String path) {
        headers.put("Accept", Collections.singletonList(acceptHeader));
        acceptableMediaTypes.add(MediaType.valueOf(acceptHeader));
        this.path = path;
    }



    @Override
    public List<MediaType> getAcceptableMediaTypes() {
        return acceptableMediaTypes;
    }
    @Override
    public MultivaluedMap<String, String> getHeaders() {
        return headers;
    }

    public String getMethod() {
        return "POST";
    }
    public void setPath(String path) {
        this.path = path;
    }
}

