package gbd.ecommerce.controllers;

import gbd.ecommerce.models.Product;
import gbd.ecommerce.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class RecuperarImagemController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "RecuperarImagem", method = RequestMethod.GET)
    public @ResponseBody
    byte[] exibirImagem(
            @RequestParam(value ="idProduct") int idProduct,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        response.setContentType("image/jpg");

        byte[] buffer = new byte[2048];
        int bytesread = 0;

        ServletOutputStream out = response.getOutputStream();
        InputStream is;

        Product product = productRepository.findById(idProduct).orElseThrow(Exception::new);

        if (product.getImage() == null){
            is = request.getSession().getServletContext().getResourceAsStream("/images/produtoSemFoto.png");
            while ((bytesread = is.read(buffer)) != -1)
                out.write(buffer, 0, bytesread);
        }
        else {
            is = new ByteArrayInputStream(product.getImage());
            while((bytesread = is.read(buffer)) != -1)
                out.write(buffer, 0, bytesread);
            out.flush();
        }
        out.close();
        is.close();

        return buffer;

    }
}
