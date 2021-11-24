package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usr",uniqueConstraints = {
        @UniqueConstraint(columnNames = "nickname")})
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
@Column()
    private String firstName;
    private String nickname;
    private String password;
    private Date date;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Todo> todo;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public User(String firstName, String nickname, String password,Date date) {
        this.firstName = firstName;
        this.nickname = nickname;
        this.password = password;
        this.date=date;
    }
}
