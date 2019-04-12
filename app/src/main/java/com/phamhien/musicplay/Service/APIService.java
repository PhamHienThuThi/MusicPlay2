package com.phamhien.musicplay.Service;

public class APIService {

    private  static  String base_url ="https://phamhien2858.000webhostapp.com/Server/";

    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
