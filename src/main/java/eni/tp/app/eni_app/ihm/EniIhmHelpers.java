package eni.tp.app.eni_app.ihm;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class EniIhmHelpers {

    public static void sendCommonFlashMessage(RedirectAttributes redirectAttributes, int type, String message) {

        redirectAttributes.addFlashAttribute("flashMessage",
                new EniFlashMessage(type, message));

    }

    public static void sendSuccesslashMessage(RedirectAttributes redirectAttributes, String message) {

        EniIhmHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCCESS, message);
    }
}

