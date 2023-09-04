package com.example.orderService;

import com.example.orderService.model.Customer;
import com.example.orderService.model.Payment;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

public class IdentifyCustomerFlowIT extends AbstractXmlFlowExecutionTests {

    public static final String FLOW_PATH = "src/main/resources/flows/identifyCustomer/customer-flow.xml";

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
        return resourceFactory.createFileResource(FLOW_PATH);
    }

    public void testStartIdentifyCustomerFlow() {
        MockExternalContext context = new MockExternalContext();
        startFlow(context);
        assertCurrentStateEquals("customer");
    }
    public void testCashPaymentTypeFlowExecution() {
        MockExternalContext context = new MockExternalContext();
        setCurrentState("customer");
        Customer customer = createTestCustomer();
        customer.setPaymentType(Customer.PaymentType.CASH);
        getFlowScope().put("customer", customer);
        getFlowScope().put("payment", null);
        context.setEventId("submit");
        resumeFlow(context);
        assertFlowExecutionEnded();
    }

    public void testCardPaymentTypeFlowExecution() {
        MockExternalContext context = new MockExternalContext();
        setCurrentState("customer");
        Customer customer = createTestCustomer();
        customer.setPaymentType(Customer.PaymentType.CREDIT_CARD);
        getFlowScope().put("customer", customer);
        context.setEventId("submit");
        resumeFlow(context);
        getFlowScope().put("payment", createTestPayment());
        assertCurrentStateEquals("cardDetails");
        context.setEventId("cardEntered");
        resumeFlow(context);
        assertFlowExecutionEnded();
    }
    private Payment createTestPayment() {
        Payment payment = new Payment();
        payment.setCcNumber("1234432112433421");
        payment.setCcCVV("321");
        payment.setCcExpiration("06/23");
        payment.setCcHolder("ARTEM ZOTOV");
        return payment;
    }
    private Customer createTestCustomer() {
        Customer customer = new Customer();
        customer.setSecondName("Зотов");
        customer.setFirstName("Артем");
        customer.setPatronymic("Михайлович");
        customer.setPhoneNumber("+79156128434");
        customer.setEmail("artem.zotov.2019@gmail.com");
        customer.setAddress("Княжье поле");
        return customer;
    }
}
