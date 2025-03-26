package mobisure.project.sinistre.document;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "file") // Specify the MongoDB collection name
public class FileDocument {

    @Id
    private String id;

    private String filename;

    private String contentType;

    private long size;

    private Date uploadDate;

    @Field("fileData")
    private byte[] fileData;

    private long sinistreId;

    public FileDocument() {
    }

    public FileDocument(String filename, String contentType, long size, byte[] fileData, long sinistreId) {
        this.filename = filename;
        this.contentType = contentType;
        this.size = size;
        this.fileData = fileData;
        this.uploadDate = new Date();
        this.sinistreId = sinistreId;
    }

    public long getSinistreId() {
        return sinistreId;
    }

    public void setSinistreId(long sinistreId) {
        this.sinistreId = sinistreId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}