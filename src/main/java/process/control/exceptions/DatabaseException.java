package process.control.exceptions;

public class DatabaseException extends Exception {

    private String reason = null;

    public DatabaseException(String reason) {
        this.reason = reason;
    }

    /**
     * get the reason why the exception was thrown
     *
     * @return String description of reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * set the reason why we are throwing this exception
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}