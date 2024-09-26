package com.appsdeveloperblog.aws.lambda;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Handler for requests to Lambda function.
 */
public class EnvironmentVariablesExample implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    //DESENCRIPTAMOS EN ESTA PARTE PARA QUE QUE LO HAGA UNA SOLA VEZ Y LA FUNCION DE SOLICITUD RESPONDA MÁS RÁPIDO
    //PARA LAS SOLICITUDES POSTERIORES
    private final String myVariable = decryptKey("MY_VARIABLE");
    private final String cognitoUserPoolId = decryptKey("MY_COGNITO_USER_POOL_ID");
    private final String cognitoClientAppSecret = decryptKey("MY_COGNITO_CLIENT_APP_SECRET");

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        LambdaLogger logger = context.getLogger();
        logger.log("MY_VARIABLE: " + myVariable);
        logger.log("COGNITO_USER_POOL_ID: " + cognitoUserPoolId);
        logger.log("COGNITO_CLIENT_APP_SECRET: " + cognitoClientAppSecret);
        return response
                .withBody("{}")
                .withStatusCode(200);

    }
    private String decryptKey(String name) {
        System.out.println("Decrypting key");
        byte[] encryptedKey = Base64.decode(System.getenv(name));
        Map<String, String> encryptionContext = new HashMap<>();
        encryptionContext.put("LambdaFunctionName",
                System.getenv("AWS_LAMBDA_FUNCTION_NAME"));

        AWSKMS client = AWSKMSClientBuilder.defaultClient();

        DecryptRequest request = new DecryptRequest()
                .withCiphertextBlob(ByteBuffer.wrap(encryptedKey))
                .withEncryptionContext(encryptionContext);

        ByteBuffer plainTextKey = client.decrypt(request).getPlaintext();
        return new String(plainTextKey.array(), Charset.forName("UTF-8"));
    }
}
