import java.util.Scanner;

public class chatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I am your Java Chatbot. How can I help you today?");
        
        while (true) {
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.startsWith("open ")) {
                openApplication(input.substring(5));
            } else if (input.startsWith("search ")) {
                openWebBrowser(input.substring(7));
            } else {
                System.out.println("I didn't understand that. Try again.");
            }
        }
        
        scanner.close();
    }

    private static void openApplication(String appName) {
        // You can expand this method to open various applications
        if (appName.equalsIgnoreCase("notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
                System.out.println("Opened Notepad");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Application not recognized.");
        }
    }

    private static void openWebBrowser(String query) {
        try {
            String url = "https://www.google.com/search?q=" + query.replace(" ", "+");
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            System.out.println("Searching for: " + query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
