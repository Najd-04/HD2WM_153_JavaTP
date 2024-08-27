package eni.tp.app.eni_app.ihm;

public class EniFlashMessage {
    public static final int TYPE_FLASH_SUCCESS=0;
    public static final int TYPE_FLASH_ERROR=1;

    public int type;
   public String message;
    public EniFlashMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }
    public String getTypeCssClass() {
        if (type == TYPE_FLASH_SUCCESS) {
            return "uk-alert-success";
        }
        if (type == TYPE_FLASH_ERROR) {
        return "uk-alert-danger";
        }
        return "uk-alert-primary";
    }

}
