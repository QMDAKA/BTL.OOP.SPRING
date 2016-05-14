package mvs.model;

/**
 * Created by Quang Minh on 4/15/2016.
 */
public class Response {
    int success;
    Object response;

    public Response() {
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
