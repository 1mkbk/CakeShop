package model;
import javax.persistence.*;  
import java.util.Date;  
  
@Entity  
@Table(name = "admin_operation")  
public class AdminOperationLog {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
 
    @Column(name = "operator_name")  
    private String operatorName;  
  
    @Column(name = "operation_time")  
    @Temporal(TemporalType.TIMESTAMP)  
    private Date operationTime;  
  
    @Column(name = "operation_content")  
    private String operationContent;  
  
    @Column(name = "ip_address")  
    private String ipAddress;  
 
}