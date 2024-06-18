package com.app.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Session;

import com.app.exeption.LoginException;
import com.app.model.Column;
import com.app.model.Table;
import com.app.util.GoogleUtilApi;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.inject.Inject;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class EmailService {
    Gmail service ;

    @Inject
    public EmailService(GoogleUtilApi api) throws IOException,GeneralSecurityException,LoginException{
        this.service = api.getGmailService();
    }
    
    public List<String> compile(Table destTable,String dest,List<Table> tables,String message)throws IOException,TemplateException{
        
        Map<String,Object> parms = new HashMap<>();
        
        



        // Replace all matches with the desired format
        Configuration freeMarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        freeMarkerConfig.setDefaultEncoding("UTF-8");
        freeMarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        
        // Build the final list of results
        List<String> results = new ArrayList<>();
        for(Object destination :destTable.getColumn(dest).getData()){
            int index = destTable.getColumn(dest).getData().indexOf(destination);
            for(Table table :tables){
                Map<String ,Object > Columns = new HashMap<>();
                for(Column column:table.getData()){
                    Columns.put(column.getName(),column.getData().get(index));
                }
                parms.put(table.getName(),Columns);
            }

            
            parms.put("index",index);
            String templateContent = message;
            stringLoader.putTemplate("email", templateContent);
            freeMarkerConfig.setTemplateLoader(stringLoader);
            Template template = freeMarkerConfig.getTemplate("email");
            StringWriter writer = new StringWriter();
            template.process(parms, writer);
            results.add(writer.toString());
            

        }

        return results;

    }
    public void sendEmail(Table destTable,String dest,List<Table> tables,String subject,String message )throws IOException,MessagingException,TemplateException {
        List<Object> destinations = destTable.getColumn(dest).getData(); 
        List<String> messages = this.compile(destTable,dest,tables, message);
        for(Object destination :destinations){
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress("me"));
            email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(destination.toString()));
            email.setSubject(subject);
            email.setText(messages.get(destinations.indexOf(destination)));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message messageEmail = new Message();
            messageEmail.setRaw(encodedEmail);
            this.service.users().messages().send("me", messageEmail).execute();


        }
    }
} 
