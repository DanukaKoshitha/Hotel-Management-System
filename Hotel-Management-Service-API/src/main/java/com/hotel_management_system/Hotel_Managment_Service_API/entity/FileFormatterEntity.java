package com.hotel_management_system.Hotel_Managment_Service_API.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;

@Embeddable
public class FileFormatterEntity {

    @Lob
    @Column(name = "file_name")
    private byte[] fileName;

    @Column(name = "resourceUrl")
    private byte[] resourcesUrl;

    @Column(name = "directory")
    private byte[] directory;

    @Column(name = "hash")
    private byte[] hash;
}
