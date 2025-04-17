package com.example.MokshaMarg.util;

public class MailBody {
	
	public static String resetPasswordBody(String userName, String otp) {
		return "<!DOCTYPE html>\r\n"
		   		+ "<html>\r\n"
		   		+ "<head>\r\n"
		   		+ "  <meta charset=\"UTF-8\">\r\n"
		   		+ "  <title>Your Verification Code</title>\r\n"
		   		+ "</head>\r\n"
		   		+ "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\r\n"
		   		+ "  <div style=\"max-width: 600px; margin: auto; background-color: white; padding: 30px; border-radius: 8px;\">\r\n"
		   		+ "    <h2 style=\"color: #2e7d32;\">Hello <span th:text=\"${userName}\">"+userName+""+"</span>,</h2>\r\n"
		   		+ "    \r\n"
		   		+ "    <p>Your One-Time Password (OTP) is:</p>\r\n"
		   		+ "\r\n"
		   		+ "    <div style=\"font-size: 28px; font-weight: bold; background: #eeeeee; padding: 10px; width: fit-content;\">\r\n"
		   		+ "      <span th:text=\"${otp}\">"+otp+""+"</span>\r\n"
		   		+ "    </div>\r\n"
		   		+ "\r\n"
		   		+ "    <p>This code will expire in <strong>2 minutes</strong>. Please do not share it with anyone.</p>\r\n"
		   		+ "\r\n"
		   		+ "    <p>If you didn’t request this, you can safely ignore this email.</p>\r\n"
		   		+ "\r\n"
		   		+ "    <p>Thanks,<br><strong>Moksha Marg Team</strong></p>\r\n"
		   		+ "  </div>\r\n"
		   		+ "</body>\r\n"
		   		+ "</html>\r\n"
		   		+ "";
	}
}
