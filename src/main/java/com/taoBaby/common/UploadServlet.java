                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 package com.taoBaby.common;

import com.taoBaby.utils.SystemContent;
import com.taoBaby.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author iuoily on 2022/5/17.
 */

@MultipartConfig
@WebServlet(value = "/common/upload", loadOnStartup = 0)
public class UploadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        String sub = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));
        String name = UUIDUtils.getId() + sub;
        InputStream inputStream = part.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(SystemContent.UPLOAD_PATH + name);
        byte[] bytes = new byte[SystemContent.BYTE_SIZE];

        while (true) {
            int read = inputStream.read(bytes);
            if (read == -1) {
                break;
            }
            fileOutputStream.write(bytes, 0, read);
        }

        fileOutputStream.close();
        inputStream.close();
        resp.getWriter().write(name);
    }
}
