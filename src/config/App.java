/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import controller.LoginOptionsController;
import view.LoginOptions;

/**
 *
 * @author A
 */
public class App {
    public static void main(String[] args) {
        new LoginOptionsController(new LoginOptions());
    }
}
