package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
/*@TableGenerator(
        name="MEMBER_SEQ_GENERATOR",
        table="MY_SEQUENCES"
        ,pkColumnValue = "MEMBER_SEQ", allocationSize = 1)*/

public class Member {


    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

   @OneToMany(mappedBy = "member")
   private List<MemberProduct> member= new ArrayList<>();
   /* @Column(name="TEAM_ID")
    private Long teamId;
*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    /* @Id
        // @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")

         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @Column(name = "name", nullable = false)
         private String username;

         public Long getId() {
             return id;
         }

         public void setId(Long id) {
             this.id = id;
         }

         public String getUsername() {
             return username;
         }

         public void setUsername(String username) {
             this.username = username;
         }
     */
    /*

            private Integer age;

            @Enumerated(EnumType.STRING)
            private RoleType roleType;

            @Temporal(TemporalType.TIMESTAMP)
            private Date createdDate;

            @Temporal(TemporalType.TIMESTAMP)
            private Date lastModifiedDate;

            private LocalDate testLocalDate;
            private LocalDateTime testLocalDateTime;

            @Lob
            private String description;


            @Transient
            private int temp;*/
    public Member() {
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }*/
}
