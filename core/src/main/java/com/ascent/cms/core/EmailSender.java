package com.ascent.cms.core;

import java.util.HashMap;
import java.util.Map;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
public class EmailSender
{

	private static final String FROM_EMAIL = "admin@marspls.com";

	private static final String MARS_HOME_URL = "http://marspls.com";

	private static final String UTF_8 = "utf-8";

	private static final String REGISTRATION_TEMPLATE = "email-templates/registration.vm";

	private static final String FORGOT_PASSWORD_TEMPLATE = "email-templates/forgot-password.vm";

	private static final String VISIT_TEMPLATE = "email-templates/visit-details.vm";

	private static final String DIAGNOSIS_TEMPLATE = "email-templates/diagnosis-details.vm";

	private static final String MEDICINE_TEMPLATE = "email-templates/medicine-details.vm";

	private static final String VISIT_FILE = "Visit Details Report.pdf";

	private static final String DIAGNOSIS_FILE = "Diagnosis Prescription Report.pdf";

	private static final String MEDICINE_FILE = "Medicine Prescription Report.pdf";

	private static final String VISIT_REPORT = "Visit Details Report";

	private static final String DIAGNOSIS_REPORT = "Diagnosis Prescription Report";

	private static final String MEDICINE_REPORT = "Medicine Prescription Report";

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	public void sendRegistrationEmail(String toEmail, String password)
	{
		EmailMessage emailMessage = new EmailMessage();

		emailMessage.setFromEmail(FROM_EMAIL);
		emailMessage.setSubject("Confirm Registration to MARS");
		emailMessage.setTemplateName(REGISTRATION_TEMPLATE);
		emailMessage.setToEmail(toEmail);

		emailMessage.addModelValue("marsHome", MARS_HOME_URL);
		emailMessage.addModelValue("userName", toEmail);
		emailMessage.addModelValue("password", password);

		try
		{
			sendMail(emailMessage);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void sendResetPasswordRequestedEmail(final String toEmail, final String token)
	{
		EmailMessage emailMessage = new EmailMessage();

		emailMessage.setFromEmail(FROM_EMAIL);
		emailMessage.setSubject("Password reset instructions on MARS");
		emailMessage.setTemplateName(FORGOT_PASSWORD_TEMPLATE);
		emailMessage.setToEmail(toEmail);

		emailMessage.addModelValue("token", token);
		emailMessage.addModelValue("marsHome", MARS_HOME_URL);
		emailMessage.addModelValue("resetPasswordLink", MARS_HOME_URL + "/passwordreset");

		try
		{
			sendMail(emailMessage);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void sendResetPasswordSuccessEmail(EmailMessage emailMessage)
	{

	}

	public void sendVisitPdfEmail(String toEmail, String filePath)
	{

		EmailMessage emailMessage = new EmailMessage();

		emailMessage.setFromEmail(FROM_EMAIL);
		emailMessage.setSubject(VISIT_REPORT);
		emailMessage.setTemplateName(VISIT_TEMPLATE);
		emailMessage.setToEmail(toEmail);
		emailMessage.setFileName(VISIT_FILE);
		emailMessage.setFilePath(filePath);

		try
		{
			sendMailWithAttachment(emailMessage);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void sendDiagnosisPrescriptionPdfEmail(String toEmail, String filePath)
	{

		EmailMessage emailMessage = new EmailMessage();

		emailMessage.setFromEmail(FROM_EMAIL);
		emailMessage.setSubject(DIAGNOSIS_REPORT);
		emailMessage.setTemplateName(DIAGNOSIS_TEMPLATE);
		emailMessage.setToEmail(toEmail);
		emailMessage.setFileName(DIAGNOSIS_FILE);
		emailMessage.setFilePath(filePath);

		try
		{
			sendMailWithAttachment(emailMessage);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void sendMedicinePrescriptionPdfEmail(String toEmail, String filePath)
	{

		EmailMessage emailMessage = new EmailMessage();

		emailMessage.setFromEmail(FROM_EMAIL);
		emailMessage.setSubject(MEDICINE_REPORT);
		emailMessage.setTemplateName(MEDICINE_TEMPLATE);
		emailMessage.setToEmail(toEmail);
		emailMessage.setFileName(MEDICINE_FILE);
		emailMessage.setFilePath(filePath);

		try
		{
			sendMailWithAttachment(emailMessage);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void sendMailWithAttachment(EmailMessage emailMessage) throws Exception
	{

		String text = resolveEmailText(emailMessage.getModel(), emailMessage.getTemplateName());
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		preparMimeMessageWithAttachment(emailMessage.getToEmail(), emailMessage.getFromEmail(),
				emailMessage.getSubject(), text, emailMessage.getFileName(), emailMessage.getFilePath(), mimeMessage);

		mailSender.send(mimeMessage);
	}

	protected void preparMimeMessageWithAttachment(final String mailTo, final String mailFrom, final String subject,
			final String text, final String fileName, final String filePath, final MimeMessage mimeMessage)
			throws Exception
	{
		MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare(MimeMessage mimeMessage) throws Exception
			{
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
				message.setTo(mailTo);
				message.setReplyTo(mailFrom);
				message.setFrom(mailFrom);
				message.setSubject(subject);
				message.setText(text, true);
				DataSource source = new FileDataSource(filePath);
				message.addAttachment(fileName, source);

			}
		};
		preparator.prepare(mimeMessage);
	}

	protected String resolveEmailText(final Map<String, Object> model, String templateName)
	{
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, UTF_8, model);
		return text;
	}

	public void sendMail(EmailMessage emailMessage) throws Exception
	{

		String text = resolveEmailText(emailMessage.getModel(), emailMessage.getTemplateName());

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		preparMimeMessage(emailMessage.getToEmail(), emailMessage.getFromEmail(), emailMessage.getSubject(), text,
				mimeMessage);

		mailSender.send(mimeMessage);
	}

	protected void preparMimeMessage(final String mailTo, final String mailFrom, final String subject,
			final String text, final MimeMessage mimeMessage) throws Exception
	{
		MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare(MimeMessage mimeMessage) throws Exception
			{
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(mailTo);
				message.setReplyTo(mailFrom);
				message.setFrom(mailFrom);
				message.setSubject(subject);
				message.setText(text, true);

			}
		};
		preparator.prepare(mimeMessage);
	}

	public class EmailMessage
	{

		private String fromEmail;

		private String toEmail;

		private String subject;

		private String body;

		private String templateName;

		private String fileName;

		private String filePath;

		private Map<String, Object> model;

		public String getFilePath()
		{
			return filePath;
		}

		public void setFilePath(String filePath)
		{
			this.filePath = filePath;
		}

		public String getFileName()
		{
			return fileName;
		}

		public void setFileName(String fileName)
		{
			this.fileName = fileName;
		}

		public String getFromEmail()
		{
			return fromEmail;
		}

		public void setFromEmail(String fromEmail)
		{
			this.fromEmail = fromEmail;
		}

		public String getToEmail()
		{
			return toEmail;
		}

		public void setToEmail(String toEmail)
		{
			this.toEmail = toEmail;
		}

		public String getSubject()
		{
			return subject;
		}

		public void setSubject(String subject)
		{
			this.subject = subject;
		}

		public String getBody()
		{
			return body;
		}

		public void setBody(String body)
		{
			this.body = body;
		}

		public String getTemplateName()
		{
			return templateName;
		}

		public void setTemplateName(String templateName)
		{
			this.templateName = templateName;
		}

		public Map<String, Object> getModel()
		{
			return model;
		}

		public void setModel(Map<String, Object> model)
		{
			this.model = model;
		}

		public void addModelValue(String key, Object value)
		{
			if (this.model == null)
			{
				this.model = new HashMap<String, Object>();
			}
			this.model.put(key, value);
		}
	}
}
