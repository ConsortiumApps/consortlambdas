package itemsreplenishment;

import api.CustomRequest;
import api.CustomResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class ItemsReplenishmentController implements RequestHandler<CustomRequest, CustomResponse> {

    @Override
    public CustomResponse handleRequest(CustomRequest input, Context context) {
        System.out.println("input = " + input);
        CustomResponse response = new CustomResponse();
        response.setReply("Hello " + input.getKey1() + input.getKey2());
        return response;
    }
}
