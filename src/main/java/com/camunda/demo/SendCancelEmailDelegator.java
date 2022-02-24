package com.camunda.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.context.ApplicationContext;

public class SendCancelEmailDelegator implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            ApplicationContext ctx = Application.getContext();
            MailService mailService = (MailService) ctx.getBean("mailService");
            String content = new StringBuilder().append("Your Booking number").append(delegateExecution.getProcessInstance().getProcessInstanceId()).append(" is cancelled.").toString();
            mailService.sendSimpleMessage(delegateExecution.getVariable("email").toString(),"Booking Cancellation",content);
        } catch (Exception e) {
            throw e;
        }
    }
}
