package models.components;

import java.lang.reflect.Constructor;

public class GenericTypeExploring {

    public <T extends LoginPage> void login(Class<T> loginPageClass) {
        Class<T>[] parameters = new Class[]{};

        try {
            Constructor<T> constructor = loginPageClass.getConstructor(parameters);
            T loginPageObj = constructor.newInstance();
            loginPageObj.login();
            loginPageObj.verifyLoginSuccess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenericTypeExploring().login(InternalLoginPage.class);
        new GenericTypeExploring().login(ExternalLoginPage.class);
    }
}
