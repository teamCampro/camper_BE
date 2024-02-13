package com.campro.s3.presentation.request;

public class FileDeleteRequest {

    private String fileName;


    public FileDeleteRequest(String fileName) {
        this.fileName = fileName;
    }
    public FileDeleteRequest(){
    }


    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "FileDeleteRequest{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
