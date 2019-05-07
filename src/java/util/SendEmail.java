/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author GAS-Diebold
 */
public class SendEmail {

    
    public boolean sendEmail(String titulo, String emailTo, String corpo) {
        try {
            SimpleEmail email = new SimpleEmail();
            //email.setHostName("smtp.nginformatica.com.br");
            email.setHostName("email-ssl.com.br");
            email.setSmtpPort(465);            
            
            //email.setAuthentication("vinicius.oliveira86@academico.ifs.edu.br", "2127990536");
            
            email.setAuthentication("contato@nginformatica.com.br", "m@r@6272@xpto2018");
            email.setSSLOnConnect(true);
            
            //email.setSSL(true);
            
            email.setFrom("contato@nginformatica.com.br");
            email.setSubject(titulo);
            email.setMsg(corpo);
            email.addTo(emailTo);
            email.send();
            return true;
        } catch (EmailException exception) {
            System.out.println(exception.toString());
            return false;
        }
    }

}
