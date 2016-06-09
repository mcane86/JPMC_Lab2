package com.michaelcane;

import java.util.Scanner;

public class UserInputHandler {

    public Scanner input = new Scanner(System.in);

    public String promptUserForString(String msg) {
        promptUser(msg);
        return input.nextLine();
    }

    public String promptUser(String msg) {
        return msg;
    }
}
