package http;

public class Response {
    private int status;
    private String payload;

    public Response(int status, String payload) {
        this.status = status;
        this.payload = payload;
    }

    public int getStatus() {
        return status;
    }

    public String getPayload() {
        return payload;
    }
}
