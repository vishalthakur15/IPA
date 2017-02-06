/*
 * package is com.epam.login.controllers.
 */
package com.epam.login.controllers;/*
									
									/*
									* These are all imports used.
									*/

/**
 * package includes class LoginController.

 */
//imports login controllers 
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.apache.hadoop.mapred.gethistory_jsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.login.models.Login;
import com.epam.login.models.PanelAvailability;
import com.epam.login.service.LoginService;
import com.epam.login.vto.CompetencyVTO;
import com.epam.login.vto.LoginVTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

//imports packages
/**
 * class that represents a controller. LoginController
 * 
 * @return this is a class
 */
@Controller
@RestController
@RequestMapping("/login")
public class LoginController {
	/*
	 * This is a public class LoginController
	 */

	@Autowired
	private LoginService loginService;
	private String userName;

	/**
	 * getLogin.
	 * 
	 * @return save the value in object
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public final String getLogin() {
		/*
		 * This is getLogin
		 */
		return "hi";

	}

	/**
	 * @param login
	 *            login
	 * @return return
	 * @throws Exception
	 *             exception
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public LoginVTO getLogin(@RequestBody final LoginVTO login) throws Exception {
		LoginVTO loginUser = loginService.validate(login.getUserName(), login.getEncryptedKey());

		if (loginUser == null) {
			System.out.println("null");
			throw new Exception("Username or password is invalid.");
		}

		return loginUser;

	}

	/**
	 * ResponseStatus.
	 */
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Username or password is invalid.")
	@ExceptionHandler(Exception.class)
	public void exceptionHandler() {
	}

	/**
	 * updatePassword.
	 * 
	 * @param login
	 *            the login
	 * @return save the value in object
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
	public final String updatePassword(@RequestBody final Login login) {
		/*
		 * This is a updatePassword
		 */
		return loginService.updatePassword(login.getUserName(), login.getEncryptedKey());

	}

	//
	/*
	 * resetPassword.
	 * 
	 * @param login the login
	 * 
	 * @param request the request
	 * 
	 * @return save the value in object
	 * 
	 * @throws MessagingException
	 */
	/**
	 * reset password.
	 * 
	 * @param request
	 *            request
	 * @param login
	 *            login
	 * @return return
	 * @throws MessagingException
	 *             MessagingException
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST, consumes = "application/json")
	public final String resetPassword(final HttpServletRequest request, @RequestBody final Login login)
			throws MessagingException {

		Calendar cal = Calendar.getInstance();
		Login user = loginService.findUserByUsername(login.getUserName());
		String msg;
		// return login service by Username
		if (user == null) {
			msg = "{\"message\":\"Invalid User\"}";

			// returns Invalid user
		} else {
			if (user.getExpiryDate() != null && (user.getExpiryDate().getTime() - cal.getTime().getTime()) >= 0) {
				msg = "{\"message\":\"You cannot reset Password\"}";

				// returns You cannot reset Password message
			} else {
				String email = user.getContact().getEmailId();

				if (email == null) {
					msg = "{\"message\":\"wrong username\"}";

					// returns wrong username message
				} else {
					System.out.println("In else");
					String token = UUID.randomUUID().toString();
					loginService.createPasswordResetTokenForUser(user, token);
					String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
							+ request.getContextPath();
					loginService.constructResetTokenEmail(appUrl, request.getLocale(), token, user, email);
					System.out.println(appUrl);
					msg = "{\"message\":\"done!\"}";
				}
			}
		}
		return msg;
		// return message;

	}

	/**
	 * showPasswordChange. It getToken It getEncryptedKey It getToken
	 * 
	 * @param login
	 *            the login
	 * @return save the value in object
	 */

	@RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
	public final String showChangePasswordPage(@RequestBody final Login login) {
		// System.out.println("GetToken"+login.getToken());
		/*
		 * showChangePasswordPage method
		 */
		// print username
		// print EncryptedKey
		// print Token
		String msg = null;
		String result = "";
		Login passToken = loginService.findUserByUsername(login.getUserName());
		Calendar cal = Calendar.getInstance();
		System.out.println(login.getToken());
		System.out.println(passToken.getToken());
		System.out.println(passToken.getExpiryDate().getTime());
		System.out.println(cal.getTime().getTime());
		if (login.getToken().equals(passToken.getToken())
				&& ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) >= 0)) {
			System.out.println("in if");
			String token1 = UUID.randomUUID().toString();
			loginService.createPasswordResetTokenForUser(passToken, token1);
			result = loginService.updatePassword(login.getUserName(), login.getEncryptedKey());
			msg = "{\"message\":\"Your password has been set\"}";
			// returns login service updatePassword

		} else {
			System.out.println("in else");
			msg = "{\"message\":\"Invalid User!\"}";
			// result = "Invalid User!";
		}

		return msg;
	} // end of loop

	/**
	 * @return the loginService
	 * @throws MessagingException
	 * @throws AddressException
	 */

	@RequestMapping(value = "/cancelInterviewMail", method = RequestMethod.POST, consumes = "application/json")
	public final String cancelInterviewMail(final HttpServletRequest request,
			@RequestBody final PanelAvailability pavail) throws AddressException, MessagingException {
		System.out.println("in controller");
		List<Integer> localAvailabilityIds = null;
		String msg = null;
		localAvailabilityIds = pavail.getAvailabilityIds();
		System.out.println(localAvailabilityIds.get(0));
		if (localAvailabilityIds != null && localAvailabilityIds.size() > 0) {
			for (int availabilityId : localAvailabilityIds) {

				PanelAvailability info = loginService.getInfoById(availabilityId);
				System.out.println(info.getAvailability_date());
				int userid = info.getLogin().getUserId();
				Login userinfo = loginService.getuserInfo(userid);
				System.out.println(userinfo.getUserName());

				String cname = userinfo.getLevels().getCompetency().getCompetencyName();
				System.out.println("in controller");

				List<LoginVTO> list = loginService.getUserNames(cname);

				for (LoginVTO usern : list) {
					// return login service by Username
					String user = usern.getUserName();
					System.out.println(user);
					if (user == null) {
						msg = "{\"message\":\"Invalid User\"}";
						// message = "Invalid User";
						// returns Invalid user
					} else {
						String email = usern.getContact().getEmailId();
						System.out.println(email);
						if (email == null) {
							msg = "{\"message\":\"wrong username\"}";
							// message = "wrong username";
							// returns wrong username message
						} else {

							// String token = UUID.randomUUID().toString();
							// loginService.createPasswordResetTokenForUser(user,
							// token);
							String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
									+ request.getContextPath();
							loginService.constructCancelEmail(appUrl, request.getLocale(), user, email, userinfo, info);
							msg = "{\"message\":\"done!\"}";
						}
					}

				}

			}

			return msg;

		}
		return null;
	}

	/**
	 * @param competency
	 * @return
	 */
	@RequestMapping(value = "/LeadPanelData", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public final String getLeadPAnel(@RequestBody final CompetencyVTO competency) {
		String name = competency.getCompetencyName();
		List<LoginVTO> loginUser = loginService.getLeadPAnel(name);

		String json = new Gson().toJson(loginUser);
		return json;

		/*
		 * This is getLogin
		 */
		
	}

	@RequestMapping(value = "/LeadCoordinatorData", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public final String getLeadCoordinator(@RequestBody final CompetencyVTO competency) {
		String name = competency.getCompetencyName();
		List<LoginVTO> loginUser = loginService.getLeadCoordinator(name);

		String json = new Gson().toJson(loginUser);
		return json;

		/*
		 * This is getLogin
		 */
	
	}
}

// end of loop
