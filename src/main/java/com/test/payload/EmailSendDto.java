package com.test.payload;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailSendDto {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
