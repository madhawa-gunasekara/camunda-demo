package com.camunda.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.context.ApplicationContext;

public class SendConfirmationEmailDelegator implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            ApplicationContext ctx = Application.getContext();
            MailService mailService = (MailService) ctx.getBean("mailService");
            String content = new StringBuilder().append("Please click this link to confirm http://localhost:8080/booking-confirmation/").append(delegateExecution.getProcessInstance().getProcessInstanceId()).toString();
            mailService.sendSimpleMessage(delegateExecution.getVariable("email").toString(),"Booking Confirmation",content);
        } catch (Exception e) {
            throw e;
        }
    }
}
