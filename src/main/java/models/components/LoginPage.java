package models.components;

public abstract class LoginPage {

    public void login(){
        System.out.println(username());
        System.out.println(password());
        System.out.println(loginBtn());
    }

    public void verifyLoginSuccess(){
        System.out.println("Verifying Dashboard display");
    }

    protected abstract String username();
    protected abstract String password();
    protected abstract String loginBtn();
}
