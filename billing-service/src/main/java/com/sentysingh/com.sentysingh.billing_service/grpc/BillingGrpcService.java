package com.sentysingh.billing_service.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import billing.BillingResponse;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest, StreamObserver<BillingResponse> responseStreamObserver){
        log.info("createBillingAccount request received {}", billingRequest.toString());

        // business logic

        BillingResponse response = BillingResponse.newBuilder().setAccountId("12345").setStatus("ACTIVE").build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}