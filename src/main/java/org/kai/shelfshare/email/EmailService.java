package org.kai.shelfshare.email;
/*
 * @created 25/06/2024 - 12:25
 * @project Shelf Share
 * @author  Suswagatam Rong
 */

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.*;
import static org.springframework.mail.javamail.MimeMessageHelper.*;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;

	// Sends email asynchronously so that user doesn't wait
	@Async
	public void sendEmail(
			String to,
			String username,
			EmailTemplateName emailTemplate,
			String confirmationUrl,
			String activationCode,
			String subject
	) throws MessagingException {
		String templateName;

		if(emailTemplate == null) {
			templateName = "confirm-email";
		} else {
			templateName = emailTemplate.name();
		}

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
				mimeMessage,
				MULTIPART_MODE_MIXED,
				UTF_8.name()
		);

		Map<String, Object> properties = new HashMap<>();
		properties.put("username", username);
		properties.put("confirmationUrl", confirmationUrl);
		properties.put("activationCode", activationCode);

		Context context = new Context();
		context.setVariables(properties);


		mimeMessageHelper.setFrom("suswagatam_rong@protonmail.com");
		mimeMessageHelper.setTo(to);
		mimeMessageHelper.setSubject(subject);

		String template = templateEngine.process(templateName, context);
		mimeMessageHelper.setText(template, true);

		mailSender.send(mimeMessage);
	}
}