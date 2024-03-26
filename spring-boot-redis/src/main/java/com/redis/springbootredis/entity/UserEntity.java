package com.redis.springbootredis.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "UserEntity.findByFirstNameLikeIgnoreCase", query = "select u from UserEntity u where upper(u.firstName) like upper(:firstName)")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
}
