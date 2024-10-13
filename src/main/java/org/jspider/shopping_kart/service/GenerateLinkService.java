package org.jspider.shopping_kart.service;

import org.jspider.shopping_kart.dao.MerchantDao;
import org.jspider.shopping_kart.dao.UserDao;
import org.jspider.shopping_kart.dto.Merchant;
import org.jspider.shopping_kart.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class GenerateLinkService {
	@Autowired
	private MerchantDao dao;
	
	@Autowired
	private UserDao userdao;
	
	public String getVerificationLink(HttpServletRequest request, Merchant merchant) {
		String token = RandomString.make(45);
		merchant.setToken(token);
		merchant.setStatus("InActive");
		dao.updateMerchant(merchant);
		
		String siteurl = request.getRequestURL().toString();
		String url = siteurl.replace(request.getServletPath(), "");
		String verify_link = url + "/merchant/verify?token=" + token;
		return verify_link;
	}
	
	public String getResetPassword(HttpServletRequest request, Merchant merchant) {
		String token = RandomString.make(45);
		merchant.setToken(token);
		dao.updateMerchant(merchant);
		
		String siteurl = request.getRequestURL().toString();
		String url = siteurl.replace(request.getServletPath(), "");
		String reset_link = url + "/merchant/reset-password?token=" + token;
		return reset_link;
	}
	
	public String getVerificationLinkUser(HttpServletRequest request, User user) {
		String token = RandomString.make(45);
		user.setToken(token);
		user.setStatus("InActive");
		userdao.updateUser(user);
		
		String siteurl = request.getRequestURL().toString();
		String url = siteurl.replace(request.getServletPath(), "");
		String verify_link = url + "/user/verify?token=" + token;
		return verify_link;
	}
	
	public String getResetPassword(HttpServletRequest request, User user) {
		String token = RandomString.make(45);
		user.setToken(token);
		userdao.updateUser(user);
		
		String siteurl = request.getRequestURL().toString();
		String url = siteurl.replace(request.getServletPath(), "");
		String reset_link = url + "/user/reset-password?token=" + token;
		return reset_link;
	}
	
	
}
