package course_1.module_3;

import course_1.Utils.ConsoleColor;
import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String hostAddr = "149.154.167.50:443";
    private static final int appApiId = 404903;
    private static final String appHash = "f8af7088e538d31e99e5bcf7d2a7d66b";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //TelegramApi tab = new TelegramApi();
        try {
            TelegramApiBridge tab = new TelegramApiBridge(hostAddr, appApiId, appHash);
            String phone = "";
            while (phone.length() < 11 ){
                System.out.print(ConsoleColor.setColor("Write your phone number: ", ConsoleColor.ANSI_YELLOW));
                phone = String.valueOf(in.nextLine()).trim();
            }
            AuthCheckedPhone acp = tab.authCheckPhone(phone);
            System.out.println(ConsoleColor.setColor("isInvited(" + acp.isInvited() + "); isRegistered("
                    + acp.isRegistered() + ")", ConsoleColor.ANSI_RED));
            AuthSentCode asc = tab.authSendCode(phone);
            String hash = asc.getPhoneCodeHash();
            String code = "";

            while (code.length() < 4 ){
                System.out.print(ConsoleColor.setColor("Write your sms code: ", ConsoleColor.ANSI_YELLOW));
                code = String.valueOf(in.nextLine()).trim();
            }

            AuthAuthorization aa = tab.authSignIn(code);
            System.out.println(ConsoleColor.setColor(aa.getUser().getFirstName() + " "
                    + aa.getUser().getLastName(), ConsoleColor.ANSI_RED));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
