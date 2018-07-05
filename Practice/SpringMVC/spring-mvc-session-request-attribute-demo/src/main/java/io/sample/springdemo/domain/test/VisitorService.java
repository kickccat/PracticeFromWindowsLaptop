package io.sample.springdemo.domain.test;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisitorService {
    
    VisitorCount updateCount(VisitorCount visitorCount) {
        visitorCount.setCount(visitorCount.getCount() + 1);
        return visitorCount;
    }
    
    void registerVisitor(VisitorData sessionData, VisitorData incomingVisitor) {
        List<Visitor> visitors = sessionData.getVisitors();
        sessionData.setCurrentVisitorName(incomingVisitor.getCurrentVisitorName());
        sessionData.setCurrentVisitorEmail(incomingVisitor.getCurrentVisitorEmail());
        visitors.add(new Visitor(incomingVisitor.getCurrentVisitorName(), incomingVisitor.getCurrentVisitorEmail()));
    }
    
    Long computerDuration(LocalDateTime sessionStartTime) {
        Duration sessionDuration = Duration.between(sessionStartTime, LocalDateTime.now());
        return sessionDuration.getSeconds();
    }
    
    String describeCurrentTime(LocalDateTime currentTime) {
        return "Current local time is " + currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond();
    }
    
    String describeCurrentDuration(Long currentDuration) {
        return "Session duration is " + currentDuration + " seconds!";
    }
}
