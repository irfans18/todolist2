package com.irfans.todolist2.utils;

public class myURL {
    private final static String BASE_URL = "http://192.168.100.6:8000/api/";
    private final static String IMAGE_URL = "http://192.168.100.6:8000/storage/";

    public final static String LOGIN_URL = BASE_URL + "user/login/customer";
    public final static String REGISTER_URL = BASE_URL + "user/register/customer";

    public final static String CREATE_TASK_URL = BASE_URL + "task/create";
    public final static String PRIVATE_TASK_URL = BASE_URL + "task/private";
    public final static String SHOW_TASK_URL = BASE_URL + "task/all";
    public final static String PUBLIC_TASK_URL = BASE_URL + "task/public";
    public final static String DELETE_TASK_URL = BASE_URL + "task/delete/";
    public final static String UPDATE_TASK_URL = BASE_URL + "task/update/";
    public final static String DETAIL_TASK_URL = BASE_URL + "task/detail/";

    public static String getImageUrl(){
        return IMAGE_URL;
    }


}
