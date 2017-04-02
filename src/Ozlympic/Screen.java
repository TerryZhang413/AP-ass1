package Ozlympic;

public class Screen {
	/**
	 * @date 1.4.2017
	 * @author Yipeng
	 * @version 1.0
	 * @Description user interface ui
	 */
	//depending on different parameter,print different data
    public void print(String message) {
        System.out.print(message);
    }

    public void print(String message, int length) {
        message = String.format("%1$-" + length + "s", message);
        System.out.print(message);
    }

    public void print(int messageInt, int length) {
        String message = String.format("%1$-" + length + "s", messageInt);
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void println(String message, int length) {
        message = String.format("%1$-" + length + "s", message);
        System.out.println(message);
    }

    public void println(int messageInt, int length) {
        String message = String.format("%1$-" + length + "s", messageInt);
        System.out.println(message);
    }
}
