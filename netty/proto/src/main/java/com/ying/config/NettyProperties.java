package com.ying.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(ignoreUnknownFields = true, prefix = "server")
public class NettyProperties {

    private Boolean enabled;
    private String bindAddress;
    private int bindPort;
    private Netty netty;

    public Netty getNetty() {
        return netty;
    }

    public void setNetty(Netty netty) {
        this.netty = netty;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getBindAddress() {
        return bindAddress;
    }

    public void setBindAddress(String bindAddress) {
        this.bindAddress = bindAddress;
    }

    public int getBindPort() {
        return bindPort;
    }

    public void setBindPort(int bindPort) {
        this.bindPort = bindPort;
    }

    public static class Netty {

        private String leakDetectorLevel;
        private int bossGroupThreadCount;
        private int workerGroupThreadCount;
        private String maxPayloadSize;

        public int getBossGroupThreadCount() {
            return bossGroupThreadCount;
        }

        public void setBossGroupThreadCount(int bossGroupThreadCount) {
            this.bossGroupThreadCount = bossGroupThreadCount;
        }

        public int getWorkerGroupThreadCount() {
            return workerGroupThreadCount;
        }

        public void setWorkerGroupThreadCount(int workerGroupThreadCount) {
            this.workerGroupThreadCount = workerGroupThreadCount;
        }

        public String getMaxPayloadSize() {
            return maxPayloadSize;
        }

        public void setMaxPayloadSize(String maxPayloadSize) {
            this.maxPayloadSize = maxPayloadSize;
        }

        public String getLeakDetectorLevel() {
            return leakDetectorLevel;
        }

        public void setLeakDetectorLevel(String leakDetectorLevel) {
            this.leakDetectorLevel = leakDetectorLevel;
        }
    }

}
