package com.figestaotcc;

public class PerfilUsuario {

    private String header;

    private String profileContent;

    public PerfilUsuario(String header, String profileContent) {
        this.header = header;
        this.profileContent = profileContent;
    }

    public String getHeader() {
        return header;
    }

    public String getProfileContent() {
        return profileContent;
    }
}
