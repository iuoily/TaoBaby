package com.taobaby.common;

import com.taobaby.utils.SystemContent;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author iuoily on 2022/5/16.
 */

@WebServlet("/common/getImage")
public class ImgServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imagepath = req.getParameter("image");
        FileInputStream fileInputStream = new FileInputStream(SystemContent.UPLOAD_PATH + imagepath);
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.setContentType("image/JPEG");
        byte[] bytes = new byte[SystemContent.BYTE_SIZE];

        while (true) {
            int read = fileInputStream.read(bytes);
            if (read == -1) {
                break;
            }
            outputStream.write(bytes, 0, read);
        }

        outputStream.close();
        fileInputStream.close();

    }
}
