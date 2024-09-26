package com.appsdeveloperblog.aws.errorresponse.lambda;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Handler for requests to Lambda function.
 */
public class DivisionExampleFunction implements RequestHandler<Map<String, String>, Map<String, Integer>> {

    public Map<String, Integer> handleRequest(final Map<String, String> input, final Context context) {

        Map<String, Integer> response = new HashMap<>();
        try{
            int dividend = Integer.parseInt(input.get("dividend"));
            int divisor = Integer.parseInt(input.get("divisor"));
            int result = dividend / divisor;

            response.put("dividend", dividend);
            response.put("divisor", dividend);
            response.put("result", result);
        }catch (Exception e){
            throw new MyException( "Exception"+ e.getMessage(), e.getCause());
        }


        return response;
    }

}
