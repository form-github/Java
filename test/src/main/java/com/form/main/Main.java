package com.form.main;

import com.form.service.LoginOperation;

/**
 * @author Form      J
 *
 */
public class Main {
    public static void main(String[] args) {
        LoginOperation loginOperation = new LoginOperation();
        loginOperation.login();
    }
}
