package com.papont.lesson.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeNameView {

    //Spell
    @Value("#{target.id + ' ' + target.firstName}")
    String getFirstName();

    String getLastName();

}
