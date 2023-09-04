package com.example.orderService;

import com.example.orderService.model.DeliveryCompany;
import com.example.orderService.model.Order;
import com.example.orderService.model.Products;
import com.example.orderService.services.FlowService;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

public class OrderingFlowIT extends AbstractXmlFlowExecutionTests {

    public static final String FLOW_PATH = "src/main/resources/flows/order/ordering-flow.xml";

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
        return resourceFactory.createFileResource(FLOW_PATH);
    }

    @Override
    protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
        builderContext.registerBean("flowService", new FlowService());
    }

    public void testStartOrderingFlow() {
        MockExternalContext context = new MockExternalContext();
        startFlow(context);
        assertCurrentStateEquals("catalog");
    }

    public void testFlowPurchaseConfirmed() {
        MockExternalContext confirmPurchase = new MockExternalContext();
        setCurrentState("thanksCustomer");
        confirmPurchase.setEventId("home");
        resumeFlow(confirmPurchase);
        assertFlowExecutionEnded();
    }

    public void testFlowPurchaseCanceled() {
        MockExternalContext cancel = new MockExternalContext();
        setCurrentState("orderInformation");
        cancel.setEventId("home");
        resumeFlow(cancel);
        assertFlowExecutionEnded();
    }

    public void testSelectDeliveryToOrderInformationTransition() {
        MockExternalContext context = new MockExternalContext();
        Order order = new Order();
        setCurrentState("selectDelivery");
        getFlowScope().put("delivery", createTestDelivery());
        getFlowScope().put("order", order);
        getFlowScope().put("products", new Products());
        context.setEventId("deliverySelected");
        resumeFlow(context);
        assertCurrentStateEquals("orderInformation");
        assertEquals(createTestDelivery(), order.getDelivery());
    }

    private DeliveryCompany createTestDelivery() {
        DeliveryCompany deliveryCompany = new DeliveryCompany();
        deliveryCompany.setName("Яндекс.Еда");
        deliveryCompany.setAvgDeliveryTime("60 мин");
        deliveryCompany.setCost(150);
        return deliveryCompany;
    }
}
