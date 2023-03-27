package peaksoft.dto.response;

import lombok.Builder;
import lombok.Data;
import peaksoft.entity.MenuItem;

import java.math.BigDecimal;
import java.util.List;
@Data
public class ChequeResponse{
        private  Long id;
        private  String fullName;
        private List<MenuItem> items;
        private double averagePrice;
        private int service;
        private double GrandTotal;



    }
