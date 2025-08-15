package com.hotel_management_system.Hotel_Managment_Service_API.util;

import org.springframework.stereotype.Service;
import javax.sql.rowset.serial.SerialBlob;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class ByteCodeHandler {
    public Blob StringToBlob(String data) throws SQLException {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        return new SerialBlob(bytes);
    }

    public String blobToString(Blob blob) throws SQLException {
        int blobLength = (int) blob.length();
        byte[] bytes = blob.getBytes(1,blobLength);
        return new String(bytes , StandardCharsets.UTF_8);
    }
}
