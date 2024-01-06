package com.weCare.modals;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String profile_id;

    private String email;

    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passWord;

    private String profile_picture;

    @Enumerated(EnumType.STRING)
    private Role role;

	public Profile(String email, String userName, String passWord, String profile_picture, Role role) {
		super();
		this.email = email;
		this.userName = userName;
		this.passWord = passWord;
		this.profile_picture = profile_picture;
		this.role = role;
	}

    
    
}
