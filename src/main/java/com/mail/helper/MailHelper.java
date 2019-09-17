package com.mail.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * MailHelper.
 * 
 * @author Automation Team
 */
public class MailHelper {

	/**
	 * constructor.
	 */
	private MailHelper() {
		// constructor.
	}

	/**
	 * logger variable.
	 */
	private static final Logger LOGGER = Logger.getLogger(MailHelper.class.getName());

	/**
	 * send mail.
	 * 
	 * @param composedMail
	 *            composedMail
	 */
	public static void sendMail(final MailComposer composedMail) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put("mail.smtp.auth", composedMail.getBolAuth());
		props.put("mail.smtp.host", composedMail.getsHost());
		props.put("mail.smtp.port", composedMail.getsPort());

		Session session = Session.getInstance(props);

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(composedMail.getsFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(composedMail.getsTo()));
			message.setSubject(composedMail.getsSubject());
			// message.setText(composedMail.getsBody());

			// ///////////////////////////////////////////////

			// creates message part
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(composedMail.getsBody(), "text/html");

			// creates multi-part
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// adds attachments

			String filePath = composedMail.getsAttachmentPath();
			if (!filePath.isEmpty()) {
				MimeBodyPart attachPart = new MimeBodyPart();
				try {
					attachPart.attachFile(filePath);
					// attachPart.setText(text);
					// attachPart.setFileName("test");
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}

			String fileContents = composedMail.getsAttachmentText();
			if (!fileContents.isEmpty()) {
				String fileName = composedMail.getsAttachmentTextName();
				if (fileName.isEmpty()) {
					System.out.println("File nam eis empty inside mail helper");
					fileName = "Report_" + getTs() + ".html";
				}
				MimeBodyPart attachPart = new MimeBodyPart();
				attachPart.setText(fileContents);
				attachPart.setFileName(fileName);
				multipart.addBodyPart(attachPart);
			}

			// sets the multi-part as e-mail's content
			message.setContent(multipart);
			// /////////////////////////////////////////
			// message.setContent(composedMail.getsBody(), "text/html");

			Transport.send(message);
			System.out.println("Sent email from Mail Helper");
			LOGGER.log(Level.INFO, "Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * getTs.
	 * 
	 * @return String String
	 */
	public static String getTs() {
		java.util.Date date = new java.util.Date();
		String ts = date.toString();
		ts = ts.replace(" ", "");
		ts = ts.replace(":", "");
		return ts;
	}

	/**
	 * send Mail.
	 * 
	 * @param reportName
	 *            reportName
	 * @param attachmentPath
	 *            attachmentPath
	 * @param attachmentText
	 *            attachmentText
	 * @param attachmentName
	 *            attachmentName
	 * @param env
	 *            env
	 */
	public static void send(final String reportName, final String attachmentPath, final String attachmentText,
			final String attachmentName) {
		
		String smtpHost = null;
		String smtpPort = null;
		String fromEmailID = null;
		String mailSubj = null;		
		String toMailIds = null;
		
		MailComposer composedMail = new MailComposer();
		composedMail.setBolAuth(false);
		
		LOGGER.log(Level.INFO, "Inside send mail");
		boolean configReader = false;
		try {
			MailConfigReader.readMailConfigValue();
			configReader = true;
		} catch (IOException e1) {
			System.out.println("Error while reading the config property reader class");
			configReader = false;
		}
					/*smtpHost =  "ATLSMTPGW.TURNER.COM";
					smtpPort = "25";
					toMailIds = "Chittupriya.Nallamuthu@turner.com,Angel.Benjamin@turner.com";
					fromEmailID = "Automation_Team <automation@nbamobileapp.com>";
					mailSubj = "NBA Automation Status Email";*/
		toMailIds = MailConfigReader.getPropertyValue("ToMail").replace(";", ",");
		smtpHost = MailConfigReader.getPropertyValue("SMTPHost");
		System.out.println("smtpHost "+smtpHost);
		smtpPort = MailConfigReader.getPropertyValue("SMTPPort");
		fromEmailID = MailConfigReader.getPropertyValue("FromMail");
		
		composedMail.setsHost(smtpHost);
		composedMail.setsPort(smtpPort);
		composedMail.setsTo(toMailIds);
		composedMail.setsFrom(fromEmailID);
		// get date and time
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		LOGGER.log(Level.INFO, " Today's Date " + dateFormat.format(date));
		
		mailSubj = MailConfigReader.getPropertyValue("MailSubj");
		composedMail.setsSubject(mailSubj + " On " + dateFormat.format(date));
		composedMail.setsAttachmentPath(attachmentPath);
		composedMail.setsAttachmentText(attachmentText);
		composedMail.setsAttachmentTextName(attachmentName);
		String mailContent = reportName;
		composedMail.setsBody(mailContent);
		LOGGER.log(Level.INFO, "Sending Mail");
		boolean exceptionThrown = true;
		int i = 0;
		while (exceptionThrown && i < 15) {
			try {
				System.out.println("Going to send email");
				sendMail(composedMail);
				LOGGER.log(Level.INFO, "Sent Mail");
				exceptionThrown = false;
			} catch (Exception e) {
				LOGGER.log(Level.INFO, "Failed to send email count = " + (i + 1));
				exceptionThrown = true;
				if (i == 10) {
					System.out.println("Failed to send email after 25 unsuccessful attempts due to exception  " + e.getMessage());
				}
				i++;
			}
		}
	
	}
	
	  public static void main(String[] args) 
	{
		//String reportPath = "./custom_report/nba_report.html";
		String reportPath = null;
		try {
			reportPath = new String(Files.readAllBytes(Paths.get("./reportLocation.txt")));
			System.out.println("readLoc "+reportPath);
			send("NBA Smoke Test Report", reportPath, "","");
		} catch (IOException e) {
			System.out.println("Exception occured while reading the path from Report Location File to send email *** "+e.getMessage());
		}
		
		
	// once file is moved successfully , copy the file from custom report to Reports_Backup folder location
	//	copyReports(reportPath);
		
	}

	public static void copyReports(String reportPath) {
		
		//String filename = reportPath;
		File oldFile = new File("./custom_report/nba_report.html");
		
		if(oldFile.exists())
		{
			String dateTime = new SimpleDateFormat("yyyyMMdd-hhmm").format(new Date());
			System.out.println("dateTime "+dateTime);
			String newReportName = "nba_report_"+dateTime + ".html";
			File file2 = new File("./Reports_Backup/" + newReportName);
			
			if(oldFile.renameTo(file2))
			{
				System.out.println("File is renamed to the copied location");
			} else {
				System.out.println("Unable to rename the file and copy to the location");
			}
		} else {
			System.out.println("Old file doesnt exists.");
		}
		
	}
}
