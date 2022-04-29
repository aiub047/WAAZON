package com.waazon.backend.utils;

import com.waazon.backend.domains.Product;
import com.waazon.backend.domains.Response.Response;

public class BackendUtils {
    public static Response successCreation() {
        Response response = new Response();
        response.setResponseMessage(ResponseMessage.SUCCESS.getMessage());
        response.setResponseCode(ResponseCode.SUCCESS.getResponseCode());
        return response;
    }

    public static Response productNotCreated() {
        Response response = new Response();
        response.setResponseMessage(ResponseMessage.PRODUCT_NOT_FOUND.getMessage());
        response.setResponseCode(ResponseCode.NOT_FOUND.getResponseCode());
        return response;
    }

    public static Response itemNotCreated() {
        Response response = new Response();
        response.setResponseMessage(ResponseMessage.ITEM_NOT_FOUND.getMessage());
        response.setResponseCode(ResponseCode.NOT_FOUND.getResponseCode());
        return response;
    }

    public static Response orderItemsNotCreated() {
        Response response = new Response();
        response.setResponseMessage(ResponseMessage.ORDER_ITEM_NOT_FOUND.getMessage());
        response.setResponseCode(ResponseCode.NOT_FOUND.getResponseCode());
        return response;
    }

    public static Product editProduct(Product restaurantItem) {
        return restaurantItem;
    }

    public static Response error(String errorMessage) {
        Response response = new Response();
        response.setResponseCode(ResponseCode.ERROR.getResponseCode());
        response.setResponseMessage(errorMessage);
        return response;
    }


}
