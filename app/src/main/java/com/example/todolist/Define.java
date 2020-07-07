package com.example.todolist;

public class Define {

    public static final String API_GET_LIST = "http://ec2-54-163-16-167.compute-1.amazonaws.com:1337/todos?user_eq=" ;
    public static final String API_ADD = "http://ec2-54-163-16-167.compute-1.amazonaws.com:1337/todos";
    public static String API_REGISTER = "http://ec2-54-163-16-167.compute-1.amazonaws.com:1337/users";
    public static String API_EDIT = "http://ec2-54-163-16-167.compute-1.amazonaws.com:1337/todos/";
    public static String API_DELETE = "http://ec2-54-163-16-167.compute-1.amazonaws.com:1337/todos/";
    public static String jwt = "jwt";

    public static String username ="username";
    public static String password ="password";
    public static String identifier ="identifier";
    public static String email ="email";
    public static String role ="role";

    public static String API_LOGIN ="http://ec2-54-163-16-167.compute-1.amazonaws.com:1337/auth/local";
    public static String authorization ="authorization";
}
