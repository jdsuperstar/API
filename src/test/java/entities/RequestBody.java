package entities;

import lombok.Data;

import java.util.List;

@Data
public class RequestBody {
    private String company_name;
    private String password;
    private String seller_name;
    private String email;
    private String phone_number;
    private String address;
    List<CustomResponse>responses;


}
