
public class Driver {

    public void option() {
        // display menus, input option, switch to different functions
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void print(String message, int length) {
        message = String.format("%1$-" + length + "s", message);
        System.out.print(message);
    }

    private void print(int messageInt, int length) {
        String message = String.format("%1$-" + length + "s", messageInt);
        System.out.print(message);
    }

    private void println(String message) {
        System.out.println(message);
    }

    private void println(String message, int length) {
        message = String.format("%1$-" + length + "s", message);
        System.out.println(message);
    }

    private void println(int messageInt, int length) {
        String message = String.format("%1$-" + length + "s", messageInt);
        System.out.println(message);
    }
}
