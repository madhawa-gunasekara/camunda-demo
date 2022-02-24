package com.camunda.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.context.ApplicationContext;

public class SendApproveEmailDelegator implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try {
            ApplicationContext ctx = Application.getContext();
            MailService mailService = (MailService) ctx.getBean("mailService");
            String content = new StringBuilder().append("Your Booking ").append(delegateExecution.getProcessInstance().getProcessInstanceId()).append("is Approved. Please find the details below.").append(System.lineSeparator())
                    .append("Your name :").append(delegateExecution.getVariable("firstName")).append(" ").append(delegateExecution.getVariable("lastName")).append(System.lineSeparator())
                    .append("Your travel date :").append(delegateExecution.getVariable("date")).append(" ").append(System.lineSeparator())
                    .append("Your destination :").append(delegateExecution.getVariable("countryTo")).append(" ").append(System.lineSeparator())
                    .append("Your departure :").append(delegateExecution.getVariable("countryFrom")).append(" ").append(System.lineSeparator())
                    .toString();
            mailService.sendSimpleMessage(delegateExecution.getVariable("email").toString(), "Booking Approved", content);
        } catch (Exception e) {
            throw e;
        }
    }
}
