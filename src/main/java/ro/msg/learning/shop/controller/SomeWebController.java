package ro.msg.learning.shop.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ro.msg.learning.shop.configuration.SomeOrderConfig;


@SessionAttributes({"currentUser"})
@Controller
public class SomeWebController {
    private static final Logger log = LogManager.getLogger(SomeOrderConfig.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginError(Model model) {
        log.info("Login attempt failed");
        model.addAttribute("error", "true");
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/welcome";
    }
}
