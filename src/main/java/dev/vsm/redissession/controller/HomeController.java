package dev.vsm.redissession.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
public class HomeController {
    private final String HOME_VIEW_COUNT = "HOME_VIEW_COUNT";

    @GetMapping("/")
    public String home(Principal principal, HttpSession session) {
        incrementCount(session, HOME_VIEW_COUNT);
        return "Hello " + principal.getName();
    }

    @GetMapping("/count")
    public String getCount(HttpSession session) {
        return HOME_VIEW_COUNT + ":" + session.getAttribute(HOME_VIEW_COUNT);
    }


    private void incrementCount(HttpSession session, String home_view_count) {
        Integer sessionCounter = (Integer) Optional.ofNullable(session.getAttribute(home_view_count)).orElse(0);
        session.setAttribute(home_view_count, ++sessionCounter);
    }
}
