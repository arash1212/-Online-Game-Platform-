package com.salehi.webservice.messaging.providers.email.gmail.service;

import com.salehi.datasource.relational.entity.messaging.MessagingProviderEntity;
import com.salehi.datasource.relational.enums.messaging.MessageProviderEnum;
import com.salehi.datasource.relational.enums.messaging.MessageTypeEnum;
import com.salehi.webservice.messaging.providers.MessageInput;
import com.salehi.webservice.messaging.providers.MessagingRepository;
import com.salehi.webservice.messaging.providers.email.gmail.dto.GmailInput;
import com.salehi.webservice.messaging.providers.interfaces.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class GmailMessageService implements IMessageService<GmailInput> {

    private final MessagingRepository messagingRepository;

    @Autowired
    public GmailMessageService(MessagingRepository messagingRepository) {
        this.messagingRepository = messagingRepository;
    }

    public JavaMailSender getMailSender() {
        MessagingProviderEntity providerEntity = this.getCredentials();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(providerEntity.getServiceUrl());
        mailSender.setUsername(providerEntity.getUsername());
        mailSender.setPassword(providerEntity.getPassword());
        mailSender.setPort(providerEntity.getPort());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    @Override
    public String sendEmail(MessageInput input) {
        JavaMailSender mailSender = this.getMailSender();
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("test-store@Support.com");
            helper.setTo(input.getTo().toArray(new String[0]));
            helper.setSubject(input.getSubject());
            helper.setText(input.getMessageBody());

            mailSender.send(mimeMessage);
            return "email sent";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public String sendSms(MessageInput input) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GmailInput getInput(MessageInput input) {
        return new GmailInput(input.getTo(), input.getSubject(), input.getMessageBody(), input.getAttachment());
    }

    @Override
    public boolean supports(MessageTypeEnum messageType) {
        return MessageTypeEnum.EMAIL.equals(messageType);
    }

    @Override
    public MessageProviderEnum getProviderType() {
        return MessageProviderEnum.GMAIL;
    }

    @Override
    public MessagingProviderEntity getCredentials() {
        return this.messagingRepository.getCredentialsByProvider(MessageProviderEnum.GMAIL);
    }
}
