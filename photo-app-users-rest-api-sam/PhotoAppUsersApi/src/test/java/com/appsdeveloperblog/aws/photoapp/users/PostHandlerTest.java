package com.appsdeveloperblog.aws.photoapp.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Optional;

public class PostHandlerTest {
  @Test
  public void successfulResponse() {
    /*
    // Crear manualmente un APIGatewayProxyRequestEvent con un cuerpo válido
    APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
    input.setBody("{ \"key\": \"value\" }");

    // Puedes pasar null si no necesitas el contexto en esta prueba
    Context context = null;

    // Instancia del handler
    PostHandler handler = new PostHandler();

    // Invocar el método handleRequest
    APIGatewayProxyResponseEvent result = handler.handleRequest(input, context);

    // Verificar que la respuesta no sea nula
    assertNotNull(result);

    // Verificar el código de respuesta esperado (200 por ejemplo)
    assertEquals(Optional.of(200).get(), result.getStatusCode());

    // Verificar que el cuerpo de la respuesta es el esperado
    assertEquals("{\"key\":\"value\",\"userId\":\"123\"}", result.getBody());*/
  }
}
