package com.fashion.services;

import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.managers.ProductManager;
import com.fashion.model.Product;
import com.fashion.model.User;
import com.fashion.repositories.ProductRepository;
import com.fashion.repositories.UserRepository;

@RestController
@ComponentScan(basePackages = "com.fashion.managers")
public class MailService {

	@Autowired
	private ProductManager productManager;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/api/mail", method = RequestMethod.GET)
	private void getAllCategories(@RequestParam("rentee") String rentee, @RequestParam("id") String id) {
		User user = userRepository.findByEmail(rentee);
		if(user != null) {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			Product product = productManager.getProduct(id);
			if (product != null && product.getSize() != null && !product.getSize().isEmpty()) {
				for (Entry<String, Integer> entry : product.getSize().entrySet()) {
					Integer value = entry.getValue();
					entry.setValue(--value);
				}
				long oldPurchased = product.getPurchased();
				product.setPurchased(oldPurchased + 1);

				productRepository.save(product);
				//return productManager.getProduct(id);

				// creates a new session with an authenticator
				Authenticator auth = new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("FashionRent2016@gmail.com", "asdf123$00");
					}
				};

				Session session = Session.getInstance(properties, auth);

				// creates a new e-mail message
				Message msg = new MimeMessage(session);

				try {
					msg.setFrom(new InternetAddress("FashionRent2016@gmail.com"));
					InternetAddress[] toAddresses = { new InternetAddress(product.getSeller()) };
					msg.setRecipient(RecipientType.CC, new InternetAddress(rentee));
					msg.setRecipients(Message.RecipientType.TO, toAddresses);
					msg.setSubject("Product rent request");// Put Some Subject
					msg.setSentDate(new Date());
					msg.setText(user.getUsername() + " has showed interest in "+product.getName()+".\nPlease contact him on this number " + user.getPhone() + " if you are interested.");// Put
																																// Some
																																// Text.

					// sends the e-mail
					Transport.send(msg);
					System.out.println("Success");
				} catch (Exception e) {
					System.out.println("Failure");
				}
			}
			
			/*
			 * Product product = productManager.getProduct(id); if(product.getSize()
			 * != null && !product.getSize().isEmpty()) { for(Entry<String, Integer>
			 * entry : product.getSize().entrySet()) { Integer value =
			 * entry.getValue(); entry.setValue(--value); } } long oldPurchased =
			 * product.getPurchased(); product.setPurchased(oldPurchased + 1);
			 * 
			 * productRepository.save(product); return
			 * productManager.getProduct(id);
			 */
		}
		
		
	}
}
