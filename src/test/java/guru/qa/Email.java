package guru.qa;

public enum Email {
    MAILWITHOUTAT("test.mail.com"),
    MAILWITHCYRILLIC("тест@mail.com"),
    MAILWITHBACKSLASH("te st@mail.com");


    private String title;

    Email(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
