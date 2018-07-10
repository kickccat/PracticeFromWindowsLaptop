package io.sample.springdemo.domain.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(names = {"visitorData", "visitorCount"})
@RequestMapping("/visitorRegister")
public class SessionRequestAttributeDemoController {
    
    @Autowired
    public VisitorService visitorService;
    
    private static Logger LOGGER = LoggerFactory.getLogger(SessionRequestAttributeDemoController.class);
    
    @RequestMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("test/sessionRequestAttributeViews/sessionRequestAttributeHome", "visitorStats", new VisitorData());
    }
    
    @ModelAttribute("visitorData")
    public VisitorData addVisitorData() {
        List<Visitor> visitors = new ArrayList<>();
        return new VisitorData(null, null, visitors);
    }
    
    @ModelAttribute("visitorCount")
    public VisitorCount countVisitors() {
        return new VisitorCount(0);
    }
    
    @RequestMapping(value = "/visitor", method = RequestMethod.POST)
    public String getVisitors(@ModelAttribute("visitor") VisitorData currentVisitor, HttpSession session, SessionStatus sessionStatus,
                              HttpServletRequest request, @SessionAttribute(name = "sessionStartTime") LocalDateTime startTime,
                              @RequestAttribute(name = "currentTime") LocalDateTime clockTime, Model model) {
        
        VisitorData visitorDataFromSession = (VisitorData) session.getAttribute("visitorData");
        visitorService.registerVisitor(visitorDataFromSession, currentVisitor);
        VisitorCount visitorCount = (VisitorCount) session.getAttribute("visitorCount");
        visitorService.updateCount(visitorCount);
    
        Long currentSessionDuration = visitorService.computerDuration(startTime);
        
        if (visitorCount.getCount() == 5) {
            sessionStatus.setComplete();
//            session.invalidate();
        }
    
//        model.addAttribute("timeHeading", visitorService.describeCurrentTime(clockTime));
//        model.addAttribute("durationHeading", visitorService.describeCurrentDuration(currentSessionDuration));
    
        Map<String, Object> modelMap = model.asMap();
        modelMap.put("timeHeading", visitorService.describeCurrentTime(clockTime));
        modelMap.put("durationHeading", visitorService.describeCurrentDuration(currentSessionDuration));
//        // Debug code
//        LOGGER.info(visitorDataFromSession.toString());
//        if (request.getMethod().equalsIgnoreCase("POST")) {
//            LOGGER.info("This is a POST request");
//        } else {
//            LOGGER.info("This is a GET request");
//        }
        return "test/sessionRequestAttributeViews/sessionRequestAttributeResult";
    }
}
