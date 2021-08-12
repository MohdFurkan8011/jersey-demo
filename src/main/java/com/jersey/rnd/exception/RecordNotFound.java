package com.jersey.rnd.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RecordNotFound extends RuntimeException implements ExceptionMapper<RecordNotFound> {


    /**
	 * 
	 */
	private static final long serialVersionUID = 364542129940502922L;

	public RecordNotFound() {
        super("Record not found by given detail.");
        System.out.println("in exception");
    }
 
    public RecordNotFound(String string) {
        super(string);
    }
 
    @Override
    public Response toResponse(RecordNotFound exception) {
    	System.out.println("....in exception");
        return Response.status(404).entity(exception.getMessage())
                                    .type("text/plain").build();
    }
	
}
