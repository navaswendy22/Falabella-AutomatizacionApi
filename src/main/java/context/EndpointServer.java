package context;

public class EndpointServer {
    private String host;
    private int port;
    private String basePath;

    String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() { return port; }

    public void setPort(int port) { this.port = port; }

    public String getBasePath() {
        return basePath == null ? "" : basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public String toString() {
        return host + port + basePath;
    }
}
