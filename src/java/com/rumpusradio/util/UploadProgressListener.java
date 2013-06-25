package com.rumpusradio.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.ProgressListener;
/*
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
*/
public class UploadProgressListener implements ProgressListener {
	private long bytesRead = 0;
    private long contentLength = 0;
    private boolean multipartFinished = false;

    public UploadProgressListener() {
    	
    }
    public UploadProgressListener(HttpServletRequest request) {
        request.getSession().setAttribute("ProgressListener_" + request.getParameter("id"), this);
    }

    @Override
    public void update(long bytesRead, long contentLength, int items) {
        this.bytesRead = bytesRead;
        this.contentLength = contentLength;
    }

    public void setMultipartFinished() {
        this.multipartFinished = true;
    }

    public boolean isFinished() {
        return multipartFinished;
    }

    public int getPercentDone() {
        if (contentLength == -1) {
            // ensure we never reach 100% but show progress
            return (int) Math.abs(bytesRead * 100.0 / (bytesRead + 10000));
        }
        return (int) Math.abs(bytesRead * 100.0 / contentLength);
    }
}
