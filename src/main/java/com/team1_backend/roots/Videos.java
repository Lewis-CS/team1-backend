package com.team1_backend.roots;

import java.io.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.*;


/**
 * Root resource (exposed at "videos" path)
 */
@Path("videos")
public class Videos{
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String post(@FormDataParam("video")InputStream uploaded,
    					@FormDataParam("video")FormDataContentDisposition videoMetaData) throws Exception {
    	String UPLOAD_PATH = "C:/temp/";
    	try
    	{
    		int read = 0;
    		byte[] bytes = new byte[1024];
    		OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + videoMetaData.getFileName()));
            while ((read = uploaded.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e)
        {
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
        return ("Data uploaded successfully !!");
    }
}

