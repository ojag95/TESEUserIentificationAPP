package com.development.ostin.useridentification;

public class PC {
    public String computernumber;
    public String sala;
    public String ipaddress;

    public PC(String computernumber, String sala, String ipaddress) {
        this.computernumber = computernumber;
        this.sala = sala;
        this.ipaddress = ipaddress;
    }

    public String getComputernumber() {
        return computernumber;
    }

    public void setComputernumber(String computernumber) {
        this.computernumber = computernumber;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
